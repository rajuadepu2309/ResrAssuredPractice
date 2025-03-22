package TestApi;

import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class HeadersDemo {
	
	@Test
	void header() {
	given()
	
	.when()
		.get("https://www.facebook.com/")
		
	.then()
		.log().cookies();
	}
}
