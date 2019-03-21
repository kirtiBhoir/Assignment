package CodeImplementationDemos.oopsConceptForCar;

public class Polymorphism {

	public void display(int carId){
		System.out.println("car id is --"+carId);
	}
	
	public void display(String carName){
		System.out.println("car name is --"+carName);
	}
	
	public void display(double carPrice,String carColor){
		System.out.println("car colour is --"+carColor);
		System.out.println("car price is --"+carPrice);
	}
	
	
	public static void main(String[] args) {
		Polymorphism p = new Polymorphism();
		p.display(1);
		p.display("alto");
		p.display(244934.09, "black");
	}

}
