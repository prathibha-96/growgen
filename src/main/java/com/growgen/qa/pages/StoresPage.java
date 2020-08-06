package com.growgen.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.growgen.qa.util.Constants;

public class StoresPage 
{
	
	WebDriver driver;
	
	@FindBy(xpath="//strong[@id='Store_Name']") WebElement storeLink;
	
	@FindBy(xpath="//button[contains(text(),'Find Other Store')]") WebElement findOtherStore;
	
	@FindBy(xpath="//select[@id='selectstore']") WebElement selectStore;
	
	@FindBy(xpath="//table[@id='Store_Details']//tr[@class='storetop  25']//td/button[contains(text(),'SELECT')]") WebElement selectBtn2;
	
	@FindBy(xpath="//table[@id='Store_Details']//tr[@class='storetop  9']//td/button[contains(text(),'SELECT')]") WebElement selectBtn1;
	
	public StoresPage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnStoreLink()
	{
		Constants.actionsClass(storeLink);
	}
	
	public void clickOnFindOtherStore()
	{
		Actions act = new Actions(driver);
		act.moveToElement(findOtherStore).click().perform();
	}
	
	public void selectStore1()
	{
		Select sel = new Select(selectStore);
		sel.selectByVisibleText("CONIFER, CO");
		selectBtn1.click();
	}
	
	public void selectStore2()
	{
		Select sel = new Select(selectStore);
		sel.selectByVisibleText("BREWER, ME");
		selectBtn2.click();
	}

}
