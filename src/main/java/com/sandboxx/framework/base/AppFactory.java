package com.sandboxx.framework.base;
import com.sandboxx.dataManagement.ConfigProcessor;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class AppFactory {

    public static AppiumDriver driver;

    public static void android_LaunchApp() throws MalformedURLException {
        String androidAppPath = ConfigProcessor.getAndroidApp();
        System.out.println(">>> Starting Android App: Sanboxx");
        String serverUrl = AppiumServer.getAppiumServerUrl();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554")
                .setAppWaitForLaunch(true)
                .setAutomationName("UiAutomator2")
                .setPlatformName("Android")
                .setPlatformVersion("13.0")
                //.setApp("/Users/Roman_Nejouta/AppFiles/Android/16_2_0/app-2.apk")
                .setApp(androidAppPath)
                .setAppPackage("com.sandboxx.android.dev")
                .setAppActivity("com.sandboxx.android.features.startup.SplashActivity")
                .setAutoGrantPermissions(true)
                .setNewCommandTimeout(Duration.ofMillis(8000))
                .setNoReset(false);

        System.out.println(">>> Initializing Android Driver");
        driver = new AndroidDriver(new URL(serverUrl),options);
        //WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigProcessor.getTimeout()));
        AppDriver.setDriver(driver);

        System.out.println("---> Driver Initialized");
        System.out.println("---> App started successfully.");

    }

    public static void iOS_LaunchApp() throws MalformedURLException {
        String iosAppPath = ConfigProcessor.getIosApp();

        System.out.println(">>> Starting iOS App: Sandboxx");
        String serverUrl = AppiumServer.getAppiumServerUrl();
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 14")
                .setPlatformVersion("16.4")
                .setApp(iosAppPath)
                .setBundleId("com.saucelabs.mydemoapp.rn"); //com.saucelabs.mydemoapp.rn || org.wdioNativeDemoApp

        System.out.println(">>> Initializing Android Driver");
        driver = new IOSDriver(new URL(serverUrl),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigProcessor.getTimeout()));

        AppDriver.setDriver(driver);

        System.out.println("---> Driver Initialized");
        System.out.println("---> App started successfully");
    }

    public static void closeApp(){
        driver.quit();
    }

}
