package com.sdet.testCases;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jsoup.Connection.Base;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sdet.pageObjects.BaseClass;
import com.sdet.pageObjects.LoginPage;
import com.sdet.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {
	
	@Test(dataProvider = "LoginData")
	public void loginDDT(String username,String password) throws IOException, InterruptedException, AWTException { // data from data provider
		
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Username provided");
		
		lp.setPassword(password);
		logger.info("Password provided");
		
		lp.clickLogin();
		logger.info("Login Clicked");
		
		if (isAlertPresent() == true) { // means Login failed
			
			
			
			Thread.sleep(2000);
			driver.switchTo().alert().accept();
			
			
			
			
			driver.switchTo().defaultContent(); // focuses to main page
			BaseClass.captureScreen(driver, "loginDDT_fail");
			
			logger.warn("Login Failed");
			Assert.assertTrue(false);
	
			
			
		}
		else
		{
			BaseClass.captureScreen(driver, "loginDDT_pass");
			Assert.assertTrue(true);
			logger.info("Login passed");
			
			lp.clickLogout();
			Thread.sleep(2000);
			driver.switchTo().alert().accept();   // closes the log out alert
			driver.switchTo().defaultContent();
		}
		
		
		
		
		

	}
	
	//userdefined method to verify whether alert is present or not
	
	public static boolean isAlertPresent() {  // when entered wrong credentials ..alert is generated
		
		try {
			driver.switchTo().alert();
	
			return true;
		} catch (Exception e){ //NoAlertPresentException
			return false;
		}
		
	}
	
	
	
	
	
	
	
	
	
	

	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException {
		
		String path = System.getProperty("user.dir") + "/src/test/java/com/sdet/testData/loginCred.xlsx";
		// get no. of rows and columns

		int rownum = XLUtils.getRowCount(path, "Sheet1"); // no. of rows-1
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1); // no. of columns

		String loginData[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) { // last rown num is total-1 row
			for (int j = 0; j < colcount; j++) {

				loginData[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j); // login array 0 row <----- login xcel
																					// 1 row

			}

		}

		return loginData;   // create as each test case== no. of inputs

	}

}
