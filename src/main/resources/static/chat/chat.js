angular.module('app')
    .directive('scroll', function($timeout) {
        return {
            restrict: 'A',
            link: function(scope, element, attr) {
                scope.$watchCollection(attr.scroll, function(newVal) {
                    $timeout(function() {
                        element[0].scrollTop = element[0].scrollHeight;
                    });
                });
            }
        }
    })
    .directive('ngEnter', function() {
        return function(scope, element, attrs) {
            element.bind("keydown", function(e) {
                if(e.which === 13 && e.ctrlKey) {
                    var text = document.getElementById("chatInput");
                    text.value += '\n';
                } else if(e.which === 13) {
                    scope.$apply(function(){
                        scope.$eval(attrs.ngEnter, {'e': e});
                    });
                    e.preventDefault();
                }
            });
        };
    })
    .controller('chatController',
    function ($scope, $http, $location, $localStorage, $filter, $routeParams, ChatService) {

        const contextPath = 'http://localhost:8180/app';
        $scope.senderId = $localStorage.currentUser.profileId;
        $scope.profile = null;
        $scope.messages = [];
        $scope.newMessage = {};

        $scope.subscribe = function() {
            ChatService.ready.then(function () {
                ChatService.unsubscribe();
                ChatService.subscribe("/user/" + $scope.senderId + "/queue/messages", function (payload) {
                    $scope.getNotification(payload);
                });
            });
        };

        $scope.sendMessage = function() {
            $scope.newMessage = {
                senderId: $scope.senderId,
                recipientId: $scope.currentChat.recipientId,
                senderName: $scope.profile.firstname + " " + $scope.profile.lastname,
                recipientName: $scope.currentChat.recipientName,
                content: $scope.messageText,
                timestamp: $filter('date')(new Date(),'yyyy-MM-dd HH:mm:ss')
            };
            ChatService.send('/app/chat', $scope.newMessage);
            $scope.messageText = "";
            $scope.messages.push($scope.newMessage);
        };

        $scope.getMessages = function(senderId, recipientId) {
            $http({
                url: contextPath + '/chats/messages/' + senderId + '/' + recipientId,
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
                url: contextPath + '/api/v1/profiles/' + $scope.senderId,
                method: 'GET'
            })
                .then(function (response) {
                    $scope.profile = response.data;
                });
        };

        $scope.getNotification = function(payload) {
            if ($scope.activeChatWindow) {
                if (payload.chatId === $scope.currentChat.chatId) {
                    $scope.getMessageById(payload.messageId);
                } else {
                    angular.forEach($scope.chats, function (value) {
                        if (value.chatId === payload.chatId) {
                            value.unreadMessageCount += 1;
                        }
                    });
                }
            }
        };

        $scope.getChatById = function(chatID) {
            angular.forEach($scope.chats, function (value) {
                if (value.chatId === chatID) {
                    $scope.currentChat = value;
                    $scope.getMessages($scope.currentChat.senderId, $scope.currentChat.recipientId);
                }
            });
        };

        $scope.setActive = function(chatId) {
            $scope.currentChat.unreadMessageCount = 0;
            $scope.getChatById(chatId);
         };

        $scope.getProfile();
        $scope.subscribe();
        $scope.getMessages($scope.currentChat.senderId, $scope.currentChat.recipientId);
});