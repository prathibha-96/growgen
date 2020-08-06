package com.growgen.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.growgen.qa.base.BasePage;
import com.growgen.qa.pages.GrowLightsPage;
import com.growgen.qa.pages.HomePage;
import com.growgen.qa.pages.LoginPage;

public class GrowLightsPageTest extends BasePage
{
	LoginPage loginPage;
	HomePage homePage;
	GrowLightsPage growLightsPage;
	
	public GrowLightsPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		  loginPage = new LoginPage(driver);
		  loginPage.clickOnMyAccountLink();
		  homePage= loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		  homePage.clickOnProductsLink();
		growLightsPage = homePage.clickOnGrowLightsLink();
		 
	}
	
	@Test(priority=1)
	public void verifyDoubleEndedPageTest()
	{
		growLightsPage.clickOnDoubleEndedLink();
	}
	
	@AfterMethod
	public void tearDownn()
	{
		loginPage.clickOnLogOut();
	}
	

}
