(function (ng) {
    var mod = ng.module("awardModule", ['authorModule', 'ui.router']);

    mod.constant("authorsContext", "api/authors");

    mod.constant("awardsContext", "awards");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/awards/';
            $urlRouterProvider.otherwise("/awardsList");

            $stateProvider.state('awards', {
                url: '/awards',
                abstract: true,
                parent: 'authorDetail',
                resolve: {
                    awards: ['$http', 'authorsContext', 'awardsContext', '$stateParams', function ($http, authorsContext, awardsContext, $params) {
                            return $http.get(authorsContext + '/' + $params.authorId + '/' + awardsContext);
                        }]
                },
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'awards.html'
                    }
                },
            }).state('awardsList', {
                url: '/list',
                parent: 'awards',
                views: {
                    'listView': {
                        templateUrl: basePath + 'awards.list.html',
                        controller: ['$scope', 'awards', function ($scope, awards) {
                                $scope.awardsRecords = awards.data;
                            }]
                    }
                }
            });
        }]);
})(window.angular);