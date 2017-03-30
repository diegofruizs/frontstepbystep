(function (ng) {
    var mod = ng.module("authorModule", ['ui.router']);
    mod.constant("authorsContext", "api/authors");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/authors/';
            var basePathBooks = 'src/modules/books/';
            $urlRouterProvider.otherwise("/authorsList");

            $stateProvider.state('authors', {
                url: '/authors',
                abstract: true,
                resolve: {
                    authors: ['$http', function ($http) {
                            return $http.get('data/authors.json');
                        }]
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'authors.html',
                        controller: ['$scope', 'authors', function ($scope, authors) {
                                $scope.authorsRecords = authors.data;
                            }]
                    }
                }
            }).state('authorsList', {
                url: '/list',
                parent: 'authors',
                views: {
                    'listView': {
                        templateUrl: basePath + 'authors.list.html'
                    }
                }
            }).state('authorDetail', {
                url: '/{authorId:int}/detail',
                parent: 'authors',
                param: {
                    authorId: null
                },
                views: {
                    'listView': {
                        resolve: {
                            books: ['$http', function ($http) {
                                    return $http.get('data/books.json');
                                }]
                        },
                        templateUrl: basePathBooks + 'books.list.html',
                        controller: ['$scope', 'books', '$stateParams', function ($scope, books, $params) {
                                $scope.booksRecords = books.data;
                                $scope.currentAuthor = $scope.authorsRecords[$params.authorId - 1];
                            }]
                    },
                    'detailView': {
                        templateUrl: basePath + 'authors.detail.html',
                        controller: ['$scope', '$stateParams', function ($scope, $params) {
                                $scope.currentAuthor = $scope.authorsRecords[$params.authorId - 1];
                            }]
                    }
                }
            });
        }]);
})(window.angular);