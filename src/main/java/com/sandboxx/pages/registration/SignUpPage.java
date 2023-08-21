package com.sandboxx.pages.registration;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SignUpPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/label_header");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sign Up']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_welcome_to_sandboxx")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement welcomeHeader;

    // Deprecated
    /*
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_enter_phone_to_sign_up")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement phoneNumberLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/edittext_phone_number")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement phoneNumberInput;
    */
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_enter_email_to_sign_up")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement emailLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/edittext_email")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement emailInput;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/iv_email_valid_icon")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement emailValidIcon;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_continue")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/text_terms_and_condition_and_privacy_policy")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement privacyPolicy;
    /*
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_continue_with_email")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueWithEmailButton;
    */
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_continue_with_social")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueWithSocialButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_already_have_an_account")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement haveAccountLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/text_login")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement loginLink;

    // Social Sign Up Modal
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Social Sign Up']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement socialSignUpHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Use one of the below accounts to sign up']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement socialSignUpSubHeader;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='CONTINUE WITH GOOGLE']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueWithGoogleBtn;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='CONTINUE WITH FACEBOOK']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueWithFacebookBtn;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_cancel")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cancelBtn;

    // # Google sign up account modal
    @AndroidFindBy(id = "com.google.android.gms:id/app_icon")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement logoIcon;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Choose an account']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement chooseAcctHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='to continue to Sandboxx Staging']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement chooseAcctSubHeader;
    @AndroidFindBy(id = "com.google.android.gms:id/list")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement accountsContainer;
    //List<WebElement> accountList = ((AndroidDriver) accountsContainer).findElements(By.id("com.google.android.gms:id/container"));
    //List<WebElement> accountList = accountsContainer.findElements(By.id("com.google.android.gms:id/container"));


    @Override
    public boolean isAt() {
        return welcomeHeader.isDisplayed() && emailLabel.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e) -> isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }

    public void submitEmail(String email) {
        emailInput.sendKeys(email);
        continueButton.click();
    }

    public void clickContinueWithEmail() {
        continueButton.click();
    }

    public void continueWithFacebook() {
        continueWithSocialButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.sandboxx.android.dev:id/button_facebook_auth")));
        continueWithFacebookBtn.click();
    }

    public void continueWithGoogle() {
        continueWithSocialButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.sandboxx.android.dev:id/button_google_auth")));
        continueWithGoogleBtn.click();
    }

    public WebElement getAccount(String email) {
        return accountsContainer.findElement(By.xpath("//android.widget.TextView[@text='" + email + "']//ancestor::android.widget.LinearLayout[@resource-id='com.google.android.gms:id/container']"));
    }

    public void chooseAccount(String email) {
        WebElement accountRow = getAccount(email);
        accountRow.click();
    }
}
