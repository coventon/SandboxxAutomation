package com.sandboxx.pages.homeView.letters;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import com.sandboxx.pages.homeView.letters.purchaseLetters.CheckoutPage;
import com.sandboxx.pages.homeView.letters.purchaseLetters.SelectBundlePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LetterReviewPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.xpath("//android.widget.TextView[@text='Letter Review']");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Letter Review']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Review Your Order']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement orderReviewHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Safe & secure checkout']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement orderReviewSubHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_credits_header")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement creditsHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/btn_more_credits")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addCreditsButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/ll_banner_group")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement alertBanner;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/iv_banner_icon")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement bannerIcon;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_banner_info")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement bannerInfo;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_from_label")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement fromLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/btn_edit_sender_address")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement editSenderAddressButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/from_name")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement fromName;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/from_line1")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement fromLine1;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/from_line3")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement fromLine3;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_send_letter")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sendLetterButton;


    // Tokens Modal
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Oops!']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement modalTitle;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"You don't have enough letters.\"]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement modalSubTitle;
    //android.widget.Button[@text='BUY LETTERS']
    @AndroidFindBy(xpath = "//android.widget.Button[@text='BUY LETTERS']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement buyLettersButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='NO THANKS']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement noThanksButton;
    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && orderReviewHeader.isDisplayed()
                && orderReviewSubHeader.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    //public LetterReviewPage(){waitForPage();}

    public boolean isLettersModalDisplayed(){
        return modalTitle.isDisplayed() && modalSubTitle.isDisplayed() && buyLettersButton.isDisplayed() && noThanksButton.isDisplayed();
    }
    public SelectBundlePage buyLetters(){
        buyLettersButton.click();
        return new SelectBundlePage();
    }

    public OrderCompletePage sendLetter(){
        sendLetterButton.click();
        return new OrderCompletePage();
    }

}
