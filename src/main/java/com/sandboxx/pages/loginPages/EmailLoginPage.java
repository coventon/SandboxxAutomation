package com.sandboxx.pages.loginPages;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import com.sandboxx.pages.homeView.HomePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
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
    public WebElement welcomeHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Enter your information below to log in']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement welcomeMsg;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Email']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement emailLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/et_login_email")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement emailInput;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/iv_email_valid_icon")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement emailValidIcon;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Email']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement passwordLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/et_login_password")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement passwordInput;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='CONTINUE']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueButton;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='CONTINUE WITH PASSWORD']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueWithPasswordButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_continue_with_social")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueWithSocial;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Don’t have an account?\"]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement noAccountLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/text_signup")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement signUpLink;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Email password combination is not valid']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement invalidCredentialsAlert;

    @Override
    public boolean isAt() {
        return welcomeMsg.isDisplayed() && welcomeHeader.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e) -> isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public EmailLoginPage(){
        waitForPage();
    }

    public void submitEmailLogin(String email){
        emailInput.sendKeys(email);
        continueButton.click();
    }
    public void loginWithEmail(String email){

        String emailInputValue = emailInput.getText();
        if(emailInputValue.equals("")){
            emailInput.sendKeys(email);
        }
        else if(!emailInputValue.equals(email)){
            emailInput.clear();
            emailInput.sendKeys(email);
        }
        continueButton.click();
    }

    public void continueWithPassword(String email,String password) throws InterruptedException {
        continueWithPasswordButton.click();
        Thread.sleep(1000);
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        continueButton.click();
    }

    public boolean loginFailed() throws InterruptedException {
        //Thread.sleep(3000);
        try{
            //new HomePage();
            return invalidCredentialsAlert.isDisplayed(); //|| pageHeader.isDisplayed();
        }
        catch (NoSuchElementException | StaleElementReferenceException e){
            return false;
        }
    }
}
