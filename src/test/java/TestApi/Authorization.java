package TestApi;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class Authorization {
    public static void main(String[] args) {
        // Set the Base URI
        

        // Define the request body
        String requestBody = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        // Send the POST request to generate token
        Response response = given()
            .contentType(ContentType.JSON) // Set content type to JSON
            .body(requestBody) // Attach request payload
        .when()
            .post("https://restful-booker.herokuapp.com/auth") // Endpoint to generate token
        .then()
            .statusCode(200) // Validate status code
            .extract().response(); // Extract response

        // Extract token from the response
        String token = response.jsonPath().getString("token");

        // Print the token
        System.out.println("Generated Token: " + token);
    }
}
