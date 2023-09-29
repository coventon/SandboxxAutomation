package com.sandboxx.pages.musterView.recruiter;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.framework.utils.PageActionsHelper;
import com.sandboxx.pages.BasePage;
import com.sandboxx.pages.musterView.recruiter.InviteCodePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class MusterDashboardTab extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.xpath("//android.widget.TextView[@text='MUSTER']");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='MUSTER']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Chat']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement chatTab;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Dashboard']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement dashboardTab;

    // # Dashboard
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='WELCOME']/preceding-sibling::android.widget.TextView")
    @iOSXCUITFindBy(xpath = "")
    public WebElement dateDisplay;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='WELCOME']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement greeting;
    @AndroidFindBy(accessibility = "Muster header image")
    @iOSXCUITFindBy(xpath = "")
    public WebElement musterHeaderImage;

    // Days counters
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='1-7 Days']/preceding-sibling::android.widget.TextView[last()]")
    @iOSXCUITFindBy(xpath = "")
    public WebElement daysRange1;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='1-7 Days']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement daysRange1Label;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='8-30 Days']/preceding-sibling::android.widget.TextView[last()]")
    @iOSXCUITFindBy(xpath = "")
    public WebElement daysRange2;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='8-30 Days']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement daysRange2Label;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='61+ Days']/preceding-sibling::android.widget.TextView[last()]")
    @iOSXCUITFindBy(xpath = "")
    public WebElement daysRange3;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='61+ Days']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement daysRange3Label;

    // Featured
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='FEATURED']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement featuredTitle;
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='Story position 1 of 4, News, unseen']//android.widget.TextView[@text='News']/preceding-sibling::android.widget.ImageView")
    @iOSXCUITFindBy(xpath = "")
    public WebElement story1Image;
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='Story position 1 of 4, News, unseen']//android.widget.TextView[@text='News']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement story1Title;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='The Sandboxx Magazine']/preceding-sibling::android.widget.ImageView")
    @iOSXCUITFindBy(xpath = "")
    public WebElement story2Image;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='The Sandboxx Magazine']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement story2Title;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Pocket Guide']/preceding-sibling::android.widget.ImageView")
    @iOSXCUITFindBy(xpath = "")
    public WebElement story3Image;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Pocket Guide']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement story3Title;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Start Here']/preceding-sibling::android.widget.ImageView")
    @iOSXCUITFindBy(xpath = "")
    public WebElement story4Image;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Start Here']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement story4Title;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='INVITE RECRUITS']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement inviteRecruitsBtn;


    public MusterDashboardTab(){waitForPage();}

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && dateDisplay.isDisplayed() && greeting.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public InviteCodePage tapInviteRecruits(){
        PageActionsHelper.scrollDown();
        inviteRecruitsBtn.click();
        return new InviteCodePage();
    }

    public String getCurrentDate(){
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
        return today.format(formatter);
    }
}
