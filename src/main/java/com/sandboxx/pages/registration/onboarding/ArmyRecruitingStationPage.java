package com.sandboxx.pages.registration.onboarding;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.framework.utils.CustomWait;
import com.sandboxx.framework.utils.PageActionsHelper;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ArmyRecruitingStationPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(1));

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

    public ArmyRecruitingStationPage(){
        waitForPage();
    }

    public WebElement getStation(String stationName){
        return AppDriver.getDriver().findElement(By.xpath("//android.widget.TextView[@text='"+stationName+"']"));
    }

    public void selectStationByLocation(String stationName){
        getStation(stationName).click();
    }

    public void selectStation(String stationName){
        CustomWait customWait = new CustomWait(AppDriver.getDriver(),1000);
        WebElement stationEl = null;
        int maxScrolls = 150;
        for (int i = 0; i < maxScrolls; i++) {
            try{
                stationEl = customWait.findElementWithCustomWait(By.xpath("//android.widget.TextView[@text='"+ stationName +"']"));
                //stationEl = AppDriver.getDriver().findElement(By.xpath("//android.widget.TextView[@text='"+ stationName +"']"));
                stationEl.click();
                break;
            }
            catch (NoSuchElementException | StaleElementReferenceException | TimeoutException ex){
                //PageActionsHelper.scrollDown();
                PageActionsHelper.scroll(PageActionsHelper.ScrollDirection.UP,0.6);
            }
        }
        if(stationEl == null){
            System.out.printf(">>>> Station: %s could not be found\n", stationName);
        }

    }
}
