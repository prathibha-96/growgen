package com.growgen.qa.testcases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.growgen.qa.base.BasePage;
import com.growgen.qa.pages.GrowLightsPage;
import com.growgen.qa.pages.HomePage;
import com.growgen.qa.pages.LoginPage;
import com.growgen.qa.util.Constants;

public class HomePageTest extends BasePage
{
	LoginPage loginPage;
	HomePage homePage;
	GrowLightsPage growLightsPage;
	
	public HomePageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		loginPage = new LoginPage(driver);
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));	
	}
	
	@Test(priority=1)
	public void verifyHomePageTitleTest()
	{
		String title = homePage.verifyHomePageTitle();
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
		Reporter.log("Home page title is verified", true);
	}

	@Test(priority=2)
	public void verifyUserNameTest()
	{
		boolean flag = homePage.verifyCorrectUserName();
		Assert.assertTrue(flag);
		Reporter.log("username is correct", true);
	}
	
	@Test(priority=3)
	public void verifyGrowLightsLinkTest()
	{
		homePage.clickOnProductsLink();
		homePage.clickOnGrowLightsLink();
	}
	
	@AfterMethod
	public void tear()
	{
		loginPage.clickOnLogOut();
	}
}
