package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage
{
	//constructor
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	
	//Locators
	
		@FindBy(xpath = "//h2[text()=\"My Account\"]")
		WebElement msgHeading;
		
		@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
		WebElement btnLogout;
	
	//Actions
		
		public void clickLogout()
		{
			btnLogout.click();
		}
	
	//return methods
		
		public boolean isMyAccountPageExist()
		{
			try 
			{
				return msgHeading.isDisplayed();
			}
			catch (Exception e)
			{
				return false;	//if meassge not displayed
			}
			
		}
	

}
