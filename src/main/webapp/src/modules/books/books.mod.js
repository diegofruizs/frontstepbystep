(function (ng) {
    // Definición del módulo
    var mod = ng.module("bookModule", ['ui.router']);
 
   // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/books/';
            // Mostrar la lista de libros será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/booksList");
            // Definición del estado 'booksList' donde se listan los libros
            $stateProvider.state('booksList', {
                // Url que aparecerá en el browser
                url: '/books/list',
                // Se define una variable books (del estado) que toma por valor 
                // la colección de libros que obtiene utilizando $http.get 
                 resolve: {
                    books: ['$http', function ($http) {
                            return $http.get('data/books.json'); // $http retorna un apromesa que aquí no se está manejando si viene con error.
                        }]
                },
                // Template que se utilizara para ejecutar el estado
                templateUrl: basePath + 'books.list.html',
                // El controlador guarda en el scope en la variable booksRecords los datos que trajo el resolve
                // booksRecords será visible en el template
                controller: ['$scope', 'books', function ($scope, books) {
                        $scope.booksRecords = books.data;
                    }]              
            });
        }
    ]);
    
    
})(window.angular);

(function (ng) {
     // Definición del módulo
    var mod = ng.module("bookModule", ['ui.router']);
 
   // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/books/';
            // Mostrar la lista de libros será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/booksList");
            // Estado abstracto que factoriza la asignación de valores a la variable books
            // Define el controlador que recibe books e inicializa booksRecords en el scope
            // Define un template cuyoi propósito es definir la vista donde se desplegará el detalle 
            // y la vista donde se deplegara la lista de libros. 
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
