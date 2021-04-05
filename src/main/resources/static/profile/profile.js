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
        .then(function successCallback(response) {
          alert('Добавлен новый сотрудник: ' + $scope.newUser.firstname + ' ' + $scope.newUser.lastname);
          $scope.newUser = null;
          $(".modal").modal("hide");
          $scope.getAllProfiles();
        }, function errorCallback(response) {
             alert(response.data.msg);
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

      $scope.uploadFile = function (element) {
        if (element.files[0] != null) {
          const payload = new FormData();
          payload.append('attachment', element.files[0]);
          $http({
            url: contextPath + '/api/v1/files/photo',
            method: 'POST',
            data: payload,
            headers: {
              'Content-Type': undefined,
            },
            transformRequest: angular.identity
          })
            .then(function successCallback(response) {
              // alert('Фото успешно загружено на сервер');
              $scope.getPreview();
            }, function errorCallback(response) {
                window.alert(response.data.msg);
            });
        }
      };

      $scope.getPreview = function () {
        $http.get(contextPath + '/api/v1/files/photo/preview', {responseType: "arraybuffer"}
        )
        .then(function (response) {
          let contentType = response.headers("content-type");
          let blob = new Blob([response.data], {type: contentType});
          $scope.img = (window.URL || window.webkitURL).createObjectURL(blob);
        });
      };

      $scope.getPhoto = function () {
        if ($scope.img != null && $scope.photoUrl == null) {
          $http.get(contextPath + '/api/v1/files/photo', { responseType: "arraybuffer" }
          )
          .then(function (response) {
            let contentType = response.headers("content-type");
            let blob = new Blob([response.data], {type: contentType});
            if ($scope.photoUrl == null) {
              $scope.photoUrl = (window.URL || window.webkitURL).createObjectURL(blob);
            }
            window.open($scope.photoUrl);
         });
        } else if ($scope.photoUrl != null) {
          window.open($scope.photoUrl);
        }
      };

      $scope.getAllDepartments();

      $scope.getAllStaffUnitsNames();

      $scope.getAllProfiles();

      $scope.getPreview();

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
