package listener;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import pages.BasePage;

public class Listener extends BasePage implements ITestListener {

	@Override
	public void onFinish(ITestContext result) {
	}

	public void onStart(ITestContext result) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	/**
	 * Method to call screenshot method after test failure
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getName().toString().trim();
		System.out.println("methodName:::" + methodName);
		try {
			takeScreenShot(methodName);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {

	}

	public void onTestStart(ITestResult result) {

	}

	public void onTestSuccess(ITestResult result) {
	}

}
