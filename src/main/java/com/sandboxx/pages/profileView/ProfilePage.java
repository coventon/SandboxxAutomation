package com.sandboxx.pages.profileView;

import com.sandboxx.dataManagement.constants.GoTo;
import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import com.sandboxx.pages.MainNavigation;
import com.sandboxx.pages.profileView.settings.SettingsPage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    //public String pageHeaderText = "Confirm your phone number";
    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/text_profile_heading");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='PROFILE']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement profileHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_settings")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement settingsButton;
    // # Profile navigation tabs
    @AndroidFindBy(accessibility = "Overview")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement overviewTab;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"Squads\"]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement squadsTab;
    @AndroidFindBy(accessibility = "Address Book")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addressBookTab;
    //--------------------
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/text_user_name")
    @iOSXCUITFindBy(id = "")
    public WebElement fullNameDisplay;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/text_user_type")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement userType;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/image_profile")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement profileImage;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_letters_sent")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lettersSentLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/text_letter_sent_count")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lettersSentCount;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_tokens")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lettersLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/text_token_count")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lettersCount;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_send_letter")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sendLetterButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_purchase_tokens")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement purchaseLetters;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/view_get_free_letters_touch_area")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lettersSentDropdown;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_letters_sent_option")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lettersSentDropdownLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/image_letter_sent_arrow")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lettersSentDropdownArrow;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/view_get_free_letters_touch_area")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement getFreeLettersDropdown;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/label_get_free_letters")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement getFreeLettersLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/image_get_free_letters_arrow")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement getFreeLettersDropdownArrow;

    @Override
    public boolean isAt() {
        return profileHeader.isDisplayed() && overviewTab.isDisplayed() && squadsTab.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }

    public AddressBookTab tapAddressBook(){
        addressBookTab.click();
        return new AddressBookTab();
    }

    public SettingsPage tapSettings(){
        settingsButton.click();
        return new SettingsPage();
    }
}
