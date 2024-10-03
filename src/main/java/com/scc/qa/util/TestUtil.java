package com.scc.qa.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
/**
 * @author - Sudhanshu Tripathi
 *
 */
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.scc.qa.base.TestBase;

public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 100;
	public static long IMPLICIT_WAIT = 10;
	public static long EXPLICIT_WAIT = 50;

	// It will perform send value in web elements after checking visibility of
	// element.
	public static void sendKeys(WebDriver driver, WebElement element, long timeout, String value) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
	}

	// It will click on element with particular explicit wait time.
	public static void clickOn(WebDriver driver, WebElement element, long timeout) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element)).click();
//		element.click();
	}

	// It will check visibility of web element.
	public static WebElement visibilityOfElement(WebDriver driver, WebElement element, long timeout) {
		return new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
	}

	// It will check all the elements in the List by explicitly
	public static List<WebElement> visibilityOfAllElemnets(WebDriver driver, WebElement element, long timeout) {
		return new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfAllElements(element));
	}

	public static boolean isElementPresent(WebElement element, long timeout) {
		boolean isElementPresent = false;
		try {
			isElementPresent = visibilityOfElement(driver, element, timeout).isDisplayed();
			return isElementPresent;
		} catch (NoSuchElementException | TimeoutException e) {
		}
		return isElementPresent;
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	public static void setExtentReport() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
		extent.addSystemInfo("Host Name", "Sudhanshu");
		extent.addSystemInfo("Environment", "Gen");
//		extent.addSystemInfo("Theme", "dark");
	}

	public static void endExtentReport() {
		extent.flush();
		extent.close();
	}

	public static void drawBorder(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border ='3px solid red'", element);
	}
}
