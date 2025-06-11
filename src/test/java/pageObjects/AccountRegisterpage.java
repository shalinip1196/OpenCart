package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterpage extends Basepage{
	
	public AccountRegisterpage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//label[normalize-space()='Yes']")
	WebElement RbtnYes;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkPrivacyPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void setFirstName(String fname)
	{
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txtLastName.sendKeys(lname);
	}
	public void setEmail(String email)
	{
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String tel)
	{
		txtTelephone.sendKeys(tel);
	}
	public void setPassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
	}
	public void setConfirmPassword(String pwd)
	{
		txtConfirmPassword.sendKeys(pwd);
	}
	
	public void setPrivacyPolicy()
	{
		chkPrivacyPolicy.click();
	}
	
	public void clickContinue()
	{
		btnContinue.click();
	}
	
	public String getConfirmationMsg()
	{
		try {
			//return "Your Account Has Been Created!";
			return(msgConfirmation.getText());
		}catch (Exception e)
		{
			return (e.getMessage());
		}
	}
	

}
