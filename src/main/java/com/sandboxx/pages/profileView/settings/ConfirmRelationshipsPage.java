package com.sandboxx.pages.profileView.settings;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ConfirmRelationshipsPage extends BasePage {

    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/title_tv");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Confirm Relationships']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Please select the relationship of each contact.']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageSubHeader;
    @AndroidFindBy(xpath = "//android.widget.ImageButton")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Back']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backLabel;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Import']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement importButton;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/device_rv")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement contactsListContainer;

    public List<WebElement> contactList = contactsListContainer.findElements(By.xpath("//android.view.ViewGroup"));

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && pageSubHeader.isDisplayed() && importButton.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }

    public ConfirmRelationshipsPage(){
        waitForPage();
    }

    public boolean isContactDisplayed(String contactName){
        WebElement contactCard = AppDriver.getDriver()
                .findElement(By.xpath("//android.view.ViewGroup/android.widget.TextView[@text='"+contactName+"']/parent::android.view.ViewGroup"));
        WebElement contactImage = contactCard.findElement(By.xpath("//android.widget.ImageView"));
        WebElement relation = contactCard.findElement(By.xpath("//android.widget.TextView[@text='Not Specified']"));


        return contactImage.isDisplayed() && relation.isDisplayed();
    }
}
