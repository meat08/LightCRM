(function () {
    'use strict';

    //Какие-то из модулей могут быть не нужны. Лишнее выпилим при рефакторинге
    angular
        .module('app', ['ngRoute', 'ngStorage', 'angular-jwt', 'ngAnimate', 'ngSanitize', 'ui.bootstrap', 'ngStomp', 'ChatService'])
        .config(config)
        .run(run);

    function config($routeProvider, $httpProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'main/main.html'
            })
            .when('/auth', {
                templateUrl: 'auth/auth.html',
                controller: 'authController'
            })
            .when('/profiles', {
                templateUrl: 'profile/profiles.html',
                controller: 'profileController'
            })
            .when('/profiles/profile', {
                templateUrl: 'profile/profile.html',
                controller: 'profileController'
            })
            .when('/tasks', {
                templateUrl: 'tasks/tasks.html',
                controller: 'taskController'
            })
            .when('/chats', {
                templateUrl: 'chat/room.html',
                controller: 'chatController'
            })
            .when('/chat/:recipientId', {
                templateUrl: 'chat/chat.html',
                controller: 'chatController'
            });
    }

    //Функция проверяет наличие пользователя в локальном хранилище и клеит токен к заголовку
    function run($rootScope, $http, $location, $localStorage, ChatService) {
        if ($localStorage.currentUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;

            //Подключение к вебсокету при обновлении страницы
            ChatService.connect('/app/ws', {}, function (error) {
                alert(error);
            });
        }
        //Доступ без токена возможен только на страницы в массиве publicPages,
        // все остальное перенаправляется на /auth
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            let publicPages = ['/auth'];
            let restrictedPage = publicPages.indexOf($location.path()) === -1;
            if (restrictedPage && !$localStorage.currentUser) {
                $location.path('/auth');
            }
        });
    }
})();

angular.module('app').controller('indexController', function ($scope, $http, $location, $localStorage) {
    $scope.tryToLogout = function () {
        delete $localStorage.currentUser;
        $http.defaults.headers.common.Authorization = '';
        ChatService.unsubscribe();
        ChatService.disconnect();
        $location.path('/auth');
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.currentUser) {
            $scope.currentUserName = $localStorage.currentUser.username;
            return true;
        } else {
            return false;
        }
    };
});
