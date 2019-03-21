package CodeImplementationDemos.spring.dependancyInjection;

public class Teacher {

	private int teacherId;
	private String teacherName;
	private String subject;
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	void displayTeacher(){
		System.out.println("teacher id is:"+teacherId);
		System.out.println("teacher name is:"+teacherName);
		System.out.println("subject name is:"+subject);
	}

}
