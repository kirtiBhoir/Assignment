package CodeImplementationDemos.restAutomation;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class RestPutUsingPojo {

	public static Map<String, String> map = new HashMap<String, String>();
	UserPojo userPojo = new UserPojo();

	@BeforeTest
	public void putdata() {

		userPojo.setBody("I am testing REST api with REST-Assured and sending a PUT request.");
		userPojo.setId(19);
		userPojo.setUserID(2);
		userPojo.setTitle("this is projectdebug.com");
		userPojo.setPutId("/100");
		
		RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
		RestAssured.basePath = "/posts/";
	}

	@Test
	public void testPUT() {
		given().contentType("application/json").body(userPojo).when().put(userPojo.getPutId()).then().statusCode(200)
				.and().body("title", equalTo("this is projectdebug.com"));
		
		System.out.println(userPojo.getId());
		System.out.println(userPojo.getUserID());
		System.out.println(userPojo.getTitle());
		System.out.println(userPojo.getBody());
		
		Response response = RestAssured.given().body(userPojo).when().put("/100").then().extract().response();
		System.out.println(response.getStatusCode());
		System.out.println(response.jsonPath());
		System.out.println(response.asString());
		
		JsonPath jsonPath = response.jsonPath();
		System.out.println("return id:" + jsonPath.getString("id"));
		System.out.println("put id:-" + userPojo.getPutId().replaceAll("/", ""));
		
		Assert.assertTrue(userPojo.getPutId().contains(jsonPath.getString("id")));
		Assert.assertTrue(userPojo.getPutId().replaceAll("/", "").equalsIgnoreCase(jsonPath.getString("id")));

	}
}
