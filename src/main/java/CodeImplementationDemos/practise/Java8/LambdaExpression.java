package CodeImplementationDemos.practise.Java8;

interface Operation {
	void operate(int x, int y);
}

class LambdaExpression {

	public static void main(String[] args) {

		Operation add = (int x, int y) -> System.out.println("Addition is:-" + (x + y));
		add.operate(50, 6);

		Operation sub = (int x, int y) -> System.out.println("Subtraction is:-" + (x - y));
		sub.operate(50, 6);

		Operation mul = (int x, int y) -> System.out.println("Multiplication is:-" + (x * y));
		mul.operate(50, 6);

		Operation div = (int x, int y) -> System.out.println("Division is:-" + (x / y));
		div.operate(50, 6);
	}

}
