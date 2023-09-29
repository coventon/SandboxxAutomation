package com.sandboxx.pages.profileView.addressBook.currentContact;

import com.sandboxx.dataManagement.testData.userModels.Address;
import com.sandboxx.dataManagement.testData.userModels.Person;
import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.framework.utils.PageActionsHelper;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditContactPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By actionBarNameLocator = By.xpath("//android.widget.TextView[@text='Edit Contact']");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Edit Contact']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement actionBarName;
    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='Save']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement saveButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Title']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement titleLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/actv_titles")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement titleDropdown;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='First Name']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement firstNameLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/contact_first_name")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement firstNameInput;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Last Name']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lastNameLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/contact_last_name")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lastNameInput;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_contact_full_name")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement displayFullNameLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/reverse_name_switch")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement displayFullNameSwitch;
    //android.widget.TextView[@text='You can enter address manually or']
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='You can enter address manually or']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addressEntrySubtitle;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='SELECT A BASE']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement selectBaseButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Address Line 1']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement contactAddress1Label;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/contact_address1")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement contactAddress1Input;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Address Line 2']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement contactAddress2Label;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/contact_address2")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement contactAddress2Input;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='City*']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cityLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/contact_city")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cityInput;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='State*']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement stateLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/contact_state")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement stateInput;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Zip Code*']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement zipLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/contact_zip")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement zipInput;

    @Override
    public boolean isAt() {
        return actionBarName.isDisplayed() && backButton.isDisplayed()
                && saveButton.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(actionBarNameLocator));
        wait.until((e)->isAt());
    }
    public EditContactPage(){waitForPage();}

    public void editAddress(Address address){
        PageActionsHelper.scrollDown();
        editAddress1(address.getAddress());
        editAddress2(address.getAddress2());
        editCity(address.getCity());
        editState(address.getState());
        editZip(address.getZipCode());
        saveButton.click();
    }

    public void editAddress1(String address){
        contactAddress1Input.clear();
        contactAddress1Input.sendKeys(address);
    }
    public void editAddress2(String address){
        contactAddress2Input.clear();
        contactAddress2Input.sendKeys(address);
    }
    public void editCity(String city){
        cityInput.clear();
        cityInput.sendKeys(city);
    }
    public void editZip(String zip){
        zipInput.clear();
        zipInput.sendKeys(zip);
    }

    public void editState(String state){
        //stateInput.click();
        stateInput.clear();
        stateInput.sendKeys(state);
        //stateInput.click();
        //WebElement stateOption = AppDriver.getDriver().findElement(By.xpath("//android.widget.TextView[@text='"+state+"']"));
        //stateOption.click();
    }
}
