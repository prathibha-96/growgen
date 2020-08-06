package com.growgen.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.growgen.qa.util.Constants;

public class GrowLightsPage 
{
	WebDriver driver;
	
	@FindBy(xpath="//a[contains(@class,'cat_url')][contains(text(),'Double Ended (DE)')]") WebElement doubleEndedLink;
	
	
	
	public GrowLightsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public DoubleEndedPage clickOnDoubleEndedLink()
	{
		Constants.webDriverWait(doubleEndedLink);
		return new DoubleEndedPage(driver);
	}
	
	
	
	
	
}
