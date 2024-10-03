package com.scc.qa.pages;

import java.util.List;

/**
 * @author - Sudhanshu Tripathi
 *
 */

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.scc.qa.base.TestBase;
import com.scc.qa.util.TestUtil;

public class LoginPage extends TestBase {
	static Logger logger = Logger.getLogger(LoginPage.class);

	/*
	 * Web elements of Login page.
	 * 
	 */

	// Using Page Factory - Object repository:
	@FindBy(xpath = "//div[@class='modal-content']")
	WebElement modelPopup;

	@FindBy(xpath = "//button[@type='button']")
	WebElement closeModelPopup;

	@FindBy(id = "login-link-navbar")
	WebElement login_Button;

	@FindBy(id = "passwordLogin")
	WebElement userLogin_Tab;

	@FindBy(xpath = "//button[text()='LOGIN NOW']")
	WebElement loginNow_Button;

	@FindBy(xpath = "//div[@class='toast-message']")
	WebElement loginError_Message;

	@FindBy(name = "loginid")
	WebElement username_Textfield;

	@FindBy(name = "pass")
	WebElement password_Textfield;

	@FindBy(id = "ipLogin")
	List<WebElement> ipLogin_Tab;

	@FindBy(xpath = "iploginid")
	WebElement ipLogin_Textfield;

	@FindBy(xpath = "//button[text()='Yes']")
	List<WebElement> yes_Button_From_Normal_Login;

	@FindBy(xpath = "//h1[@class='tagHeadline']")
	WebElement dashboard_Tagline;

	@FindBy(id = "liStandardLogin")
	WebElement standard_Login_Button;

	@FindBy(id = "UserName")
	WebElement user_Text_Field;

	@FindBy(id = "Password")
	WebElement password_Text_Field;

	@FindBy(linkText = "Sign In")
	WebElement sign_In_Button;

	@FindBy(id = "liIPAccessLogin")
	List<WebElement> ip_Login_Tab;

	@FindBy(id = "tbIPAccessUserName")
	WebElement ip_Access_Text_Fielsd;

	@FindBy(id = "btnGuestUser")
	List<WebElement> guest_Login_Tab_From_session_Page;

	@FindBy(id = "ctl00_ContentPlaceHolder1_Button1")
	List<WebElement> yes_Button_From_SessionExpire_Page;

	@FindBy(xpath = "//div[@class='modal-content']")
	List<WebElement> model_Popup;

	@FindBy(xpath = "//button[@type='button']")
	WebElement close_Model_popup;

	@FindBy(id = "login-link-navbar")
	WebElement login_Link;

	@FindBy(id = "guestLogin")
	List<WebElement> guest_Login_Tab_From_Normal_Page;

	public LoginPage() {
		super();
		PageFactory.initElements(driver, this);
	}

	// Page Actions:
	public void openingLoginPopUp() {
		element = TestUtil.visibilityOfElement(driver, modelPopup, TestUtil.EXPLICIT_WAIT);
		if (element.isDisplayed()) {
			TestUtil.clickOn(driver, closeModelPopup, 5);
		}
		login_Button.click();
	}

	public void openingApplication() {
		driver.get(prop.getProperty("applicationUrl"));
	}

	public String validateLoginErrorMessageWithoutCredential() {
		openingLoginPopUp();
		TestUtil.clickOn(driver, userLogin_Tab, 30);
		TestUtil.clickOn(driver, loginNow_Button, 10);
		element = TestUtil.visibilityOfElement(driver, loginError_Message, 20);
		return element.getText();
	}

	public String validateLoginErrorMessageWithoutPassword() {
		openingLoginPopUp();
		TestUtil.clickOn(driver, userLogin_Tab, 30);
		TestUtil.sendKeys(driver, username_Textfield, 10, prop.getProperty("userNamePlatinumPlus"));
		TestUtil.clickOn(driver, loginNow_Button, 10);
		element = TestUtil.visibilityOfElement(driver, loginError_Message, 20);
		return element.getText();
	}

	public String validateLoginErrorMessageWithoutUsername() {
		openingLoginPopUp();
		TestUtil.clickOn(driver, userLogin_Tab, 30);
		TestUtil.sendKeys(driver, password_Textfield, 3, prop.getProperty("password"));
		TestUtil.clickOn(driver, loginNow_Button, 10);
		element = TestUtil.visibilityOfElement(driver, loginError_Message, 20);
		return element.getText();
	}

//	 public DashboardPage login(String userName, String passWord) throws Throwable {
//		logger.debug("Login is in progress...");
//		openingLoginPopUp();
//		if (TestUtil.isElementPresent(ipLogin_Tab, 1)) {
//			ipLogin_Tab.click();
//			ipLogin_Textfield.sendKeys(prop.getProperty("ipLogin"));
//		} else {
//			userLogin_Tab.click();
//			TestUtil.sendKeys(driver, username_Textfield, 2, prop.getProperty("userNamePlatinumPlus"));
//			TestUtil.sendKeys(driver, password_Textfield, 2, prop.getProperty("password"));
//		}
//		loginNow_Button.click();
//		Thread.sleep(1000);
//		String currentUrl = driver.getCurrentUrl();
//		if (currentUrl.endsWith("Dashboard.aspx")) {
//		} else {
//			TestUtil.clickOn(driver, yes_Button, 5);
//		}
//		if (TestUtil.isElementPresent(dashboard_Tagline, TestUtil.EXPLICIT_WAIT)) {
//			logger.debug("Login is successful.");
//		}
//		Assert.assertTrue(TestUtil.isElementPresent(dashboard_Tagline, 10), "Login is not completed.");
//		return new DashboardPage();
//	}

