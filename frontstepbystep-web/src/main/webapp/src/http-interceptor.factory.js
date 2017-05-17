(function (ng) {
  var appModule = angular.module('mainApp');

  appModule.factory('httpInterceptor', ['$q', function ($q) {
      var interceptor = {
        response: function (response) {
          return response;
        },
        responseError: function (rejection) {
          var status = rejection.status;
          function showError(message) {
            var template = `<div class="alert alert-danger">
                                        <strong>Error!</strong> ${message} </div>`;
            var alertPanel = document.getElementById('alertPanel');
            alertPanel.innerHTML = template;
            alertPanel.style.opacity = 1;

            setTimeout(function () {
              alertPanel.style.opacity = 0;
            }, 5000);
          }
          showError("Error " + status + ": " + rejection.data);
          return $q.reject(rejection);
        }
      };
      return interceptor;
    }]);

  appModule.config(['$httpProvider', function ($httpProvider) {
      $httpProvider.interceptors.push('httpInterceptor');
    }]);
})(window.angular);