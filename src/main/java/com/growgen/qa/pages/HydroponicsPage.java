package com.growgen.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.growgen.qa.util.JavascriptUtil;

public class HydroponicsPage 
{
	WebDriver driver;
	
	@FindBy(xpath="//li[@class='item']//a[contains(text(),'Water Pumps')]") WebElement waterPumpProductLink;
	
	@FindBy(xpath="(//button[@class='action addtoCart actions-secondary'])[5]") WebElement fifthCartIconLink;
	
	@FindBy(xpath="(//button[@class='action addtoCart actions-secondary'])[2]") WebElement firstCartIconLink;
	
	@FindBy(xpath="(//button[@class='action addtoCart actions-secondary'])[10]") WebElement tenthCartIconLink;
	
	@FindBy(xpath="(//button[@class='action addtoCart actions-secondary'])[12]") WebElement twelthCartIconLink;
	
	@FindBy(xpath="//button[@class='action-primary action-accept']") WebElement alertBtn;
	
	public HydroponicsPage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnWaterPumpProduct()
	{
		waterPumpProductLink.click();
	}
	
	public void clickOnFifthCartIcon()
	{
		try {
		JavascriptUtil.scrollByHeight();
		fifthCartIconLink.click();
		}
		catch(Exception e)
		{
			alertBtn.click();
		}
	}
	
	public void clickOnFirstCartIcon()
	{
		try
		{
		JavascriptUtil.scrollUp();
		firstCartIconLink.click();
		}
		catch(Exception e)
		{
			alertBtn.click();
		}
	}
	
	public void addLastProduct()
	{
		try
		{
			JavascriptUtil.scrollDown();
			tenthCartIconLink.click();
			twelthCartIconLink.click();	
		}
		catch(Exception e)
		{
			alertBtn.click();
		}
	}

}
