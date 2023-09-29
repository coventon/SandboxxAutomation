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

    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/email_account_verification_header");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Verify Your Account']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Before you can proceed, you must verify your account by logging in with your email, or social account below.']")
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
        return pageHeader.isDisplayed() && pageSubHeader.isDisplayed() && emailInput.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }

    public VerifyAccountPage(){
        waitForPage();
    }

    public void submitEmailAndPassword(String email,String password){
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        continueButton.click();
    }
    public void continueWithGoogle(){
        continueWithSocialBtn.click();
        continueWithGoogleBtn.click();
    }
}
