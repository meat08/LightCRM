angular.module('app').controller('companyController', function ($scope, $http) {
    const contextPath = 'http://localhost:8180/app';

    $scope.getCompanyContent = function () {
        $http({
            url: contextPath + '/api/v1/companies',
            method: 'GET'
        }).then(function (response) {
            $scope.companies = response.data;
        });
    };

    $scope.getCompanyContent();
});
