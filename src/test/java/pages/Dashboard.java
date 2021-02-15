package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Dashboard {

	WebDriver driver;
	ExtentReports report;
	ExtentTest test;

	@FindBy(xpath = "//input[@name='sname']")
	WebElement search;

	@FindBy(xpath = "//a[@href='https://intranet.einfochips.com/employee_intranet/portal/user/myinfo']")
	WebElement profile;

	public Dashboard(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(this.driver, this);
	}

	public void search() {
		search.sendKeys("shubhangi");
		search.sendKeys(Keys.ENTER);
		test.log(LogStatus.INFO, "Search profile");

	}

	public void profile() {
		profile.sendKeys(Keys.ENTER);
		test.log(LogStatus.INFO, "View Your Profile");

	}

}
