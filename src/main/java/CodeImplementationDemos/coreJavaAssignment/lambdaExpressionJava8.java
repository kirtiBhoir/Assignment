package CodeImplementationDemos.coreJavaAssignment;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class lambdaExpressionJava8 {
	/*
	 * Functional interface
	 */
	interface ConvertDataType {
		int operation(String input);
	}

	/**
	 * Implementation
	 * 
	 * @param data
	 * @param obj
	 * @return
	 */

	// int operate(String data, ConvertDataType obj) {
	// return obj.operation(data);
	// }

	interface calculate {
		int operation(int input1, int input2);
	}

	/**
	 * Method to demonstrate for each
	 */
	public void forEachDemo() {
		int i;
		int count = 1;
		List<Integer> myList = new ArrayList<Integer>();
		for (i = 0; i <= 5; i++) {
			myList.add(count++);
		}
		myList.forEach(element -> System.out.println(element));
		System.out.println("List size:--" + myList.size());

		List<String> nameList = Arrays.asList("kirti", "priti", "abc");
		List<String> expectedList = nameList.stream().filter(element -> element.contains("ti"))
				.collect(Collectors.toList());
		expectedList.forEach(name -> System.out.println(name));
		expectedList.stream().forEach(title -> assertTrue(title.contains("ti")));

	}

	/**
	 * Method to demonstarte functional Interface with lambda expression
	 */
	public void FunctionalInterfaceWithLambdaExpression() {
		String operand1 = "1";
		String operand2 = "2";

		int sumResult;
		ConvertDataType convertToInteger = (String value) -> Integer.parseInt(value);
		sumResult = convertToInteger.operation("1") + convertToInteger.operation("2");
		System.out.println("Addition is" + sumResult);

	}

	/**
	 * Method to perform all mathematical operation using fuctional interface
	 * 
	 * @param args
	 */

	public void doCalculation() {

		calculate addition = (int value1, int value2) -> (value1 + value2);
		calculate subtraction = (int value1, int value2) -> (value1 - value2);
		calculate multiplication = (int value1, int value2) -> (value1 * value2);
		calculate division = (int value1, int value2) -> (value1 / value2);

		System.out.println("addition is" + addition.operation(2, 3));
		System.out.println("addition is" + subtraction.operation(2, 3));
		System.out.println("addition is" + multiplication.operation(2, 3));
		System.out.println("addition is" + division.operation(2, 3));

	}

	/**
	 * Method to demonstrate java 8 stream api
	 * 
	 * @param args
	 */

	public void UseOfStreamApi() {
		List<Integer> numberList = Arrays.asList(1, 5, 2, 4, 6, 7);
		List<Integer> squareList = numberList.stream().map(x -> x * x).collect(Collectors.toList());
		List<Integer> cubeList = numberList.stream().map(x -> x * x * x).collect(Collectors.toList());
		numberList.forEach(element -> System.out.print(element));
		System.out.println("-------------------");
		squareList.forEach(element -> System.out.print(element));
		System.out.println("-------------------");
		cubeList.forEach(element -> System.out.print(element));

		System.out.println("*************************************");
		List<Integer> show = numberList.stream().sorted().collect(Collectors.toList());
		System.out.println(show);

	}

	public static void main(String args[]) {
		lambdaExpressionJava8 s = new lambdaExpressionJava8();
		s.forEachDemo();
		s.FunctionalInterfaceWithLambdaExpression();
		s.doCalculation();
		s.UseOfStreamApi();

	}
}