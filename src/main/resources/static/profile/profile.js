angular.module('app').controller('profileController',
    function ($scope, $http, $localStorage, profileService) {
      const contextPath = 'http://localhost:8180/app';

      profileService.getProfile().then(function(response) {
        $scope.profile = response.data;
      });

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

      $scope.status = {
        isopen: false
      };

      $scope.toggleDropdown = function($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.status.isopen = !$scope.status.isopen;
      };

      $scope.appendToEl = angular.element(document.querySelector('#dropdown-long-content'));

    });
