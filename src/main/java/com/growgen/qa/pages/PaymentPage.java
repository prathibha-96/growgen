package com.growgen.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.growgen.qa.util.Constants;

import io.qameta.allure.Step;

public class PaymentPage
{
	WebDriver driver;
	Select sel;
	
	@FindBy(id="anet_creditcard_cc_number") WebElement creditCardField;
	
	@FindBy(name="payment[cc_exp_month]") WebElement month;
	
	@FindBy(name="payment[cc_exp_year]") WebElement year;
	
	@FindBy(name="payment[cc_cid]") WebElement cvv;
	
	@FindBy(xpath="//div[@class='checkout-agreement field choice required']//input[@type='checkbox']") WebElement checkBox;
	
	@FindBy(xpath="//span[contains(text(),'Place Order')]") WebElement placeOrder;
	
	public PaymentPage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}

	@Step("verify payment details credentials entered..")
	public void validateCreditCardDetails(String num,String m,String y, String number )
	{
		creditCardField.sendKeys(num);
		sel= new Select(month);
		sel.selectByValue(m);
	    sel=new Select(year);
		sel.selectByVisibleText(y);
		cvv.sendKeys(number);
		Constants.webDriverWait(checkBox);
		Constants.webDriverWait(placeOrder);
		
	}
}
