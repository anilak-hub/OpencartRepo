package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage  {

	public HomePage(WebDriver driver) 
	{
		super(driver);
	}
	@FindBy(xpath = "//span[.='My Account']")
	WebElement myAccount;

	@FindBy(xpath = "//a[.='Register']")
	WebElement register;
	
	@FindBy(xpath = "//a[.='Login']")
	WebElement login;

	public void clickMyAccount() {
		myAccount.click();
		//System.out.println("clicked on my acc in home poage");
	}
	
	public void clickRegister() {
		register.click();
	}
	public void clickLogin() {
		login.click();
		//System.out.println("clicked on login in home poage");
	}
}
