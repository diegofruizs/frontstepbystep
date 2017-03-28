(function (ng) {
    var mod = ng.module("bookModule", ['ui.router']);
    mod.constant("booksContext", "api/books");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/books/';
            $urlRouterProvider.otherwise("/booksList");

            $stateProvider.state('books', {
                url: '/books',
                abstract: true,
                resolve: {
                    books: ['$http', function ($http) {
                            return $http.get('data/books.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'books.html',
                        controller: ['$scope', 'books', function ($scope, books) {
                                $scope.booksRecords = books.data;
                            }]
                    }
                }
            }).state('booksList', {
                url: '/list',
                parent: 'books',
                views: {
                    'listView': {
                        templateUrl: basePath + 'books.list.html'
                    }
                }
            }).state('bookDetail', {
                url: '/{bookId:int}/detail',
                parent: 'books',
                param: {
                    bookId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'books.list.html'
                    },
                    'detailView': {
                        templateUrl: basePath + 'books.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentBook = $scope.booksRecords[$params.bookId-1];
                            }]
                    }

                }

            });
        }]);
})(window.angular);
