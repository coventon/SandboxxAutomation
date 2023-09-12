package com.sandboxx.pages.homeView.letters;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ComposeLetterPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.xpath("//android.widget.TextView[@text='Compose']");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Compose']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/action_next")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement nextButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_recipient_initials")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement recipientInitials;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/tv_recipient_name")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement recipientName;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/btn_save_draft")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement saveDraftButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/message_count")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement messageCount;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/message_editor")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement messageMessageEditor;


    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && messageCount.isDisplayed()
                && messageMessageEditor.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public ComposeLetterPage(){waitForPage();}

    public void enterMessage(String message){
        messageMessageEditor.sendKeys(message);
    }
    public PhotoUploadPage tapNext(){
        nextButton.click();
        return new PhotoUploadPage();
    }

}
