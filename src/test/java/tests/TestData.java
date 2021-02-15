package tests;

import org.testng.annotations.DataProvider;

import utilities.ExcelUtility;

public class TestData {

	@DataProvider(name = "loginData")
	public Object[][] dataProvider() {
		Object[][] testData = ExcelUtility.getTestData("abc");
		return testData;
	}

}
