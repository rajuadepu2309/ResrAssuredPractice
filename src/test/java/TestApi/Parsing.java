package TestApi;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class Parsing {

    @Test
    void bodyParse() {
        // Sending GET request
        Response res = given()
                .contentType(ContentType.JSON)
                .when()
                	.get("http://localhost:3000/students");

        // Convert response to JSON Array (assuming response starts with [ ])
        JSONArray jo = new JSONArray(res.asString());

        boolean studentFound = false;

        // Iterate through student objects
        for (int i = 0; i < jo.length(); i++) {
            String studentName = jo.getJSONObject(i).getString("name");

            if (studentName.equals("Raju")) {
                studentFound = true;
                break;
            }
        }

        // Assert that the student "Raju" exists
        Assert.assertTrue(studentFound, "true");
    }
}