var friendEditorApp = angular.module('friendEditorApp', [ "ui.router" ]);

var friendEditorController = friendEditorApp
	.controller('friendEditorCtrl', function($scope, $http) {
		$scope.nome = "André";
	});

friendEditorApp.config(function($stateProvider, $urlRouterProvider) {

	$urlRouterProvider.otherwise("home");

	$stateProvider.state('home', {
		url : "/home",
		templateUrl : "views/home.html"
	}).state('list', {
		url : "/list",
		templateUrl : "views/list.html",
		controller : "friendEditorCtrl"
	})
});
