package TestApi;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class ParsingTypesOfResponse {

    @Test
    
    void Parse() {
    	
    	Response res = given()
    			
    			.when()
    				.get("http://localhost:3000/Book");
    	
    	JSONArray student = new JSONArray(res.asString());
    	boolean status = false;
    	for(int i=0; i<student.length(); i++) {
    		
    			JSONObject bookName = student.getJSONObject(i);
    			String bookTitle = bookName.getString("title");
    			
    			if(bookTitle.equals("beat saber")) {
    				status=true;
    				break;
    			}
    	}
    	
    	Assert.assertEquals(status, true);
    }
}
