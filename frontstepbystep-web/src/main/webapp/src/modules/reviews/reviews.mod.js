(function (ng) {
    var mod = ng.module("reviewModule", ['ui.router']);

    mod.constant("booksContext", "api/books");

    mod.constant("reviewsContext", "reviews");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/reviews/';
            $urlRouterProvider.otherwise("/reviewsList");

            $stateProvider.state('reviews', {
                url: '/reviews',
                abstract: true,
                parent: 'bookDetail',
                resolve: {
                    reviews: ['$http', 'booksContext', 'reviewsContext', '$stateParams', function ($http, booksContext, reviewsContext, $params) {
                            return $http.get(booksContext + '/' + $params.bookId + '/' + reviewsContext);
                        }]
                },
                views: {
                    'childrenView': {
                        templateUrl: basePath + 'reviews.html'
                    }
                },
            }).state('reviewsList', {
                url: '/list',
                parent: 'reviews',
                views: {
                    'listView': {
                        templateUrl: basePath + 'reviews.list.html',
                        controller: ['$scope', 'reviews', function ($scope, reviews) {
                                $scope.reviewsRecords = reviews.data;
                            }]
                    }
                }
            });
        }]);
})(window.angular);