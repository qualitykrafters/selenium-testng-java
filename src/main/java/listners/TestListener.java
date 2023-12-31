package listners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.Base;
import utilities.ExtentReporter;

public class TestListener extends Base implements ITestListener{
	// implements ITestListener

	WebDriver driver = null;
	ExtentReports extentReport = ExtentReporter.getExtentReport();
	ExtentTest extentTest;
	ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReport.createTest(result.getName()+" Test execution started");
		extentTestThread.set(extentTest);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
//		extentTest.log(Status.PASS,testName+" Test Got Passed");
		extentTestThread.get().log(Status.PASS,testName+ " Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
//		extentTest.fail(result.getThrowable());
		extentTestThread.get().fail(result.getThrowable());
		
		String testMethodName = result.getName();
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			String screenshotFilePath=takeScreenshot(testMethodName,driver);
			extentTestThread.get().addScreenCaptureFromPath(screenshotFilePath, testMethodName);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		
	}

}
