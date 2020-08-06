package com.growgen.qa.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.growgen.qa.base.BasePage;
import com.growgen.qa.pages.CheckOutPage;
import com.growgen.qa.pages.DoubleEndedPage;
import com.growgen.qa.pages.GrowLightsPage;
import com.growgen.qa.pages.HomePage;
import com.growgen.qa.pages.HydroponicsPage;
import com.growgen.qa.pages.LoginPage;
import com.growgen.qa.pages.MiniCartPage;
import com.growgen.qa.pages.ShoppingCartPage;
import com.growgen.qa.pages.StoresPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class CheckOutPageTest extends BasePage
{
	LoginPage loginPage;
	HomePage homePage;
	 GrowLightsPage growLightPage;
	 DoubleEndedPage doubleEndedPage;
	 ShoppingCartPage shoppingCartPage;
	 CheckOutPage checkOutPage;
	 MiniCartPage miniCartPage;
	 HydroponicsPage hydroPonicPage;
	 StoresPage storesPage;
	 
	public CheckOutPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException
	{
		 loginPage= new LoginPage(driver);
		 loginPage.clickOnMyAccountLink();
		 homePage=loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		 storesPage = new StoresPage(driver);
		 storesPage.clickOnStoreLink();
		 storesPage.clickOnFindOtherStore();
		 storesPage.selectStore1();
		 Thread.sleep(3000);
		 homePage.clickOnProductsLink();
		 hydroPonicPage=homePage.clickOnHydroPonicLink();
		 hydroPonicPage.clickOnWaterPumpProduct();
		 try 
		 {
		      hydroPonicPage.clickOnFifthCartIcon();
		 }catch(Exception e)
		 {
			 System.out.println("no popup present to accept");
			
		 }
		 try 
		 {
		     hydroPonicPage.clickOnFirstCartIcon();
		 }
		 catch(Exception e)
		 {
			 
		 }
		 miniCartPage = new MiniCartPage(driver);
		 miniCartPage.clickOnMiniCartIcon();
		 shoppingCartPage=miniCartPage.clickOnViewAndEditIcon();
		 
	}
	
	@Test(description="verify checkout calculation")
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description : Verify checkout calculation test")
	public void checkOutCalculationTest() throws InterruptedException
	{
		 shoppingCartPage.selectPromoShirt();
		 Thread.sleep(3000);
		 shoppingCartPage.clickOnRemoveProduct();
		 shoppingCartPage.getCalculation();
		 Thread.sleep(4000);
		 checkOutPage=shoppingCartPage.clickOnCheckOut();
		 Thread.sleep(4000);
		 checkOutPage.shippedTheseItemsCalculation();
		 Thread.sleep(3000);
		 checkOutPage.orderTotal();
		 Thread.sleep(3000);
		 driver.navigate().back();
		 Thread.sleep(3000);
		 shoppingCartPage.getCalculation();
		 Thread.sleep(4000);
	}

}
