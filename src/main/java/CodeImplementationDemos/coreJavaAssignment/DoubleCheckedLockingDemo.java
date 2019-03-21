package CodeImplementationDemos.coreJavaAssignment;

class DoubleCheckedLocking {

	private static DoubleCheckedLocking obj;

	private DoubleCheckedLocking() {

		System.out.println("Instance created");
	}

	public static DoubleCheckedLocking getInstance() {

		// double checked locking
		if (obj == null) {

			synchronized (DoubleCheckedLocking.class) {

				if (obj == null) {
					obj = new DoubleCheckedLocking();
				}
			}

		}

		return obj;
	}

}

public class DoubleCheckedLockingDemo {

	public static void main(String[] args) {

		Thread t1 = new Thread(new Runnable() {

			public void run() {

				DoubleCheckedLocking obj1 = DoubleCheckedLocking.getInstance();
			}
		});

		Thread t2 = new Thread(new Runnable() {

			public void run() {

				DoubleCheckedLocking obj1 = DoubleCheckedLocking.getInstance();
			}
		});

		t1.start();
		t2.start();
	}

}

// 3 - What is Object-Level Locking and Class-Level Locking
// thread count
// extent report
// pojo internal working