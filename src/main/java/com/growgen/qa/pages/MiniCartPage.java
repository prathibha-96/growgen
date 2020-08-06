package com.growgen.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.growgen.qa.util.Constants;

public class MiniCartPage 
{
    WebDriver driver;
	
	@FindBy(xpath="//a[@class='action showcart']") WebElement miniCartIcon;
	
	@FindBy(xpath="//span[contains(text(),'View and edit cart')]") WebElement viewAndEditCart;
	
	@FindBy(xpath="//div[@class='items-total']") WebElement totalCount;
	
	public MiniCartPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnMiniCartIcon()
	{
		miniCartIcon.click();
	}
	
	public void miniCartCount()
	{
		
	}
	
	public ShoppingCartPage clickOnViewAndEditIcon()
	{
		WebDriverWait wait= new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'View and edit cart')]")));
		viewAndEditCart.click();
		return new ShoppingCartPage(driver);
	}

	
}
