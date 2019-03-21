package CodeImplementationDemos.restAutomation;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class StudentStub {
	
	public StudentStub() {		
	}
	
	public void createStudentStub() {
		
		stubFor(post(urlEqualTo("/studentstub"))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", "text/html")
						.withBody("Student has been added")));
		
		
	}
}
