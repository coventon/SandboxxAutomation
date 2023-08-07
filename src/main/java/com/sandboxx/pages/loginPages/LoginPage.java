package com.sandboxx.pages.loginPages;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/label_header");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Log In']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_welcome_to_sandboxx")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sandboxxWelcomeHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_enter_phone_to_sign_up")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement phoneSignUpText;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/edittext_phone_number")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement phoneInput;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_continue_with_phone_number")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueWithPhoneButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/text_terms_and_condition_and_privacy_policy")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement termConditionPrivacyPolicyLink;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_continue_with_email")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueWithEmailButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_continue_with_social")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueWithSocialButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_captcha_and_privacy_policy_msg")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement captchaAndPrivacyMsg;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_already_have_an_account")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement haveAccountLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/text_login_or_signup")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement signUpLink;

    //@AndroidFindBy(xpath = "//android.widget.TextView[@text='Please login using your email address:']")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Please login using your email address:')]")

    @iOSXCUITFindBy(id = "")
    public WebElement signInWithPhoneAlert;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && sandboxxWelcomeHeader.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }

    public CodeVerificationPage signInWithPhone(String phoneNumber){
        phoneInput.sendKeys(phoneNumber);
        continueWithPhoneButton.click();
        return new CodeVerificationPage();
    }

    public EmailLoginPage continueWithEmail(){
        continueWithEmailButton.click();
        return new EmailLoginPage();
    }

}
