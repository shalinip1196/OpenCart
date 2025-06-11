package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Loginpage extends Basepage{
	
		public Loginpage(WebDriver driver)
		{
			super(driver);
		}
		
		
		@FindBy(xpath="//input[@id='input-email']")
		WebElement txtEmailAddress;
		
		@FindBy(xpath="/html/body/div[2]/div/div/div/div[2]/div/form/div[2]/input")
		WebElement txtpassword;
		
		@FindBy(xpath="//input[@value='Login']")
		WebElement btnLogin;
		
		public void setemail(String email)
		{
			txtEmailAddress.sendKeys(email);
		}
		
		public void setpassword(String password)
		{
			txtpassword.sendKeys(password);
		}
		
		public void clickLogin()
		{
			btnLogin.click();
		}
		

}
