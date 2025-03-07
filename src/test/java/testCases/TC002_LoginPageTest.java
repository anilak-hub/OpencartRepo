package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginPageTest extends BaseClass {
	@Test(groups = {"Regression","Sanity", "Master"})
	public void verify_Login() {
		logger.info("****** Starting TC002_LoginPageTest *******");
		try {
		//Home page
		HomePage hp= new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info("Clicked on my account");
		hp.clickLogin();
		logger.info("Clicked on Login");
		
		//Login page
		LoginPage login = new LoginPage(driver);
		
		login.setEmail(p.getProperty("email"));
		login.setPswd(p.getProperty("password"));
		logger.info("credentials entered");
		login.clickLogin();
		logger.info("Clicked on Login Button");
		
		//My Account page
		 MyAccountPage ma = new MyAccountPage(driver);
		 
		 boolean loginPageDisplayed = ma.msgIsDisplayed();
		 //Assert.assertEquals(loginPageDisplayed, true, "Login failed");
		Assert.assertTrue(loginPageDisplayed);
		
		ma.clickLogout();
		}
		catch (Exception e) {
			logger.error("Test failed");
			Assert.fail();
		}
		
		logger.info("****** Finished TC002_LoginPage_Test *******");
		
		
	}
	
}
