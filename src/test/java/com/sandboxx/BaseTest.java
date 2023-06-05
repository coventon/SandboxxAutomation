package com.sandboxx;

import com.sandboxx.dataManagement.ConfigProcessor;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.FileNotFoundException;

public class BaseTest {
    public AppiumDriver driver;
    public WebDriverWait wait;

    @BeforeSuite
    public void setUpSuite() throws FileNotFoundException {

      ConfigProcessor.loadConfig();

    }

    @BeforeClass
    public void setUp() {
        // Set up Start appium server ,Appium driver,wait, environment, os, basePage before test
        Reporter.log("=====Appium Server Started=====", true);

        Reporter.log("=====Application Started=====", true);

    }

    @AfterClass
    public void tearDown() {
        // driver.quit, appium driver close.

        Reporter.log("=====Application Stopped=====", true);
        Reporter.log("=====Appium Server Stopped=====", true);
    }
}
