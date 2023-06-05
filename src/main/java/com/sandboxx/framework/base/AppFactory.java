package com.sandboxx.framework.base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public class AppFactory {

    public static AppiumDriver driver;

    public static void android_LaunchApp() throws MalformedURLException {

        System.out.println(">>> Starting Android App: Sanboxx");
        String serverUrl = AppiumServer.getAppiumServerUrl();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554")
                .setAppWaitForLaunch(true)
                .setAutomationName("UiAutomator2")
                .setPlatformName("Android")
                .setPlatformVersion("13.0")
                .setApp("/Users/Roman_Nejouta/AppFiles/Android/16_2_0/app-2.apk")
                .setAppPackage("com.sandboxx.android.dev")
                .setAppActivity("com.sandboxx.android.features.startup.SplashActivity")
                .setAutoGrantPermissions(true)
                .setNewCommandTimeout(Duration.ofMillis(8000))
                .setNoReset(false);

        System.out.println(">>> Initializing Android Driver");
        driver = new AndroidDriver(new URL(serverUrl),options);
        AppDriver.setDriver(driver);

        System.out.println("---> Driver Initialized");
        System.out.println("---> App started successfully.");

    }

    public static void iOS_LaunchApp() throws MalformedURLException {

        System.out.println(">>> Starting iOS App: Sandboxx");
        String serverUrl = AppiumServer.getAppiumServerUrl();
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 14")
                .setPlatformVersion("16.4")
                .setBundleId("com.saucelabs.mydemoapp.rn"); //com.saucelabs.mydemoapp.rn || org.wdioNativeDemoApp

        System.out.println(">>> Initializing Android Driver");
        driver = new IOSDriver(new URL(serverUrl),options);
        AppDriver.setDriver(driver);

        System.out.println("---> Driver Initialized");
        System.out.println("---> App started successfully");
    }

    public static void closeApp(){
        driver.quit();
    }

}
