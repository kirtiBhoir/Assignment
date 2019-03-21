package CodeImplementationDemos.restAutomation;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestDeleteAutomation {
	@BeforeTest
	public void setup(){
		
		RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
		RestAssured.basePath = "/posts/";
	}
 
	@Test
	public void testDelete(){
	Response response= given().when().delete("/1")
		.then()
		.extract().response();
	
	JsonPath jsonPath = response.jsonPath();
	System.out.println("Json path:---"+jsonPath);
	
	
	System.out.println(response);
	System.out.println(response.asString());
	for(Header header : response.getHeaders()){
		System.out.println(header);
	}
	
	Assert.assertTrue(response.getHeaders().get("Expires").getValue().equals("-1"));
	
	System.out.println("Session id:--" +response.getSessionId());
	System.out.println("Status line:--" +response.getStatusCode());
	System.out.println("Status code:--" +response.getStatusLine());
	
//	given()
//		.when()
//		.delete("/1")
//		.then()
//		.header("Expires", equalTo("-1"));		
	}
}
