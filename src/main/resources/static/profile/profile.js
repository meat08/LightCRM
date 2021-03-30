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
        });
      };

      $scope.getProfile();

        $scope.getAllProfiles = function () {
          console.log('getAllProfiles');
            $http({
                url: contextPath + '/api/v1/profiles',
                method: 'GET'
            }).then(function (response) {
                $scope.allProfiles = response.data;
            });
        };

      $scope.submitCreateNewUser = function () {
        $http.post(contextPath + '/api/v1/profiles/register/', $scope.newUser)
        .then(function (response) {
          $scope.newUser = null;
          $(".modal").modal("hide");
          alert('Добавлен новый сотрудник');
          $scope.getAllProfiles();
        });

      };

      $scope.getAllDepartments = function () {
        console.log('getAllDepartments');
        $http({
          url: contextPath + '/api/v1/departments/names',
          method: 'GET'
        })
        .then(function (response) {
          $scope.departmentnames = response.data;
        });
      };

      $scope.getAllStaffUnitsNames = function () {
        console.log('getAllStaffUnitsNames');
        $http({
          url: contextPath + '/api/v1/staff_units/names',
          method: 'GET'
        })
        .then(function (response) {
          $scope.unitnames = response.data;
        });
      };


      $scope.getAllDepartments();

      $scope.getAllStaffUnitsNames();

      $scope.getAllProfiles();

    });