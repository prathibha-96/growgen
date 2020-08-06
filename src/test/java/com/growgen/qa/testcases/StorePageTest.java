package com.growgen.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.growgen.qa.base.BasePage;
import com.growgen.qa.pages.HomePage;
import com.growgen.qa.pages.LoginPage;
import com.growgen.qa.pages.StoresPage;

public class StorePageTest extends BasePage
{
	LoginPage loginPage;
	HomePage homePage;
	StoresPage storePage;
	
	
	public StorePageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		loginPage= new LoginPage(driver);
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		storePage = new StoresPage(driver);
	}

	@Test
	public void VerifyStoreSelected() throws InterruptedException
	{
		Thread.sleep(3000);
		storePage.clickOnStoreLink();
		Thread.sleep(3000);
		storePage.clickOnFindOtherStore();
		Thread.sleep(3000);
		storePage.selectStore1();
		Thread.sleep(4000);
	}
	
	@AfterMethod
	public void tear() throws InterruptedException
	{
		Thread.sleep(3000);
		loginPage.clickOnLogOut();
	}
}
