angular.module('app').controller('authController',
    function ($scope, $http, $localStorage) {
      const contextPath = 'http://localhost:8180/app';

      $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
        .then(function successCallback(response) {
          if (response.data.token) {
            $http.defaults.headers.common.Authorization = 'Bearer '
                + response.data.token;

            $localStorage.currentUser = {
              username: $scope.user.username,
              token: response.data.token,
              roles: response.data.roles,
                profileId: response.data.profileId
            };

            $scope.user.username = null;
            $scope.user.password = null;

            console.log($localStorage.currentUser);
          }
        }, function errorCallback(response) {
          console.log(response.data);
          window.alert(response.data.msg);
          $scope.clearUser();
        });
      };

      $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
          $scope.user.username = null;
        }
        if ($scope.user.password) {
          $scope.user.password = null;
        }
      };

      $scope.clearUser = function () {
        delete $localStorage.currentUser;
        $http.defaults.headers.common.Authorization = '';
      };

      $scope.isUserLoggedIn = function () {
        if ($localStorage.currentUser) {
          return true;
        } else {
          return false;
        }
      };
    });