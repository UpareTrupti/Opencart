package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage
{
	//constructor
		
		public RegistrationPage(WebDriver driver)
		{
			super(driver);
		}
	
	//Locators
		@FindBy(xpath = "//input[@id='input-firstname']")
		WebElement txtFirstName;
		
		@FindBy(xpath = "//input[@id='input-lastname']")
		WebElement txtLastName;
		
		@FindBy(xpath = "//input[@id='input-email']")
		WebElement txtEmail;
		
		@FindBy(xpath = "//input[@id='input-telephone']")
		WebElement txtTelephone;
		
		@FindBy(xpath = "//input[@id='input-password']")
		WebElement txtPassword;
		
		@FindBy(xpath = "//input[@id='input-confirm']")
		WebElement txtPasswordConfirm;
		
		@FindBy(xpath = "//input[@value='0']")
		WebElement btnSubscribe;
		
		@FindBy(xpath = "//input[@name='agree']")
		WebElement chkPrivacy;
		
		@FindBy(xpath = "//input[@value='Continue']")
		WebElement btnContinue;
		
		@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
		WebElement confMsg;
		
		
	//Actions
		
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
		
		public void setTelephone(String telephone)
		{
			txtTelephone.sendKeys(telephone);
		}
		
		public void setPassword(String pwd)
		{
			txtPassword.sendKeys(pwd);
		}
		
		public void setConfirmPassword(String pwd)
		{
			txtPasswordConfirm.sendKeys(pwd);
		}
		
		public void clickPrivacyPolicy()
		{
			chkPrivacy.click();
		}
	
		public void clickContinue()
		{
			btnContinue.click();
		}
		
		public String getConfMessage()
		{
			try
			{
				return confMsg.getText();
			}
			catch (Exception e)
			{
				return e.getMessage();
			}
		}
		
		
}
