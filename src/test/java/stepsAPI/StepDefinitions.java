package stepsAPI;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class StepDefinitions {

    private Response response;

    @Given("the API is available")
    public void the_api_is_available() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3000;
    }

    @When("I request all movies")
    public void i_request_all_movies() {
        response = given().when().get("/api/movies");
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain movies with titles {string}, {string}, {string}")
    public void the_response_should_contain_movies_with_titles(String title1, String title2, String title3) {
        response.then()
                .body("size()", is(3))
                .body("[0].title", equalTo(title1))
                .body("[1].title", equalTo(title2))
                .body("[2].title", equalTo(title3));
    }

    @When("I register a new user with username {string}, email {string}, and password {string}")
    public void i_register_a_new_user_with_username_email_and_password(String username, String email, String password) {
        String requestBody = String.format("{\"username\": \"%s\",\"email\": \"%s\",\"password\": \"%s\"}", username, email, password);
        response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/users/register");
    }

    @Then("the response should contain a success message and userId")
    public void the_response_should_contain_a_success_message_and_userId() {
        response.then()
                .body("message", equalTo("User registered successfully"))
                .body("userId", notNullValue());
    }

    @When("I log in with username {string} and password {string}")
    public void i_log_in_with_username_and_password(String username, String password) {
        String requestBody = String.format("{\"username\": \"%s\",\"password\": \"%s\"}", username, password);
        response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/users/login");
    }

    @Then("the response should contain a success message and token")
    public void the_response_should_contain_a_success_message_and_token() {
        response.then()
                .body("message", equalTo("User logged in successfully"))
                .body("token", notNullValue());
    }

    @When("I add a new movie with title {string}, director {string}, genre {string}, and release date {string}")
    public void i_add_a_new_movie_with_title_director_genre_and_release_date(String title, String director, String genre, String releaseDate) {
        String requestBody = String.format("{\"title\": \"%s\",\"director\": \"%s\",\"genre\": \"%s\",\"releaseDate\": \"%s\"}", title, director, genre, releaseDate);
        response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/movies");
    }

    @Then("the response should contain a success message and movieId")
    public void the_response_should_contain_a_success_message_and_movieId() {
        response.then()
                .body("message", equalTo("Movie added successfully"))
                .body("movieId", notNullValue());
    }

    @When("I search for movies with title {string}")
    public void i_search_for_movies_with_title(String title) {
        response = given()
                .queryParam("title", title)
                .when()
                .get("/api/movies/search");
    }

    @Then("the response should contain movies with title {string}")
    public void the_response_should_contain_movies_with_title(String title) {
        response.then()
                .body("movies.size()", greaterThan(0))
                .body("movies[0].title", equalTo(title));
    }

    @When("I filter movies by genre {string}")
    public void i_filter_movies_by_genre(String genre) {
        response = given()
                .queryParam("genre", genre)
                .when()
                .get("/api/movies/filter");
    }

    @Then("the response should contain movies with genre {string}")
    public void the_response_should_contain_movies_with_genre(String genre) {
        response.then()
                .body("movies.size()", greaterThan(0))
                .body("movies[0].genre", equalTo(genre));
    }

    @When("I rent a movie with userId {string} and movieId {string}")
    public void i_rent_a_movie_with_userId_and_movieId(String userId, String movieId) {
        String requestBody = String.format("{\"userId\": \"%s\",\"movieId\": \"%s\"}", userId, movieId);
        response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/rentals");
    }

    @Then("the response should contain a success message and rentalId")
    public void the_response_should_contain_a_success_message_and_rentalId() {
        response.then()
                .body("message", equalTo("Movie rented successfully"))
                .body("rentalId", notNullValue());
    }

    @When("I request user profiles")
    public void i_request_user_profiles() {
        response = given().when().get("/api/users");
    }

    @Then("the response should contain users with usernames {string}, {string}, {string}")
    public void the_response_should_contain_users_with_usernames(String username1, String username2, String username3) {
        response.then()
                .body("size()", is(3))
                .body("[0].username", equalTo(username1))
                .body("[1].username", equalTo(username2))
                .body("[2].username", equalTo(username3));
    }

    @When("I delete user with ID {string}")
    public void i_delete_user_with_ID(String userId) {
        response = given().when().delete("/users/" + userId);
    }

    @Then("the response should contain a success message")
    public void the_response_should_contain_a_success_message() {
        response.then()
                .body("message", equalTo("User account deleted successfully"));
    }

    @When("I update user with ID {string} to have username {string} and email {string}")
    public void i_update_user_with_ID_to_have_username_and_email(String userId, String username, String email) {
        String requestBody = String.format("{\"username\": \"%s\",\"email\": \"%s\"}", username, email);
        response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put("/users/" + userId);
    }

    @Then("the response should contain a success message and updated user details")
    public void the_response_should_contain_a_success_message_and_updated_user_details() {
        response.then()
                .body("message", equalTo("User profile updated successfully"))
                .body("user.username", equalTo("not_rohan"))
                .body("user.email", equalTo("notrohan@example.com"));
    }

    @When("I request rental details for rental ID {string}")
    public void i_request_rental_details_for_rental_ID(String rentalId) {
        response = given().when().get("/rentals/" + rentalId);
    }

    @Then("the response should contain userId {string}, movieId {string}, and rentalDate {string}")
    public void the_response_should_contain_userId_movieId_and_rentalDate(String userId, String movieId, String rentalDate) {
        response.then()
                .body("userId", equalTo(userId))
                .body("movieId", equalTo(movieId))
                .body("rentalDate", equalTo(rentalDate));
    }
}

