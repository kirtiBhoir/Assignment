package coreJavaAssignment;

//A simple program to Test Interface default 
//methods in java 
interface TestInterface 
{ 
 // abstract method 
 public void square(int a); 

 // default method 
 default void show() 
 { 
   System.out.println("Default Method Executed"); 
 } 
 
 static void method1(){
	 
	 System.out.println("Static Method Executed"); 
 }
 
} 

public class InterfaceChangesJava8 implements TestInterface{

	public static void main(String[] args) {
		InterfaceChangesJava8 d = new InterfaceChangesJava8(); 
	        d.square(4); 
	  
	        // default method executed 
	        d.show(); 
	        
	     // Static method executed 
	        TestInterface.method1(); 
		
	}

	@Override
	// implementation of square abstract method 
    public void square(int a) 
    { 
        System.out.println(a*a); 
    } 
  
}
