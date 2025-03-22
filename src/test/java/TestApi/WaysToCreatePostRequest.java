package TestApi;
import org.json.JSONObject;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.*;


public class WaysToCreatePostRequest {
	//1. by using HashMap to create request body
	
	//@Test
	void postUsingHashMap() {
		
		HashMap data=new HashMap();
		data.put("name", "john");
		data.put("age", 25);
		data.put("grade", "12th");
		
		String subjectsArr[] = {"Biology", "Chemistry", "English"};
		data.put("subjects", subjectsArr);
		
		given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post("http://localhost:3000/students")
			
		
		.then()
			.statusCode(201)
			.body("name",equalTo("john"))
			.body("age", equalTo(25))
			.log().all();
			
	}
	
	
	//2. creating request body by using JSON library
	@Test
	
		void postUsingJSONLibrary() {
			
			PojoClass data= new PojoClass();
			data.setName("john");
			data.setGrade("12th");
			data.setAge(26);
			String subjectsArr[] = {"Biology", "Chemistry", "English"};
			data.setSubjects(subjectsArr);
			
			
			given()
				.contentType("application/json")
				.body(data)
				
			.when()
				.post("http://localhost:3000/students")
				
			
			.then()
				.statusCode(201)
				.body("name",equalTo("john"))
				.body("age", equalTo(26))
				.log().all();
	}
		
		
}
