package com.sandboxx.pages.profileView.addressBook.currentContact;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CurrentContactPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By actionBarNameLocator = By.xpath("//android.view.ViewGroup[@resource-id='com.sandboxx.android.dev:id/action_bar']/android.widget.TextView");
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='com.sandboxx.android.dev:id/action_bar']/android.widget.TextView")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement actionBarName;
    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/action_next")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement nextButton;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_contact_initials")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement contactInitials;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_contact_name")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement contactName;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADDRESS']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addressLabel;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Edit Contact']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement editContactButton;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_contact_address_line1")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addressLine1;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_contact_address_line2")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addressLine2;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_contact_address_city")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addressCity;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_contact_address_state")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addressState;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_contact_address_zip")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addressZip;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_delete_contact")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement deleteContactButton;

    @Override
    public boolean isAt() {
        return actionBarName.isDisplayed() && backButton.isDisplayed()
                && actionBarName.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(actionBarNameLocator));
        wait.until((e)->isAt());
    }
    public CurrentContactPage(){waitForPage();}
}
