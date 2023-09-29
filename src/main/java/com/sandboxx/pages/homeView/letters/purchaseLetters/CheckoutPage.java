package com.sandboxx.pages.homeView.letters.purchaseLetters;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import com.sandboxx.pages.profileView.ProfilePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.xpath("//android.widget.TextView[@text='Checkout']");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Checkout']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Back']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Summary']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement summary;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Support Corporal']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement supportCorporal;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Support Corporal']/following-sibling::android.widget.TextView")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement supportCorporalPrice;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Support Commander']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement supportCommanderLabel;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Support Commander']/following-sibling::android.widget.TextView")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement supportCommanderPrice;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Savings']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement savingsLabel;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Savings']/following-sibling::android.widget.TextView")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement savingsAmount;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Tax ')]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement taxLabel;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Tax ')]/following-sibling::android.widget.TextView")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement taxPrice;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Total']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement totalLabel;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Total']/following-sibling::android.widget.TextView")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement totalPrice;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Payment Method']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement paymentMethodHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_bundle_payment_method")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement paymentMethod;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Select Payment Method']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement selectPaymentButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='PURCHASE']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement purchaseButton;

    // # Select Payment Method modal
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/bt_supported_payment_methods_header")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement paymentMethodModalHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/bt_payment_method_icon")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement paymentMethodModalIcon;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/bt_payment_method_type")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement paymentType;

    // Letters Purchase Success Modal
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_bundle_purchase_success")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement successPurchaseMsg;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/btn_continue")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueButton;
    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && summary.isDisplayed()
                && paymentMethodHeader.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public CheckoutPage(){waitForPage();}

    public void selectPaymentMethod(){
        selectPaymentButton.click();
    }

    public void selectCreditCard(){
        paymentType.click();
    }
    public void tapPurchase(){
        wait.until(ExpectedConditions.elementToBeClickable(purchaseButton));
        purchaseButton.click();
    }
    public ProfilePage continueWithPurchase(){
        continueButton.click();
        return new ProfilePage();
    }
}
