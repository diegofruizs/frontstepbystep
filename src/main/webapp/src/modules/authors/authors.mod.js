(function (ng) {
    // Definición del módulo
    var mod = ng.module("authorModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/authors/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/authorsList");
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('authorsList', {
                // Url que aparecerá en el browser
                url: '/authors/list',
                // Se define una variable authors (del estado) que toma por valor 
                // la colección de autores que obtiene utilizando $http.get 
                resolve: {
                    authors: ['$http', function ($http) {
                            return $http.get('data/authors.json'); // $http retorna un apromesa que aquí no se está manejando si viene con error.
                        }]
                },
                views: {
                    'mainView': {
                        // Template que se utilizara para ejecutar el estado
                        templateUrl: basePath + 'authors.list.html',
                        // El controlador guarda en el scope en la variable authorsRecords los datos que trajo el resolve
                        // authorsRecords será visible en el template
                        controller: ['$scope', 'authors', function ($scope, authors) {
                                $scope.authorsRecords = authors.data;
                            }]
                    }
                }
            });
        }
    ]);
})(window.angular);
