package com.sandboxx.pages.homeView.letters;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.framework.utils.CustomWait;
import com.sandboxx.pages.BasePage;
import com.sandboxx.pages.profileView.addressBook.currentContact.CurrentContactPage;
import com.sandboxx.pages.profileView.addressBook.newContact.MailingAddressPage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
        wait.until((e) -> isAt());
    }

    public RecipientPage() {
        waitForPage();
    }

    public MailingAddressPage tapNewContact() {
        newContactLabel.click();
        return new MailingAddressPage();
    }

    public boolean isContactDisplayed(String contactName) {
        try {
            WebElement contactCard = AppDriver.getDriver()
                    .findElement(By.xpath("//android.widget.TextView[@text='" + contactName + "']/parent::android.widget.LinearLayout"));
            //System.out.println("Checkbox checked bool: "+ Boolean.parseBoolean(isChecked));
            return contactCard.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public boolean isContactDisplayed2(String contactName) {
        CustomWait customWait = new CustomWait(AppDriver.getDriver(), 200);

        try {
            //new HomePage();
            return customWait.findElementWithCustomWait(By.xpath("//android.widget.TextView[@text='" + contactName + "']")).isDisplayed();
            //return invalidCredentialsAlert.isDisplayed(); //|| pageHeader.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e) {
            return false;
        }
    }

    public WebElement getContactCard(String contactName) {
        WebElement contactCard = AppDriver.getDriver()
                .findElement(By.xpath("//android.widget.TextView[@text='" + contactName + "']/ancestor::android.widget.LinearLayout[@resource-id='com.sandboxx.android.dev:id/ll_address_book_entry']"));
        return contactCard;
    }

    public CurrentContactPage selectContact(String contactName) {
        getContactCard(contactName).click();
        return new CurrentContactPage();
    }

    public void tapAddressBookTab() throws InterruptedException {
        addressBookTab.click();
        Thread.sleep(1000);
    }

    public boolean isContactCardDisplayed(String contactName) {

        WebElement contactCard = null;

        List<WebElement> contactList = AppDriver.getDriver().findElements(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='com.sandboxx.android.dev:id/rc_users']//android.widget.TextView[@resource-id='com.sandboxx.android.dev:id/tv_contact_name']"));
        for (WebElement e : contactList) {
            if (e.getText().equals(contactName)) {
                System.out.println("contact Name: " + e.getText());
                contactCard = e;
                break;
            }
        }

        return contactCard != null;
    }
}
