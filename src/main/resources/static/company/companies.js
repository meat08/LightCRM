angular.module('app').controller('companyController', function ($scope, $http) {
    const contextPath = 'http://localhost:8180/app';

    $scope.showAllCompanies = function () {
        $http({
            url: contextPath + '/api/v1/companies',
            method: 'GET'
        }).then(function (response) {
            $scope.showCompanyInfo = false;
            $scope.showAllCompaniesInfo = true;
            $scope.companies = response.data;
        });
    };

    $scope.createCompany = function () {
    //TODO
    };

    $scope.deleteCompany = function (companyId) {
        $http({
            url: contextPath + '/api/v1/companies/' + companyId,
            method: 'DELETE'
        }).then(function (response) {
            $scope.showAllCompanies();
        });
    }

    $scope.showCompany = function (companyId) {
        $http({
            url: contextPath + '/api/v1/companies/' + companyId,
            method: 'GET'
        }).then(function (response) {
            $scope.showAllCompaniesInfo = false;
            $scope.showCompanyInfo = true;
            $scope.companyInfo = response.data;
        });
    }

    $scope.showContact = function (contact) {
        $scope.showCompanyContact = true;
        $scope.contactInfo = contact;
    }

    $scope.showAllCompanies();
});
