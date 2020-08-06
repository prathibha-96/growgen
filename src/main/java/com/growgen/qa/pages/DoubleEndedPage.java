package com.growgen.qa.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.growgen.qa.util.Constants;
import com.growgen.qa.util.JavascriptUtil;

public class DoubleEndedPage 
{
	WebDriver driver;
	
	@FindBy(xpath="//li[contains(@class,'item product product-item nth-child-2n nth-child-3np1 nth-child-4n')]//button[contains(@class,'action addtoCart actions-secondary')]") 
	WebElement sunSystemCartIcon;
	
	@FindBy(xpath="//li[contains(@class,'item product product-item nth-child-2n nth-child-3np1 nth-child-4n')]//button[contains(@class,'action addtoCart actions-secondary')]")
	WebElement gavitaProCartIcon;
	
    @FindBy(xpath="(//button[@class='action addtoCart actions-secondary'])[5]") WebElement fifthCartIconLink;
	
	@FindBy(xpath="(//button[@class='action addtoCart actions-secondary'])[2]") WebElement firstCartIconLink;
	
	@FindBy(xpath="//button[@class='action-primary action-accept']") WebElement alertBtn;
	
	public DoubleEndedPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
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
	
	public void clickOnCartIcon()
	{
		 Constants.webDriverWait(sunSystemCartIcon);
		 try
		 {
			 Alert a= driver.switchTo().alert();
			 a.accept();
		 }
		 catch(Exception e)
		 {
			 System.out.println("no popup is present");
		 }
	}
	
	
}
