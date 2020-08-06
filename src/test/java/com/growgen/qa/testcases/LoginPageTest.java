package com.growgen.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.growgen.qa.base.BasePage;
import com.growgen.qa.listeners.AllureListener;
import com.growgen.qa.pages.HomePage;
import com.growgen.qa.pages.LoginPage;
import com.growgen.qa.util.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners({AllureListener.class})
public class LoginPageTest extends BasePage
{
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setUp()
	{
	    loginPage = new LoginPage(driver);
	    loginPage.clickOnMyAccountLink();
	}
	
	@Test(priority=1, description="verifying login page title test")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Description : Verify login page title test")
	public void loginPageTitleTest()
	{
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);	
	}
	
	
	@Test(priority=2, description="verifying grow generatio logo test")
	@Severity(SeverityLevel.NORMAL)
	@Description("Test Description : Verify growgen logo is displayed test")
	public void growGenLogoImageTest()
	{
		boolean flag = loginPage.validateGrowGenLogo();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=3, description="verify login into app test")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Description : Verify login page test into application with correct credentials")
	public void loginTest()
	{
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
//	@AfterMethod
//	public void tear()
//	{
//		loginPage.clickOnLogOut();
//	}
}
