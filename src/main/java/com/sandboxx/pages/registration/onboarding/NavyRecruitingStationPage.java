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

public class NavyRecruitingStationPage extends BasePage {
    public enum RecruitingStation {
        PORTLAND, MINEAPOLIS, CAPITALCITY, ALAMOCITY, SEATTLE
    }
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    public String pageHeaderText = "Select your recruiting station";
    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/title_tv");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Select your recruiting station']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Start typing or select from the dropdown menu below.']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageSubHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/region_search_et")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement searchInput;
    @AndroidFindBy(xpath = "//android.widget.ImageButton")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Back']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backLabel;

    // # Stations
    // West
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='REGION WEST']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement regionWestHeader;
    //android.widget.TextView[@text='Portland • Portland']
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Portland • Portland']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement portlandStation;

    // Central
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='REGION CENTRAL']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement regionCentralHeader;

    // East
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='REGION EAST']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement regionEastHeader;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && pageSubHeader.isDisplayed()
                && pageHeader.getText().equals(pageHeaderText);
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public NavyRecruitingStationPage(){
        waitForPage();
    }
    public WebElement getStatoin(String stationName){
        return AppDriver.getDriver().findElement(By.xpath("//android.widget.TextView[@text='"+stationName+"']"));
    }

    public void selectStation(String stationName){
        getStatoin(stationName).click();
    }
}
