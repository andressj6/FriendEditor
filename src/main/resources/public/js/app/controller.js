var friendEditorController = friendEditorApp
	.controller('friendEditorCtrl', function($scope, $http) {
		$scope.nome = "André";
		$scope.checkLogin = function() {
			FB
				.getLoginStatus(function(response) {
					if (response.status === 'connected') {
						var userInfo = {
							userId : response.authResponse.userID,
							accessToken : response.authResponse.accessToken
						}
						saveFriends(userInfo);
						return true;
					} else if (response.status === 'not_authorized') {
						console.log("Not authorized");
						return false;
					} else {
						console.log("Tentando Login");
						FB
							.login(function(response) {
								if (response.authResponse) {
									var userInfo = {
										userId : response.authResponse.userID,
										accessToken : response.authResponse.accessToken
									}
									saveFriends(userInfo);
									return true;
								} else {
									alert("Não foi possivel efetuar o login no facebook");
									return false;
								}
							}, {
								scope : "email, user_friends"
							});
					}
				});
		}

		var saveFriends = function(userInfo) {
			console.log(userInfo);
			$http.post('users/', userInfo).then(function() {
				console.log("Success");
			}, function(response) {
				console.log("Error");
				console.log(response)
			});
		}
	});
