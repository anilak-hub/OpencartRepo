package testCases;

  
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterPage;

public class TC001_AccountRegistrationTest extends BaseClass{

	@Test(groups = {"Regression", "Master"})
	public void registrationPageTest() {
		logger.info("***Starting TC001_AccountRegistrationTest***");

		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on Myaccount");

			hp.clickRegister();
			logger.info("Clicked on Register");
			RegisterPage rp = new RegisterPage(driver);

			logger.info("Providing Customer deatils for registering");
			rp.setFirstName(randomString());
			rp.setLastName(randomString());
			rp.setEmail(randomString()+"@gmail.com");
			rp.phoneNumber(randomNumber());
			String pswd = randomPswd();
			rp.setPswd(pswd);
			rp.setcnfrmPswd(pswd);
			logger.info("All details provided");

			rp.clickCheckbox();
			logger.info("Clicked on privacy confirmation checkbox");

			rp.continueButton();
			logger.info("Clicked on Continue button");

			String cnfmsg = rp.getConfirmationMsg();
			Assert.assertEquals(cnfmsg, "Your Account Has Been Created!");
			logger.info("Validated registration confirmation message");
 			if(cnfmsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
 
			}
			else {
 				logger.error("Test failed");
				logger.debug("Debug logs");
				Assert.assertTrue(false);
			}
		}
		catch (Exception e) {
			
			Assert.fail();
		}
		logger.info("***Finished TC001_AccountRegistrationTest***");
	}
}
