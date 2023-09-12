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

public class MarinesRecruitingStationPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    public String pageHeaderText = "Select your recruiting station";
    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/title_tv");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Select your recruiting station']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Start typing or select from the dropdown menu below.']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageSubHeader;
    @AndroidFindBy(xpath = "//android.widget.ImageButton")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Back']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/region_search_et")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement searchBox;

    // Marines Stations
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='1ST MARINE CORPS DISTRICT']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement firstMCDLabel;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Massachusetts • Boston']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement BostonMA;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Massachusetts • Springfield']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement SpringfieldMA;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='New York • Albany']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement albanyNY;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && pageSubHeader.isDisplayed()
                && searchBox.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }

    public MarinesRecruitingStationPage(){
        waitForPage();
    }

    public WebElement getStation(String stationName){
        return AppDriver.getDriver().findElement(By.xpath("//android.widget.TextView[@text='"+stationName+"']"));
    }

    public void selectStationByLocation(String stationName){
        getStation(stationName).click();
    }
}
