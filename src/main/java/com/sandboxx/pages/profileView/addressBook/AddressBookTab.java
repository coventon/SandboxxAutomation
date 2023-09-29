package com.sandboxx.pages.profileView.addressBook;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.framework.utils.PageActionsHelper;
import com.sandboxx.pages.BasePage;
import com.sandboxx.pages.profileView.addressBook.currentContact.CurrentContactPage;
import com.sandboxx.pages.profileView.addressBook.newContact.MailingAddressPage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AddressBookTab extends BasePage {

    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));
    private final By searchInputLocator = By.xpath("//android.widget.EditText[@text='Enter search here…']");

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/edittext_recipient_search")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement searchInput;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/image_new_contact")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement newContactIcon;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_new_contact")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement newContactLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/image_import_contacts")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement importContactsIcon;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_import_contacts")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement importContactsLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/image_no_contact_logo")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement noContactsLogo;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/text_no_contacts_yet")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement noContactsLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_add_new_contact")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addNewContactButton;

    @Override
    public boolean isAt() {
        return searchInput.isDisplayed() && newContactIcon.isDisplayed() && newContactLabel.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputLocator));
    }
    public AddressBookTab(){waitForPage();}

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

    public CurrentContactPage selectContact(String contactName){
        getContactCard(contactName).click();
        return new CurrentContactPage();
    }
    public CurrentContactPage selectAddedContact(String contactName){
        getContactWithoutLinkIcon(contactName).click();
        return new CurrentContactPage();
    }
    public MailingAddressPage addNewContact(){
        newContactLabel.click();
        return new MailingAddressPage();
    }

    public WebElement isContactPresent(String contactName){
        WebElement contactElement = null;
        while (contactElement == null){
            List<WebElement> allContacts = AppDriver.getDriver().findElements(By.id("com.sandboxx.android.dev:id/tv_contact_name"));

            // check all state for the desired state name
            for(WebElement e:allContacts){
                if(e.getText().equals(contactName)){
                    contactElement = e;
                    break;
                }
            }
        }

        // If element is not found scroll down
        if(contactElement == null){
            PageActionsHelper.scrollDown();
        }
        return contactElement;
    }

    public WebElement getContactWithoutLinkIcon(String contactName){
        WebElement contactCard = AppDriver.getDriver()
                .findElement(By.xpath("//android.widget.TextView[@text='"+contactName+"']/ancestor::android.widget.LinearLayout[@resource-id='com.sandboxx.android.dev:id/ll_address_book_entry' and not(.//android.widget.ImageView)]"));
        return contactCard;
    }
}
