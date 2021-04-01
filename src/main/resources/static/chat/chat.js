angular.module('app').controller('chatController',
    function ($scope, $http, $location, $localStorage, $stomp, $filter, $routeParams) {

        const contextPath = 'http://localhost:8180/app';
        //TODO переделать. Черновой вариант
        $scope.senderId = $localStorage.currentUser.profileId;
        $scope.profile = null;
        $scope.messages = [];
        $scope.chats = [];
        $scope.newMessage = {};

        $scope.connectToStomp = function() {
            //TODO отключить дебаг на проде
            $stomp.setDebug(function (args) {
                console.log(args + '\n');
            });

            $stomp
                .connect('/app/ws', {})

                .then(function (frame) {
                    $scope.subscription = $stomp.subscribe("/user/" + $scope.senderId + "/queue/messages", function (payload, headers, res) {
                        $scope.getNotification(payload);
                    });
                })
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
            $stomp.send('/app/chat', $scope.newMessage);
            $scope.messageText = "";
            $scope.getMessages();
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
            $scope.getMessages();
            console.log("Уведомление получено:");
            console.log(payload);
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
            $scope.recipientId = $routeParams.recipientId;
            $scope.getMessages();
        }

        $scope.connectToStomp();
        $scope.getChats();
        $scope.getProfile();
        $scope.openChat();

});