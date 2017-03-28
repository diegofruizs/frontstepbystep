(function (ng) {
    var mod = ng.module("reviewModule", ['ui.router']);
    mod.constant("reviewsContext", "api/reviews");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/reviews/';
            $urlRouterProvider.otherwise("/reviewsList");

            $stateProvider.state('reviews', {
                url: '/reviews',
                abstract: true,
                parent: 'bookDetail',
                views: {
                    childrenView: {                       
                        resolve: {
                            books: ['$http', function ($http) {
                                    return $http.get('data/books.json');
                                }],
                            reviews: ['$http', function ($http) {
                                    return $http.get('data/reviews.json');
                                }]
                        },
                        templateUrl: basePath + 'reviews.html',
                        controller: ['$scope', 'books', 'reviews', '$stateParams', function ($scope, books, reviews, $params) {
                                $scope.currentBook = books.data[$params.bookId - 1];
                                $scope.reviewsRecords = reviews.data;
                            }]
                    }
                }

            }).state('reviewsList', {
                url: '/list',
                parent: 'reviews',
                views: {
                    'listView': {
                        templateUrl: basePath + 'reviews.list.html'
                    }
                }
            });
        }]);
})(window.angular);