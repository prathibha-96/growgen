package com.growgen.qa.pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.growgen.qa.util.Constants;

import io.qameta.allure.Step;

public class CheckOutPage  
{
	public WebDriver driver;
	JavascriptExecutor js;
	
	@FindBy(xpath="//div[@class='shipped-line-items']/div//div/li//div[@class='product']//div/div//div[@class='subtotal']//span[@class='price']") List<WebElement> itemsTable;
	
	@FindBy(xpath="//table[@class='data table table-totals']//td") List<WebElement> table;
	
	@FindBy(xpath="//tr[@class='totals sub']//td/span[@class='price']") WebElement cartSubTotal;
	
	@FindBy(xpath="//tr[@class='grand totals']//td//span") WebElement orderTotal;
	
	@FindBy(xpath="//button[@class='button action continue primary']") WebElement proceedToPay;
	
	public CheckOutPage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@Step("verify ship these items calculation")
	public void shippedTheseItemsCalculation()
	{
		int countTotal=itemsTable.size();
		System.out.println("size of ship here item is " +countTotal);
		int temp=0;
		float sum=0;
		for(int i=0;i<countTotal-1;i++)
		{
			WebElement cel=itemsTable.get(i);
			String t=cel.getText().toString();
			String text2=t.replaceAll("[$,]", "");
			Float n=Float.parseFloat(text2);
			System.out.println(n);
			sum++;
			sum=sum+n;			
		}
		System.out.println("count : "+temp);
		System.out.println("sum : "+sum);
		
		String total = cartSubTotal.getText();
		String cartTotal=total.replaceAll("[$,]","");
		System.out.println("cart sub-total is " + cartTotal);
		
		Float num = Float.parseFloat(cartTotal);
		
		if(sum==num)
		{
			Reporter.log(sum+"="+num,true);
		}
		else
		{
			Reporter.log(sum+" != " +num,true);
		}
	}

	@Step("check out calculation..")
	public void orderTotal() throws InterruptedException
	{
		JavascriptExecutor js= (JavascriptExecutor)driver;
	    js.executeScript("window.scrollBy(0,400)");
		int count=table.size();
		System.out.println(count);
		int temp=0;
		float sum=0;
		for(int i=0;i<count-1;i++)
		{
			WebElement cell=table.get(i);
			String text=cell.getText();
			try {
			String text1=text.replaceAll("[$,]", "");
			Float n=Float.parseFloat(text1);
			System.out.println(n);
			temp++;
			sum=sum+n;
			}
			catch(Exception e)
			{
				System.out.println("");
			}
		}
		System.out.println("count : "+temp);
		System.out.println("sum : "+sum);
		
		String total = orderTotal.getText();
		String orderTotal=total.replaceAll("[$,]","");
		System.out.println("order total is " + orderTotal);
		
		Float n2 = Float.parseFloat(orderTotal);
		
		if(sum==n2)
		{
			Reporter.log(sum+"="+n2,true);
		}
		else
		{
			Reporter.log(sum+" != " +n2);
		}
		
	}
	
	public PaymentPage clickOnProceedToPay()
	{
		js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		Constants.webDriverWait(proceedToPay);
		
		return new PaymentPage(driver);
	}

}
