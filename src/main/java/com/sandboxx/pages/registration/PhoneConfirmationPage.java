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

public class PhoneConfirmationPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    public String pageHeaderText = "Confirm your phone number";
    private final By pageHeaderLocator = By.xpath("//android.widget.TextView[1]");
    @AndroidFindBy(xpath = "//android.widget.TextView[1]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;

    public String pageSubHeaderText = "Access all of Sandboxx";
    private final By pageSubHeaderLocator = By.xpath("//android.widget.TextView[2]");
    @AndroidFindBy(xpath = "//android.widget.TextView[2]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageSubHeader;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/et_phone_number")
    @iOSXCUITFindBy(id = "")
    public WebElement phoneNumberInput;

    @AndroidFindBy(xpath = "//android.widget.TextView[3]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageDescription;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/btn_send_confirmation_code")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sendConfirmationButton;

    @Override
    public boolean isAt() {
        return pageSubHeader.isDisplayed() && phoneNumberInput.isDisplayed()
                && pageSubHeader.getText().equals(pageSubHeaderText);
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public void submitPhoneConfirmation(String phone){
        phoneNumberInput.sendKeys(phone);
        sendConfirmationButton.click();
    }

}
