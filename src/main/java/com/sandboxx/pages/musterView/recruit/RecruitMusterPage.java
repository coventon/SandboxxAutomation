package com.sandboxx.pages.musterView.recruit;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecruitMusterPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    //public String pageHeaderText = "Confirm your phone number";
    private final By pageHeaderLocator = By.xpath("//android.widget.TextView[@text='MUSTER']");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='MUSTER']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Chat']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement chatTab;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Prepare']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement prepareTab;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/ivAlertIcon")
    @iOSXCUITFindBy(xpath = "")
    public WebElement chatIcon;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='No chats']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement noChatsMsg;

    public RecruitMusterPage(){waitForPage();}

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && chatTab.isDisplayed() && prepareTab.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }

    public MusterPreparePage tapPrepareTab(){
        prepareTab.click();
        return new MusterPreparePage();
    }
}
