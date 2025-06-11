package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.Loginpage;
import pageObjects.MyAccountpage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	@Test(groups={"Sanity", "Master"})
	public void verify_login()
	{
		logger.info("*********Staring TC002_LoginTest *********");
		try {
		//Home page
		Homepage hp = new Homepage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login page
		Loginpage lp = new Loginpage(driver);
		lp.setemail(p.getProperty("email"));
		lp.setpassword(p.getProperty("pwd"));
		lp.clickLogin();
		
		//MyAccount page
		MyAccountpage macc = new MyAccountpage(driver);
		boolean targetpage = macc.isMyAccountPageExists();
		
		Assert.assertTrue(targetpage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("********Finished TC002_LoginTest ************");
				
	}

}
