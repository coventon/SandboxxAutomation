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

public class GiftCardsPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    public String pageHeaderText = "What branch of service are they?";
    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/gift_card_header");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Gift Cards']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Help your recruit cover training expenses, celebrate graduation, or simply show your support']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageSubHeader;
    @AndroidFindBy(xpath = "//android.widget.ImageButton")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/card_image")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cardImage;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='BUY A GIFT CARD']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement buyGiftCardButton;
    //android.widget.TextView[@text='Manage']
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Manage']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement manageCardsTitle;
    //android.widget.TextView[@text='Redeem or view sent gift cards']
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Manage']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement manageCardsSubTitle;
    //android.widget.Button[@text='MANAGE GIFT CARD']
    @AndroidFindBy(xpath = "//android.widget.Button[@text='MANAGE GIFT CARD']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement manageCardsButton;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && pageSubHeader.isDisplayed()
                && cardImage.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public GiftCardsPage(){waitForPage();}

    public CardPurchasePage buyGiftCards(){
        buyGiftCardButton.click();
        return new CardPurchasePage();
    }
    public ManageGiftCardsPage manageGiftCard(){
        manageCardsButton.click();
        return new ManageGiftCardsPage();
    }
}
