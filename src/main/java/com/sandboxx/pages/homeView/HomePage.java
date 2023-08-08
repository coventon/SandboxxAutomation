package com.sandboxx.pages.homeView;

import com.sandboxx.dataManagement.constants.GoTo;
import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    //public String pageHeaderText = "Confirm your phone number";
    private final By welcomeGreetingLocator = By.id("com.sandboxx.android.dev:id/tv_dsh_welcome_greeting");
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_dsh_welcome_greeting")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement welcomeGreeting;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/icon_inbox")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement inboxIcon;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_dsh_cutoff_number")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cutOffNumber;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_dsh_cutoff_text")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cutOffText;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/iv_dsh_letter_cutoff_info")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cutOffInfo;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_dsh_graduation_text")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement graduationText;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_settings")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement settingsButton;

    public HomePage(){
        createMainNavigation();
    }
    @Override
    public boolean isAt() {
        return welcomeGreeting.isDisplayed() && inboxIcon.isDisplayed() && cutOffText.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeGreetingLocator));
    }

    public void navigateToProfile(){
        navigateTo(GoTo.Sandboxx_Profile);
    }

}
