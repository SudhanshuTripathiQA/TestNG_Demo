package com.scc.qa.pages;

/**
 * @author - Sudhanshu Tripathi
 *
 */

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.scc.qa.base.TestBase;

public class DashboardPage extends TestBase {
	static Logger logger = Logger.getLogger(DashboardPage.class);
	
	/*
	 * Web elements of Dashboard page.
	 * 
	 */
	
	// Using Page Factory - Object repository:
	@FindBy(id = "CompanyLogo")
	WebElement companyLogo;

	@FindBy(xpath = "//h1[@class='tagHeadline']")
	WebElement dashboard_Tagline;

	@FindBy(id = "search_word")
	WebElement wordSearchTile;

	// Initializing the Page Objects:
	public DashboardPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean validateCompanyLogo() {
		return companyLogo.isDisplayed();
	}

	public String validateDashboardTitle() {
		return driver.getTitle();
	}

	public String validateDashboardTagline() {
		return dashboard_Tagline.getText();
	}

	public SearchPage clickOnWordSearchTile() {
		wordSearchTile.click();
		return new SearchPage();
	}
}
