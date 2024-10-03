package com.scc.qa.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.scc.qa.base.TestBase;
import com.scc.qa.pages.DashboardPage;
import com.scc.qa.pages.LoginPage;
import com.scc.qa.pages.SearchPage;

/**
 * @author - Sudhanshu Tripathi
 *
 */
public class DashboardPageTest extends TestBase {
	DashboardPage dashboardPage;
	LoginPage loginPage;
	SearchPage searchPage;
	Logger logger = Logger.getLogger(DashboardPageTest.class);

	// It is used to call superclass methods, and to access the superclass
	// constructor.
	public DashboardPageTest() {
		super();
	}

	@Parameters({ "loginApproach", "serverName", "typeOfLogin" })
	@BeforeMethod
	public void setUp(String approachOfLogin, String nameOfServer, String loginType) throws Throwable {
		initialization();
		loginPage = new LoginPage();
		dashboardPage = new DashboardPage();
		dashboardPage = loginPage.login(prop.getProperty("userNamePlatinumPlus"), TestBase.prop.getProperty("password"),
				approachOfLogin, loginType);
	}

	@Test(priority = 1)
	void verifyDashboardTitileTest() {
		String dashboardTitle = dashboardPage.validateDashboardTitle();

		logger.debug(dashboardTitle
				.contains("SCC Online Legal Research | Search By Citation, Party Name, Section, Topic & Statutes Guide")
						? "Dashboard page title is matched as expected."
						: "Dashboard page title not matched.");

		Assert.assertEquals(dashboardTitle,
				"SCC Online Legal Research | Search By Citation, Party Name, Section, Topic & Statutes Guide",
				"Dashboard page title not matched.");
	}

	@Test(priority = 2)
	void verifyCompanyLogoTest() {
		boolean flag = dashboardPage.validateCompanyLogo();

		logger.debug(flag ? "Company logo is present." : "Comapny logo is not present.");

		Assert.assertTrue(flag, "Comapny logo is not present.");
	}

	@Test(priority = 3)
	void verifyDashboardTaglineTest() {
		String dashboardTagline = dashboardPage.validateDashboardTagline();

		logger.debug(dashboardTagline.contains("SCC Online Dashboard") ? "Dashboard tagline is matched."
				: "Dashboard tagline not matched.");

		Assert.assertEquals(dashboardTagline, "SCC Online Dashboard", "Dashboard tagline not matched.");
	}

	@Test(priority = 4)
	void verifyWordSearchTileTest() {
		searchPage = dashboardPage.clickOnWordSearchTile();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
