package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DataDriven" ) //getting data provider from diff class
	public void verify_loginDDT(String email, String pwd, String exp) throws InterruptedException
{
		logger.info("******Starting TC_003_LoginDDT *****");

		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login page
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		//My account page
		MyAccountPage mac = new MyAccountPage(driver);
		boolean targetPage = mac.isMyAccountPageExist();
		
		/*Data valid - login success -test pass -logout
		 * Data valid - login failed - test fail
		 * Data invalid - login sucess - test fail - logout
		 * Data invalid - login failed -test pass 
		 */
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				mac.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				mac.clickLogout();
				Assert.assertTrue(false);

			}
			else
			{
				Assert.assertTrue(true);
			}
		}	
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		Thread.sleep(3000);
		logger.info("******Finished TC_003_LoginDDT *****");
		
		}
}


