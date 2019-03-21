package CodeImplementationDemos.oopsConceptForCar;



public class Encapsulation {
	private String carName;

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

}

class Car1 {
	public static void main(String[] args) {
		Encapsulation c = new Encapsulation();
		c.setCarName("alto");
		System.out.println("car name is:-"+c.getCarName());

	}

}
