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

public class MailingAddressPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.xpath("//android.widget.TextView[@text='Mailing Address']");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Mailing Address']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.ImageButton")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Rank']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement rankLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/rank_spinner")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement rankDropdown;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='First Name']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement firstNameLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/first_name_et")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement firstNameInput;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Last Name']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lastNameLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/last_name_et")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lastNameInput;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='SELECT A BASE']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement selectBaseButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='ENTER BASE MANUALLY']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement enterBaseManuallyButton;

    // Rank list
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='MARINES RCT']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement marineRCT;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='MARINES PVT']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement marinePVT;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='MARINES PFC']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement marinePFC;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && backButton.isDisplayed()
                && rankLabel.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public MailingAddressPage(){waitForPage();}

    public void submitMailingAddress(String rank, String firstName, String lastName){
        rankDropdown.click();
        selectRank(rank);
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        selectBaseButton.click();
    }

    public void selectRank(String rank){
        switch (rank){
            case "MARINES RCT":
                marineRCT.click();
                break;
            case "MARINES PVT":
                marinePVT.click();
                break;
            case "MARINE PFC":
                marinePFC.click();
                break;
        }
    }

}
