package javaSeleniumAcademy.TestComponents;

import java.io.IOException;
import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import javaSeleniumAcademy.Resources.ExtendReportNG;

public class Listeners extends BasePage implements ITestListener {
	
    ExtentTest test;
	ExtentReports extend = ExtendReportNG.getReportObject();
	ThreadLocal<ExtentTest> extendTests = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		
	test= extend.createTest(result.getMethod().getMethodName());
	extendTests.set(test); //unique thread id for each test case to avoid mimatch result in report.
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extendTests.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		extendTests.get().fail(result.getThrowable()); //for error logs in the report
		
		String filePath = null;
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());			
		}catch (Exception err) {
			err.printStackTrace();
		}
		
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extendTests.get().addScreenCaptureFromPath(filePath);
		//screenshot , attach to report
		extendTests.get().log(Status.FAIL, "Test Failed");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extend.flush();
	}

}
