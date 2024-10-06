package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//p[@class='oxd-userdropdown-name']")
	WebElement userDropdown;
	
	@FindBy(xpath = "//p[@class='oxd-userdropdown-name']/following-sibling::i")
	WebElement userDropdownButton;
	
	public void clickUserDropdownButton() {
		userDropdownButton.click();
	}
	
	public boolean isUserDropDownPresent() {
		try {
			return userDropdown.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

}
