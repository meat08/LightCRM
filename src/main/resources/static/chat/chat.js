angular.module('app').controller('chatController',
    function ($scope, $http, $location, $localStorage, $stomp, $filter) {

        $scope.senderId = $localStorage.currentUser.profileId;

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
                        $scope.sendMessage();
                    })
        };

        $scope.sendMessage = function() {
            $stomp.send('/app/chat', {
               senderId: 2,
               recipientId: $scope.senderId,
               senderName: "Test",
               recipientName: "Me",
               content: "Превед, медвед",
               timestamp: $filter('date')(new Date(),'yyyy-MM-dd HH:mm:ss')
            });
        };

        $scope.getNotification = function(payload) {
            console.log("Уведомление получено:");
            console.log(payload);
        };

            // // Unsubscribe
            // subscription.unsubscribe()
            //
            // // Send message
            // $stomp.send('/dest', {
            //   message: 'body'
            // }, {
            //   priority: 9,
            //   custom: 42 // Custom Headers
            // })
            //
            // // Disconnect
            // $stomp.disconnect().then(function () {
            //   $log.info('disconnected')
            // })


});