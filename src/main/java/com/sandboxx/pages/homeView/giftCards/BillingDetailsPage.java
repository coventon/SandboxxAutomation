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

public class BillingDetailsPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/billing_details_header");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Billing Details']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;

    @AndroidFindBy(xpath = "//android.widget.ImageButton")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Amount on Card']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cardAmountLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/gift_card_amount_tv_subtotal")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cardSubtotal;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Activation Fee']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement activationFeeLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/activation_fee_total")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement activationFeeTotal;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Total']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement totalLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/total")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement totalValue;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Select Payment Method']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement paymentMethodHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='You will not be charged until you review this order on the next page.']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement paymentMethodSubHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_payment_method")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement paymentMethodCard;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Add New Payment Method']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addPaymentMethodButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='CONTINUE']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueButton;

    // Add Payment Modal
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Select Payment Method']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement paymentModalHeader;
    @AndroidFindBy(xpath = "com.sandboxx.android.dev:id/bt_payment_method_icon")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement paymentMethodIcon;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Credit or Debit Card']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement paymentMethodLabel;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && cardAmountLabel.isDisplayed()
                && cardSubtotal.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public BillingDetailsPage(){waitForPage();}

    public void addNewPaymentMethod(){
        addPaymentMethodButton.click();
        paymentMethodLabel.click();
    }
    public void selectPaymentMethod(String cardNumber){
        WebElement card = AppDriver.getDriver().findElement(By.xpath("//android.widget.TextView[@text='"+cardNumber+"']"));
        card.click();
    }
    public OrderReviewPage selectPaymentMethodAndContinue(String cardNumber){
        selectPaymentMethod(cardNumber);
        continueButton.click();
        return new OrderReviewPage();
    }
}
