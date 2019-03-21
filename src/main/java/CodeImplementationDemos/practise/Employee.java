package CodeImplementationDemos.practise;

public class Employee {

	public String name;
	public String job;
	
	public Employee(){
		
	}
	
	public Employee(String name, String job) {

		this.name = getName();
		this.job=getJob();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

}
