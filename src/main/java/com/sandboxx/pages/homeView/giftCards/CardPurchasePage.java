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

public class CardPurchasePage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/buy_gift_card_header");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sandboxx Gift Card']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/buy_gift_card_image")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cardImage;
    @AndroidFindBy(xpath = "//android.widget.ImageButton")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement closeButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Choose Amount (USD)']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement buyCardSubheader;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='$25']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement button25;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='$50']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement button50;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='$100']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement button100;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='$200']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement button200;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='USD gift cards only valid in U.S.']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement priceDisclaimer;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Delivery Method - Mail']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement deliveryMethod;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/info_iv")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement deliveryMethodInfoIcon;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sandboxx Gift Cards are emailed within 3 business days.']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement giftCardDisclaimer;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='I have read and accept the Sandboxx Gift Card']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement termsText;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Terms & Conditions']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement termsLink;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/terms_check_box")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement termsCheckbox;
    //android.widget.Button[@text='CONTINUE']
    @AndroidFindBy(xpath = "//android.widget.Button[@text='CONTINUE']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueButton;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && buyCardSubheader.isDisplayed()
                && button25.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public CardPurchasePage(){waitForPage();}

    public CardRecipientPage select25Card(){
        button25.click();
        checkAndContinue();
        return new CardRecipientPage();
    }

    public void checkAndContinue(){
        termsCheckbox.click();
        continueButton.click();
    }
}
