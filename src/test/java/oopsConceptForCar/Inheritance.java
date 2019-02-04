package oopsConceptForCar;


class SuperClassA {

	double carPrice=838237.00;
	public void displayCarPrice(){
		System.out.println("car price is---"+carPrice);
	}
	
}

class SubClassB extends SuperClassA{
	String carName="alto";
	public void displayCarName(){
		System.out.println("car name is_--"+carName);
	}
	
}
public class Inheritance {
		public static void main(String args[]){
			SubClassB a = new SubClassB();
			
			a.displayCarName();
			a.displayCarPrice();
		}
	}