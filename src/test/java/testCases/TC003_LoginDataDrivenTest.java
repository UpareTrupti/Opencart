package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

//valid data->Login success	test pass
//Invalid data->Login Unsuccess test pass

//valid Data->Login unsuccess 	test fail
//Invalid Data->Login success	test fail

public class TC003_LoginDataDrivenTest extends BaseClass
{
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class,groups = "Datadriven")
	public void verifyLoginDDT(String emailadd,String pwd, String data )
	{
		HomePage hmp = new HomePage(driver);
		
		hmp.clickMyAccount();
		hmp.clickLogin();
		
	LoginPage lpg = new LoginPage(driver);
	
		lpg.setEmailAddress(emailadd);
		lpg.setPassword(emailadd);
		lpg.clickLogin();
		
	MyAccountPage my_acc_pg = new MyAccountPage(driver);
		boolean myAccountPageStatus = my_acc_pg.isMyAccountPageExist();
		
		if(data.equalsIgnoreCase("valid"))
		{
			if(myAccountPageStatus==true)
			{
				my_acc_pg.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
			
		}
		if(data.equalsIgnoreCase("invalid"))
		{
			if(myAccountPageStatus==true)
			{
				my_acc_pg.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
	}
}
