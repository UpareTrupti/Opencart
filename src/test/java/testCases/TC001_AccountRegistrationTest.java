package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass
{
	//public WebDriver driver;
	
	@Test(groups = {"Regression" , "Master"})
	void verifyAccountRegistration() throws InterruptedException
	{
		HomePage hmpg = new HomePage(driver);
		hmpg.clickMyAccount();
		hmpg.clickRegister();
		
		RegistrationPage rpg = new RegistrationPage(driver);
		
		
		rpg.setFirstName(randomStrings());
		rpg.setLastName(randomStrings());
		rpg.setEmail(randomStrings()+"@gmail.com");
		rpg.setTelephone(randomNumeric());
		
		String password = randomAlphaNumeric();
		rpg.setPassword(password);
		rpg.setConfirmPassword(password);
		
		rpg.clickPrivacyPolicy();
		rpg.clickContinue();
		
		Thread.sleep(2000);	
		
		String confMessage = rpg.getConfMessage();
		org.testng.Assert.assertEquals(confMessage,"Your Account Has Been Created!" );
		
		
	}
	
	
}
