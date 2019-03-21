package CodeImplementationDemos.practise.Java8;

interface functional {
	void show();
}

public class FunctionalInterface {

	public static void showData() {
		System.out.println("hi static method");
	}

	public static void main(String[] args) {
		functional i1 = FunctionalInterface::showData;
		i1.show();
	}

}
