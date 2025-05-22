package testCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass
{
	@Test(groups = {"Sanity", "Master"})
	public void verifyLogin()
	{
		HomePage hmp = new HomePage(driver);
		
			hmp.clickMyAccount();
			hmp.clickLogin();
			
		LoginPage lpg = new LoginPage(driver);
		
			lpg.setEmailAddress(prp.getProperty("email"));
			lpg.setPassword(prp.getProperty("password"));
			lpg.clickLogin();
			
		MyAccountPage my_acc_pg = new MyAccountPage(driver);
	
			boolean myAccountPageStatus = my_acc_pg.isMyAccountPageExist();
			//Assert.assertEquals(myAccountPageStatus, true , "Login failed");	//first 2 parameter will compare. if it is failed then return meassge.
						//OR
			Assert.assertTrue(myAccountPageStatus);
						//OR
			
	}

}
