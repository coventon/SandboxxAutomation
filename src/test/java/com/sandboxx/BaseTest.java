package com.sandboxx;

import com.sandboxx.dataManagement.ConfigProcessor;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.FileNotFoundException;

public class BaseTest {
    public AppiumDriver driver;
    public WebDriverWait wait;

    @BeforeSuite
    public void setUpSuite() throws FileNotFoundException {
        System.out.println(">>> Before Suite: Setup Appium Server");
        Reporter.log("=====Appium Server Started=====", true);

        ConfigProcessor.loadConfig();
    }

    @BeforeClass
    public void setUp() {
        System.out.println(">>> Before Class: ");
        // Set up Start appium server ,Appium driver,wait, environment, os, basePage before test
        Reporter.log("=====Appium Driver Initialized=====", true);

        Reporter.log("=====Application Started=====", true);
    }
   @BeforeTest
   public void testSetUp(){
       System.out.println(">>> Before Test: Login, Navigate to method, Logout. Set up page or parameters");
   }
    @BeforeMethod
    public void methodSetUp(){
        System.out.println(">>> Before Method: Login, Navigate To Page, Logout");
    }

    @AfterMethod
    public void methodTearDown(){
        System.out.println(">>> After Method: return to initial position, close the app, Log out");
        System.out.println("---------------------------------------------");

    }

    @AfterTest
    public void testTeardown(){
        System.out.println(">>> After Test");
    }

    @AfterClass
    public void tearDown() {
        System.out.println(">>> After Class");
        // driver.quit, appium driver close.

        Reporter.log("=====Application Stopped=====", true);
        Reporter.log("=====Appium Driver Stopped=====", true);
    }

    @AfterSuite
    public void suiteTearDown(){
        System.out.println(">>> After Suite: Stop Appium Server");
        Reporter.log("=====Appium Server Stopped=====", true);
    }

    @Test
    public void test1(){
        System.out.println("---> This is the test 1");
    }

    @Test
    public void test2(){
        System.out.println("---> This is the test 2");
    }

    @Test
    public void test3(){
        System.out.println("---> This is the test 3");
    }
}
