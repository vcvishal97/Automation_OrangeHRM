package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC02_LoginDDT extends BaseClass {

	LoginPage loginPage;
	HomePage homePage;
	
	@Test(priority = 1, groups = {"master", "datadriven"}, dataProvider = "validCredentials", dataProviderClass = DataProviders.class)
	public void validLogin(String username, String password) {
		try {
			loginPage = new LoginPage(driver);
			homePage = new HomePage(driver);
			logger.info("**** Starting loginWithValidCredentials ***");
			doLogin(loginPage, username, password);
			logger.info("Validating");
			Assert.assertTrue(homePage.isUserDropDownPresent());
			logout();
		} catch (Exception e) {
			logger.error("Test failed.");
			logger.debug("Debug logs.");
			Assert.fail();
		}
	}
	
	@Test(priority = 2, groups = {"master", "datadriven"}, dataProvider = "invalidCredentials", dataProviderClass = DataProviders.class)
	public void invalidLogin(String username, String password) {
		try {
			loginPage = new LoginPage(driver);
			logger.info("**** Starting loginWithInvalidCredentials ***");
			doLogin(loginPage, username, password);
			logger.info("Validating");
			Assert.assertTrue(loginPage.isInvalidAlertPresent());
		} catch (Exception e) {
			logger.error("Test failed.");
			logger.debug("Debug logs.");
			Assert.fail();
		}
	}
	
	public void logout() {
		logger.info("Logging out.");
		homePage.clickUserDropdownButton();
		loginPage.clickLogout();
	}
	
}
