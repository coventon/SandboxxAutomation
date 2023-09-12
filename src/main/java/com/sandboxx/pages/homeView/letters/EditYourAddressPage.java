package com.sandboxx.pages.homeView.letters;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditYourAddressPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.xpath("//android.widget.TextView[@text='Edit Your Address']");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Edit Your Address']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(accessibility = "Navigate up")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement closeButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/action_save")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement saveButton;

    // # Form
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='First Name']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement firstNameLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/sender_first_name")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement firstNameInput;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Last Name']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lastNameLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/sender_last_name")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lastNameInput;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Address Line 1']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addressLine1Label;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/sender_confirm_address1")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addressLine1Input;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Address Line 2']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addressLine2Label;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/sender_confirm_address2")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addressLine2Input;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='City*']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cityLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/sender_confirm_city")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cityInput;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='State*']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement stateLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/sender_confirm_state")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement stateDropdown;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Zip Code*']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement zipCodeLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/sender_confirm_zip")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement zipCodeInput;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && firstNameLabel.isDisplayed()
                && firstNameInput.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public EditYourAddressPage(){waitForPage();}

    public void enterAddress(String address1,String city, String state, String zipCode){
        addressLine1Input.sendKeys(address1);
        cityInput.sendKeys(city);
        stateDropdown.sendKeys(state);
        zipCodeInput.sendKeys(zipCode);
    }

    public void saveAddress(){
        saveButton.click();
    }

    public void submitAddress(String address1,String city, String state, String zipCode){
        enterAddress(address1,city,state,zipCode);
        saveAddress();
    }
}
