package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterpage;
import pageObjects.Homepage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{

	
	@Test(groups={"Regression", "Master"})
	public void verify_account_registration()
	{
		
		logger.info("********Statring TC001_AccountRegistrationTest ***********");
		try {
		Homepage hp = new Homepage(driver);
		
		logger.info("****Clicked on MyAccount Link****");
		hp.clickMyAccount();
		
		logger.info("****Clicked on Register Link******");
		hp.clickRegister();
		
		
		logger.info("****Providing Customer details*****");
		AccountRegisterpage repage = new AccountRegisterpage(driver); // if not passing the driver, at the time of execution constructor will get invoke
		repage.setFirstName(randomString().toUpperCase()); //randomly generated the first name
		repage.setLastName(randomString().toUpperCase()); //randomly generated the last name
		repage.setEmail(randomString()+"@gmail.com"); //randomly generated the email
		repage.setTelephone(randomNumber());
		
		String password = randomAlphaNumeric();
		repage.setPassword(password);
		repage.setConfirmPassword(password);
		repage.setPrivacyPolicy();
		repage.clickContinue();
		
		
		logger.info("******validating expected message*******");
		String confmsg = repage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			logger.error("Test Failed");
			logger.debug("Debug logs..");
			Assert.assertTrue(false);
		}
		
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!!!");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("*****Finished TC001_AccountRegistrationTest*****");
		
	}
	
	
	

}
