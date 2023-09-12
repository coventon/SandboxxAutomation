package com.sandboxx.pages.homeView;

import com.sandboxx.dataManagement.constants.GoTo;
import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import com.sandboxx.pages.homeView.giftCards.GiftCardsPage;
import com.sandboxx.pages.homeView.letters.RecipientPage;
import com.sandboxx.pages.inviteView.InvitePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    //public String pageHeaderText = "Confirm your phone number";
    private final By welcomeGreetingLocator = By.id("com.sandboxx.android.dev:id/tv_dsh_welcome_greeting");
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_dsh_welcome_greeting")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement welcomeGreeting;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/icon_inbox")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement inboxIcon;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_dsh_cutoff_number")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cutOffNumber;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_dsh_cutoff_text")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cutOffText;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/iv_dsh_letter_cutoff_info")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cutOffInfo;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_dsh_graduation_text")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement graduationText;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/fab_compose")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement composeButton;

    // Letters section
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Letters']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lettersHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_dsh_credit_count")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lettersCreditCount;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Letters\n" + "Remaining']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement purchaseCreditsButton;

    // # Gift Cards Section
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Send a Sandboxx Gift Card to your \n" + " recruit or service member']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement giftCardsTitle;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='GIFT CARDS']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement giftCardsButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/image_my_wallet")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement giftCardsImage;

    // Referral Section
    //android.widget.TextView[@text='Give a Letter, Get a Letter']
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Give a Letter, Get a Letter']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement referralTitle;
    //android.widget.Button[@text='REFER A FRIEND']
    @AndroidFindBy(xpath = "//android.widget.Button[@text='REFER A FRIEND']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement referFriendButton;

    public HomePage(){
        createMainNavigation();
    }
    @Override
    public boolean isAt() {
        return welcomeGreeting.isDisplayed() && inboxIcon.isDisplayed() && cutOffText.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeGreetingLocator));
    }

    public void navigateToProfile(){
        navigateTo(GoTo.Sandboxx_Profile);
    }

    public RecipientPage tapCompose(){
        composeButton.click();
        return new RecipientPage();
    }

    public GiftCardsPage navigateToGiftCards(){
        giftCardsButton.click();
        return new GiftCardsPage();
    }

    public InvitePage tapReferFriend(){
        referFriendButton.click();
        return new InvitePage();
    }

}
