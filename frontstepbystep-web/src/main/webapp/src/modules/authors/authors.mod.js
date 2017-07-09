(function (ng) {
  var mod = ng.module("authorModule", ['ui.router']);

  mod.constant("authorsContext", "api/authors");

  mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
      var basePath = 'src/modules/authors/';
      var baseBookPath = 'src/modules/books/';
      $urlRouterProvider.otherwise("/authorsList");

      $stateProvider.state('authors', {
        url: '/authors',
        abstract: true,
        resolve: {
          authors: ['$http', 'authorsContext', function ($http, authorsContext) {
              return $http.get(authorsContext);
            }]
        },
        views: {
          'mainView': {
            templateUrl: basePath + 'authors.html'
          }
        }
      }).state('authorsList', {
        url: '/list',
        parent: 'authors',
        views: {
          'listView': {
            templateUrl: basePath + 'authors.list.html',
            controller: ['$scope', 'authors', function ($scope, authors) {
                $scope.authorsRecords = authors.data;
                
              }]
          }
        }
      }).state('authorDetail', {
        url: '/{authorId:int}/detail',
        parent: 'authors',
        param: {
          authorId: null
        },
        resolve: {
          currentAuthor: ['$http', 'authorsContext', '$stateParams', function ($http, authorsContext, $params) {
              return $http.get(authorsContext + '/' + $params.authorId);
            }]
        },
        views: {
          'detailView': {
            templateUrl: basePath + 'authors.detail.html',
            controller: ['$scope', 'currentAuthor', function ($scope, currentAuthor) {
                $scope.currentAuthor = currentAuthor.data;
              }]
          },
          'listView': {
            templateUrl: baseBookPath + 'books.list.html',
            controller: ['$scope', 'currentAuthor', function ($scope, currentAuthor) {
                $scope.booksRecords = currentAuthor.data.books;
              }]
          }
        }
      }).state('authorsCreate', {
        url: '/create',
        parent: 'authors',
        views: {
          'detailView': {
            templateUrl: basePath + 'authors.create.html',
            controller: 'authorCreateCtrl'
          }
        }
      });
    }]);
})(window.angular);