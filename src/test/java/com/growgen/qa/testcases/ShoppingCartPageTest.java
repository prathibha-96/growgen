package com.growgen.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.growgen.qa.base.BasePage;
import com.growgen.qa.listeners.AllureListener;
import com.growgen.qa.pages.DoubleEndedPage;
import com.growgen.qa.pages.GrowLightsPage;
import com.growgen.qa.pages.HomePage;
import com.growgen.qa.pages.HydroponicsPage;
import com.growgen.qa.pages.LoginPage;
import com.growgen.qa.pages.MiniCartPage;
import com.growgen.qa.pages.ShoppingCartPage;
import com.growgen.qa.pages.StoresPage;
import com.growgen.qa.util.JavascriptUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;


@Listeners({AllureListener.class})
public class ShoppingCartPageTest extends BasePage
{
	LoginPage loginPage;
	HomePage homePage;
	GrowLightsPage growLightsPage;
	DoubleEndedPage doubleEndedPage;
	ShoppingCartPage shoppingCartPage;
	MiniCartPage miniCart;
	HydroponicsPage hydroPonicsPage;
	StoresPage storesPage;
	
	public ShoppingCartPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException
	{
		loginPage = new LoginPage(driver);
		loginPage.clickOnMyAccountLink();
		Thread.sleep(3000);
		homePage= loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		storesPage= new StoresPage(driver);
		storesPage.clickOnStoreLink();
		storesPage.clickOnFindOtherStore();
		storesPage.selectStore1();
		homePage.clickOnProductsLink();
		growLightsPage=homePage.clickOnGrowLightsLink();
		doubleEndedPage=growLightsPage.clickOnDoubleEndedLink();
		doubleEndedPage.clickOnFirstCartIcon();
		doubleEndedPage.clickOnFifthCartIcon();
		miniCart= new MiniCartPage(driver);
		miniCart.clickOnMiniCartIcon();
		miniCart.clickOnViewAndEditIcon();
		shoppingCartPage= new ShoppingCartPage(driver);
		
	}
	
	
//	@Test(priority=2, description="verify checkout calculation")
//	@Severity(SeverityLevel.CRITICAL)
//	@Description("Test Description : Verify checkout calculation test")
//	public void shoppingCartTest() throws InterruptedException
//	{
//		shoppingCartPage.selectPromoShirt();
//		Thread.sleep(3000);
//		shoppingCartPage.getCalculation();
//	}
	
	@Test(priority=2)
	public void changingStoreTest() throws InterruptedException
	{
		shoppingCartPage.selectPromoShirt();
		shoppingCartPage.clickOnRemoveProduct();
		Thread.sleep(3000);
		JavascriptUtil.scrollUp();
		homePage.clickOnGenProLogo();
		storesPage.clickOnStoreLink();
		storesPage.clickOnFindOtherStore();
		storesPage.selectStore2();
		homePage.clickOnProductsLink();
		hydroPonicsPage=homePage.clickOnHydroPonicLink();
		hydroPonicsPage.addLastProduct();
		miniCart.clickOnMiniCartIcon();
		miniCart.clickOnViewAndEditIcon();
		shoppingCartPage.selectPromoShirt();
		shoppingCartPage.selectPickUpLink();
		Thread.sleep(3000);
		shoppingCartPage.getCalculation();
		
	}
	
	
	@AfterMethod
	public void tearDownn()
	{
		loginPage.clickOnLogOut();
	}
	
}
