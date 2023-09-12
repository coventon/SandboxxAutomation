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

public class CardSenderPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/gift_card_sender_header");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Who's it from?\"]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;

    @AndroidFindBy(xpath = "//android.widget.ImageButton")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;

    // Sender form
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='From']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement fromLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/name_edit_text")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement fromInput;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/from_character_count")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement fromCharacterCount;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Email Address']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement emailLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/email_edit_text")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement emailInput;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Gift Message']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement messageLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/gift_message_edit_text")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement messageInput;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/gift_message_character_count")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement messageCharacterCount;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='CONTINUE']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueButton;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && fromLabel.isDisplayed()
                && fromInput.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public CardSenderPage(){waitForPage();}

    public void fillForm(String name, String email, String message){
        fromInput.sendKeys(name);
        emailInput.sendKeys(email);
        messageInput.sendKeys(message);
    }

    public BillingDetailsPage submitSenderForm(String name, String email, String message){
        fillForm(name,email,message);
        continueButton.click();
        return new BillingDetailsPage();
    }
}
