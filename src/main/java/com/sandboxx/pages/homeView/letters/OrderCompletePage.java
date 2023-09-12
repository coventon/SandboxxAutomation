package com.sandboxx.pages.homeView.letters;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import com.sandboxx.pages.homeView.HomePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderCompletePage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.xpath("//android.widget.TextView[@text='Thanks for Your Order']");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Thanks for Your Order']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Your letter is being prepped for shipment.']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageSubHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Refer your friends and family to Sandboxx and get a free letter once they send their first one.']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement completedBody;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='REFER A FRIEND']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement referFriendButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='GO TO DASHBOARD']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement goToDashboardButton;
    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && pageSubHeader.isDisplayed()
                && completedBody.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public OrderCompletePage(){waitForPage();}

    public HomePage goToDashboard(){
        goToDashboardButton.click();
        return new HomePage();
    }
}
