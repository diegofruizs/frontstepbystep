(function (ng) {
    var mod = ng.module("awardModule", ['ui.router']);
    mod.constant("awardsContext", "api/awards");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/awards/';
            $urlRouterProvider.otherwise("/awardsList");

            $stateProvider.state('awards', {
                url: '/awards',
                abstract: true,
                resolve: {
                    awards: ['$http', function ($http) {
                            return $http.get('data/awards.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'awards.html',
                        controller: ['$scope', 'awards', function ($scope, awards) {
                                $scope.awardsRecords = awards.data;
                            }]
                    }
                }
            }).state('awardsList', {
                url: '/list',
                parent: 'awards',
                views: {
                    'view': {
                        templateUrl: basePath + 'awards.list.html'
                    }
                }
            }).state('awardDetail', {
                url: '/{awardId:int}/detail',
                parent: 'awards',
                param: {
                    bookId: null
                },
                views: {
                   
                    'view': {
                        templateUrl: basePath + 'awards.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentAward = $scope.awardsRecords[$params.awardId-1];
                            }]
                    }
                }
            });
        }]);
})(window.angular);
