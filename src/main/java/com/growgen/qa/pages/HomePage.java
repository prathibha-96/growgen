package com.growgen.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.growgen.qa.util.Constants;

public class HomePage 
{
	WebDriver driver;
	
	@FindBy(xpath="//span[contains(text(),'Logged in as prathibha p')]") WebElement loggedInUserName;
	
	@FindBy(css="div.section-item-content.nav-sections-item-content>nav>ul>li:first-of-type") WebElement productsLink;
	
	@FindBy(css="ul.subchildmenu.col-md-8.mega-columns.columns1>li:nth-of-type(1)") WebElement growLights;
	
	@FindBy(css="ul.subchildmenu.col-md-8.mega-columns.columns1>li:nth-of-type(2)") WebElement hydroPonics;
	
	@FindBy(css="ul.subchildmenu.col-md-8.mega-columns.columns1>li:nth-of-type(3)") WebElement nutrients;
	
	@FindBy(css="ul.subchildmenu.col-md-8.mega-columns.columns1>li:nth-of-type(4)") WebElement climateControl;
	
	@FindBy(xpath="//a[@class='logo']") WebElement growGenLogo;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnGenProLogo()
	{
		Constants.webDriverWait(growGenLogo);
	}
	
	public String verifyHomePageTitle()
	{
		return Constants.webDriverWaitTitle(driver.getTitle());
	}
	
	public boolean verifyCorrectUserName()
	{
		return loggedInUserName.isDisplayed();
	}
	
	public void clickOnProductsLink()
	{
		Constants.actionsClass(productsLink);
	}
	
	public GrowLightsPage clickOnGrowLightsLink()
	{
		Constants.webDriverWait(growLights);
		return new GrowLightsPage(driver);
	}
	
	public HydroponicsPage clickOnHydroPonicLink()
	{
		Constants.webDriverWait(hydroPonics);
		return new HydroponicsPage(driver);
	}
	
	
}
