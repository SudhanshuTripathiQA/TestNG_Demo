package com.scc.qa.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.scc.qa.base.TestBase;
import com.scc.qa.util.TestUtil;

public class BrowseCuratedPage extends TestBase {

	static Logger logger = Logger.getLogger(BrowseCuratedPage.class);

	/* Web elements for BrowseCuratedPage page. */

	// Using Page Factory - Object repository:
	@FindBy(xpath = "//div[@class='curated_header']")
	private WebElement header;

	// @FindBy(xpath = "//div[@id='browse-Topic']")
	@FindBy(id = "browse-Topic")
	private WebElement browse_Topic_Tile;

	@FindBy(xpath = "//ul[@id='ctl00_ContentPlaceHolder1_divCurated']/li")
	private List<WebElement> browse_Curated_Tiles;

	@FindBy(xpath = "//a[@title='Constitutional Law']")
	private WebElement browse_Curated_Sub_Tile;

	// Initializing the Page Objects:
	public BrowseCuratedPage() {
		PageFactory.initElements(driver, this);
	}

	/* Page Actions:*/
	public boolean validateBrowseTopicTile() {
		return browse_Topic_Tile.isDisplayed();
	}

	public Boolean validateCuratedHeader() {
		browse_Topic_Tile.click();
		return header.isDisplayed();
	}

	public int validateBrowseCuratedTilesCount() {
		TestUtil.clickOn(driver, browse_Topic_Tile, TestUtil.EXPLICIT_WAIT);
		return browse_Curated_Tiles.size();
	}

	public boolean validateBrowseSubTileVisibiltyTest() {
		TestUtil.clickOn(driver, browse_Topic_Tile, TestUtil.EXPLICIT_WAIT);
		return browse_Curated_Sub_Tile.isDisplayed();
	}

}
