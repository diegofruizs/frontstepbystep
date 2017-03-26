(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        // Internal modules dependencies       
        'bookModule'

    ]);
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);