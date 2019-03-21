package CodeImplementationDemos.coreJavaAssignment;

interface Interface1 {

	public void print();
}

interface Interface2 {

	public void print();
}

public class MultipleInheritance implements Interface1, Interface2 {

	@Override
	public void print() {
		System.out.println("in method print");

	}

	public static void main(String[] args) {

		MultipleInheritance obj = new MultipleInheritance();
		obj.print();

	}

}
