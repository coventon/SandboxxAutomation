package com.sandboxx.pages.homeView.giftCards;

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

public class CardRecipientPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.xpath("//android.widget.TextView[@text=\"Who's it for?\"]");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Who's it for?\"]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;

    @AndroidFindBy(xpath = "//android.widget.ImageButton")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/choose_from_contacts")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement fromContactsButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Choose From Contacts']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement fromContactsLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/choose_contacts_arrow")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement fromContactsIcon;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/add_new_recipient")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addNewRecipientDropdown;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Add New Recipient']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addNewRecipientLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/add_new_recipient")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addNewRecipientIcon;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='CONTINUE']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueButton;

    // New Recipient Form
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='First Name']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement firstNameLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/first_name_edit_text")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement firstNameInput;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Last Name']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lastNameLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/last_name_edit_text")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lastNameInput;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Recipient Address 1']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement address1Label;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/recipient_address_1_edit_text")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement address1Input;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Recipient Address 2']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement address2Label;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/recipient_address_2_edit_text")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement address2Input;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='City*']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cityLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/city_edit_text")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cityInput;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='State*']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement stateLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/state_selector")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement stateInput;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Zip Code*']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement zipLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/zipcode_edit_text")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement zipInput;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && fromContactsButton.isDisplayed()
                && addNewRecipientDropdown.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public CardRecipientPage(){waitForPage();}

    public CardSenderPage submitNewContact(Person contact){
        addNewRecipientDropdown.click();
        fillContactForm(contact);
        continueButton.click();
        return new CardSenderPage();
    }

    public void fillContactForm(Person recipient){
        firstNameInput.sendKeys(recipient.getFirstName());
        lastNameInput.sendKeys((recipient.getLastName()));
        Address address = recipient.getAddress();
        address1Input.sendKeys(address.getAddress());
        PageActionsHelper.scrollDown();
        cityInput.sendKeys(address.getCity());
        selectState(address.getState());
        zipInput.sendKeys(address.getZipCode());
    }
    public void selectState(String state){
        stateInput.click();
        WebElement stateOption = AppDriver.getDriver().findElement(By.xpath("//android.widget.TextView[@text='"+state+"']"));
        stateOption.click();
    }

}
