package com.growgen.qa.listeners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.growgen.qa.base.BasePage;

public class ExtentReportListener extends BasePage implements ITestListener
{
public static final String OUTPUT_FOLDER = "./builder/";
	public static final String FILE_NAME = "TestExecutionReport.html";

	public static ExtentReports extent = init();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public static ExtentReports init() {

		Path path = Paths.get(OUTPUT_FOLDER);
		// if directory exists?
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// fail to create directory
				e.printStackTrace();
			}
		}
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER + FILE_NAME);
		htmlReporter.config().setDocumentTitle("GrowGen Reports");
		htmlReporter.config().setReportName("Automation Test Results");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setReportUsesManualConfiguration(true);
		extent.setSystemInfo("OS", "Windows10");
		extent.setSystemInfo("HostName", "LocalHost");

		return extent;
	}

	public synchronized void onStart(ITestContext context) {
		System.out.println("Test Suite started!");
	}

	public synchronized void onFinish(ITestContext context) {
		System.out.println(("Test Suite is ending!"));
		extent.flush();
		test.remove();
	}

	public synchronized void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String qualifiedName = result.getMethod().getQualifiedName();
		int last = qualifiedName.lastIndexOf(".");
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");
		String className = qualifiedName.substring(mid + 1, last);

		System.out.println(methodName + " started!");
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());

		extentTest.assignCategory(result.getTestContext().getSuite().getName());
		/*
		 * methodName = StringUtils.capitalize(StringUtils.join(StringUtils.
		 * splitByCharacterTypeCamelCase(methodName), StringUtils.SPACE));
		 */
		extentTest.assignCategory(className);
		test.set(extentTest);
		test.get().getModel().setStartTime(getTime(result.getStartMillis()));
	}

	public synchronized void onTestSuccess(ITestResult result) {
//		System.out.println((result.getMethod().getMethodName() + " passed!"));
//		test.get().pass("Test passed");
//		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
		String logText = "<b>Test Method " + result.getMethod().getMethodName() + " Successful</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		test.get().log(Status.PASS, m);
	}

	public synchronized void onTestFailure(ITestResult result) {
	//	System.out.println((result.getMethod().getMethodName() + " failed!"));
		
		String logText = "<b>Test Method " +  result.getMethod().getMethodName()  + " Failed</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		test.get().log(Status.FAIL, m);
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		test.get().fail("<details><summary><b><font color=red>" +
						"Exception Occured, click to see details:"+ "</font></b></summary>" +
						exceptionMessage.replaceAll(",", "<br>") + "</details> \n");
		try {
			test.get().fail("<b><font color=red>" + "Screenshot of failure" + "</font></b>",
					MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		} catch (IOException | InterruptedException e) {
			test.get().fail("Test Failed, cannot attach screenshot");
		}
		
	}

	public synchronized void onTestSkipped(ITestResult result) {
		String logText = "<b>Test Method " + result.getMethod().getMethodName() + " Skipped</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		test.get().log(Status.SKIP, m);
	}

	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}



}
