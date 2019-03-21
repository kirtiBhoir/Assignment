package CodeImplementationDemos.practise;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class APISample {

	static Response response;
	static ResponseBody responseBody;

	@Test
	public void validateStatus() {
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		response = httpRequest.get("/Hyderabad");

		// Get the status code from the Response. In case of
		// a successfull interaction with the web service, we
		// should get a status code of 200.
		int statusCode = response.getStatusCode();

		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode /* actual value */,
				200 /* expected value */, "Correct status code returned");

		// Get the status line from the Response and store it in a variable
		// called statusLine
		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine /* actual value */,
				"HTTP/1.1 200 OK" /* expected value */, "Correct status code returned");

	}

	@Test(enabled=false)
	public void validateHeaders() {
		Headers allHeaders = response.headers();

		// Iterate over all the Headers
		for (Header header : allHeaders) {
			System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
		}

		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType /* actual value */,
				"application/json" /* expected value */);

		// Reader header of a give name. In this line we will get
		// Header named Server
		String serverType = response.header("Server");
		Assert.assertEquals(serverType /* actual value */,
				"nginx/1.12.1" /* expected value */);

		// Reader header of a give name. In this line we will get
		// Header named Content-Encoding
		String contentEncoding = response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding /* actual value */,
				"gzip" /* expected value */);

	}

	@Test(enabled=false)
	public void validateBody() {
		// Retrieve the body of the Response
		responseBody = response.getBody();

		// By using the ResponseBody.asString() method, we can convert the body
		// into the string representation.
		String bodyAsString = responseBody.asString();
		System.out.println("Response Body is: " + responseBody.asString());
		Assert.assertEquals(
				bodyAsString.contains("Hyderabad") /* Expected value */,
				true /* Actual Value */, "Response body contains Hyderabad");

		// convert the body into lower case and then do a comparison to ignore
		// casing.

		Assert.assertEquals(
				bodyAsString.toLowerCase()
						.contains("hyderabad") /* Expected value */,
				true /* Actual Value */, "Response body contains Hyderabad");

		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Then simply query the JsonPath object to get a String value of the
		// node
		// specified by JsonPath: City (Note: You should not put $. in the Java
		// code)
		String city = jsonPathEvaluator.get("City");

		// Let us print the city variable to see what we got
		System.out.println("City received from Response " + city);

		// Validate the response
		Assert.assertEquals(city, "Hyderabad", "Correct city name received in the Response");

		System.out.println("City received from Response " + jsonPathEvaluator.get("City"));

		// Print the temperature node
		System.out.println("Temperature received from Response " + jsonPathEvaluator.get("Temperature"));

		// Print the humidity node
		System.out.println("Humidity received from Response " + jsonPathEvaluator.get("Humidity"));

		// Print weather description
		System.out.println("Weather description received from Response " + jsonPathEvaluator.get("Weather"));

		// Print Wind Speed
		System.out.println("City received from Response " + jsonPathEvaluator.get("WindSpeed"));

		// Print Wind Direction Degree
		System.out.println("City received from Response " + jsonPathEvaluator.get("WindDirectionDegree"));
	}

}
