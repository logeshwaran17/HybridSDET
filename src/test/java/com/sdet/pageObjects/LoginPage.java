package com.sdet.pageObjects;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.sdet.utilities.*;
public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="uid")
	@CacheLookup
	WebElement txtUserName;

	@FindBy(name="password")
	@CacheLookup
	WebElement txtPassword;
	

	@FindBy(name="btnLogin")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(linkText = "Log out")
	@CacheLookup
	WebElement logout;
	
	public void setUserName(String uname) {
	txtUserName.sendKeys(uname);

	}
	
	public void setPassword(String upassword) throws IOException {
	txtPassword.sendKeys(upassword);


	}
		
	public void clickLogin() {
	btnLogin.click();

	}
	
	public void clickLogout() {
		logout.click();
	}
	
	
	}
