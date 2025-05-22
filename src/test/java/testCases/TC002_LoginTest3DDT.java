package testCases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC002_LoginTest3DDT extends BaseClass
{
	
	@Test(groups = {"Sanity", "Master"},dataProvider = "practice_data",dataProviderClass = DataProviders.class)
	public void verifyLogin(String emailadd,String pwd)
	{
		HomePage hmp = new HomePage(driver);
		
			hmp.clickMyAccount();
			hmp.clickLogin();
			
		LoginPage lpg = new LoginPage(driver);
		
			lpg.setEmailAddress(emailadd);
			lpg.setPassword(pwd);
			lpg.clickLogin();
			
		MyAccountPage my_acc_pg = new MyAccountPage(driver);
	
		boolean myAccountPageStatus = my_acc_pg.isMyAccountPageExist();
			//Assert.assertEquals(myAccountPageStatus, true , "Login failed");	//first 2 parameter will compare. if it is failed then return meassge.
						//OR
			//Assert.assertTrue(myAccountPageStatus);
						//OR
			if(myAccountPageStatus==true)
			{
				my_acc_pg.clickLogout();
				Assert.assertTrue(true);
			}
				
			else
				Assert.assertTrue(false);
	}

}
