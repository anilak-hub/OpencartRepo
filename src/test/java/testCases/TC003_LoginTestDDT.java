package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;


/*
 * If data is valid - Login succesfull - Logout - test pass
 *                    Loging failed - test fail
 * 
 * 
 * If data is invalid - Login Succesful - Logout - test fail 
 *                      Login failed - test pass
 */

public class TC003_LoginTestDDT extends BaseClass {

	// If Data provider is in the same class then we don't need to specify location
	
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = {"DataDriven", "Master"}) 
	// Getting Data Provider from another package and another class
	
	
	public void verify_LoginDDT(String email, String pswd, String exp) {

		logger.info("******Starting TC003_LoginTest******");
		try {
			// Homepage
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			// Loginpage
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(email);
			lp.setPswd(pswd);
			lp.clickLogin();

			MyAccountPage map = new MyAccountPage(driver);
			boolean isDisplayed = map.msgIsDisplayed();


			if (exp.equalsIgnoreCase("Valid")) {
				if (isDisplayed == true) {
					map.clickLogout();
					System.out.println("Login Succesfull- Valid data");
					Assert.assertTrue(true);

				} else {
					System.out.println("Login failed- Valid data");
					Assert.assertTrue(false);
				}
			}

			if (exp.equalsIgnoreCase("Invalid")) {
				if (isDisplayed == true) {
					map.clickLogout();
					System.out.println("Login Succesful- Valid data");
					Assert.assertTrue(false);
				}
				else {
					System.out.println("Login failed - Invalid data");
					Assert.assertTrue(true);
				}

			}
		}
		catch (Exception e) {
			Assert.fail();
		}
		logger.info("******Finished TC003_LoginTest******");
	}

}
