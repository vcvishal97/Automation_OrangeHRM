package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@name='username']")
	WebElement username;
	
	@FindBy(xpath = "//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement loginButton;
	
	@FindBy(xpath = "//a[@class='oxd-main-menu-item']/span[text()='Admin']")
	WebElement admin;
	
	@FindBy(xpath = "//div[@class='orangehrm-login-error']//p[text()='Invalid credentials']")
	WebElement invalidAlert;
	
	@FindBy(xpath = "//a[text()='Logout']")
	WebElement logoutButton;
	
	public void clickLogout() {
		logoutButton.click();
	}
	
	public boolean isInvalidAlertPresent() {
		try {
			return invalidAlert.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getInvalidAlertMessage() {
		return invalidAlert.getText();
	}
	
	public void clickAdmin() throws Exception {
		if(admin.isDisplayed())
			admin.click();
		else
			throw new Exception("Admin button not found.");
	}
	
	public void setUsername(String username) {
		this.username.sendKeys(username);
	}
	
	public void setPassword(String password) {
		this.password.sendKeys(password);
	}
	
	public void clickLogin() {
		this.loginButton.click();
	}
	
}
