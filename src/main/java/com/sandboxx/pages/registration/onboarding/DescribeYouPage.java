package com.sandboxx.pages.registration.onboarding;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import com.sandboxx.pages.registration.WelcomePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DescribeYouPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/heading_text");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Which best describes you?']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Mom']/ancestor::androidx.cardview.widget.CardView")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement momCard;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Dad']/ancestor::androidx.cardview.widget.CardView")
    @iOSXCUITFindBy(xpath = "")
    public WebElement dadCard;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Spouse']/ancestor::androidx.cardview.widget.CardView")
    @iOSXCUITFindBy(xpath = "")
    public WebElement spouseCard;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Other']/ancestor::androidx.cardview.widget.CardView")
    @iOSXCUITFindBy(xpath = "")
    public WebElement otherCard;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Friend']/ancestor::androidx.cardview.widget.CardView")
    @iOSXCUITFindBy(xpath = "")
    public WebElement friendCard;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && momCard.isDisplayed()
                && dadCard.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public DescribeYouPage(){waitForPage();}

    public RecipientAddressPage selectOther(){
        otherCard.click();
        return new RecipientAddressPage();
    }
}
