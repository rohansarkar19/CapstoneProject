Feature: Movie Rental Management

Scenario: Add a new movie
    Given the API is available
    When I add a new movie with title "Interstellar", director "Christopher Nolan", genre "Sci-Fi", and release date "2014-11-07"
    Then the response status should be 201
    And the response should contain a success message and movieId