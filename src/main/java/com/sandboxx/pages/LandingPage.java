package com.sandboxx.pages;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.loginPages.LoginPage;
import com.sandboxx.pages.registration.SignUpPage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LandingPage extends BasePage{

    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ImageView")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sandboxxLogo;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/iv_landing_image")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sanboxxSliderImage1;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/text_landing_heading")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sliderHeader1;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_landing_text")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sliderSubHeader1;

    // Image slider buttons
    @AndroidFindBy(xpath = "//android.widget.HorizontalScrollView[@resource-id='com.sandboxx.android.dev:id/tabs']/android.widget.LinearLayout/android.widget.LinearLayout[1]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sliderButton1;
    @AndroidFindBy(xpath = "//android.widget.HorizontalScrollView[@resource-id='com.sandboxx.android.dev:id/tabs']/android.widget.LinearLayout/android.widget.LinearLayout[2]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sliderButton2;
    @AndroidFindBy(xpath = "//android.widget.HorizontalScrollView[@resource-id='com.sandboxx.android.dev:id/tabs']/android.widget.LinearLayout/android.widget.LinearLayout[3]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sliderButton3;

    private final By signUpButtonLocator = By.id("com.sandboxx.android.dev:id/btn_sign_up");
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/btn_sign_up")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement signUpButton;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/btn_log_in")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement loginButton;


    @Override
    public boolean isAt() {
        return signUpButton.isDisplayed() && loginButton.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(signUpButtonLocator));
    }

    public SignUpPage clickSignUpButton(){
        signUpButton.click();
        return new SignUpPage();
    }

    public LoginPage clickLoginButton(){
        loginButton.click();
        return new LoginPage();
    }
}
