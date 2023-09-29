package com.sandboxx.pages.profileView.addressBook.currentContact;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import com.sandboxx.pages.profileView.addressBook.AddressBookTab;
import com.sandboxx.pages.profileView.addressBook.newContact.MailingAddressPage;
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

    // Delete Contact Confirmation Modal
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Are you sure?']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement deleteModalHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Do you really want to delete ')]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement deleteModalSubtitle;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='DELETE']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement deleteButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='CANCEL']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cancelButton;

    // Auto linked contact section (Add as a contact)
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Add as a contact']/preceding-sibling::android.widget.ImageView")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addContactIcon;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Add as a contact']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addContactLabel;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.sandboxx.android.dev:id/ll_connected_explanation_group']/child::android.widget.ImageView")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement autoConnectedIcon;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_connected_explanation")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement autoConnectedExplanation;
    //public  final String  autoConnectedExplanationText = "You and Conrad Hobbs are connected. Being connected means that you can see the other person's details, like their address. In order to edit or update their address, you will need to add your connection as a contact.";

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Recipient does not have a valid address.']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement invalidAddressAlert;
    @Override
    public boolean isAt() {
        return actionBarName.isDisplayed() && backButton.isDisplayed()
                && contactInitials.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(actionBarNameLocator));
        wait.until((e)->isAt());
    }
    public CurrentContactPage(){waitForPage();}

    public void tapNext(){
        nextButton.click();
    }

    public AddressBookTab deleteContact(){
        deleteContactButton.click();
        deleteButton.click();
        return new AddressBookTab();
    }
    public EditContactPage editContact(){
        editContactButton.click();
        return new EditContactPage();
    }

    public MailingAddressPage addAsContact(){
        addContactLabel.click();
        return new MailingAddressPage();
    }

    public String  getAutoConnectedExplanationText(String contactName){
        return "You and "+contactName+" are connected. Being connected means that you can see the other person's details, like their address. In order to edit or update their address, you will need to add your connection as a contact.";
    }
}
