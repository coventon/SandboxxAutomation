package com.sandboxx.pages.homeView.letters;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LetterAddonsPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.xpath("//android.widget.TextView[@text='Letter Add-ons']");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Letter Add-ons']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/action_next")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement nextButton;

    // # Add Ons
    // # Motivation
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Daily Drive: Motivation']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement motivationHeader;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Daily Drive: Motivation']//ancestor::androidx.cardview.widget.CardView//android.widget.TextView[@text='$40.00 for 4 weeks']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement motivationPrice;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Send John inspirational quotes and images, every day for the next 20 weekdays']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement motivationDescription;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Daily Drive: Motivation']//ancestor::androidx.cardview.widget.CardView//android.widget.Button[@text='ADD TO CART']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement motivationAddToCardBtn;

    // # No Add-Ons Modal
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='No Add-ons?']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement modalTitle;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Would you like to include a newsletter or a gift card?']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement modalSubTitle;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='ADD ITEMS']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addItemsButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='NO, THANKS']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement noThanksBtn;
    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && motivationHeader.isDisplayed()
                && motivationPrice.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public LetterAddonsPage(){waitForPage();}

    public void tapNext(){
        nextButton.click();
        noThanksBtn.click();
    }
}
