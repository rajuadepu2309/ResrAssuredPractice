package TestApi;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;

import java.util.Map;


public class CookiesDemo {
	
	//@Test
	
	void cookie() {
	
	given()
	
	.when()
		.get("https://www.google.com/")
		
	.then()
		.cookie("AEC", "AVcja2fvtG3CWmPVDi4sPkvQZl1dT4xIYVUGo6ay06QJB4yQ7cJhcdiZCqw");
		
	}
	
	//@Test
	
	void printCookies() {
	
	Response res = given()
	
	.when()
		.get("https://www.google.com/");
	
		//String cookie_value =res.getCookie("AEC");
		//System.out.println("Cookie name  ===>"+ cookie_value);
	
	
		Map<String, String> cookie_values = res.getCookies();
		
		for(String k: cookie_values.keySet()){
			
			String cookie_value = res.getCookie(k);
			System.out.println(k+"          "+cookie_value);
			
		}
	}
	
	@Test
	
	void cookies() {
		
		Response response = given()
			
				
		.when()
			.get("https://www.facebook.com/");
		
		Map<String, String> cookie_values = response.getCookies();
		
		System.out.println(cookie_values);
		
		/*for(String c: cookie_values.keySet()) {
			
			String cookie_value = response.getCookie(c);
			System.out.println(c+"         "+cookie_value);
		}*/
	}
}
