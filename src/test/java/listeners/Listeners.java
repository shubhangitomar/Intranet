package listeners;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import tests.LoginTest;

public class Listeners extends LoginTest implements IInvokedMethodListener, ITestListener, ISuiteListener {
	WebDriver driver;

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// Before every method in the Test Class
		System.out.println("beforeInvocation: " + testResult.getTestClass().getName() + " => "
				+ method.getTestMethod().getMethodName());
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		// After every method in the Test Class
		System.out.println("afterInvocation: " + testResult.getTestClass().getName() + " => "
				+ method.getTestMethod().getMethodName());
	}

	public void onTestStart(ITestResult result) {
		// When test method starts
		System.out.println("onTestStart -> Test Name: " + result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		// If test method is successful
		System.out.println("onTestSuccess -> Test Name: " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		// If test method is failed
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.println("Failed: " + result.getMethod().getMethodName());
			String filename = new Date().getTime() + ".png";
			String directory = System.getProperty("user.dir") + "//screenshots//";
			File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(sourceFile, new File(directory + filename));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void onTestSkipped(ITestResult result) {
		// If test method is failed
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// Ignore this
	}

	public void onStart(ITestContext context) {
		// Before <test> tag of xml file
		System.out.println("onStart -> Test Tag Name: " + context.getName());
		ITestNGMethod methods[] = context.getAllTestMethods();
		System.out.println("These methods will be executed in this test tag:");
		for (ITestNGMethod method : methods) {
			System.out.println(method.getMethodName());
		}
	}

	public void onFinish(ITestContext context) {
		// After <test> tag of xml file
		System.out.println("onFinish -> Test Tag Name: " + context.getName());
	}

	public void onStart(ISuite suite) {
		// When <suite> tag starts
		System.out.println("onStart: before suite starts");
	}

	public void onFinish(ISuite suite) {
		// When <suite> tag completes
		System.out.println("onFinish: after suite completes");
	}

}
