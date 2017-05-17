(function (ng) {
    var mod = ng.module("bookModule", ['ui.router']);
    mod.constant("booksContext", "api/books");
    mod.config(['$stateProvider',  '$urlRouterProvider', function ( $stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/books/';
            $urlRouterProvider.otherwise("/booksList");

            $stateProvider.state('books', {
                url: '/books',
                abstract: true,
                resolve: {
                    books: ['$http', 'booksContext', function ($http, booksContext) {
                            return $http.get(booksContext);
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
                resolve:  {
                    currentBook: ['$http', 'booksContext', '$stateParams', function ($http, booksContext, $params) {
                            return $http.get(booksContext+'/'+$params.bookId);
                        }]
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'books.detail.html',
                        controller: ['$scope', 'currentBook', 'userService', function ($scope, currentBook, userService) {
                                $scope.currentBook = currentBook.data;
                                $scope.isRol = function(rol){
                                  return (userService.getRoles().indexOf(rol) > -1);
                                };
                                
                            }]
                    }

                }

            });
        }]);
})(window.angular);
