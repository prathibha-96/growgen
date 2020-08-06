package com.growgen.qa.testcases;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.growgen.qa.base.BasePage;
import com.growgen.qa.pages.DoubleEndedPage;
import com.growgen.qa.pages.GrowLightsPage;
import com.growgen.qa.pages.HomePage;
import com.growgen.qa.pages.LoginPage;
import com.growgen.qa.util.Constants;

public class DoubleEndedPageTest extends BasePage
{
	LoginPage loginPage;
	HomePage homePage;
	 GrowLightsPage growLightsPage;
	 DoubleEndedPage doubleEndedPage;
	public DoubleEndedPageTest()
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
	  growLightsPage= homePage.clickOnGrowLightsLink();
	  doubleEndedPage = growLightsPage.clickOnDoubleEndedLink();
	  
	}
	
	@Test(priority=1)
	public void clickOnCartIcon()
	{
		
		 
		 
	}
	

	@AfterMethod
	public void tearDownn()
	{
		loginPage.clickOnLogOut();
	}
}
