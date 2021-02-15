package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Page {

	protected WebDriver driver;
	ExtentTest test;

	public Page(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
	}

	public void navigate(String url) {
		driver.navigate().to(url);
	}

	public void setUserName(String str) {
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(str);
		test.log(LogStatus.INFO, "Enter UserName");
	}

	public void setPassword(String str) {
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(str);
		test.log(LogStatus.INFO, "Enter password");
	}

//	  Click on Login Button

	public void clickLogin() {
		driver.findElement(By.xpath("//button[@name='Submit']")).click();
		test.log(LogStatus.INFO, "Clicked Login Button");
	}

//	  @param strUserName
//	  @param strPasword
//	  @return

	public void loginToIntranet(String userName, String password) {
		this.setUserName(userName);
		this.setPassword(password);
		this.clickLogin();
	}

	public void forgetpass() {
		driver.findElement(By.xpath("//a[@href='https://pss.einfochips.com']")).click();
		test.log(LogStatus.INFO, "Forgot Password");

	}

}
