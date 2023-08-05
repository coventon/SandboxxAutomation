package com.sandboxx.pages.registration;

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

public class WelcomePage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    //public String pageHeaderText = "Confirm your phone number";
    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/welcome_tv");
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Welcome to Sandboxx!')]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;

    public final String pageSubHeaderText = "A career in the military is very rewarding, and we’re here to support you through your journey – giving you access to career content, lifestyle utilities, and more.";
    public final String basicTrainingSubHeader = "Not knowing what to expect when you arrive at basic training can be overwhelming. We'll help you prepare physically and mentally for the experience, so you can arrive with confidence.";
    private final By pageSubHeaderLocator = By.id("com.sandboxx.android.dev:id/welcome_subtitle_tv");
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/welcome_subtitle_tv")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageSubHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/finish_btn",xpath = "//android.widget.Button[@text,'ENTER SANDBOXX']")
    @iOSXCUITFindBy(id = "")
    public WebElement enterSandboxxButton;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && pageSubHeader.isDisplayed() && enterSandboxxButton.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }

    public HomePage enterSandboxx(){
        enterSandboxxButton.click();
        return new HomePage();
    }
}
