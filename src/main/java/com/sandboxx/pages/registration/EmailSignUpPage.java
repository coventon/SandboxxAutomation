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

public class EmailSignUpPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/label_header");
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_header")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_welcome_to_sandboxx")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement welcomeHeader;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_enter_phone_to_sign_up")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement welcomeSubheader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_first_name")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement firstNameLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/edittext_first_name")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement firstNameInput;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_last_name")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lastNameLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/edittext_last_name")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lastNameInput;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_email")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement emailLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/edittext_email")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement emailInput;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_password")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement passwordLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/edittext_password")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement passwordInput;
    @AndroidFindBy(accessibility = "Show password")
    @iOSXCUITFindBy(accessibility = "Show password")
    public WebElement showPasswordIcon;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_continue")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/text_terms_and_condition_and_privacy_policy")
    @iOSXCUITFindBy(id = "")
    public WebElement termsAndConditions;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_continue_with_email")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueWithEmailButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_continue_with_social")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueWithSocialButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_captcha_and_privacy_policy_msg")
    @iOSXCUITFindBy(id = "")
    public WebElement capchaPrivacy;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_already_have_an_account")
    @iOSXCUITFindBy(id = "")
    public WebElement haveAccountLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/text_login")
    @iOSXCUITFindBy(id = "")
    public WebElement loginLink;

    // # Error Email Already In Use Alert
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/title")
    @iOSXCUITFindBy(id = "")
    public WebElement emailInUseHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/subtitle")
    @iOSXCUITFindBy(id = "")
    public WebElement emailInUseSubHeader;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_positive")
    @iOSXCUITFindBy(id = "")
    public WebElement loginButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_negative")
    @iOSXCUITFindBy(id = "")
    public WebElement cancelButton;

    @Override
    public boolean isAt() {
        return welcomeHeader.isDisplayed() && welcomeSubheader.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }

    public void fillEmailForm(String firstName, String lastName, String email, String password){
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
    }
}
