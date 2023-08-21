package com.sandboxx.pages.homeView.letters;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import com.sandboxx.pages.profileView.addressBook.newContact.MailingAddressPage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecipientPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.xpath("//android.widget.TextView[@text='Recipient']");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Recipient']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/iv_recipient_search")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement searchIcon;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/et_recipient_search")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement searchInput;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/recipient_new_image")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement newContactIcon;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='New Contact']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement newContactLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/iv_import_contacts")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement importContactsIcon;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Import Contacts']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement importContactsLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/layout_phone_verification")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement phoneVerificationCard;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc='Recent']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement recentTab;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc='Address Book']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addressBookTab;
    @AndroidFindBy(xpath = "com.sandboxx.android.dev:id/image_no_contact_logo")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement noContactLogo;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/text_no_contacts_yet")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement noContactLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_add_new_contact")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addNewContactButton;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && backButton.isDisplayed()
                && searchInput.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public RecipientPage(){waitForPage();}

    public MailingAddressPage tapNewContact(){
        newContactLabel.click();
        return new MailingAddressPage();
    }
    public boolean isContactDisplayed(String contactName){
        WebElement contactCard = AppDriver.getDriver()
                .findElement(By.xpath("//android.widget.TextView[@text='"+contactName+"']/parent::android.widget.LinearLayout"));
        //System.out.println("Checkbox checked bool: "+ Boolean.parseBoolean(isChecked));
        return contactCard.isDisplayed();
    }
    public WebElement getContactCard(String contactName){
        WebElement contactCard = AppDriver.getDriver()
                .findElement(By.xpath("//android.widget.TextView[@text='"+contactName+"']/ancestor::android.widget.LinearLayout[@resource-id='com.sandboxx.android.dev:id/ll_address_book_entry']"));
        return contactCard;
    }

    public void selectContact(String contactName){
        getContactCard(contactName).click();
    }
}
