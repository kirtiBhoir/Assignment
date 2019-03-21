package CodeImplementationDemos.coreJavaAssignment;

import java.util.Arrays;
import java.util.Comparator;

class c1 implements Comparator<String> {

	public int compare(String strA, String strB) {
		return strB.compareTo(strA);
	}
}

public class ComparatorDemo {

	public static void main(String[] args) {

		String strs[] = { "i", "z", "u", "k", "p" };

		c1 rsc = new c1();

		Arrays.sort(strs, rsc);

		for (String s : strs)
			System.out.println(s + " ");

		Arrays.sort(strs);

		System.out.print("Sorted string");
		for (String s : strs)
			System.out.println(s + " ");
	}

}
