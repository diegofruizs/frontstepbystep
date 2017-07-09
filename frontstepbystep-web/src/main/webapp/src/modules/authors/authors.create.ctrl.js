(
  function (ng) {
    var mod = ng.module("authorModule");
    mod.constant("authorsContext", "api/authors");

    mod.controller('authorCreateCtrl', ['$scope', '$http', 'authorsContext', '$state',
      function ($scope, $http, authorsContext, $state) {
        $scope.sendAuthor = function() {
          $http.post(authorsContext, {
            name: $scope.authorName,
            birthDate: $scope.authorBirthDate,
            description: $scope.authorDescription,
            image: $scope.authorImage
          }).then(function(data) {
            alert("Author created succesfuly");
            $state.go('authorsList');
          }, function(error) {
            alert("There was an error creating the Author");
          });
        };
      }
    ]);
  }
)(angular);