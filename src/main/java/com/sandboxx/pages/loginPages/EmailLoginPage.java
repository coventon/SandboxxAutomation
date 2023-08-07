package com.sandboxx.pages.loginPages;

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

public class EmailLoginPage extends BasePage {

    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/label_header");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Log In']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Welcome back!']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sandboxxWelcomeHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Enter your information below to log in']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement formHeader;
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
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/btn_log_in")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueButton;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_continue_with_phone")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueWithPhone;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_continue_with_social")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueWithSocial;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Don’t have an account?']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement noAccountLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/text_signup")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement signUpLink;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && sandboxxWelcomeHeader.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e) -> isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }

    public HomePage submitEmailLogin(String email, String password){
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        continueButton.click();
        return new HomePage();
    }
}
