package com.scc.qa.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.scc.qa.base.TestBase;
import com.scc.qa.pages.BrowseCuratedPage;
import com.scc.qa.pages.DashboardPage;
import com.scc.qa.pages.LoginPage;

public class BrowseCuratedPageTest extends TestBase {

	DashboardPage dashboardPage;
	LoginPage loginPage;
	BrowseCuratedPage curatedPage;
	Logger logger = Logger.getLogger(BrowseCuratedPageTest.class);

	public BrowseCuratedPageTest() {
		super();
	}

	@Parameters({ "loginApproach", "serverName", "typeOfLogin" })
	@BeforeMethod
	public void setUp(String approachOfLogin, String nameOfServer, String loginType) throws Throwable {
		initialization();
		loginPage = new LoginPage();
		dashboardPage = new DashboardPage();
		curatedPage = new BrowseCuratedPage();
		dashboardPage = loginPage.login(prop.getProperty("userNamePlatinumPlus"), TestBase.prop.getProperty("password"),
				approachOfLogin, loginType);
	}

	@Test
	void verifyBrowseCuratedHeaderTest() {
		boolean isHeaderVisible = curatedPage.validateCuratedHeader();
		Assert.assertTrue(isHeaderVisible, "Browse curated	page header is not matched");
		logger.debug(isHeaderVisible ? "Browse curated page header is matched as expected"
				: "Browse curated page header is not matched");
	}

	// Test case- 49591
	@Test
	void verifyBrowseCuratedTileTest() {
		boolean isCuratedTileVisible = curatedPage.validateBrowseTopicTile();

		logger.debug(isCuratedTileVisible ? "Browse Curated tile is visible on dashboard page as expected"
				: "Browse tile is not visible on dashboard page");

		Assert.assertTrue(isCuratedTileVisible, "Browse tile is not visible after user logged-in");
	}

	// Test case- 49592
	@Test
	void verifyBrowseCuratedSubTilesCountTest() {
		int curatedTilesCount = curatedPage.validateBrowseCuratedTilesCount();

		logger.debug(curatedTilesCount == 12 ? "Browse curated sub tiles count is matched as expected"
				: "Browse curated sub tiles count not matched");

		Assert.assertEquals(curatedTilesCount, 12, "Browse curated sub tiles count not matched");
	}

	// Test case- 69237
	@Test
	void verifyBrowseCuratedSubTilesVisibleTest() {
		boolean isSubtileVisible = curatedPage.validateBrowseSubTileVisibiltyTest();
		Assert.assertTrue(isSubtileVisible, "Browse curated sub tile is not visible");
		logger.debug(isSubtileVisible ? "Browse curated sub tile is visible as expected"
				: "Browse curated sub tile is not visible");
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
