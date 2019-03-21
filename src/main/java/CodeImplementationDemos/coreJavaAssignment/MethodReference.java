package CodeImplementationDemos.coreJavaAssignment;

interface I1 {
	void show();
}

public class MethodReference {
	static void showData() {
		System.out.println("Hello, this is static method.");
	}

	public static void main(String[] args) {
		I1 i1 = MethodReference::showData;
		System.out.println("before call to interface");
		i1.show();
	}

}
