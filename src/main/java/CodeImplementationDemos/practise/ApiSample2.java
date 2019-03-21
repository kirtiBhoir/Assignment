package CodeImplementationDemos.practise;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiSample2 {
	static Response response;
	static String responseBody = "";
	static String cityName = "mumbai";

	static String serverName = "Cleartrip Application Server V 4.0.2";
	static String contentLength = "83";
	static String connectionType = "keep-alive";
	static String contentEncoding = "gzip";
	static JSONObject jsonObj;

	static String ExpectedCitySymbol = "[BOM]";
	static String ExpectedAirportName = "[Mumbai, IN - Chatrapati Shivaji Airport (BOM)]";

	/**
	 * Method to fetch response for city name
	 * 
	 * @param cityName
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	@Test(priority = 1)
	public void getCityDetails() throws FileNotFoundException, IOException, ParseException {
		System.out.println("User wants to fetch details for:---" + cityName);
		RestAssured.baseURI = "https://www.cleartrip.com/";
		RequestSpecification httpRequest = RestAssured.given();

		response = httpRequest.request(Method.GET, "/places/airports/search?string={city}", "mumbai");
		System.out.println("Response is  " + response);
		responseBody = response.getBody().asString();
		System.out.println("Response body  is  " + responseBody);
	}

	/**
	 * Method to validate status details from response
	 */
	@Test(priority = 2)
	public void validateStatusDetails() {
		Assert.assertEquals(response.getStatusCode(), 200, "Correct status code returned");
		System.out.println("Response status code expected is" + 200 + "Received is " + response.getStatusCode());
		Assert.assertTrue(response.getStatusLine().contains("OK"));
		System.out.println("Response status Line expected is" + "OK" + "Received is " + response.getStatusLine());
	}

	/**
	 * Method to validate header details from response
	 */
	@Test(priority = 3)
	public void validateHeader() {
		Headers headerList = response.getHeaders();
		System.out.println(":::::::::::::::::::::::::header list:::::::::::::::::::::::::");
		headerList.forEach(element -> System.out.println(element));
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");

		Assert.assertTrue(response.getHeaders().get("content-length").getValue().equals(contentLength));
		System.out.println("Expected content length is" + contentLength + "\n" + "Received is "
				+ response.getHeaders().get("content-length").getValue());

		Assert.assertTrue(response.getHeaders().get("server").getValue().equalsIgnoreCase(serverName));
		System.out.println("Expected server name is" + serverName + "\n" + "Received is "
				+ response.getHeaders().get("server").getValue());

		Assert.assertTrue(response.getHeaders().get("connection").getValue().equalsIgnoreCase(connectionType));
		System.out.println("Expected connection type is" + connectionType + "\n" + "Received is "
				+ response.getHeaders().get("connection").getValue());

		Assert.assertTrue(response.getHeaders().get("content-encoding").getValue().equalsIgnoreCase(contentEncoding));
		System.out.println("Expected content encoding is" + contentEncoding + "\n" + "Received is "
				+ response.getHeaders().get("content-encoding"));
	}

	/**
	 * Method to validate body from response
	 * 
	 * @param cityName
	 */
	@Test(priority = 4)
	public void validateCityDetails() {

		JsonPath jsonPathObj = response.jsonPath();
		System.out.println("Fetching details for " + cityName);
		Assert.assertTrue(responseBody.toLowerCase().contains(cityName));
		System.out.println("json path object " + jsonPathObj);
		String receivedCitySymbol = jsonPathObj.getString("k");
		String receivedAirportName = jsonPathObj.getString("v");

		System.out.println("received city symbol is " + receivedCitySymbol);
		System.out.println("received airport name is " + receivedAirportName);

		Assert.assertEquals(receivedCitySymbol, ExpectedCitySymbol, "Correct city name received in the Response");
		Assert.assertEquals(jsonPathObj.get("k").toString(), ExpectedCitySymbol);

		Assert.assertEquals(receivedAirportName, ExpectedAirportName, "Correct airport name received in the Response");
		Assert.assertEquals(jsonPathObj.get("v").toString(), ExpectedAirportName);

	}

}
