package com.sandboxx.pages.registration.onboarding;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BranchSelectionPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    public String pageHeaderText = "What branch of service are they?";
    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/heading_text");
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'What branch of service are they?')]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;

    // # Cards
    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[1]")
    @iOSXCUITFindBy(xpath = "")
    public WebElement nationalGuardCard;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='National Guard']")
    @iOSXCUITFindBy(id = "")
    public WebElement nationalGuardHeader;
    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[2]")
    @iOSXCUITFindBy(xpath = "")
    public WebElement reserveCard;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Reserve']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement reserveHeader;
    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[3]")
    @iOSXCUITFindBy(xpath = "")
    public WebElement activeDutyCard;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Active Duty']")
    @iOSXCUITFindBy(id = "")
    public WebElement activeDutyHeader;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && nationalGuardCard.isDisplayed()
                && pageHeader.getText().equals(pageHeaderText);
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public BranchSelectionPage(){waitForPage();}

    public BranchServicePage tapActiveDuty(){
        activeDutyHeader.click();
        return new BranchServicePage();
    }
    public void selectServiceBranch(String branch){
        switch (branch){
            case "National Guard":
                nationalGuardCard.click();
                break;
            case"Reserve":
                reserveHeader.click();
                break;
            case"Active Duty":
                activeDutyCard.click();
                break;
        }
    }
}
