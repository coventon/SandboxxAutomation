package com.sandboxx.pages.loginPages;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import com.sandboxx.pages.registration.UseSandboxxPage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CodeVerificationPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/label_header");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Enter the verification code we just sent you.']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/et_verification_code")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement verificationCodeInput;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/btn_verify")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement verifyButton;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/btn_resend_sms")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement resendCodeButton;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && verificationCodeInput.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public CodeVerificationPage(){
        waitForPage();
    }

    public void submitVerificationCode(String code){
        verificationCodeInput.sendKeys(code);
        verifyButton.click();
        //return new UseSandboxxPage();
    }
}
