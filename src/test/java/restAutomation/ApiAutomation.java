package restAutomation;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApiAutomation {
	@Test
	public void postAPI() {

		RestAssured.baseURI = "https://reqres.in";

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("name", "kirti");
		jsonObject.put("job", "tester");

		Response response = RestAssured.given().body(jsonObject.toJSONString()).when().post("/api/users").then()
				.statusCode(201).extract().response();

		String res = response.asString();

		System.out.println("Response: " + res);

		JsonPath jsonPath = response.jsonPath();

		System.out.println(jsonPath.get("id").toString());
	}

}
