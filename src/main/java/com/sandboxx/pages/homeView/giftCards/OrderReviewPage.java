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

public class OrderReviewPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/order_review_header");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Order Review']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.ImageButton")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/gift_card_image")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cardImage;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Amount on Card']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cardAmountLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/amount_on_card_total")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cardAmountTotal;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Card Activation Fee:']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement activationFeeLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/card_activation_fee_total")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement activationFeeTotal;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Total Cost:']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement totalCostLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/cost_total")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement totalCost;
    //android.widget.TextView[@text='From:']
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='From:']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement fromLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/from_name")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement fromValue;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='To:']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement toLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/to_text")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement toValue;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='All Sandboxx Gift Card Sales are Final']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement saleDisclaimer;
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@text,'PLACE ORDER')]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement placeOrderButton;


    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && cardImage.isDisplayed()
                && cardAmountLabel.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public OrderReviewPage(){waitForPage();}

    public OrderDetailsPage placeOrder(){
        placeOrderButton.click();
        return new OrderDetailsPage();
    }
}
