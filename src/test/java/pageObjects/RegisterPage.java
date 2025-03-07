package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage{

	public RegisterPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@placeholder='First Name']")//wrong x path given to fail the test "Name"
	WebElement txtFirstName;
	
	@FindBy(xpath = "//input[@placeholder='Last Name']")
	WebElement txtLastName;
	
	@FindBy(xpath = "//input[@placeholder='E-Mail']")
	WebElement txtEmail;
	
	@FindBy(xpath = "//input[@placeholder='Telephone']")
	WebElement txtPhone;
	
	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement txtPswd;
	
	@FindBy(xpath = "//input[@placeholder='Password Confirm']")
	WebElement txtCnfrmPswd;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkAgreeTerms;
	
	@FindBy(xpath = "//input[@value=\"Continue\"]")
	WebElement btnContinue;
	
	@FindBy(xpath = "//h1[.='Your Account Has Been Created!']")
	WebElement loginConfirmation;
	
	public void setFirstName(String fName) {
		txtFirstName.sendKeys(fName);
	}
	
	public void setLastName(String lName) {
		txtLastName.sendKeys(lName);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void phoneNumber(String phoneNmbr) {
		txtPhone.sendKeys(phoneNmbr);
	}
	
	public void setPswd(String pswd) {
		txtPswd.sendKeys(pswd);
	}
	
	public void setcnfrmPswd(String pswd) {
		txtCnfrmPswd.sendKeys(pswd);
	}
	
	public void clickCheckbox() {
		chkAgreeTerms.click();
	}
	
	public void continueButton() {
		btnContinue.click();
	}
	public String getConfirmationMsg() {
		try {
			return (loginConfirmation.getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return (e.getMessage());
		}
	}
}
