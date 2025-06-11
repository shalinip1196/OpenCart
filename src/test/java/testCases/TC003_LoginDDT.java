package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Homepage;
import pageObjects.Loginpage;
import pageObjects.MyAccountpage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass{
	
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="Datadriven") //getting dataprovider from different class
	public void verify_LoginDDT(String email, String pwd, String exp)
	{
		
		logger.info("*****Starting TC003_LoginDDT *********");
		
			try {
			//Home page
			Homepage hp = new Homepage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			
			//Login page
			Loginpage lp = new Loginpage(driver);
			lp.setemail(email);
			lp.setpassword(pwd);
			lp.clickLogin();
			
			//MyAccount page
			MyAccountpage macc = new MyAccountpage(driver);
			boolean targetpage = macc.isMyAccountPageExists();
			
			/*
			 * Data is valid - login success - test pass - logout
			 * 					login failed - test fail
			 * 
			 * Data is invalid - login success -  test fail - logout 
			 * 					login failed - test pass
			 * 
			 * 
			 * */
			
			
			if(exp.equalsIgnoreCase("Valid"))
			{
				if(targetpage==true)
				{
					macc.clickLogout();
					Assert.assertTrue(true);
					
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			
			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(targetpage==true)
				{
					macc.clickLogout();
					Assert.assertTrue(false);
					
				}
				else
				{
					Assert.assertTrue(true);
				}
			}
			
			}catch(Exception e)
			{
				Assert.fail();
			}
			logger.info("*****Finished TC003_LoginDDT *********");
			
			
			
	}	
	
	
	

}
