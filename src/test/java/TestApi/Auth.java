package TestApi;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;


public class Auth {
	
	String url = "https://restful-booker.herokuapp.com";
	
	public String getToken() {
		
		String requestBody = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        // Send the POST request to generate token
        Response response = given()
            .contentType(ContentType.JSON) // Set content type to JSON
            .body(requestBody)
            .baseUri(url)// Attach request payload
        .when()
            .post(url + "/auth") // Endpoint to generate token
        .then()
            .statusCode(200) // Validate status code
            .extract().response(); // Extract response

        // Extract token from the response
        String token = response.jsonPath().getString("token");

        // Print the token
        System.out.println("Generated Token: " + token);

		
		return token;
		
	}
	
	
	
	@Test(priority = 1)
	void getDetailsById() {
		given()
		.contentType("application/JSON")
		.baseUri(url)
		.when()
			.get(url + "/booking/3")
		.then()
			.statusCode(200)
			.contentType("application/JSON")
			.log().all();
	}
	
		@Test(priority = 2)
		void PostRequest() {
		
		// Create a Map for booking dates
        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2018-03-01");
        bookingDates.put("checkout", "2019-01-03");

        // Create a Map for booking details
        Map<String, Object> bookingDetails = new HashMap<>();
        bookingDetails.put("firstname", "daniel");
        bookingDetails.put("lastname", "christian");
        bookingDetails.put("totalprice", 200);
        bookingDetails.put("depositpaid", true);
        bookingDetails.put("bookingdates", bookingDates);
        bookingDetails.put("additionalneeds", "lunch");

		given()
			.contentType("application/JSON")
			.body(bookingDetails)
		.when()
			.post("https://restful-booker.herokuapp.com/booking")
			
		.then()
			
			.log().all();
	}
		
		@Test(priority = 3)
		void PutRequest() {
		
			String token= getToken();
		// Create a Map for booking dates
			Map<String, String> bookingDates = new HashMap<>();
			bookingDates.put("checkin", "2018-03-01");
			bookingDates.put("checkout", "2019-01-03");

        // Create a Map for booking details
			Map<String, Object> bookingDetails = new HashMap<>();
			bookingDetails.put("firstname", "rishab");
			bookingDetails.put("lastname", "pant");
			bookingDetails.put("totalprice", 200);
			bookingDetails.put("depositpaid", true);
			bookingDetails.put("bookingdates", bookingDates);
			bookingDetails.put("additionalneeds", "dinner");

			given()
				//.auth().basic("Cookie", "abc123")
				.cookie("token", token)
				.contentType("application/JSON")
				.body(bookingDetails)
				.baseUri(url)
			.when()
				.patch(url +"/booking/1")
			
			.then()
				.log().all();

	}
}