angular.module('app').controller('authController',
    function ($scope, $http, $location, $localStorage) {
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

            $location.path('/');
            console.log($localStorage.currentUser);
          }
        }, function errorCallback(response) {
          console.log(response.data);
          window.alert(response.data.msg);
          $scope.clearUser();
        });
      };
});