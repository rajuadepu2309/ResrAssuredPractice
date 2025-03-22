package TestApi;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;
public class PostRequestUsingAuth {
	
	
	
	//@Test(priority = 1)
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
			.statusCode(200)
			.log().all();
		
		

	}
		@Test
		void PutRequest() {
		
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
				.cookie("token", "79dfd3281e865c3")
				.contentType("application/JSON")
				.body(bookingDetails)
			.when()
				.patch("https://restful-booker.herokuapp.com/booking/1")
			
			.then()
				.log().all();
		
		

	}
}
