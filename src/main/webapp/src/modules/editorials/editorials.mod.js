(function (ng) {
    // Definición del módulo
    var mod = ng.module("editorialModule", ['ui.router']);
 
   // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/editorials/';
            // Mostrar la lista de editoriales será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/editorialsList");
            // Definición del estado 'editorialsList' donde se listan los editoriales
            $stateProvider.state('editorialsList', {
                // Url que aparecerá en el browser
                url: '/editorials/list',
                // Se define una variable editorials (del estado) que toma por valor 
                // la colección de editoriales que obtiene utilizando $http.get 
                 resolve: {
                    editorials: ['$http', function ($http) {
                            return $http.get('data/editorials.json'); // $http retorna un apromesa que aquí no se está manejando si viene con error.
                        }]
                },
                // Template que se utilizara para ejecutar el estado
                templateUrl: basePath + 'editorials.list.html',
                // El controlador guarda en el scope en la variable editorialsRecords los datos que trajo el resolve
                // editorialsRecords será visible en el template
                controller: ['$scope', 'editorials', function ($scope, editorials) {
                        $scope.editorialsRecords = editorials.data;
                    }]              
            });
        }
    ]);
})(window.angular);
