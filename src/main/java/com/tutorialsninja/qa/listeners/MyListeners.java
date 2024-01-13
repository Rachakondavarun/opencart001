package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utilis.ExtentReporter;
import com.tutorialsninja.qa.utilis.Utilities;

public class MyListeners implements ITestListener{
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	
	
	@Override
	public void onStart(ITestContext context) {
		
	extentReport = ExtentReporter.generateExtentReport();
	
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO,result.getName()+" Started Executing ");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
	
		extentTest.log(Status.PASS,result.getName()+" Successfully Executed ");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {

	    
	    WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			
			e.printStackTrace();
		}
	    
	    String destinationScreenshotPath = Utilities.captureScreenshot(driver, result.getName());
	    
	    extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
	    extentTest.log(Status.INFO,result.getThrowable());
	    extentTest.log(Status.FAIL, result.getName()+" Testcase Failed ");
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+" Testcase Skipped ");
	
	}
	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReport\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
