package CodeImplementationDemos.practise;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Method;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class Basic {

	@Test
	public void testApi() {
		
		RestAssured.defaultParser= Parser.JSON;
		RestAssured.baseURI="http://www.cleartrip.com";
		RequestSpecification httpRequest = RestAssured.given();
		
		Response response1 = httpRequest.get("/places/airports/search?string=mumbai");
		System.out.println(response1);
		
		Response response2 =httpRequest.request(Method.GET, "/places/airports/search?string=mumbai");
		System.out.println(response2);
		
		//validate status
		
		Assert.assertEquals(response2.getStatusCode()==200,true ,"Correct status code returned");
		Assert.assertEquals(response2.getStatusLine().contains("OK"), true,"staus line correct");
		
		//validate headers
		
		for(Header header:response1.getHeaders()){
			System.out.println("header name:" +header);
		}
		
		Assert.assertEquals(response1.getHeaders().get("content-length").getValue().equalsIgnoreCase("83"),true,"correct content length");
		
		//validate body
		
		System.out.println("response body:"+response1.getBody().asString());
		JsonPath jsonPath = response1.jsonPath();
		System.out.println("json path :--"+jsonPath);
		
		String cityName =jsonPath.get("k").toString().replaceAll("\\[", "").replaceAll("\\]","");
		String airportName =jsonPath.get("v").toString().replaceAll("\\[", "").replaceAll("\\]","");
		
				
		System.out.println("city name :--"+cityName);
		System.out.println("airport  name :--"+airportName);
		
		//validate response body using POJO
//
//		ResponseBody body = response1.getBody();
//		ApiResponse responseBody = body.as(ApiResponse.class);
//		
//		Assert.assertEquals("BOM", responseBody.k);
//		Assert.assertEquals("Mumbai, IN - Chatrapati Shivaji Airport (BOM)", responseBody.v);
		
	}
}
