package com.learnautomation.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

	public static WebDriver startApplication(WebDriver driver, String browserName, String appURL) {
		if(browserName.equals("Chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if (browserName.equals("Firefox")) 
		{
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}else if (browserName.equals("IE")) 
		{
			System.setProperty("webdriver.chrome.driver", "D:\\eclipse-workspace\\SeleniumDemo\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}else 
		{
			System.out.println("We do not supoort thi browser");
		}
		
		//driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(appURL);
		//Need wait in case some elements are loading slowly
		
		return driver;
	}
	
	public static void quitBrowser(WebDriver driver) {
		driver.quit();
	}
}
