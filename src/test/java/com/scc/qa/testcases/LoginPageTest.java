package com.scc.qa.testcases;

/**
 * @author - Sudhanshu Tripathi
 *
 */
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.scc.qa.base.TestBase;
import com.scc.qa.pages.DashboardPage;
import com.scc.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {
	LoginPage loginPage;
	DashboardPage dashboardPage;
	Logger logger = Logger.getLogger(LoginPageTest.class);

	// It is used to call superclass methods, and to access the superclass
	// constructor.
	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		loginPage.openingApplication();
	}

	@Test(priority = 1)
	public void verifyErrorMessageWithoutCredentialTest() {
		String errorMessage = loginPage.validateLoginErrorMessageWithoutCredential();

		logger.debug(errorMessage.contains("Enter your Login ID")
				? "Without credential error message: '" + errorMessage + "' is matched as expected."
				: "Without credential error message is not matched.");

		Assert.assertTrue(errorMessage.contains("Enter your Login ID"),
				"Without credential error message is not matched.");
	}

	@Test(priority = 2)
	public void verifyErrorMessageWithoutPasswordTest() {
		String errorMessage = loginPage.validateLoginErrorMessageWithoutPassword();

		logger.debug(errorMessage.contains("Enter your Password")
				? "Without Password error message: '" + errorMessage + "' is matched as expected."
				: "Without Password error message is not matched.");

		Assert.assertTrue(errorMessage.contains("Enter your Password"),
				"Without Password error message is not matched.");
	}

	@Test(priority = 3)
	public void verifyErrorMessageWithoutUsernameTest() {
		String errorMessage = loginPage.validateLoginErrorMessageWithoutUsername();

		logger.debug(errorMessage.contains("Enter your Login ID")
				? "Without Login ID error message: '" + errorMessage + "' is matched as expected."
				: "Without Login ID error message is not matched.");

		Assert.assertTrue(errorMessage.contains("Enter your Login ID"),
				"Without Login ID error message is not matched.");
	}

	@Parameters({ "loginApproach", "serverName", "typeOfLogin" })
	@Test(priority = 4)
	void loginTest(String approachOfLogin, String nameOfServer, String loginType) {
		try {
			dashboardPage = loginPage.login(prop.getProperty("userNamePlatinumPlus"),
					TestBase.prop.getProperty("password"), approachOfLogin, loginType);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
