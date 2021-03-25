angular.module('app').controller('profileController',
    function ($scope, $http, $localStorage) {
      const contextPath = 'http://localhost:8180/app';

      $scope.getProfile = function () {
        console.log('getProfile');
        $http({
          url: contextPath + '/api/v1/profiles/profile',
          method: 'GET'
        })
        .then(function (response) {
          console.log(response.data);
          $scope.profile = response.data;
          //$scope.oldUserName = $scope.profile.firstname;
          $scope.oldBirtday = $scope.profile.birthday;
        });
      };

      $scope.getProfile();

        $scope.allProfiles = function () {
            $http({
                url: contextPath + '/api/v1/profiles',
                method: 'GET'
            }).then(function (response) {
                $scope.allProfiles = response.data;
            });
        };

        $scope.allProfiles();

    });