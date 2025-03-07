package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	

	public MyAccountPage(WebDriver driver) {
		
		super(driver);
	}
	

	@FindBy(xpath = "//h2[.='My Account']")
	WebElement msgHeading;
//
	@FindBy(xpath = "//span[.='My Account']") 
	WebElement myAccount;

	@FindBy(xpath = "//ul[@class=\"dropdown-menu dropdown-menu-right\"]//a[text()='Logout']")
	WebElement lnkLogout;

	public boolean msgIsDisplayed() {
		try {
			return (msgHeading.isDisplayed());
		} catch (Exception e) {
			return false;
		}
	}
	
	
	public void clickLogout() {
		myAccount.click();
		lnkLogout.click();
		
	}
}