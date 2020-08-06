package com.growgen.qa.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.growgen.qa.base.BasePage;

public class JavascriptUtil extends BasePage {
	
	static JavascriptExecutor js;
	
	public static void scrollByHeight() {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
	}

	public static void scrollUp()
	{
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-500)");
	}
	
	public static void scrollDown()
	{
		js = (JavascriptExecutor) driver;
		js.executeScript("0,document.body.scrollHeight");
	}
	
	public static void clickElementByJS(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", element);

	}
	
	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void sendKeysUsingJSWithId(String id, String value) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("document.getElementById('" + id + "').value='" + value + "'");
	}

	public void sendKeysUsingJSWithName(String name, String value) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("document.getElementByName('" + name + "').value='" + value + "'");
	}
}
