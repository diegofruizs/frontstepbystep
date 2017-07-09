(
  function (ng) {
    var mod = ng.module("authorModule");
    mod.constant("authorsContext", "api/authors");

    mod.controller('authorUpdateCtrl', ['$scope', '$http', 
      'authorsContext', '$state', 'authors', '$filter',
      function ($scope, $http, authorsContext, $state, authors, $filter) {
        var author = $filter('filter')(authors.data, {id: $state.params.authorId})[0];
        $scope.authorName = author.name;
        $scope.authorBirthDate = new Date(author.birthDate);
        $scope.authorDescription = author.description;
        $scope.authorImage = author.image;
        $scope.sendAuthor = function() {
          $http.put(authorsContext + '/' + author.id, {
            id: author.id,
            name: $scope.authorName,
            birthDate: $scope.authorBirthDate,
            description: $scope.authorDescription,
            image: $scope.authorImage
          }).then(function(data) {
            alert("Author updating succesfuly");
            $state.go('authorsList');
          }, function(error) {
            alert("There was an error updating the Author");
          });
        };
      }
    ]);
  }
)(angular);