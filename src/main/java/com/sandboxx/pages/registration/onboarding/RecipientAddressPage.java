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

public class RecipientAddressPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/title_tv");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Do you know the mailing address of your recipient?']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Yes']/ancestor::androidx.cardview.widget.CardView")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement yesCard;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='No']/ancestor::androidx.cardview.widget.CardView")
    @iOSXCUITFindBy(xpath = "")
    public WebElement noCard;
    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && yesCard.isDisplayed()
                && noCard.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public RecipientAddressPage(){waitForPage();}

    public void selectYes(){
        yesCard.click();
    }
    public WelcomePage selectNo(){
        noCard.click();
        return new WelcomePage();
    }
}
