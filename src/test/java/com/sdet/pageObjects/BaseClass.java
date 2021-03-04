package com.sdet.pageObjects;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.sdet.utilities.ReadConfig;

import org.testng.annotations.AfterClass;

import io.github.bonigarcia.wdm.WebDriverManager; // config.properties to Readconfig.java to BaseClass

public class BaseClass {
	
	ReadConfig readconfig=new ReadConfig();   // creating object for readconfig java class
	
	
	public String baseURL=readconfig.getApplicatonURL();
	public String username=readconfig.getUserName();
	public String password=readconfig.getPassword();
	public static WebDriver driver;
	
	public static Logger logger;
	
	@Parameters("browser")  // or executing in desired browser
	@BeforeClass
	public void setup(String br) {
		
		
		logger=Logger.getLogger("eBanking"); 
		PropertyConfigurator.configure("Log4j.properties");
		
		if(br.equals("chrome")) {
		
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		
		else if(br.equals("firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(br.equals("ie")){
		
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
			
		}
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseURL);
	//	driver.manage().window().maximize();
		logger.info("URL is opened");
		
	}
	@AfterClass
	public void tearDown() {
		driver.quit();
		logger.info("Browser closed");
		

	}
	
	public static void captureScreen(WebDriver driver,String tname) throws IOException {
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
		

	}
}
