angular.module('app').controller('chatController',
    function ($scope, $http, $location, $localStorage, $filter, $routeParams, ChatService) {

        const contextPath = 'http://localhost:8180/app';
        //TODO переделать. Черновой вариант
        $scope.senderId = $localStorage.currentUser.profileId;
        $scope.profile = null;
        $scope.messages = [];
        $scope.chats = [];
        $scope.newMessage = {};

        $scope.subscribe = function() {
            ChatService.unsubscribe();
            ChatService.subscribe("/user/" + $scope.senderId + "/queue/messages", function (payload) {
                $scope.getNotification(payload);
            });
        };

        $scope.sendMessage = function() {
            $scope.newMessage = {
                senderId: $scope.senderId,
                recipientId: $scope.recipientId,
                senderName: $scope.profile.firstname + " " + $scope.profile.lastname,
                recipientName: "Me",
                content: $scope.messageText,
                timestamp: $filter('date')(new Date(),'yyyy-MM-dd HH:mm:ss')
            };
            ChatService.send('/app/chat', $scope.newMessage);
            $scope.messageText = "";
            $scope.messages.push($scope.newMessage);
        };

        $scope.getMessages = function() {
            //TODO где-то тут баг
            $http({
                url: contextPath + '/chats/messages/' + $scope.senderId + '/' + $scope.recipientId,
                method: 'GET'
            })
                .then(function (response) {
                    $scope.messages = response.data;
                });
        };

        $scope.getMessageById = function(id) {
            $http({
                url: contextPath + '/chats/messages/' + id,
                method: 'GET'
            })
                .then(function (response) {
                    $scope.messages.push(response.data);
                });
        };

        $scope.getProfile = function() {
            $http({
                url: contextPath + '/api/v1/profiles/' + $scope.senderId, //TODO переделать получение мини дто
                method: 'GET'
            })
                .then(function (response) {
                    $scope.profile = response.data;
                });
        };

        $scope.getNotification = function(payload) {
            $scope.getMessageById(payload.messageId);
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

        $scope.openChat = function() {
            $scope.getMessages();
        }

        $scope.subscribe();
        $scope.getChats();
        $scope.getProfile();
        $scope.openChat();

});