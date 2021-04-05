angular.module('app').controller('taskController', function ($scope, $http) {
    const contextPath = 'http://localhost:8180/app';

    $scope.allTasks = function () {
        $http({
            url: contextPath + '/api/v1/tasks',
            method: 'GET'
        }).then(function (response) {
            $scope.allTasks = response.data
        });
    };

    $scope.allTasks();
});
