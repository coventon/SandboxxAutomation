package com.sandboxx.pages.profileView.settings;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import com.sandboxx.pages.registration.WelcomePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InviteFriendsPage extends BasePage {

    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    public String pageHeaderText = "What branch of service are they?";
    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/title_tv");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Invite your friends & family']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Build a support network before you leave for basic training by inviting friends & family to use Sandboxx to write you letters.']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageSubHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Invite']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement inviteButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='No Thanks']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement noThanksButton;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && pageSubHeader.isDisplayed()
                && inviteButton.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }

    public WelcomePage tapNoThanks(){
        noThanksButton.click();
        return new WelcomePage();
    }

    public ImportContactsPage tapInvite(){
        inviteButton.click();
        return new ImportContactsPage();
    }
}
