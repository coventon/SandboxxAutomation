package com.sandboxx.pages.inviteView;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InvitePage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/tv_referrals_title");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Invite Friends & Family,\n" + "Earn Rewards']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Invite friends & family to send letters with Sandboxx. Once they create a new account & send their first letter, you’ll each get a free letter.']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageSubtitle;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='REFER 1']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement referOneTitle;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/iv_refer_one")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement referOneIcon;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='1 Letter ($4)']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement referOneReward;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='REFER 2']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement referTwoTitle;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/iv_refer_two")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement referTwoIcon;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='3 Letters ($12)']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement referTwoReward;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='REFER 3']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement referThreeTitle;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/iv_refer_three")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement referThreeIcon;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='5 Letters ($20)']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement referThreeReward;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Check your referral status']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement checkReferralButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='Read terms and conditions']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement termsButton;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/et_referral_link")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement referralLink;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/text_input_end_icon")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement referralCopyIcon;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/btn_share_via_sms")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement smsButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/btn_share_via_email")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement emailButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/btn_share_via_other")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement otherButton;
    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && pageSubtitle.isDisplayed()
                && referralLink.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public InvitePage(){waitForPage();}

    public void clickSmsButton(){
        smsButton.click();
    }
}
