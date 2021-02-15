package tests;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class DashboardTest extends LoginTest {

	@Test(dependsOnMethods = { "login" })
	public void Search() throws InterruptedException {
		dashboard.search();
		Thread.sleep(1000);
		Reporter.log("Search method ran successfully");
	}

//	@Test(dependsOnMethods = { "login" })
//	public void Profile() throws InterruptedException {
//		dashboard.profile();
//		Thread.sleep(1000);
//		Reporter.log("Profile method ran successfully");
//	}

}
