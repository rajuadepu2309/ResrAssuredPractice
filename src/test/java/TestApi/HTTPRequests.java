package TestApi;
import org.testng.annotations.*;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;


public class HTTPRequests {
	int id;
	
	
	//Test case 1
	@Test(priority = 1)
	void getUsers() {
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();	
	}
	
	
	//Test case 2
	@Test(priority = 2)
	void PostUser() {
		
		HashMap data=new HashMap();
		data.put("name","john");
		data.put("job","Learner");
		
		id = given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
			
		//.then()
			//.statusCode(201)
			//.log().all();
	}
	
	
	//Test case 3
	@Test(priority = 3, dependsOnMethods= ("PostUser"))
	
	void UpdateUser() {
		
		HashMap data=new HashMap();
		data.put("name","Daniel");
		data.put("job","Cricketer");
		
		given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.put("https://reqres.in/api/users/"+id)
			
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority = 4)
	
	void DeleteUser() {
		
		given()
			.contentType("application/json")
			
		.when()
			.delete("https://reqres.in/api/users/"+id)
			
		.then()
			.statusCode(204)
			.log().all();
	}

}
