package CodeImplementationDemos.spring.dependancyInjection;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class MainClass {

	public static void main(String[] args) {
		Resource r = new ClassPathResource("applicationContext.xml");
		BeanFactory factory = new XmlBeanFactory(r);

		// constructor based
		System.out.println("___________________________________________");
		System.out.println("Constructor based");
		Book b = (Book) factory.getBean("book");
		b.displayBook();
		System.out.println("___________________________________________");

		System.out.println("___________________________________________");
		System.out.println("Constructor based");
		School s = (School) factory.getBean("school");
		s.displaySchool();
		System.out.println("___________________________________________");

		// setter getter based

		System.out.println("___________________________________________");
		System.out.println("setter getter based");
		Teacher t = (Teacher) factory.getBean("teacher");
		t.displayTeacher();
		System.out.println("___________________________________________");

	}
}
