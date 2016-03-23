friendEditorApp.config(function($stateProvider, $urlRouterProvider) {

	$urlRouterProvider.otherwise("home");

	$stateProvider.state('home', {
		url : "/home",
		templateUrl : "views/home.html",
		controller : "friendEditorCtrl"
	}).state('list', {
		url : "/list",
		templateUrl : "views/list.html",
		controller : "friendEditorCtrl"
	})
});