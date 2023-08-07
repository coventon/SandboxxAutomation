package com.sandboxx.pages.profileView.settings;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ImportContactsPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/title_tv");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Import Contacts']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Select which contacts you would like to import from the below list.']")
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
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/device_contact_search_et")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement searchInput;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/device_rv")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement contactsListContainer;

    public List<WebElement> contactList = contactsListContainer.findElements(By.xpath("//android.view.ViewGroup"));


    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && pageSubHeader.isDisplayed() && searchInput.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public ImportContactsPage(){
        waitForPage();
    }

    public void selectContactByName(String contactName){
        WebElement contactCard = AppDriver.getDriver()
                .findElement(By.xpath("//android.widget.TextView[@text='"+contactName+"']/parent::android.view.ViewGroup"));
        WebElement name = contactCard.findElement(By.xpath("//android.widget.TextView[@text='"+contactName+"']"));
        name.click();
    }

    public boolean isContactSelected(String contactName){
        WebElement contactCard = AppDriver.getDriver()
                .findElement(By.xpath("//android.view.ViewGroup/android.widget.TextView[@text='"+contactName+"']/parent::android.view.ViewGroup"));
        WebElement checkbox = contactCard.findElement(By.xpath("//android.widget.CheckBox"));
        String isChecked = checkbox.getAttribute("checked");
        System.out.println("Checkbox checked: "+ isChecked);
        System.out.println("Checkbox checked bool: "+ Boolean.parseBoolean(isChecked));
        return Boolean.parseBoolean(isChecked);
    }

    public ConfirmRelationshipsPage tapImport(){
        importButton.click();
        return new ConfirmRelationshipsPage();
    }

}
