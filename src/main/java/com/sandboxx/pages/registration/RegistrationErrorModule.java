package com.sandboxx.pages.registration;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationErrorModule extends BasePage {

    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By errorTitleLocator = By.id("com.sandboxx.android.dev:id/title");
    @AndroidFindBy(xpath = "//android.widget.ImageView")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement errorIcon;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/title")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement errorTitle;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/subtitle")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement errorSubTitle;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_positive")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement loginButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_negatives")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cancelButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='ENTER NEW NUMBER']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement enterNewNumberButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='SKIP']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement skipButton;

    @Override
    public boolean isAt() {
        return loginButton.isDisplayed() && cancelButton.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorTitleLocator));
    }
}
