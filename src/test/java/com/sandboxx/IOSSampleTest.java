package com.sandboxx;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.framework.base.AppFactory;
import com.sandboxx.framework.base.AppiumServer;
import com.sandboxx.framework.utils.PageActionsHelper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class IOSSampleTest {

    public static void main(String [] args) throws InterruptedException, MalformedURLException {

        System.out.println("---> Starting iOS Sample Test");

        AppiumServer.start();
        Thread.sleep(3000);

        AppFactory.iOS_LaunchApp();
        //-----------------

        //Thread.sleep(1000);

        AppDriver.getDriver().findElement(AppiumBy.accessibilityId("tab bar option menu")).click();
        //Thread.sleep(1000);
        AppDriver.getDriver().findElement(AppiumBy.accessibilityId(("menu item log in"))).click();
        //Thread.sleep(1000);
        try{
            if(AppDriver.getDriver().findElement(AppiumBy.accessibilityId("Username input field")).isDisplayed()) {

                AppDriver.getDriver().findElement(AppiumBy.accessibilityId("Username input field")).sendKeys("bob@example.com");
                AppDriver.getDriver().findElement(AppiumBy.accessibilityId("Password input field")).sendKeys("10203040");
                //Thread.sleep(1000);
                AppDriver.getDriver().findElement(AppiumBy.accessibilityId("Login button")).click();
            }
        }
        catch (NoSuchElementException e){
            System.out.println("User is logged in. Login form is not displayed");
        }

        AppDriver.getDriver().findElement(AppiumBy.xpath("//XCUIElementTypeButton[@name='tab bar option catalog']")).click();
        PageActionsHelper.scrollDown();
        Thread.sleep(2000);
        PageActionsHelper.scrollUp();
        AppDriver.getDriver().findElement(By.xpath("//*[@name='tab bar option menu']")).click();
        Thread.sleep(2000);
        PageActionsHelper.swipeLeft();
        Thread.sleep(2000);
        WebElement catalogItem1 = AppDriver.getDriver().findElement(By.xpath("//*[@name='Sauce Labs Onesie']"));
        Assert.assertTrue(catalogItem1.isDisplayed());

        //Thread.sleep(5000);
        String bundleId = (String)((IOSDriver)AppDriver.getDriver()).getCapabilities().getCapability(IOSMobileCapabilityType.BUNDLE_ID);
        System.out.printf("Bunlde ID: %s%n", bundleId);



        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", "com.saucelabs.mydemoapp.rn");
        final boolean wasRunningBefore = (Boolean)((IOSDriver)AppDriver.getDriver()).executeScript("mobile: terminateApp", params);
        //driver.terminateApp(bundleId);

        // ----------------
        AppFactory.closeApp();
        AppiumServer.stop();
    }
}
