package com.sandboxx.pages.registration;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SelectRecruitingStationPage extends BasePage {

    public enum RecruitingStation {
        RS318, RS339, RS311, RS337, RS314
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
    @AndroidFindBy(xpath = "//android.widget.ImageButton")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Back']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backLabel;

    // # Recruiting Stations
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='318th Recruiting Squadron (Officer Accessions) • 318th Recruiting Squadron (Officer Accessions)']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement squadron318;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='339th Recruiting Squadron • 339th Recruiting Squadron']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement squadron339;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='311th Recruiting Squadron • 311th Recruiting Squadron']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement squadron311;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='337th Recruiting Squadron • 337th Recruiting Squadron']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement squadron337;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='314th Recruiting Squadron • 314th Recruiting Squadron']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement squadron314;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='319th Recruiting Squadron • 319th Recruiting Squadron']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement squadron319;

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

    public SelectRecruitingStationPage(){
        waitForPage();
    }

    public void tapSquadron(RecruitingStation station){
        //squadron339.click();
        switch (station){
            case RS318:
                squadron318.click();
                break;
            case RS339:
                squadron339.click();
                break;
            case RS311:
                squadron311.click();
                break;
        }
    }

    public ShipDateSelectPage selectSquadron(RecruitingStation station){
        tapSquadron(station);
        return new ShipDateSelectPage();
    }
}
