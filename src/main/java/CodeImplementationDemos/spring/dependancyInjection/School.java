package CodeImplementationDemos.spring.dependancyInjection;

public class School {

	private int schoolId;
	private String schoolName;

	public School() {
		System.out.println("In Default School Constructor");
	}

	public School(int schoolId) {
		this.schoolId = schoolId;
	}

	public School(String schoolName) {
		this.schoolName = schoolName;
	}

	public School(int schoolId, String schoolName) {
		this.schoolId = schoolId;
		this.schoolName = schoolName;
	}

	void displaySchool() {
		System.out.println("school id:--" + schoolId);
		System.out.println("school name:--" + schoolName);
	}

}
