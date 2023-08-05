package com.sandboxx.singleTestSetUp;

import com.sandboxx.IOS.IosBaseTest;
import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.singlePageSetUp.HomePageOld;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CloudGrayIosSampleTest extends IosBaseTest {
    @Test
    public void theAppTestIos() throws InterruptedException {
        String input = "Test input";
        System.out.printf("Begin test: %s", testName);
        HomePageOld homePageOld = new HomePageOld();
        //homePage.echoBox.click();
        homePageOld.navigateToEchoBox();

        //Thread.sleep(3000);
        //homePage.echoBoxTextInput.sendKeys(input);
        homePageOld.enterValueIntoEcho(input);
        //homePage.saveBtn.click();
        homePageOld.saveValue();

        Object platformName = ((RemoteWebDriver) AppDriver.getDriver()).getCapabilities().getCapability(CapabilityType.PLATFORM_NAME);
        String platform = String.valueOf(platformName);
        System.out.println("Platform Name: "+platform);

        if(platform.equalsIgnoreCase("android")){
            Assert.assertTrue(AppDriver.getDriver().findElement(By.xpath("//android.widget.TextView[@content-desc='"+input+"']")).isDisplayed());
        }
        else if(platform.equalsIgnoreCase("ios")){
            Assert.assertEquals(homePageOld.savedMsg.getText(),input);
        }
    }
}
