angular.module('app').service('profileService', function ($http) {
        const contextPath = 'http://localhost:8180/app';

        var getProfile = function () {
            return $http.get(contextPath + '/api/v1/profiles/profile').then(function (result) {
                return result;
            });
        };

        return {
            getProfile: getProfile
        };
    });
