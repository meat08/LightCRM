angular.module('app').controller('roomController',
    function ($scope, $http, $localStorage, ChatService, $uibModal) {

        const contextPath = 'http://localhost:8180/app';
        $scope.senderId = $localStorage.currentUser.profileId;
        $scope.profile = null;
        $scope.chats = [];

        $scope.subscribe = function() {
            ChatService.unsubscribe();
            ChatService.subscribe("/user/" + $scope.senderId + "/queue/messages", function (payload) {
                $scope.getNotification(payload);
            });
        };

        $scope.getNotification = function(payload) {
            console.log(payload); //TODO при получении нотификации увеличивать счеткик непрочитанных
        };

        $scope.getChats = function() {
            $http({
                url: contextPath + '/chats/rooms/' + $scope.senderId,
                method: 'GET'
            })
                .then(function (response) {
                    $scope.chats = response.data;
                });
        };

        $scope.open = function (recipientId) {
            $scope.recipientId = recipientId;
            $uibModal.open({
                templateUrl: 'chat/chat.html',
                controller: 'chatController',
                size: 'lg',
                scope: $scope
                })
            };

        $scope.getChats();
        $scope.subscribe();

    });