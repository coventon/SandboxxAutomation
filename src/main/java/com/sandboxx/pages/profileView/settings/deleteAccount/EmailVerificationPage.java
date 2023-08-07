package com.sandboxx.pages.profileView.settings.deleteAccount;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmailVerificationPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/email_account_verification_header");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Verify Your Account']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Before you can proceed, you must verify your account by logging in with your email, phone, or social account below.']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageSubHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Email']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement emailLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/et_login_email")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement emailInput;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Password']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement passwordLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/et_login_password")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement passwordInput;
    @AndroidFindBy(accessibility = "Show password")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement showPwdButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_forgot_password")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement forgotPasswordLink;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/continue_button")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/layout_or_barrier")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement layoutBarrier;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='OR']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement barrierLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/continue_with_phone")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueWithPhoneBtn;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/continue_with_social")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueWithSocialBtn;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && pageSubHeader.isDisplayed() && emailLabel.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }

    public EmailVerificationPage(){
        waitForPage();
    }

    public void enterFormData(String email, String password){
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
    }
}
