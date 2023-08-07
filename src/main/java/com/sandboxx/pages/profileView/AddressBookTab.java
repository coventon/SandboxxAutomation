package com.sandboxx.pages.profileView;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
}
