package com.growgen.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.growgen.qa.util.Constants;

import io.qameta.allure.Step;

public class LoginPage 
{
	//Page Factory
	
	WebDriver driver;
	JavascriptExecutor js;
	
	@FindBy(xpath="//div[@class='custom-block']//a[contains(text(),'My Account')]") WebElement myAccountLink;
	
	@FindBy(id="email") WebElement username;
	
	@FindBy(id="pass") WebElement password;
	
	@FindBy(id="send2") WebElement signIn;
	
	@FindBy(xpath="//a[@class='logo']//img") WebElement growGenLogo;
	
	@FindBy(css="div.panel.header>ul.header.links>li:nth-of-type(2)") WebElement logOutLink;
	
	//initializing page objects
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions 
	@Step("getting login page title step...")
	public String validateLoginPageTitle()
	{
		return driver.getTitle();
	}
	
	@Step("verifying growgen logo is displayed...")
	public boolean validateGrowGenLogo()
	{
		return growGenLogo.isDisplayed();
	}
	
	public void clickOnMyAccountLink()
	{
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",myAccountLink);
	}
	
	@Step("login with username : {0} and password : {1} step...")
	public HomePage login(String user, String pass)
	{
		
	    js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,300)");
		js.executeScript("arguments[0].setAttribute('value','"+user+"');",username);
		js.executeScript("arguments[0].setAttribute('value','"+pass+"');",password);
		
		js.executeScript("arguments[0].click();",signIn);
		
		return new HomePage(driver);
	}
	
	public void clickOnLogOut()
	{
		Constants.webDriverWait(logOutLink);
	}

}
