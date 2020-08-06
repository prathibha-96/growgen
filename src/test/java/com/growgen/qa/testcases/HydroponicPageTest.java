package com.growgen.qa.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.growgen.qa.base.BasePage;
import com.growgen.qa.pages.HomePage;
import com.growgen.qa.pages.HydroponicsPage;
import com.growgen.qa.pages.LoginPage;
import com.growgen.qa.pages.MiniCartPage;
import com.growgen.qa.pages.ShoppingCartPage;
import com.growgen.qa.pages.StoresPage;

public class HydroponicPageTest extends BasePage
{
	LoginPage loginPage;
	HomePage homePage;
	HydroponicsPage hydroPonicPage;
	MiniCartPage minicartPage;
	ShoppingCartPage shoppingCartPage;
	StoresPage storesPage;
	
	public HydroponicPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
		  loginPage = new LoginPage(driver);
		  loginPage.clickOnMyAccountLink();
		  homePage= loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		  storesPage = new StoresPage(driver);
		  storesPage.clickOnStoreLink();
		  storesPage.clickOnFindOtherStore();
		  storesPage.selectStore1();
		  
	}
	
	@Test
	public void selectProductTest() throws InterruptedException
	{
		Thread.sleep(3000);
		homePage.clickOnProductsLink();
		hydroPonicPage=homePage.clickOnHydroPonicLink(); 
		hydroPonicPage.clickOnWaterPumpProduct();
		
		hydroPonicPage.clickOnFifthCartIcon();
		Thread.sleep(3000);
		hydroPonicPage.clickOnFirstCartIcon();
		Thread.sleep(3000);
		storesPage.clickOnStoreLink();
		storesPage.clickOnFindOtherStore();
		storesPage.selectStore2();
		minicartPage= new MiniCartPage(driver);
		minicartPage.clickOnMiniCartIcon();
		Thread.sleep(3000);
		shoppingCartPage= minicartPage.clickOnViewAndEditIcon();
		Thread.sleep(3000);
	}

}
