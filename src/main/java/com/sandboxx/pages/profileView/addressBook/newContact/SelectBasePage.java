package com.sandboxx.pages.profileView.addressBook.newContact;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.framework.utils.PageActionsHelper;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SelectBasePage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    @AndroidFindBy(xpath = "//android.widget.ImageButton")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Cancel']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cancelButton;

    private final By marineCorpsHeaderLocator = By.xpath("//android.widget.TextView[@text='Marine Corps']");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Marine Corps']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement marineCorpsHeader;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CAMP LEJUENE, NC']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement campLejune;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CAMP PENDLETON, CA']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement campPendleton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='PARRIS ISLAND, SC']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement parrisIsnand;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='QUANTICO, VA']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement quantico;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SAN DIEGO, CA']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sanDiego;

    // Army
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Army']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement armyHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='FORT JACKSON, SC']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement fortJackson;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='FORT KNOX, KY']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement fortKnox;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='FORT LEONARD WOOD, MO]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement fortLeonardWood;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='FORT MOORE, GA']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement fortMoor;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='FORT SILL, OK']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement fortSill;

    // Army
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Navy']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement navyHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='GREAT LAKES, IL']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement greatLakes;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='NEWPORT, RI']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement newport;

    @Override
    public boolean isAt() {
        return marineCorpsHeader.isDisplayed() && backButton.isDisplayed()
                && campLejune.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(marineCorpsHeaderLocator));
    }
    public SelectBasePage(){waitForPage();}

    public void selectBase(String base){
        switch (base){
            case "CAMP LEJUENE":
                campLejune.click();
                break;
            case "CAMP PENDLETON":
                campPendleton.click();
                break;
            case "PARRIS ISLAND":
                parrisIsnand.click();
                break;
            case "GREAT LAKES":
                PageActionsHelper.scrollDown();
                greatLakes.click();
                break;
        }
    }
}
