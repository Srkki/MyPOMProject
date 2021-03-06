package com.learnautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	WebDriver driver;
	
	public LoginPage(WebDriver ldriver) {
		this.driver = ldriver;
	}
	
	//@FindBy(name = "username") WebElement uname;
	//@FindBy(name = "password") WebElement pass;
	//@FindBy(xpath="//input[@value='Login']") WebElement loginButton;
	
	
	@FindBy(name = "email") WebElement uname;
	@FindBy(name = "password") WebElement pass;
	@FindBy(xpath="//a[contains(@href, 'https://ui.cogmento.com')]") WebElement loginButton;
	@FindBy(xpath="//div[@value='Login']") WebElement loginBtn;
	
	public void loginToCRM(String usernameApp, String passwordApp) {
		loginButton.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		uname.sendKeys(usernameApp);
		pass.sendKeys(passwordApp);
		//loginBtn.click(); //need valid credentials for login (email/password)
	}
}
