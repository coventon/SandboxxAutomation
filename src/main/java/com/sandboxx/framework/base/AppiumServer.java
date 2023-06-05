package com.sandboxx.framework.base;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class AppiumServer {

    static AppiumDriverLocalService server;
    static int appiumServerPort = 4723;

    static void setInstance(){

        // @Print environment
        System.getenv().forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });

        System.out.println(">>> Initiating AppiumServiceBuilder...");

        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.usingDriverExecutable(new File("/usr/local/bin/node"))
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                //.usingAnyFreePort()
                .usingPort(4723)
                .withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
                .withLogFile(new File("AppiumAutomationLog.txt"))
                .withIPAddress("127.0.0.1");

        System.out.println(">>> AppiumServiceBuilder initiated");
        System.out.println(">>> Initiating Appium Server");

        server = AppiumDriverLocalService.buildService(builder);
    }

    static AppiumDriverLocalService getInstance(){
        System.out.println(">>> Getting Appium Server Instance...");
        if(server == null ){
            setInstance();
        }
        return  server;
    }

    public static void start(){
        if (!isServerRunning(appiumServerPort)) {

            getInstance().start();
            System.out.println(">>> Appium Server is running on url : port : "+server.getUrl());
            System.out.println(">>> Appium state is Running: "+ server.isRunning());
        }

    }

    public static void stop(){
        if(server != null){
            getInstance().stop();
            System.out.println(">>> Appium Server Stopped");
        }
    }

    public static String getAppiumServerUrl(){
        return server.getUrl().toString();
    }

    private static boolean isServerRunning(int port){
        try(Socket socket = new Socket()){
            InetAddress localhost = InetAddress.getByName("localhost");
            InetSocketAddress socketAddress = new InetSocketAddress(localhost,port);
            socket.connect(socketAddress,1000);
            System.out.println(">>> Appium server is already running...");
            // If connection is successful, the server is already running
            return true;
        }catch (java.io.IOException e){
            // If the socket connection fails, the server is not running
            System.out.println(">>> Server is not running...");
            return false;
        }
    }

    public static void killProcessOnPort(int port){
        try{
            Process process = Runtime.getRuntime().exec("lsof -n -i4TCP:" + port + " | grep LISTEN | awk '{print $2}'");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null){
                String pid = line.trim();
                Runtime.getRuntime().exec("kill -9" + pid); // kills process on port
                Runtime.getRuntime().exec("/usr/bin/killall -9 node"); // Kills all node processes.
                System.out.println("Process running on port " + port + " with PID" + pid +  " has been killed.");
                return;
            }
            System.out.println("No process found running on port " + port + ".");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        AppiumServer.start();
        Thread.sleep(4000);
        AppiumServer.stop();

        /*  ==== This Portion is for testing purposes ====
        AppiumDriverLocalService server;

        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.usingDriverExecutable(new File("/usr/local/bin/node"))
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                //.usingAnyFreePort()
                .usingPort(4723)
                .withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
                .withLogFile(new File("AppiumAutomationLog.txt"))
                .withIPAddress("127.0.0.1");

        server = AppiumDriverLocalService.buildService(builder);

        // Start server
        System.out.println(">>> Starting Appium Server");
        if(!server.isRunning()){
            server.start();
        }
        //server.start();
        System.out.println("Appium Server is running on url : port : " + server.getUrl());
        System.out.println("Appium state is Running: " + server.isRunning());
        Thread.sleep(4000);

        // -----------
        // Initiate driver
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554")
                .setAppWaitForLaunch(true)
                .setAutomationName("UiAutomator2")
                .setPlatformName("Android")
                .setPlatformVersion("13.0")
                //.setApp("/Users/Roman_Nejouta/AppFiles/Android/16_2_0/app-2.apk")
                .setAppPackage("com.sandboxx.android.dev")
                .setAppActivity("com.sandboxx.android.features.startup.SplashActivity")
                .setAutoGrantPermissions(true)
                .setNewCommandTimeout(Duration.ofMillis(4000))
                .setNoReset(false);

        AppiumDriver driver = new AndroidDriver(server.getUrl(),options);
        System.out.println(">>>> Driver is initialized... ");
        Thread.sleep(2000);

        // ------------------Test Begin -----------

        WebElement sanboxxLogo = driver.findElement(AppiumBy.xpath("//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ImageView"));
        Assert.assertTrue(sanboxxLogo.isDisplayed(), "Sandbox Logo is no displayed");
        WebElement sanboxxSliderImage1  = driver.findElement(AppiumBy.id("com.sandboxx.android.dev:id/iv_landing_image"));
        Assert.assertTrue(sanboxxSliderImage1.isDisplayed(), "Sandbox Slider Image 1 is no displayed");

        WebElement landingHeading = driver.findElement(AppiumBy.id("com.sandboxx.android.dev:id/text_landing_heading"));
        String landingHeadingText = landingHeading.getText();
        Assert.assertEquals(landingHeadingText, "Show Your Love");
        WebElement landingText = driver.findElement(AppiumBy.id("com.sandboxx.android.dev:id/tv_landing_text"));
        String landingTextContent = landingText.getText();
        Assert.assertEquals(landingTextContent, "Attach a photo with each letter");
        // ----------------- Test End ------------



// Image buttons
        driver.findElement(AppiumBy.xpath("//android.widget.HorizontalScrollView[@resource-id='com.sandboxx.android.dev:id/tabs']/android.widget.LinearLayout/android.widget.LinearLayout[1]"));
        driver.findElement(AppiumBy.xpath("//android.widget.HorizontalScrollView[@resource-id='com.sandboxx.android.dev:id/tabs']/android.widget.LinearLayout/android.widget.LinearLayout[2]")).click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.widget.HorizontalScrollView[@resource-id='com.sandboxx.android.dev:id/tabs']/android.widget.LinearLayout/android.widget.LinearLayout[3]")).click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.xpath("//android.widget.HorizontalScrollView[@resource-id='com.sandboxx.android.dev:id/tabs']/android.widget.LinearLayout/android.widget.LinearLayout[1]")).click();

        driver.findElement(AppiumBy.id("com.sandboxx.android.dev:id/btn_sign_up"));
        driver.findElement(AppiumBy.id("com.sandboxx.android.dev:id/btn_log_in"));

        // Check app state
        String appState = ((AndroidDriver) driver).queryAppState("com.sandboxx.android.dev").toString();
        System.out.printf("--> App state: %s%n", appState);


        String appPackage = (String) driver.getCapabilities().getCapability(AndroidMobileCapabilityType.APP_PACKAGE);
        System.out.printf("App Package: %s%n", appPackage);

        Map<String,Object> params = new HashMap<>();
        //params.put("appId","com.sandboxx.android.dev");
        params.put("appId","/Users/Roman_Nejouta/AppFiles/Android/app-2.apk");

        final boolean wasBackgrounded = (Boolean)driver.executeScript("mobile: backgroundApp", params);
        System.out.printf("App backgrounded: %s\n", wasBackgrounded);

        Map<String,Object> params2 = new HashMap<>();
        params2.put("keycode",0x00000003);

        Thread.sleep(3000);

        String appState2 = ((AndroidDriver) driver).queryAppState("com.sandboxx.android.dev").toString();
        System.out.printf("--> App state: %s%n", appState2);

        Thread.sleep(2000);

        ((AndroidDriver) driver).activateApp("com.sandboxx.android.dev");
        Thread.sleep(3000); // Keep the app active for 4 seconds

        //------------

        System.out.println(">>>> Quitting driver...");
        System.out.println("Driver is not null: "+ driver != null);
        driver.quit();
        System.out.println("Driver is not null: "+ driver != null);
        //-----------
        // Stop Server
        System.out.println(">>> Stopping Appium Server");
        server.stop();
*/

    }
}
