var friendEditorApp = angular.module('friendEditorApp', [ "ui.router" ]);

var friendEditorController = friendEditorApp
	.controller('friendEditorCtrl', function($scope, $http) {
		$scope.nome = "André";
	});


