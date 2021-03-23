(function () {
    'use strict';

    //Какие-то из модулей могут быть не нужны. Лишнее выпилим при рефакторинге
    angular
        .module('app', ['ngRoute', 'ngStorage', 'angular-jwt', 'ngAnimate', 'ngSanitize', 'ui.bootstrap', 'app.services'])
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
        .when('/profiles/profile', {
            templateUrl: 'profile/profile.html',
            controller: 'profileController'
        });
    }

    //Функция проверяет наличие пользователя в локальном хранилище и клеит токен к заголовку
    function run($rootScope, $http, $location, $localStorage) {
        if ($localStorage.currentUser) {
            $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.currentUser.token;
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
