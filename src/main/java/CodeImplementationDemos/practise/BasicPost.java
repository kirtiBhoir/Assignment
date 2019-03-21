package CodeImplementationDemos.practise;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BasicPost {

	
	
	@Test
	public void testPostApi(){
		//provide base uri
		
		RestAssured.baseURI ="https://reqres.in";
		//create employee class object
		Employee emp = new Employee("kirti bhoir","tester");
		Response response = RestAssured.given().body(emp).post("/api/users").then().statusCode(201)
				.extract().response();
		System.out.println("response is "+response.getBody().asString());
		JsonPath jsonPath = response.jsonPath();
		System.out.println("id:"+jsonPath.getString("id"));
	}
}
