package com.sandboxx.pages.androidApps.googlePlay;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SandboxxPlayPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.xpath("//android.view.View[@content-desc='Search Google Play']");
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Search Google Play']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;

    @AndroidFindBy(accessibility = "Navigate up")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sandboxx']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sandboxxTitle;
    //android.widget.TextView[@text='Sandboxx, Inc.']
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sandboxx']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sandboxxSubtitle;
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Install']/following-sibling::android.widget.Button")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement installButton;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && sandboxxTitle.isDisplayed() && sandboxxSubtitle.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e) -> isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }

    public SandboxxPlayPage() {
        waitForPage();
    }
}
