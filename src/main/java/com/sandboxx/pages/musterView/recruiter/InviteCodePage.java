package com.sandboxx.pages.musterView.recruiter;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InviteCodePage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.xpath("//android.widget.TextView[@text='Share this invite code with your recruits to join private or group chats. You can share this code however you like—text it, email it, or say it aloud.']");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Share this invite code with your recruits to join private or group chats. You can share this code however you like—text it, email it, or say it aloud.']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(accessibility = "Close icon")
    @iOSXCUITFindBy(xpath = "")
    public WebElement closeIcon;
    @AndroidFindBy(xpath = "//android.widget.TextView[2]")
    @iOSXCUITFindBy(xpath = "")
    public WebElement inviteCode;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SHARE INVITE CODE']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement shareInviteCodeBtn;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='COPY INVITE CODE']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement copyInviteCodeBtn;
    public InviteCodePage(){waitForPage();}

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && inviteCode.isDisplayed() && shareInviteCodeBtn.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }

    public String getInviteCode(){
        return inviteCode.getText();
    }

    public void closeInvitePage(){
        closeIcon.click();
    }
    public void shareInviteCode(){
        shareInviteCodeBtn.click();
    }
    public void copyInviteCode(){
        copyInviteCodeBtn.click();
    }

}
