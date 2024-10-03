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

public class SearchPageTest extends TestBase {
	DashboardPage dashboardPage;
	LoginPage loginPage;
	SearchPage searchPage;
	Logger logger = Logger.getLogger(SearchPageTest.class);

	// It is used to call superclass methods, and to access the superclass
	// constructor.
	public SearchPageTest() {
		super();
	}

	@Parameters({ "loginApproach", "serverName", "typeOfLogin" })
	@BeforeMethod
	public void setUp(String approachOfLogin, String nameOfServer, String loginType) throws Throwable {
		initialization();
		loginPage = new LoginPage();
		dashboardPage = new DashboardPage();
		searchPage = new SearchPage();
		dashboardPage = loginPage.login(prop.getProperty("userNamePlatinumPlus"), TestBase.prop.getProperty("password"),
				approachOfLogin, loginType);
		searchPage = dashboardPage.clickOnWordSearchTile();
	}

//	@Test(priority = 1)
	void verifySearchPageLabelTest() {
		logger.debug(searchPage.validateSearchPageLabel() ? "Search page label is matched."
				: "Search page label is not matched");
		Assert.assertTrue(searchPage.validateSearchPageLabel(), "Search page label is not matched");
	}

	@Test(priority = 2)
	void verifySearchPageURLTest() {
		logger.debug(searchPage.validateSearchPageURL().equals(prop.getProperty("searchPageUrl"))
				? "Search page URL is matched."
				: "Search page URL is not matched.");
		Assert.assertEquals(searchPage.validateSearchPageURL(), prop.getProperty("searchPageUrl"),
				"Search page URL is not matched.");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
