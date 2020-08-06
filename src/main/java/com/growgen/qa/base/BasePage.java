package com.growgen.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.growgen.qa.util.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage 
{
	public static WebDriver driver;
    public static Properties prop;
	
	public BasePage()
	{
		
		try {
			prop = new Properties();
			FileInputStream ip= new FileInputStream("./src/main/java/com/growgen/qa/config/configuration.pro.properties");
			prop.load(ip);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//to run your selenium’s tests in parallel, Webdriver object should be thread-safe, i.e. a single object can be used with multiple threads at the same time without causing problems. 
	//thread local driver object for webdriver,
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
	
	//multithreading concept 
	public static synchronized WebDriver getDriver()
	{
		return tdriver.get();
	}
	
	@BeforeClass
	public static WebDriver initialization() 
	{
		
		String browserName=prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("Edge"))
		{
			WebDriverManager.iedriver().setup();
			driver= new InternetExplorerDriver();
		}
		
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
		tdriver.set(driver);
		return getDriver();
		
	}
	
	@AfterClass
	public static void tearDown()
	{
		driver.quit();
	}
	
	/***
	 * 
	 * screenshot
	 * 
	 *
	 */
	
	public String getScreenshot() throws IOException, InterruptedException
	{
		
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("screenshot captured failed...");
		}

		return path;
	}
	
	
	
}
