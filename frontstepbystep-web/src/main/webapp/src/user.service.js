(function (ng) {
  var appModule = angular.module('mainApp');

  appModule.service('userService', ['$q', function ($q) {
      function getUser(){
        return {
          nombre: 'John Doe',
          email: 'john.doe@example.com',
          usuario: 'john.doe',
          roles: [
            'administrador',
            'general'
          ]
        };
      }
      
      function getRoles(){
        return getUser().roles;
      }
      return {
        getUser: getUser,
        getRoles: getRoles
      };
    }]);
})(window.angular);