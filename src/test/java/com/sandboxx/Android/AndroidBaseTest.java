package com.sandboxx.Android;

import com.sandboxx.dataManagement.ConfigProcessor;
import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.framework.base.AppFactory;
import com.sandboxx.framework.base.AppiumServer;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class AndroidBaseTest {

    String testName;
    @BeforeSuite
    public void setUpSuite() throws FileNotFoundException {
        System.out.println(">>> Before Suite: Setup Global Parameters");
        //Reporter.log("=====Appium Server Started=====", true);

        ConfigProcessor.loadConfig();
    }

    @BeforeTest
    public void testSetUp(){
        System.out.println(">>> Before Test: Login, Navigate to method, Logout. Set up page or parameters");
    }

    @BeforeClass
    public void setUp() {
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
        AppFactory.android_LaunchApp();
    }

    @AfterMethod
    public void methodTearDown(){
        System.out.println(">>> After Method: Driver quit");
        AppDriver.getDriver().quit();
        System.out.println("---------------------------------------------");

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
    public void testTeardown(){
        System.out.println(">>> After Test");
    }

    @AfterSuite
    public void suiteTearDown(){
        System.out.println(">>> After Suite: Stop Appium Server");
        Reporter.log("=====Appium Server Stopped=====", true);
    }
}
