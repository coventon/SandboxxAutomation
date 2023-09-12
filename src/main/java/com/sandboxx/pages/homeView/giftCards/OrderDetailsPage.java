package com.sandboxx.pages.homeView.giftCards;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import com.sandboxx.pages.homeView.HomePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderDetailsPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/order_review_header");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Order Details']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/gift_card_image")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cardImage;

    // Success Alert
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/order_success")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement orderSucscessIcon;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/success_thanks")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement successThanks;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"We're process your order.  Please\n" + " check your email for confirmation.\"]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement successText;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Your order number is')]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement successOrderNumber;

    // Details
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
    // Message
    //android.widget.TextView[@text='Message:']
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Message:']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement messageLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/message_content")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement messageContent;
    // Payment
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Payment:']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement paymentLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/payment_info")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement paymentInfo;
    //Buttons
    @AndroidFindBy(xpath = "//android.widget.Button[@text='SEND ANOTHER GIFT']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sendAnotherButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='BACK TO DASHBOARD']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backToDashboardButton;
    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && cardImage.isDisplayed()
                && orderSucscessIcon.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public OrderDetailsPage(){waitForPage();}

    public HomePage tapBackToDashboards(){
        backToDashboardButton.click();
        return new HomePage();
    }

}
