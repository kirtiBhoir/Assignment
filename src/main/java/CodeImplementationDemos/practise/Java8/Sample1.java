package CodeImplementationDemos.practise.Java8;

class A{
	static int i=10;
	int j=20;
}
public class Sample1 extends A {

	public static void main(String args[]){
		A a =null;
		A a1= new A();
		A a2= new A();
		a1.i=20;
		System.out.println(a1.i);
		System.out.println(a2.i);
		//System.out.println(a.j);
		System.out.println(a.i);
		System.out.println(A.i);
		try{
			System.out.println(a.j);
		}catch(Exception ex){
			System.out.println("Null pointer exception");
			ex.printStackTrace();
		}
	}
}
