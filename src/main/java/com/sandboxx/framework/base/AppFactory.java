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
                //.setApp("/Users/Roman_Nejouta/AppFiles/Android/16_4_0/app-2.apk")
                .setApp(androidAppPath)
                .setAppPackage("com.sandboxx.android.dev")
                .setAppActivity("com.sandboxx.android.features.startup.SplashActivity") // Logged in Activity: com.sandboxx.android.features.startup.LandingActivity
                .setAutoGrantPermissions(true)
                .setChromedriverExecutableDir(System.getProperty("user.dir"+"/drivers/chrome/chromedriver_mac64/chromedriver"))
                .setChromedriverExecutable(System.getProperty("user.dir"+"/drivers/chrome/chromedriver_mac64/chromedriver"))
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

    public static void androidDevice_LaunchApp() throws MalformedURLException {
        String androidAppPath = ConfigProcessor.getAndroidApp();
        System.out.println(">>> Starting Sandboxx App on Android Real Device");
        String serverUrl = AppiumServer.getAppiumServerUrl();
        String androidRealDeviceName= ConfigProcessor.getAndroidRealDeviceName();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(androidRealDeviceName)
                .setAppWaitForLaunch(true)
                .setAutomationName("UiAutomator2")
                .setPlatformName("Android")
                .setPlatformVersion(ConfigProcessor.getAndroidRealDevicePlatformVersion())
                .setApp(androidAppPath)
                .setAppPackage("com.sandboxx.android.dev")
                .setAppActivity("com.sandboxx.android.features.startup.SplashActivity")
                .setAutoGrantPermissions(true)
                .setNewCommandTimeout(Duration.ofMillis(5000))
                .setNoReset(false);

        System.out.println(">>> Initializing Android Driver");
        driver = new AndroidDriver(new URL(serverUrl),options);
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

    public static void iOSDevice_LaunchApp() throws Exception {
        throw new Exception("Method Not Yet Implemented");
    }

    public static void closeApp(){
        driver.quit();
    }




    // =================== Demo ios app launch method ==========================
    public static void iOS_Demo_LaunchApp() throws MalformedURLException {
        String iosTheAppPath = "/Users/Roman_Nejouta/AppFiles/DemoApps/ios/TheApp.app";
        String bundleID = "com.appiumpro.the_app";

        System.out.println(">>> Starting iOS The App");
        String serverUrl = AppiumServer.getAppiumServerUrl();
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 14")
                .setPlatformVersion("16.4")
                .setApp(iosTheAppPath)
                .setBundleId(bundleID);

        System.out.println(">>> Initializing iOS Driver");
        driver = new IOSDriver(new URL(serverUrl),options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigProcessor.getTimeout()));

        AppDriver.setDriver(driver);

        System.out.println("---> Driver Initialized");
        System.out.println("---> App started successfully");
    }

    //========== Demo App Launch
    public static void android_Demo_LaunchApp() throws MalformedURLException {
        String androidTheAppPath = "/Users/Roman_Nejouta/AppFiles/DemoApps/android/TheApp.apk";
        System.out.println(">>> Starting Android App: The App");
        String serverUrl = AppiumServer.getAppiumServerUrl();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554")
                .setAppWaitForLaunch(true)
                .setAutomationName("UiAutomator2")
                .setPlatformName("Android")
                .setPlatformVersion("13.0")
                //.setApp("/Users/Roman_Nejouta/AppFiles/Android/16_2_0/app-2.apk")
                .setApp(androidTheAppPath)
                .setAppPackage("com.appiumpro.the_app")
                .setAppActivity("com.appiumpro.the_app.MainActivity")
                .setAutoGrantPermissions(true)
                .setNewCommandTimeout(Duration.ofMillis(8000))
                .setNoReset(false);

        System.out.println(">>> Initializing Android Driver");
        driver = new AndroidDriver(new URL(serverUrl),options);
        //WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigProcessor.getTimeout()));
        AppDriver.setDriver(driver);

        System.out.println("---> Android Driver Initialized");
        System.out.println("---> App started successfully.");
    }

}
