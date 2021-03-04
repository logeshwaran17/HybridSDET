package com.sdet.testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.sdet.pageObjects.BaseClass;
import com.sdet.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	
	@Test
	public void loginTest() throws IOException {
	
		
		
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		
		logger.info("Entered username");
		lp.setPassword(password);
		logger.info("Entered password");
		
		lp.clickLogin();
		logger.info("Clicked Login button");
				
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			logger.info("Login Test Passed");
			
		}
		else {
			logger.info("Login Test Failed");
			BaseClass.captureScreen(driver, "loginTest");
			Assert.assertTrue(false);
			
		}
	}

}
