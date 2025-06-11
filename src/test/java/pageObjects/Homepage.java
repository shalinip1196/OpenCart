package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Homepage extends Basepage{
	
	public Homepage(WebDriver driver)
	{
		super(driver);
	}
	
	
	@FindBy(xpath="//*[@id=\"top-links\"]/ul/li[2]/a") //Myaccount link
	WebElement lnkMyAccount;
	@FindBy(xpath="//*[@id=\"top-links\"]/ul/li[2]/ul/li[1]/a") //Register link
	WebElement lnkRegister;
	
	@FindBy(xpath="//a[normalize-space()='Login']") //Login link
	WebElement lnkLogin;
	
	
	
	
	public void clickMyAccount()
	{
		lnkMyAccount.click();
	}
	public void clickRegister()
	{
		lnkRegister.click();
	}
	
	public void clickLogin()
	{
		lnkLogin.click();
	}

}
