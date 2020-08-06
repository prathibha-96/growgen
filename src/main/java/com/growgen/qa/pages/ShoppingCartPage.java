package com.growgen.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.growgen.qa.util.Constants;
import com.growgen.qa.util.JavascriptUtil;

public class ShoppingCartPage
{
	WebDriver driver;
	
	@FindBy(xpath="//span[@class='base']") WebElement shoppingCartLogoImage;
	
	@FindBy(xpath="(//select[@class='select super-attribute-select _disabled configurable-option'])[1]") WebElement chooseOption;
	
	@FindBy(xpath="(//div[@class='product-item']/label//input[@type='checkbox'])[1]") WebElement checkBox;
	
	@FindBy(xpath="//div[@class='actions-primary']") WebElement addToCart;
	
	@FindBy(xpath="//table[@class='data table totals']//td[@class='amount']//span") List<WebElement> table;
	
	@FindBy(xpath="//header[@class='modal-header']//button[@class='action-close']") WebElement close;
	
	@FindBy(xpath="//table[@class='data table totals']//tr[@class='grand totals']//td//span") WebElement orderTotal;
	
	@FindBy(xpath="//span[contains(text(),'Go to Checkout')]") WebElement checkOut;
	
	@FindBy(xpath="(//a[@class='action action-delete'])[2]") WebElement removeLink;
	
	@FindBy(xpath="(//div[@class='delivery_pickup  '])[1]") WebElement pickUpLink;
	
	public ShoppingCartPage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean validateShoppingCartImage()
	{
		return shoppingCartLogoImage.isDisplayed();
	}

	public void selectPickUpLink()
	{
		Constants.webDriverWait(pickUpLink);
	}
	
	public void selectPromoShirt() 
	{
		
		try 
		{
			
			WebDriverWait wait= new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='header']")));
			
			//clicking on check box
			//driver.findElement(By.xpath("//input[@class='aw-afptc-input-checkbox")).click();
			
			checkBox.click();
			
			//selecting dropdown menu
			Select sel= new Select(chooseOption);
			List<WebElement> allOption=sel.getOptions();
			int sizee = allOption.size();
			System.out.println("size is "+sizee);
			sel.selectByVisibleText("Medium");
			
			//add to cart
			Constants.webDriverWait(addToCart);
			close.click();
		}
		catch(Exception e)
		{
			System.out.println("popup is not present");
		}
	}

	public void getCalculation() throws InterruptedException
	{

	    JavascriptExecutor js= (JavascriptExecutor)driver;
	    js.executeScript("window.scrollBy(0,300)");
	    
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
	
	public CheckOutPage clickOnCheckOut()
	{
		Constants.webDriverWait(checkOut);
		
		return new CheckOutPage(driver);
	}
	
	public void clickOnRemoveProduct()
	{
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeAsyncScript("window.scrollBy(0,800)");
		JavascriptUtil.clickElementByJS(removeLink);
	
	}
	
}
