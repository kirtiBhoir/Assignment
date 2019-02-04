package coreJavaAssignment;

interface I1 {
	void show();
}

public class MethodReference {
	public static void showData() {
		System.out.println("Hello, this is static method.");
	}

	public static void main(String[] args) {
		I1 i1 = MethodReference::showData;
		i1.show();
	}

}
