package pages;

import org.springframework.stereotype.Component;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@Component
public class ApiValidationPage extends BasePage {
	static Response response;
	static String responseBody = "";

	/**
	 * Method to fetch response for city name
	 * 
	 * @param cityName
	 */
	public void fetchDetailsForCity(String cityName) {
		System.out.println("city name" + cityName);
		RestAssured.baseURI = getValue("baseUrl");
		RequestSpecification httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, getValue("searchApiEndPoint"), cityName);
		responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
	}

	/**
	 * Method to validate status details from response
	 */
	public void validateStatusDetails() {
		Assert.assertEquals(response.getStatusCode(), 200, "Correct status code returned");
		Assert.assertTrue(response.getStatusLine().contains("OK"));
	}

	/**
	 * Method to validate header details from response
	 */
	public void validateHeader() {
		Assert.assertTrue(response.getHeaders().get("content-length").getValue().equals(getValue("contentLength")));
		Assert.assertTrue(response.getHeaders().get("server").getValue().equalsIgnoreCase(getValue("serverName")));
		Assert.assertTrue(
				response.getHeaders().get("connection").getValue().equalsIgnoreCase(getValue("connectionType")));
		Assert.assertTrue(
				response.getHeaders().get("content-encoding").getValue().equalsIgnoreCase(getValue("contentEncoding")));
	}

	/**
	 * Method to validate body from response
	 * 
	 * @param cityName
	 */
	public void validateCityDetails(String cityName) {

		JsonPath jsonPathEvaluator = response.jsonPath();
		System.out.println("Fetching details for :-" + cityName);
		Assert.assertTrue(responseBody.toLowerCase().contains(cityName));
		System.out.println(jsonPathEvaluator);
		String citySymbol = jsonPathEvaluator.getString("k");
		String cityAirport = jsonPathEvaluator.getString("v");
		System.out.println("city symbol---" + citySymbol);
		System.out.println("city name---" + cityAirport);
		Assert.assertEquals(citySymbol, getValue("citySymbol"), "Correct city name received in the Response");
		Assert.assertEquals(jsonPathEvaluator.get("k").toString(), getValue("citySymbol"));
		Assert.assertTrue(jsonPathEvaluator.get("v").toString().contains(getValue("cityAirport")));
	}

}
