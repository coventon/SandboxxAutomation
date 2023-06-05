package com.sandboxx;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.framework.base.AppFactory;
import com.sandboxx.framework.base.AppiumServer;
import com.sandboxx.framework.utils.PageActionsHelper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class AndroidSampleTest {

    public static void main(String [] args) throws InterruptedException, MalformedURLException {

        System.out.println("---> Start Android Sample Test");

        AppiumServer.start();
        Thread.sleep(3000);

        AppFactory.android_LaunchApp();

        WebElement sanboxxLogo = AppDriver.getDriver().findElement(AppiumBy.xpath("//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ImageView"));
        Assert.assertTrue(sanboxxLogo.isDisplayed(), "Sandbox Logo is no displayed");
        WebElement sanboxxSliderImage1  = AppDriver.getDriver().findElement(AppiumBy.id("com.sandboxx.android.dev:id/iv_landing_image"));
        Assert.assertTrue(sanboxxSliderImage1.isDisplayed(), "Sandbox Slider Image 1 is no displayed");

        WebElement landingHeading = AppDriver.getDriver().findElement(AppiumBy.id("com.sandboxx.android.dev:id/text_landing_heading"));
        String landingHeadingText = landingHeading.getText();
        Assert.assertEquals(landingHeadingText, "Show Your Love");
        WebElement landingText = AppDriver.getDriver().findElement(AppiumBy.id("com.sandboxx.android.dev:id/tv_landing_text"));
        String landingTextContent = landingText.getText();
        Assert.assertEquals(landingTextContent, "Attach a photo with each letter");

        // Image buttons
        AppDriver.getDriver().findElement(AppiumBy.xpath("//android.widget.HorizontalScrollView[@resource-id='com.sandboxx.android.dev:id/tabs']/android.widget.LinearLayout/android.widget.LinearLayout[1]"));
        AppDriver.getDriver().findElement(AppiumBy.xpath("//android.widget.HorizontalScrollView[@resource-id='com.sandboxx.android.dev:id/tabs']/android.widget.LinearLayout/android.widget.LinearLayout[2]")).click();
        Thread.sleep(1000);
        AppDriver.getDriver().findElement(AppiumBy.xpath("//android.widget.HorizontalScrollView[@resource-id='com.sandboxx.android.dev:id/tabs']/android.widget.LinearLayout/android.widget.LinearLayout[3]")).click();
        Thread.sleep(1000);
        AppDriver.getDriver().findElement(AppiumBy.xpath("//android.widget.HorizontalScrollView[@resource-id='com.sandboxx.android.dev:id/tabs']/android.widget.LinearLayout/android.widget.LinearLayout[1]")).click();

        AppDriver.getDriver().findElement(AppiumBy.id("com.sandboxx.android.dev:id/btn_sign_up"));
        AppDriver.getDriver().findElement(AppiumBy.id("com.sandboxx.android.dev:id/btn_log_in"));

        // Check app state
        String appState = ((AndroidDriver) AppDriver.getDriver()).queryAppState("com.sandboxx.android.dev").toString();
        System.out.printf("--> App state: %s%n", appState);

        //Thread.sleep(5000);
        String appPackage = (String) ((AndroidDriver) AppDriver.getDriver()).getCapabilities().getCapability(AndroidMobileCapabilityType.APP_PACKAGE);
        System.out.printf("App Package: %s%n", appPackage);

        // Terminate app
        Map<String,Object> params = new HashMap<>();
        //params.put("appId","com.sandboxx.android.dev");
        params.put("appId","/Users/Roman_Nejouta/AppFiles/Android/app-2.apk");
        //params.put("appActivity","com.sandboxx.android.features.startup.SplashActivity");
        //final boolean wasRunningBefore = (Boolean)driver.executeScript("mobile: terminateApp", params);
        //System.out.printf("App terminated: %s\n", wasRunningBefore);
        //Thread.sleep(3000);

        // Background app
        final boolean wasBackgrounded = (Boolean)((AndroidDriver) AppDriver.getDriver()).executeScript("mobile: backgroundApp", params);
        System.out.printf("App backgrounded: %s\n", wasBackgrounded);
        //Thread.sleep(2000);

        Map<String,Object> params2 = new HashMap<>();
        params2.put("keycode",0x00000003);
        //final boolean homeButtonPressed = (Boolean)
        //driver.executeScript("mobile: pressKey", params2);
        //System.out.printf("Home button pressed: %s", homeButtonPressed);
        //((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.MOVE_HOME));
        Thread.sleep(3000);

        String appState2 = ((AndroidDriver) AppDriver.getDriver()).queryAppState("com.sandboxx.android.dev").toString();
        System.out.printf("--> App state: %s%n", appState2);

        // Activate app running on the background
        ((AndroidDriver) AppDriver.getDriver()).activateApp("com.sandboxx.android.dev");
        //final boolean appActivated = (Boolean)driver.executeScript("mobile: activateApp", params);
        //System.out.printf("App Activated: %s \n", appActivated);
        Thread.sleep(2000);

        // test swipe login page slider

        PageActionsHelper.swipeLeft();
        Thread.sleep(1000);
        PageActionsHelper.swipeLeft();
        Thread.sleep(1000);
        PageActionsHelper.swipeRight();
        Thread.sleep(1000);
        PageActionsHelper.swipeRight();
        Thread.sleep(3000);
        PageActionsHelper.scroll(PageActionsHelper.ScrollDirection.LEFT,0.6);
        Thread.sleep(2000);
        PageActionsHelper.scroll(PageActionsHelper.ScrollDirection.LEFT,0.6);
        Thread.sleep(2000);
        PageActionsHelper.scroll(PageActionsHelper.ScrollDirection.RIGHT,0.6);
        Thread.sleep(2000);
        PageActionsHelper.scroll(PageActionsHelper.ScrollDirection.RIGHT,0.6);
        Thread.sleep(2000);


        AppFactory.closeApp();
        AppiumServer.stop();
    }
}
