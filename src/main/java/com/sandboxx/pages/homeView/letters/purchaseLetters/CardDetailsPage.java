package com.sandboxx.pages.homeView.letters.purchaseLetters;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CardDetailsPage extends BasePage {

    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.xpath("//android.widget.TextView[@text='Card Details']");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Card Details']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Back']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/bt_card_form_card_number_icon")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cardNumberIcon;
    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Card Number']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cardNumberInput;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='NEXT']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement nestButton;

    // # Card Icons
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='AMEX']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement amexIcon;
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='DISCOVER']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement discoverIcon;
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='JCB']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement jcbIcon;
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='MASTERCARD']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement mastercardIcon;
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='VISA']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement visaIcon;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/bt_card_form_expiration")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement expirationInput;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/bt_card_form_cvv")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cvvInput;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='ADD CARD']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addCardButton;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && cardNumberIcon.isDisplayed()
                && cardNumberInput.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public CardDetailsPage(){waitForPage();}

    public void enterCardNumber(String cardNum){
        cardNumberInput.sendKeys(cardNum);
    }
    public void submitCardNumber(String cardNum){
        enterCardNumber(cardNum);
        nestButton.click();
    }

    public void submitExpirationCvv(String expiration, String cvv){
        expirationInput.sendKeys(expiration);
        cvvInput.sendKeys(cvv);
        addCardButton.click();
    }
}
