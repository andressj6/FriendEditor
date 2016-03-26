var friendEditorController = friendEditorApp
        .controller('friendEditorCtrl', function ($scope, $http) {
            $scope.nome = "André";
            $scope.checkLogin = function () {
                $("#loading").modal("show");
                //Tries to login using Facebook javascript api (Just to execute the enpoint from outside the java context)
                FB.getLoginStatus(function (response) {
                    if (response.status === 'connected') {
                        var userInfo = {
                            userId: response.authResponse.userID,
                            accessToken: response.authResponse.accessToken
                        }
                        //if its already logged in, executes the "save friends and user" endpoint
                        saveFriends(userInfo);
                        $("#loading").modal("hide");
                        return true;
                    } else if (response.status === 'not_authorized') {
                        console.log("Not authorized");
                        $("#loading").modal("hide");
                        return false;
                    } else {
                        console.log("Trying to Log in");
                        FB.login(function (response) {
                            if (response.authResponse) {
                                var userInfo = {
                                    userId: response.authResponse.userID,
                                    accessToken: response.authResponse.accessToken
                                }
                                saveFriends(userInfo);
                                return true;
                            } else {
                                alert("Couldn't login to Facebook");
                                return false;
                            }
                            $("#loading").modal("hide");
                        }, {
                            scope: "email, user_friends"
                        });
                    }
                });
            }

            var saveFriends = function (userInfo) {
                console.log(userInfo);
                $http.post('users/', userInfo).then(function () {
                    console.log("Success");
                }, function (response) {
                    console.log("Error");
                    console.log(response)
                });
            }
        });
