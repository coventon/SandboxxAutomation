package com.sandboxx.Android;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.observer.entity.MediaEntity;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.sandboxx.dataManagement.ConfigProcessor;
import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.framework.base.AppFactory;
import com.sandboxx.framework.base.AppiumServer;
import com.sandboxx.framework.utils.MailSender;
import com.sandboxx.framework.utils.TestListener;
import com.sandboxx.framework.utils.TestResultData;
import com.sandboxx.framework.utils.TestUtil;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

//@Listeners(TestListener.class)
public class AndroidBaseTest {

    public static ExtentReports extentReport;
    public static ExtentSparkReporter sparkReporter;
    public static ExtentTest extentLogger;
    String testName;
    static protected int testsPassed;
    static protected int testsFailed;
    static protected LocalDateTime testStartDateTime;
    static protected LocalDateTime testFinishDateTime;

    @BeforeSuite
    public void setUpSuite() throws FileNotFoundException {
        System.out.println(">>> Before Suite: Setup Global Parameters");
        //Reporter.log("=====Appium Server Started=====", true);

        ConfigProcessor.loadConfig();
    }

    @BeforeTest
    public void testSetUp() {
        System.out.println(">>> Initialize Extent Report");
        extentReport = new ExtentReports();
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/testOutput/TestReport.html");
        extentReport.attachReporter(sparkReporter);
        extentReport.setSystemInfo("Platform", "Android");
        extentReport.setSystemInfo("Host Name", "Mac OS");
        extentReport.setSystemInfo("Environment", "Staging");
        sparkReporter.config().setDocumentTitle("Sandboxx Automation Test");
        sparkReporter.config().setReportName("Sandboxx Daily Regression Test");
        //sparkReporter.config().setTheme(Theme.DARK);

        testsPassed = 0;
        testsFailed = 0;
        testStartDateTime = LocalDateTime.now();
    }

    @BeforeClass
    public void setUp() throws InterruptedException {
        System.out.println(">>> Before Class: ");
        // Set up Start appium server ,Appium driver,wait, environment, os, basePage before test
        Reporter.log("=====Appium Driver Initialized=====", true);
        AppiumServer.start();

        Reporter.log("=====Application Started=====", true);
    }

    @BeforeMethod
    public void methodSetUp(Method method) throws MalformedURLException {
        System.out.println(">>> Before Method: Launching Driver and Starting App");
        testName = method.getName();
        extentLogger = extentReport.createTest("Test Login with Phone number: " + testName);
        AppFactory.android_LaunchApp();
    }

    @AfterMethod
    public void methodTearDown(ITestResult result) throws IOException, InterruptedException {
        if (result.getStatus() == ITestResult.FAILURE) {
            //MarkupHelper is used to display the output in different colors
            extentLogger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            extentLogger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
            //String screenshotPath = TestUtil.takeScreenshot();
            //
            String screenshotAsBase64 = TestUtil.takeScreenshotAsBase64();
            extentLogger.fail("Failed Test Screenshot", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotAsBase64).build());

            testsFailed++;
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentLogger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentLogger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test Case Passed", ExtentColor.GREEN));
            testsPassed++;
        }

        System.out.println(">>> After Method: Driver quit");
        AppDriver.getDriver().quit();
        System.out.println("---------------------------------------------");

        //TestResultData testData =  getTestResults();
        //System.out.println(testData.toString());
        //MailSender.sendEmailWithReport();
    }

    @AfterClass
    public void tearDown() {
        System.out.println(">>> After Class");
        // driver.quit, appium driver close.
        AppiumServer.stop();
        Reporter.log("=====Application Stopped=====", true);
        Reporter.log("=====Appium Driver Stopped=====", true);
    }

    @AfterTest
    public void testTeardown() {
        testFinishDateTime = LocalDateTime.now();
        System.out.println(">>> After Test: Finalizing Test Report");
        extentReport.flush();

        TestResultData testData =  getTestResults();
        System.out.println(testData.toString());
        MailSender.sendEmailWithReport(testData);
    }

    @AfterSuite
    public void suiteTearDown() {
        System.out.println(">>>> After Suite: Stop Appium Server");
        Reporter.log("=====Appium Server Stopped=====", true);
    }

    public void failed() throws IOException {
        File srcFile = ((TakesScreenshot) AppDriver.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(System.getenv("HOME") + "/TestScreenshots"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static TestResultData getTestResults(){
        TestResultData resultData = new TestResultData();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        resultData.setTestsPasses(String.valueOf(testsPassed));
        resultData.setTestsFailed(String.valueOf(testsFailed));
        resultData.setTotal(String.valueOf(testsFailed+testsPassed));
        resultData.setPassRate(calculatePassRate());
        resultData.setStartTime(testStartDateTime.format(formatter));
        resultData.setFinishTime(testFinishDateTime.format(formatter));
        resultData.setDuration(calculateDuration());
        resultData.setEnvironment("Staging");
        resultData.setPlatform("Android");

        return resultData;
    }

    private static String calculateDuration(){
        Duration duration = Duration.between(testStartDateTime,testFinishDateTime);
        long hours = duration.toHours();
        long minutes = duration.toMinutes();
        long seconds = duration.getSeconds();

        StringBuilder durationString = new StringBuilder();
        if(hours > 0){
            durationString.append(hours).append(" hr ");
        }
        if(minutes >0){
            durationString.append(minutes).append(" min ");
        }
        durationString.append(seconds).append(" sec");

        return durationString.toString();
    }

    private static String calculatePassRate(){
        int totalTests = testsPassed + testsFailed;
        double passRate = (double) testsPassed/totalTests * 100;

        return String.format("%.2f%%",passRate);
    }
}
