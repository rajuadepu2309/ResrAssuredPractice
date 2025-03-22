package TestApi;
import org.testng.annotations.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class TestAPI {
	
	
	@Test(priority = 1)
	
	void test() {
		
		given()
		
		.when()
			.get("https://restful-booker.herokuapp.com/booking")
			
		.then()
			.statusCode(200);
	}
	
	
	@Test(priority =2)
	
	void getId() {
		
		given()
		
		.when()
			.get("https://restful-booker.herokuapp.com/booking/1")
		.then()
			.log().body();
	}
	
	@Test(priority = 3)
	void post(String token) {
		
		
		Map<String, Object> data = new HashMap<>();
		data.put("firstname", "daniel");
		data.put("lastname", "kumar");
		data.put("totalprice", 533);
		data.put("depositpaid", true);
		
		Map<String, String> bookingDates = new HashMap<>();
		bookingDates.put("checkin", "2017-06-13");
        bookingDates.put("checkout", "2023-12-06");
		
        data.put("bookingdates", bookingDates);
		
		given()
		.header("Authorization","Bearer" + token)
		.contentType(ContentType.JSON)
		.body(data)
		
		.when()
			.post("https://restful-booker.herokuapp.com/booking")
		.then()
			.statusCode(201)
			.body("firstname",equalTo("daniel"))
			.body("lastname", equalTo("kumar"));
	}
}
