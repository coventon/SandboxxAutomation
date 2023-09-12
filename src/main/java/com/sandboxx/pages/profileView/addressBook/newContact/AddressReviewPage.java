package com.sandboxx.pages.profileView.addressBook.newContact;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddressReviewPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.xpath("//android.widget.TextView[@text='Address Review']");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Address Review']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Please confirm the address below to ensure your letter is delivered.']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageSubHeader;
    @AndroidFindBy(xpath = "//android.widget.ImageButton")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Review']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement reviewLabel;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/name_tv")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement contactRankName;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/line1_tv")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement baseInfo;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/line2_tv")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement address1;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/line3_tv")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement address2;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='CONFIRM ADDRESS']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement confirmAddressButton;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && reviewLabel.isDisplayed()
                && pageSubHeader.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public AddressReviewPage(){waitForPage();}

    public String getRankName(){
       return contactRankName.getText();
    }
    public String getBaseInfo(){
        return baseInfo.getText();
    }
    public String getAddress(){
         return address1.getText();
    }
    public String getCityStateZip(){
        return address2.getText();
    }

    public String getFullAddress(){
        return  getRankName()+"\n"+getBaseInfo()+"\n"+getAddress()+"\n"+getCityStateZip();
    }
    public void confirmAddress(){
        confirmAddressButton.click();
    }

}
