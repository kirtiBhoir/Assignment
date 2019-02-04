package restAutomation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class CarTests {
	
	Student student = new Student(1,"abc pqr",19);
	
	StudentStub studentStub = new StudentStub();
	
	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8082);
	
	@Test
	public void testCarSerialization() {
		
		studentStub.createStudentStub();
		
		given().
			contentType("application/json").
			body(student).
			and().
			log().
			body().
		when().
			post("http://localhost:8082/studentstub").
		then().
			assertThat().
			body(equalTo("Student has been added"));
	}

}
