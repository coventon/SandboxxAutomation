package com.sandboxx.pages.registration.onboarding;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import com.sandboxx.pages.profileView.settings.InviteFriendsPage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShipDateSelectPage extends BasePage {

    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    public String pageHeaderText = "What branch of service are they?";
    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/title_tv");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Do you know your ship date?']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.ImageButton")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Back']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backLabel;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Yes']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement yesButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='No']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement noButton;

    // Date Picker
    @AndroidFindBy(id = "android:id/date_picker_header_year")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement currentYear;
    @AndroidFindBy(id = "android:id/date_picker_header_date")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement currentDate;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='CANCEL']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cancelButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='OK']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement okButton;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && yesButton.isDisplayed()
                && noButton.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }

    public ShipDateSelectPage(){
        waitForPage();
    }

    public void tapYes(){
        yesButton.click();
    }
    public void tapDatePickerOK(){
        okButton.click();
    }
    public InviteFriendsPage tapNo(){
        noButton.click();
        return new InviteFriendsPage();
    }
}
