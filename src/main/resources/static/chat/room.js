angular.module('app').controller('roomController',
    function ($scope, $http, $localStorage, ChatService, $uibModal, $q) {

        const contextPath = 'http://localhost:8180/app';
        $scope.senderId = $localStorage.currentUser.profileId;
        $scope.chats = [];
        $scope.activeChatWindow = false;

        $scope.subscribe = function() {
            ChatService.ready.then(function () {
                ChatService.unsubscribe();
                ChatService.subscribe("/user/" + $scope.senderId + "/queue/messages", function (payload) {
                    $scope.getNotification(payload);
                });
            });
        };

        $scope.getNotification = function(payload) {
            angular.forEach($scope.chats, function (value) {
                if (value.chatId === payload.chatId) {
                    value.unreadMessageCount += 1;
                }
            });
        };

        $scope.getChats = function() {
            $http({
                url: contextPath + '/chats/rooms/' + $scope.senderId,
                method: 'GET'
            })
                .then(function (response) {
                    $scope.chats = response.data;
                    angular.forEach($scope.chats, function (value) {
                        $http.get(contextPath + '/api/v1/files/photo/preview/' + value.recipientId,
                            {responseType: "arraybuffer"}
                        )
                            .then(function (response) {
                                let contentType = response.headers("content-type");
                                let blob = new Blob([response.data], {type: contentType});
                                value.recipientAvatar = (window.URL || window.webkitURL).createObjectURL(blob);
                            });
                    })
                });
        };

        $scope.getChatById = function(chatID) {
            angular.forEach($scope.chats, function (value) {
                if (value.chatId === chatID) {
                    $scope.currentChat = value;
                }
            });
        };

        $scope.open = function (recipientId, currentChatId) {
            $scope.recipientId = recipientId;
            $scope.getChatById(currentChatId, recipientId);
            $scope.activeChatWindow = true;
            $uibModal.open({
                templateUrl: 'chat/chat.html',
                controller: 'chatController',
                windowClass: 'my-modal',
                size: 'lg',
                scope: $scope
                })
                .result.then(null, function () {
                    $scope.subscribe();
                    $scope.activeChatWindow = false;
            })
            };

        $scope.subscribe();
        $scope.getChats();

    });