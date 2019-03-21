package CodeImplementationDemos.spring.dependancyInjection;

public class Book {

	private int bookId;
	private String bookName;

	public Book() {
		System.out.println("In Default Book Constructor");
	}

	public Book(int bookId) {
		this.bookId = bookId;
	}

	public Book(String bookName) {
		this.bookName = bookName;
	}

	public Book(int bookId, String bookName) {
		this.bookId = bookId;
		this.bookName = bookName;
	}

	void displayBook() {
		System.out.println("book id:--" + bookId);
		System.out.println("book name:--" + bookName);
	}

}
