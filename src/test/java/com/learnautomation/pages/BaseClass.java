package com.learnautomation.pages;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.learnautomation.utility.BrowserFactory;
import com.learnautomation.utility.ConfigDataProvider;
import com.learnautomation.utility.ExcelDataProvider;
import com.learnautomation.utility.Helper;

public class BaseClass {

	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;
	
	@BeforeSuite
	public void setUpSuite() 
	{
		Reporter.log("Setting up reports and Test is getting ready", true); // Custom log, just for my information, not for test report
		
		excel = new ExcelDataProvider();
		config = new ConfigDataProvider();
		
		//ExtentHtmlReporter html = new ExtentHtmlReporter("Extent.html"); // Depricated
		ExtentSparkReporter spatkReporter = new ExtentSparkReporter(new File(System.getProperty("user.dir")+ "/Reports/FreeCRM_"+Helper.getCurrentDateTime()+".html"));
		report = new ExtentReports();
		report.attachReporter(spatkReporter);
	}
	
	@Parameters({"browser", "urlToBeTested"})//if there is more than one parameter needs curly brackets
	@BeforeClass
	public void setup(String browser, String url) {
		//driver = BrowserFactory.startApplication(driver, "Chrome", "https://www.freecrm.com/index.html"); //hardcoded browser name
		//driver = BrowserFactory.startApplication(driver, config.getBrowser(), config.getStagingURL()); //read data from config file
		driver = BrowserFactory.startApplication(driver, browser, url); //read browser name from pom.xml
	}
	
	@AfterClass
	public void tearDown() {
		//BrowserFactory.quitBrowser(driver);
	}
	
	@AfterMethod
	public void tearDownMethod(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE) {
			//Helper.captureScreenshot(driver); this is used before logger
			
			logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		else if (result.getStatus()==ITestResult.SUCCESS) {
			logger.pass("Test passed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		else if (result.getStatus()==ITestResult.SKIP) {
			logger.skip("Test skipped", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		
		report.flush();
	}
}
