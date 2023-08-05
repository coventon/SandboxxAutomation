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

public class SignUpPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/label_header");
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_header")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_welcome_to_sandboxx")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement welcomeHader;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_enter_phone_to_sign_up")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement phoneNumberLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/edittext_phone_number")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement phoneNumberInput;


    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_continue_with_email")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueWithEmailButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_continue_with_social")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueWithSocialButton;



    @Override
    public boolean isAt() {
        return welcomeHader.isDisplayed() && phoneNumberLabel.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }

    public EmailSignUpPage clickContinueWithEmail(){
        continueWithEmailButton.click();
        return new EmailSignUpPage();
    }
}
