package com.sdet.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.util.PermissionUtils.FileType;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.sdet.pageObjects.BaseClass;

import freemarker.template.SimpleDate;

public class Reporting extends TestListenerAdapter { // listener class used to generate extent reports  // integrate this in xml file
	
	public ExtentHtmlReporter htmlreport;
	public ExtentReports reporter;
	public ExtentTest logger;
	
	
	
	@Override
	public void onStart(ITestContext testContext) {
		String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());  // timestamp
		String reportName="Test Report-"+timeStamp+".html";  // create file name with latest time stamp
		htmlreport=new ExtentHtmlReporter("./test-output/"+reportName); // every report comes with new time stamp
		htmlreport.config().setTheme(Theme.DARK);
		htmlreport.config().setDocumentTitle("eBanking Report logs");
		htmlreport.config().setReportName("Extent Report file");
		htmlreport.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlreport.setAppendExisting(true);
		
		reporter=new ExtentReports();
		reporter.attachReporter(htmlreport);
		
		reporter.setSystemInfo("OS", "Windows");
		reporter.setSystemInfo("Tester", "Logesh");
		reporter.setSystemInfo("host name", "Local Host");
		
		
	}
	@Override
	public void onTestSuccess(ITestResult tr) {
		logger=reporter.createTest(tr.getName());  // creates new entry in the report
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
		
		String screenshotPath=System.getProperty("user.dir")+"/Screenshots/"+tr.getName()+"_pass.png";
		File f=new File(screenshotPath);
		
		
		if(f.exists()) {
			try {
				logger.addScreenCaptureFromPath(screenshotPath);
			} catch (Exception e) { 
				e.printStackTrace();
			}
			}
		
		
		}
	
	
	@Override
	public void onTestFailure(ITestResult tr) {
		logger=reporter.createTest(tr.getName());
		
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		
		String screenshotPath=System.getProperty("user.dir")+"/Screenshots/"+tr.getName()+"_fail.png";
		File f=new File(screenshotPath);
		
		
		if(f.exists()) {
			try {
				logger.addScreenCaptureFromPath(screenshotPath);
			} catch (Exception e) { 
				e.printStackTrace();
			}
			}
		}
	
	
	@Override
	public void onFinish(ITestContext testContext) {
		reporter.flush();
	}
	}
	

