package com.sandboxx.pages.profileView.settings;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import com.sandboxx.pages.LandingPage;
import com.sandboxx.pages.profileView.settings.deleteAccount.VerifyAccountPage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SettingsPage extends BasePage {

    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.xpath("//android.widget.TextView[@text='Settings']");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Settings']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='PROFILE']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement profileHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Edit Profile']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement editProfileDropdown;
    @AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Next\"])[1]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement editProfileArrow;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Add family & friends']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addFamilyAndFriendsBtn;
    @AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Next\"])[2]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addFamilyAndFriendsArrow;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ACCOUNT']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement accountHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/text_phone_verfication")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement changePhoneNumberDropdown;
    @AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Next\"])[2]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement changePhoneNumberArrow;
    @AndroidFindBy(id = "//android.widget.TextView[@text='Change Email']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement changeEmailDropdown;
    @AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Next\"])[3]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement changeEmailArrow;
    @AndroidFindBy(id = "//android.widget.TextView[@text='Change Password']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement changePasswordDropdown;
    @AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Next\"])[4]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement changePasswordArrow;
    @AndroidFindBy(id = "//android.widget.TextView[@text='Change Password']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement paymentMethodDropdown;
    @AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Next\"])[5]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement paymentMethodArrow;
    @AndroidFindBy(id = "//android.widget.TextView[@text='Subscriptions']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement subscriptionsDropdown;
    @AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Next\"])[6]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement subscriptionsArrow;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/rl_delete_account")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement deleteAccountButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete Account']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement deleteAccountLabel;
    @AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Next\"])[7]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement deleteAccountArrow;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SUPPORT']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement supportsHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/settings_support_love_sandboxx_layout")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement loveSandboxxTile;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/settings_support_love_sandboxx")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement loveSandboxxTileHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/setings_support_love_sandboxx_subheading")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement loveSandboxxTileSubHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/settings_support_rate_us_layout")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement rateUsTile;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/settings_contact_support")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement needHelpTile;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/settings_contact_support_title")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement needHelpHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/setings_contact_support")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement needHelpSubHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/rl_contact_us")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement contactSandboxxTile;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='LEGAL']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement legalHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/settings_fine_print_terms_of_service_layout")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement termOfServiceDropdown;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/settings_fine_print_terms_of_service")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement termOfServiceTitle;
    @AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Next\"])[2]\n")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement termOfServiceArrow;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/settings_fine_print_privacy_layout")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement privacyPolicyDropdown;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/settings_fine_print_privacy")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement privacyPolicyTitle;
    @AndroidFindBy(xpath = "((//android.widget.ImageView[@content-desc=\"Next\"])[3]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement privacyPolicyArrow;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/rl_news_credit")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement newCreditsDropdown;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/text_news_credit")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement newCreditsTitle;
    @AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Next\"])[4]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement newCreditsArrow;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/btn_log_out")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement logoutButton;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && profileHeader.isDisplayed() && editProfileDropdown.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }

    public SettingsPage(){
        waitForPage();
    }

    public LandingPage tapLogout(){
        logoutButton.click();
        return new LandingPage();
    }

    public VerifyAccountPage tapDeleteAccount(){
        deleteAccountButton.click();
        return new VerifyAccountPage();
    }
}
