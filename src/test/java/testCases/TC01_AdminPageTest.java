package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AdminPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC01_AdminPageTest extends BaseClass{

	LoginPage loginPage;
	HomePage homePage;
	AdminPage adminPage;
	
	@Test(groups = {"master", "regression"}, priority = 0)
	public void loginWithValidCredentials() {
		try {
			loginPage = new LoginPage(driver);
			homePage = new HomePage(driver);
			logger.info("**** Starting loginWithValidCredentials ***");
			String username = property.readProperty("username");
			String password = property.readProperty("password");
			doLogin(loginPage, username, password);
			logger.info("Validating");
			
			Assert.assertTrue(homePage.isUserDropDownPresent());
		} catch (Exception e) {
			logger.error("Test failed.");
			logger.debug("Debug logs.");
			Assert.fail();
		}
	}
	
	@Test(groups = {"master", "regression"}, priority = 1)
	public void clickAdminButton() {
		try{
			loginPage.clickAdmin();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test(groups = {"master", "regression"}, priority = 2, dataProvider = "validSearchTerms", dataProviderClass = DataProviders.class)
	public void testSearchValidSystemUsers(String searchTerm) {
		searchSystemUsers(searchTerm);
		Assert.assertTrue(adminPage.getRecordsFoundStatus(), "No records found. Searched with invalid data instead on a valid one.");
	}
	
	@Test(groups = {"master", "regression"}, priority = 3, dataProvider = "invalidSearchTerms", dataProviderClass = DataProviders.class)
	public void testSearchInvalidSystemUsers(String searchTerm) {
		searchSystemUsers(searchTerm);
		Assert.assertTrue(adminPage.getNoRecordsFoundStatus(), "Records found. Searched with valid data instead of an invalid one.");
	}
	
	@Test(groups = {"master", "regression"}, priority = 4)
	public void logout() {
		logger.info("Logging out.");
		homePage.clickUserDropdownButton();
		loginPage.clickLogout();
	}
	
	public void searchSystemUsers(String searchTerm) {
		if(adminPage == null)
			adminPage = new AdminPage(driver);
		adminPage.setUsername(searchTerm);
		adminPage.clickSearch();
	}
	
}
