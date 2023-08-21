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

public class VerifyAccountPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/text_title_verify_account");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Verify Your Account']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Before you can proceed, you must verify your account by logging in with your email, phone, or social account below.']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageSubHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/edittext_phone_number")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement phoneInput;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_continue_with_phone_number")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"We’ll text you a code to verify your number. Standard message and data rates apply.\"]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement buttonSubheader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/layout_or_barrier")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement layoutBarrier;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='OR']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement barrierLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/continue_with_email")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueWithEmailBtn;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/continue_with_social")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueWithSocialBtn;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/terms_and_conditions")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement termsAndConditionsLink;

    // Social Login
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Social Log In']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement socialLoginHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Use one of the below accounts to log in']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement socialLoginSubHeader;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='CONTINUE WITH GOOGLE']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueWithGoogleBtn;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='CONTINUE WITH FACEBOOK']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueWithFacebookBtn;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CANCEL']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cancelBtn;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && pageSubHeader.isDisplayed() && phoneInput.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }

    public VerifyAccountPage(){
        waitForPage();
    }

    public EmailVerificationPage continueWithEmail(){
        continueWithEmailBtn.click();
        return new EmailVerificationPage();
    }
    public void continueWithGoogle(){
        continueWithSocialBtn.click();
        continueWithGoogleBtn.click();
    }
}
