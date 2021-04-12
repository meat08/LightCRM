angular.module('app').controller('taskEditController',
        function($scope, $http, $routeParams){
            const contextPath = 'http://localhost:8180/app';

            $scope.fillTask = function(){

                $http({
                    url: contextPath + '/api/v1/tasks/' + $routeParams.taskId,
                    method: 'GET'
                })
                .then(function (response) {
                    $scope.task = response.data;
                });

            }
            $scope.fillTask();
        }
    );
