package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;

public class location {

	public static final String File_Path = System.getProperty("user.dir") + "//src//test//java//utilities//";

	public static final String File_Name = "ExcelData.xlsx";

	public static ExtentReports getInstance() {
		ExtentReports extent;
		String Path = System.getProperty("user.dir") + "//Reports.html//";
		extent = new ExtentReports(Path, false);
		extent.addSystemInfo("Selenium Version", "2.52").addSystemInfo("Platform", "Mac");

		return extent;
	}

	public static String takeScreenshot(WebDriver driver, String fileName) throws IOException {
		fileName = fileName + ".png";
		String directory = System.getProperty("user.dir") + "//screenshots//";
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sourceFile, new File(directory + fileName));
		String destination = directory + fileName;
		return destination;
	}

}
