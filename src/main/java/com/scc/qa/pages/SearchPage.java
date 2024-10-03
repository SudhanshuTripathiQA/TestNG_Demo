package com.scc.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.scc.qa.base.TestBase;

/**
 * @author - Sudhanshu Tripathi
 *
 */

public class SearchPage extends TestBase {
	/*
	 * Web elements of Search page.
	 * 
	 */
	@FindBy(xpath = "//b[contains(text(),'The Surest Way to Legal Research')]")
	WebElement searchPageLabel;

	// Initializing the Page Objects:
	public SearchPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean validateSearchPageLabel() {
		return searchPageLabel.isDisplayed();
	}
	
	public String validateSearchPageURL() {
		return driver.getCurrentUrl();
	}

}
