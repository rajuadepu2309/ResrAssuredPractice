package TestApi;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class TypesOfQueryAndPathParameters {
	
	//http://localhost:3000/students?id=3
	
	@Test
	
	void parameters() {
		
		given()
			.pathParam("path", "students")
			.queryParam("id", 2)
			
		.when()
			.get("http://localhost:3000/{path}")
		
		.then()
			.statusCode(200)
			.log().all();
	}
}
