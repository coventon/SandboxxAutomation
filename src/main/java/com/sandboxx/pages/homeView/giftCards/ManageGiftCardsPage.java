package com.sandboxx.pages.homeView.giftCards;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ManageGiftCardsPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/text_welcome_heading");
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Welcome to Sandboxx, ')]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Check your gift card balance below!']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageSubHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/image_back")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc='My Gift Cards']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement myGiftCardsTab;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc='Sent Gift Cards']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sentGiftCardsTab;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/text_no_gift_card_msg")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement noGiftCardMsg;
    final String noGiftCardsText = "It doesn't look like you have any gift cards. You can purchase a gift card, or manually add an existing one by clicking the + button below. Gift cards are delivered digitally, not in a letter.";
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_add_gift_card")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addGiftCardButton;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && pageSubHeader.isDisplayed()
                && myGiftCardsTab.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public ManageGiftCardsPage(){waitForPage();}

    public boolean isNoGiftCardTextPresent(){
        return noGiftCardMsg.getText().equals(noGiftCardsText);
    }
    public GiftCardsPage navigateBack(){
        backButton.click();
        return new GiftCardsPage();
    }
}
