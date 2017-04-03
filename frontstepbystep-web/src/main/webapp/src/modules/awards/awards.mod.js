(function (ng) {
    var mod = ng.module("awardModule", ['authorModule', 'ui.router']);

 
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
                        templateUrl: basePath + 'awards.html',
                        controller: ['$scope', function ($scope) {
                                $scope.awardsRecords = $scope.currentAuthor.awards;
                            }]
                    }
                }
            }).state('awardsList', {
                url: '/list',
                parent: 'awards',
                views: {
                    'listView': {
                        templateUrl: basePath + 'awards.list.html'
                    }
                }
            }).state('awardDetail', {
                url: '/{awardId:int}/detail',
                parent: 'awards',
                param: {
                    awardId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'awards.detail.html',
                        controller: ['$scope', '$stateParams', '$filter', function ($scope, $params, $filter) {
                                var found = $filter('filter')( $scope.awardsRecords, {id: $params.awardId}, true);
                                if (found.length) {
                                    $scope.currentAward = found[0];
                                } else {
                                    $scope.currentAward = '';
                                }
                            }]
                    }
                }
            });
        }]);
})(window.angular);