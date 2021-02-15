package tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pages.Dashboard;
import pages.Page;
import utilities.ExcelUtility;
import utilities.location;

public class LoginTest {
	protected Page page;
	protected Dashboard dashboard;
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	SoftAssert softAssert;

	@BeforeClass(alwaysRun = true)
	@Parameters({ "url", "browser" })
	public void setup(String url, String browser) throws Exception {

		report = location.getInstance();
		test = report.startTest("Verify welcome Test");
		dashboard = new Dashboard(driver, test);

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		softAssert = new SoftAssert();
		test.log(LogStatus.INFO, "Browser Started.....");
		page = new Page(driver, test);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		test.log(LogStatus.INFO, "Browser Maximized....");
		page.navigate(url);
		test.log(LogStatus.INFO, "Navigate to url.....");

		ExcelUtility.setExcelFile(
				"C:\\Users\\dell\\eclipse-workspace\\maven-intranet\\src\\test\\java\\utilities\\ExcelData.xlsx",
				"LoginTests");
		test.log(LogStatus.INFO, "Navigate to excel");

	}

	@DataProvider(name = "loginData")
	public Object[][] dataProvider() {
		Object[][] testData = ExcelUtility.getTestData("abc");
		return testData;
	}

	@Test(groups = "loginpage", dataProvider = "loginData")
	public void login(String username, String password) throws Exception {
		test.log(LogStatus.INFO, "Navigate to login");
		page.loginToIntranet(username, password);

		if (username == "shubhangi.tomar" && password == "Einfochips0130@") {
			Reporter.log("Login method ran successfully");
		} else {
			boolean result = driver.findElements(By.xpath("//div[@class='alert alert-danger']")).size() != 0;
			softAssert.assertTrue(result);
//			softAssert.assertAll();

		}
		Thread.sleep(500);
	}

	@Test(enabled = false)
	public void forgotpass() {
		page.forgetpass();
	}

	@AfterMethod
	public void tearDown(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			String directory = location.takeScreenshot(driver, testResult.getName());
			String imagePath = test.addScreenCapture(directory);
			test.log(LogStatus.FAIL, "Verify Welcome Text Failed", imagePath);
		}

	}

	@AfterTest
	public void cleanup() {
		driver.quit();
		report.endTest(test);
		report.flush();
	}

}