	public DashboardPage login(String userName, String password, String loginApproach, String loginType) {
		try {
//			wait=new WebDriverWait(driver, 20);
//			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			if (loginApproach.equalsIgnoreCase("sessionExpirePage")) {
				logger.info("Login process started from Session expire page...");

				driver.get(prop.getProperty("sessionExpirePageURL"));

				if (loginType.equalsIgnoreCase("Standard")) {
					TestUtil.clickOn(driver, standard_Login_Button, TestUtil.EXPLICIT_WAIT);
					user_Text_Field.sendKeys(userName);
					password_Text_Field.sendKeys(password);
					sign_In_Button.click();
				} else if (loginType.equalsIgnoreCase("IP")) {
//					boolean isIPLoginTabPresent = driver.findElements(By.id("liIPAccessLogin")).size() > 0;
					boolean isIPLoginTabPresent = ip_Login_Tab.size() > 0;
					if (isIPLoginTabPresent) {
						ip_Login_Tab.get(0).click();
						ip_Access_Text_Fielsd.sendKeys(prop.getProperty("ipLogin"));
						sign_In_Button.click();
					} else {
						logger.debug(
								"IP login Tab is not present, So login can not proceed. Please change your login approach.");
						Assert.fail(
								"IP login Tab is not present, So login can not proceed. Please change your login approach.");
					}
				} else if (loginType.equalsIgnoreCase("Guest")) {
					boolean isGuestAccessVisible = guest_Login_Tab_From_session_Page.size() > 0;
					if (isGuestAccessVisible) {
						guest_Login_Tab_From_session_Page.get(0).click();
					} else {
						logger.debug(
								"Guest access tab is not present, So login can not proceed. Please change your login approach.");
						Assert.fail(
								"Guest access tab is not present, So login can not proceed. Please change your login approach.");
					}
				}
				Thread.sleep(1000);
				boolean isYesButtonVisible = yes_Button_From_SessionExpire_Page.size() > 0;
				if (isYesButtonVisible) {
					yes_Button_From_SessionExpire_Page.get(0).click();
				}
			} else if (loginApproach.equalsIgnoreCase("normalLogin")) {
				driver.get(prop.getProperty("applicationUrl"));
				logger.debug("Login is in progress from normal login process...");
				boolean isModelPopUpVisible = model_Popup.size() > 0;
				if (isModelPopUpVisible) {
					close_Model_popup.click();
				}
				TestUtil.clickOn(driver, login_Link, TestUtil.EXPLICIT_WAIT);
//				wait.until(ExpectedConditions.elementToBeClickable(By.id("login-link-navbar"))).click();
				if (loginType.equalsIgnoreCase("IP")) {
					boolean isIpLoginTabPresent = ipLogin_Tab.size() > 0;
					if (isIpLoginTabPresent) {
//						ipLogin_Tab.get(0).click();
						TestUtil.clickOn(driver, ipLogin_Tab.get(0), TestUtil.EXPLICIT_WAIT);
//						ipLogin_Textfield.sendKeys(prop.getProperty("ipLogin"));
						TestUtil.sendKeys(driver, ipLogin_Textfield, TestUtil.EXPLICIT_WAIT,
								prop.getProperty("ipLogin"));
					} else {
						Assert.fail("IP login tab is not present.");
					}
				}
				if (loginType.equalsIgnoreCase("Standard")) {
					TestUtil.clickOn(driver, userLogin_Tab, TestUtil.EXPLICIT_WAIT);
//					wait.until(ExpectedConditions.elementToBeClickable(By.id("passwordLogin"))).click();
					TestUtil.sendKeys(driver, username_Textfield, TestUtil.EXPLICIT_WAIT, userName);
//					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("loginid"))).sendKeys(userName);
					TestUtil.sendKeys(driver, password_Textfield, TestUtil.EXPLICIT_WAIT, password);
//					wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("pass"))).sendKeys(password);
				}
				if (loginType.equalsIgnoreCase("Guest")) {
					boolean isGuestLoginTabPresent = guest_Login_Tab_From_Normal_Page.size() > 0;
					if (isGuestLoginTabPresent) {
						guest_Login_Tab_From_Normal_Page.get(0).click();
					} else {
						Assert.fail("Guest login tab is not present.");
					}
				}
//				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='LOGIN NOW']"))).click();
				TestUtil.clickOn(driver, loginNow_Button, TestUtil.EXPLICIT_WAIT);
				Thread.sleep(1000);
				String currentUrl = driver.getCurrentUrl();
				boolean isYesButtonPresent = yes_Button_From_Normal_Login.size() > 0;
				int counter = 0;
				while (!currentUrl.endsWith("Dashboard.aspx") && !isYesButtonPresent && counter < 2) {
					Thread.sleep(1000);
					currentUrl = driver.getCurrentUrl();
					isYesButtonPresent = yes_Button_From_Normal_Login.get(0).isDisplayed();
					counter++;
				}
				if (yes_Button_From_Normal_Login.size() > 0) {
					yes_Button_From_Normal_Login.get(0).click();
				}
			}
			if (verifyAccountExpirationPage()) {
				Assert.assertTrue(TestUtil.isElementPresent(dashboard_Tagline, TestUtil.EXPLICIT_WAIT));
				logger.debug("Login is successful.");
			} else {
				Assert.fail("Please login to renew your subscription");
			}
		} catch (Exception e) {
			logger.debug("Login is not successful.", e);
			Assert.fail("Login is not successful.");
		}
		return new DashboardPage();
	}

	public boolean verifyAccountExpirationPage() {
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.contains("renew?") | currentUrl.contains("Renew")) {
			logger.debug("Login is not sucessful, Please login to renew your subscription");
			return false;
		} else {
			return true;
		}
	}
}
