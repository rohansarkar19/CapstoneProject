Feature: Movie Rental Management

  Scenario: Get all movies
    Given the API is available
    When I request all movies
    Then the response status should be 200
    And the response should contain movies with titles "Inception", "The Shawshank Redemption", "The Dark Knight"

  Scenario: Register a new user
    Given the API is available
    When I register a new user with username "rohan_sarkar", email "rohansarkar@example.com", and password "rohan@123"
    Then the response status should be 201
    And the response should contain a success message and userId

  Scenario: User login
    Given the API is available
    When I log in with username "rohan_sarkar" and password "rohan@123"
    Then the response status should be 200
    And the response should contain a success message and token

  Scenario: Add a new movie
    Given the API is available
    When I add a new movie with title "Interstellar", director "Christopher Nolan", genre "Sci-Fi", and release date "2014-11-07"
    Then the response status should be 201
    And the response should contain a success message and movieId

  Scenario: Search movies by title
    Given the API is available
    When I search for movies with title "Inception"
    Then the response status should be 200
    And the response should contain movies with title "Inception"

  Scenario: Filter movies by genre
    Given the API is available
    When I filter movies by genre "Sci-Fi"
    Then the response status should be 200
    And the response should contain movies with genre "Sci-Fi"

  Scenario: Rent a movie
    Given the API is available
    When I rent a movie with userId "1" and movieId "2"
    Then the response status should be 201
    And the response should contain a success message and rentalId

  Scenario: Get user profile
    Given the API is available
    When I request user profiles
    Then the response status should be 200
    And the response should contain users with usernames "john_doe", "jane_smith", "rohan_sarkar"

  Scenario: Delete a user
    Given the API is available
    When I delete user with ID "3"
    Then the response status should be 200
    And the response should contain a success message

  Scenario: Update user details
    Given the API is available
    When I update user with ID "1" to have username "not_rohan" and email "notrohan@example.com"
    Then the response status should be 200
    And the response should contain a success message and updated user details

  Scenario: Get rental details
    Given the API is available
    When I request rental details for rental ID "1"
    Then the response status should be 200
    And the response should contain userId "1", movieId "2", and rentalDate "2023-09-01"