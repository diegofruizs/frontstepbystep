(function (ng) {
    var mod = ng.module("awardModule", ['ui.router']);

    mod.constant("authorsContext", "api/authors");

    mod.constant("awardsContext", "awards");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/awards/';
            $urlRouterProvider.otherwise("/awardsList");

            $stateProvider.state('awards', {
                url: '/awards',
                abstract: true,
                parent: 'authorDetail',
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'awards.html'
                    }
                }
            }).state('awardsList', {
                url: '/list',
                parent: 'awards',
                views: {
                    'listView': {
                        templateUrl: basePath + 'awards.list.html',
                        controller: ['$scope', 'currentAuthor',function ($scope,currentAuthor) {
                                $scope.awardsRecords = currentAuthor.data.awards;
                            }]
                    }
                }
            }).state('awardDetail', {
                url: '/{awardId:int}/detail',
                parent: 'awards',
                param: {
                    awardId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'awards.detail.html',
                        controller: ['$scope', '$stateParams', '$filter', 'currentAuthor', function ($scope, $params, $filter,currentAuthor) {
                               $scope.awardsRecords = currentAuthor.data.awards;
                               var found = $filter('filter')( $scope.awardsRecords, {id: $params.awardId}, true);
                                if (found.length) {
                                    $scope.currentAward = found[0];
                                } else {
                                    $scope.currentAward = '';
                                }
                            }]
                    },
                    'listView': {
                        templateUrl: basePath + 'awards.list.html',
                        controller: ['$scope', 'currentAuthor',function ($scope,currentAuthor) {
                                $scope.awardsRecords = currentAuthor.data.awards;
                            }]
                    }
                }
            });
        }]);
})(window.angular);