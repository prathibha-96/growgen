package com.growgen.qa.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.growgen.qa.base.BasePage;
import com.growgen.qa.pages.CheckOutPage;
import com.growgen.qa.pages.DoubleEndedPage;
import com.growgen.qa.pages.GrowLightsPage;
import com.growgen.qa.pages.HomePage;
import com.growgen.qa.pages.LoginPage;
import com.growgen.qa.pages.PaymentPage;
import com.growgen.qa.pages.ShoppingCartPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class PaymentPageTest extends BasePage
{
	 LoginPage loginPage;
	 HomePage homePage;
	 GrowLightsPage growLightPage;
	 DoubleEndedPage doubleEndedPage;
	 ShoppingCartPage shoppingCartPage;
	 CheckOutPage checkOutPage;
	 PaymentPage paymentPage;
	 
	public PaymentPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException
	{
		 loginPage= new LoginPage(driver);
		 loginPage.clickOnMyAccountLink();
		 homePage=loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		 homePage.clickOnProductsLink();
		 growLightPage=homePage.clickOnGrowLightsLink();
		 doubleEndedPage=growLightPage.clickOnDoubleEndedLink();
		
		 shoppingCartPage.selectPromoShirt();
		 checkOutPage=shoppingCartPage.clickOnCheckOut();
		 paymentPage=checkOutPage.clickOnProceedToPay();
		 Thread.sleep(3000);
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object data[][]= {{"4111111111111111","2","2023","023"},
				{"4111111111111111"," "," "," "}
				};
		return data;
				
	}
	
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Test Description : Verify payment test")
	@Test(priority=1,dataProvider="getData", description="verifying payment test")
	public void verifyPaymentTest(String creditCardnum, String month, String year, String cvvNum) throws InterruptedException
	{
		paymentPage.validateCreditCardDetails(creditCardnum, month, year, cvvNum);
		Thread.sleep(3000);
	}

}
