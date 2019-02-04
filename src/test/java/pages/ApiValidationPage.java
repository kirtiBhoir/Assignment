package pages;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import com.cucumber.listener.Reporter;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@Component
public class ApiValidationPage extends BasePage {
	static Response response;
	static String responseBody = "";

	static String apiEndPoint = "";
	static String serverName = "";
	static String contentLength = "";
	static String connectionType = "";
	static String contentEncoding = "";
	static String citySymbol = "";
	static String cityAirport = "";
	static Object obj;
	static JSONObject jsonObj;
	private static String jsonFilepath = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\data.json";

	/**
	 * Method to fetch response for city name
	 * 
	 * @param cityName
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void fetchDetailsForCity(String cityName) throws FileNotFoundException, IOException, ParseException {
		Reporter.addStepLog("User wants to get details for " + cityName);
		RestAssured.baseURI = getValue("baseUrl");
		RequestSpecification httpRequest = RestAssured.given();
		obj = new JSONParser().parse(new FileReader(jsonFilepath));
		jsonObj = (JSONObject) obj;
		apiEndPoint = (String) jsonObj.get("searchApiEndPoint");
		response = httpRequest.request(Method.GET, apiEndPoint, cityName);
		Reporter.addStepLog("Response is  " + response);
		responseBody = response.getBody().asString();
		Reporter.addStepLog("Response body  is  " + responseBody);
	}

	/**
	 * Method to validate status details from response
	 */
	public void validateStatusDetails() {
		Assert.assertEquals(response.getStatusCode(), 200, "Correct status code returned");
		Reporter.addStepLog("Response status code expected is" + 200 + "Received is " + response.getStatusCode());
		Assert.assertTrue(response.getStatusLine().contains("OK"));
		Reporter.addStepLog("Response status Line expected is" + "OK" + "Received is " + response.getStatusLine());
	}

	/**
	 * Method to validate header details from response
	 */
	public void validateHeader() {
		Headers headerList = response.getHeaders();
		System.out.println(":::::::::::::::::::::::::header list:::::::::::::::::::::::::");
		headerList.forEach(element -> System.out.println(element));
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		contentLength = (String) jsonObj.get("contentLength");
		serverName = (String) jsonObj.get("serverName");
		connectionType = (String) jsonObj.get("connectionType");
		contentEncoding = (String) jsonObj.get("contentEncoding");

		Assert.assertTrue(response.getHeaders().get("content-length").getValue().equals(contentLength));
		Reporter.addStepLog("Expected content length is" + contentLength + "\n" + "Received is "
				+ response.getHeaders().get("content-length").getValue());

		Assert.assertTrue(response.getHeaders().get("server").getValue().equalsIgnoreCase(serverName));
		Reporter.addStepLog("Expected server name is" + serverName + "\n" + "Received is "
				+ response.getHeaders().get("server").getValue());

		Assert.assertTrue(response.getHeaders().get("connection").getValue().equalsIgnoreCase(connectionType));
		Reporter.addStepLog("Expected connection type is" + connectionType + "\n" + "Received is "
				+ response.getHeaders().get("connection").getValue());

		Assert.assertTrue(response.getHeaders().get("content-encoding").getValue().equalsIgnoreCase(contentEncoding));
		Reporter.addStepLog("Expected content encoding is" + contentEncoding + "\n" + "Received is "
				+ response.getHeaders().get("content-encoding"));
	}

	/**
	 * Method to validate body from response
	 * 
	 * @param cityName
	 */
	public void validateCityDetails(String cityName) {

		JsonPath jsonPathObj = response.jsonPath();
		Reporter.addStepLog("Fetching details for " + cityName);
		Assert.assertTrue(responseBody.toLowerCase().contains(cityName));
		Reporter.addStepLog("json path object " + jsonPathObj);
		String citySymbol = jsonPathObj.getString("k");
		String cityAirport = jsonPathObj.getString("v");

		Reporter.addStepLog("city symbol is " + citySymbol);
		Reporter.addStepLog("city airport name is " + cityAirport);
		citySymbol = (String) jsonObj.get("citySymbol");
		Assert.assertEquals(citySymbol, citySymbol, "Correct city name received in the Response");
		Assert.assertEquals(jsonPathObj.get("k").toString(), citySymbol);

	}

}
