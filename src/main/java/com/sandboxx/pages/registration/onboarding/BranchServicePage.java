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

public class BranchServicePage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    public String pageHeaderText = "What branch of service are they?";
    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/heading_text");
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'What branch of service are they?')]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;

    // # Cards
    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[1]")
    @iOSXCUITFindBy(xpath = "")
    public WebElement armyCard;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Army']")
    @iOSXCUITFindBy(id = "")
    public WebElement armyHeader;
    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[2]")
    @iOSXCUITFindBy(xpath = "")
    public WebElement airForceCard;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Air Force']")
    @iOSXCUITFindBy(id = "")
    public WebElement airForceHeader;
    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[3]")
    @iOSXCUITFindBy(xpath = "")
    public WebElement navyCard;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Navy']")
    @iOSXCUITFindBy(id = "")
    public WebElement navyHeader;
    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[4]")
    @iOSXCUITFindBy(xpath = "")
    public WebElement marineCorpsCard;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Marine Corps']")
    @iOSXCUITFindBy(id = "")
    public WebElement marineCorpsHeader;
    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[5]")
    @iOSXCUITFindBy(xpath = "")
    public WebElement spaceForceCard;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Space Force']")
    @iOSXCUITFindBy(id = "")
    public WebElement spaceForceHeader;
    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[6]")
    @iOSXCUITFindBy(xpath = "")
    public WebElement coastGuardCard;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Coast Guard']")
    @iOSXCUITFindBy(id = "")
    public WebElement coastGuardHeader;
    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && armyHeader.isDisplayed()
                && pageHeader.getText().equals(pageHeaderText);
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public BranchServicePage(){
        waitForPage();
    }

    public WelcomePage selectMarineCorp(){
        marineCorpsHeader.click();
        return new WelcomePage();
    }
    public SelectRecruitingStationPage selectAirForce(){
        airForceHeader.click();
        return new SelectRecruitingStationPage();
    }

    public void selectNavy(){
        navyCard.click();
    }
}
