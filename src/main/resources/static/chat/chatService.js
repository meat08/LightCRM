'use strict';

angular.module('ChatService', [])
    .factory('ChatService', ['$rootScope', '$stomp', function($rootScope, $stomp) {

        var subscription;

        return {

            connect: function(destination, header, errorCallback) {
                $stomp.setDebug(function (args) {
                    console.log(args + '\n');
                });
                $stomp.connect(destination, header, function (error) {
                    $rootScope.$apply(function(){
                        errorCallback(error);
                    });
                });
            },

            subscribe: function(destination, callback) {
                subscription = $stomp.subscribe(destination, function (payload, headers, res) {
                    $rootScope.$apply(function(){
                        callback(payload);
                    });
                });
            },

            send: function(destination, object) {
                $stomp.send(destination, object);
            },

            unsubscribe: function () {
                if (subscription != null) {
                    subscription.unsubscribe();
                }
            },

            disconnect: function () {
                $stomp.disconnect();
            }
        }

    }]);