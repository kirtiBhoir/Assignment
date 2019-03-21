package CodeImplementationDemos.practise;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApiAutomationPost {
	@Test
	public void postAPI() {

		//using pojo
		RestAssured.baseURI = "https://reqres.in";
		Employee emp =new Employee("kirti","tester");

		Response response1 = RestAssured.given().body(emp).when().post("/api/users").then()
				.statusCode(201).extract().response();

		String res = response1.asString();

		System.out.println("Response: " + res);

		JsonPath jsonPath = response1.jsonPath();

		System.out.println(jsonPath.get("id").toString());
		

	}




}
