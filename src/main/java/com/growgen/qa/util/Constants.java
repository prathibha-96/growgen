package com.growgen.qa.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.growgen.qa.base.BasePage;

public class Constants extends BasePage
{
	static WebDriverWait wait;
	
	public static long PAGE_LOAD_TIMEOUT = 30;
	
	public static long IMPLICIT_WAIT = 10;
	
	public static final String LOGIN_PAGE_TITLE = "Customer Login | Discounted Hydroponic Commercial Indoor & Outdoor Grow Supplies at GrowGeneration";
	
	public static final String HOME_PAGE_TITLE = "My Account | Discounted Hydroponic Commercial Indoor & Outdoor Grow Supplies at GrowGeneration";

	public static final String DOUBLE_ENDED_PAGE_TITLE="Double Ended (DE) - Grow Lights - Products | Discounted Hydroponic Commercial Indoor & Outdoor Grow Supplies at GrowGeneration";

	public static void webDriverWait(WebElement target)
	{
		wait = new WebDriverWait(driver,50);
		wait.until(ExpectedConditions.elementToBeClickable(target)).click();
	}
	
	public static String webDriverWaitTitle(String target)
	{
		wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.titleIs(target));
		return target;	
	}
	
	public static void actionsClass(WebElement target) 
	{
		Actions act= new Actions(driver);
		act.moveToElement(target).build().perform();	
	}
}
