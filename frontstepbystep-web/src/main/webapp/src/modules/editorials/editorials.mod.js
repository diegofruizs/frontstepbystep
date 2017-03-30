(function (ng) {
    var mod = ng.module("editorialModule", ['ui.router']);
    mod.constant("editorialsContext", "api/editorials");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/editorials/';
            $urlRouterProvider.otherwise("/editorialsList");
            self = this;
             $stateProvider.state('editorials', {
                url: '/editorials',
                abstract: true,
                resolve: {
                    editorials: ['$http', function ($http) {
                            return $http.get('data/editorials.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'editorials.html',
                        controller: ['$scope', 'editorials', function ($scope, editorials) {
                                $scope.editorialsRecords = editorials.data;
                            }]
                    }
                }
            }).state('editorialsList', {
                url: '/list',
                parent: 'editorials',
                views: {
                    'listView': {
                        templateUrl: basePath + 'editorials.list.html'
                    }
                }
            });
        }]);
})(window.angular);
