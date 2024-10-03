package com.scc.qa.base;

/**
 * @author - Sudhanshu Tripathi
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.scc.qa.util.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public static Logger logger;
	public static WebDriverWait wait;
	public static WebElement element;
	public static ExtentReports extent;
	public static ExtentTest extentLogger;

	public TestBase() {
//		File filePath = new File(
//				System.getProperty("user.dir") + "/src/main/java/com/scc" + "/qa/config/config.properties");
		File filePath = new File("./src/main/resources/configFor" + Utility.serverName + "Server.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			logger.error("Exception in fileInput if file is not found", e);
			e.printStackTrace();
		}
		prop = new Properties();
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			logger.error("Exception in Properties file fileInput is not found", e);
			e.printStackTrace();
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy-hhmmss");
		System.setProperty("sys:user.home", dateFormat.format(new Date()));
		System.setProperty("logFileLocation", TestBase.prop.getProperty("logFileLocation"));
		PropertyConfigurator
				.configure(System.getProperty("user.dir") + "/src/main/java/com/scc" + "/qa/config/log4j.properties");
		logger = Logger.getLogger(TestBase.class);
	}

	public static void initialization() {
//		WebDriverManager.chromedriver().setup();
		if (Boolean.parseBoolean(prop.getProperty("isHeadLessMode"))) {
			ChromeOptions option = new ChromeOptions();
//			option.addArguments("headless");
//			option.setHeadless(true);
			option.addArguments("--headless=new");
			option.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(option);
			logger.debug("Browser opened in headless mode.");
		} else {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			logger.debug("Browser launched");
		}
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}

}
