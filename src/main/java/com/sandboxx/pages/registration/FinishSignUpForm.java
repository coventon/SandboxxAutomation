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

public class FinishSignUpForm extends BasePage {

    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));
    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/finishHeader");

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Finish Signing Up']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Enter your details below']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageSubHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='First Name']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement firstNameLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/signup_firstname")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement firstNameInput;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Last Name']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lastNameLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/signup_lastname")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lastNameInput;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Email']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement emailLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/signup_email")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement emailInput;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Password (8+ characters)']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement passwordLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/signup_password")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement passwordInput;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/signup_password")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement showPasswordIcon;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Adding your password will allow you to log in using your email.']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement passwordDisclaimer;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='SIGN UP']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement signUpButton;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && pageSubHeader.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }

    public void fillSignUpForm(String firstName,String lastName, String email, String password){
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);

        String emailInputValue = emailInput.getText();

        if(emailInputValue.equals("")){
            emailInput.sendKeys(email);
        }
        else if(!emailInputValue.equals(email)){
            emailInput.clear();
            emailInput.sendKeys(email);
        }
        passwordInput.sendKeys(password);
    }

    public void submitForm(String firstName, String lastName, String email, String password){
        fillSignUpForm(firstName,lastName,email,password);
        signUpButton.click();
    }
}
