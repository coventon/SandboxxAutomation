package com.sandboxx.pages.androidApps.messages;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.framework.utils.PageActionsHelper;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NewConversationPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.xpath("//android.widget.TextView[@text='New conversation']");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='New conversation']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;

    @AndroidFindBy(accessibility = "Navigate up")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='To']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement toLabel;
    @AndroidFindBy(id = "com.google.android.apps.messaging:id/recipient_text_view")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement toInput;

    @AndroidFindBy(id = "com.google.android.apps.messaging:id/compose_message_text")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement messageInput;
    @AndroidFindBy(id = "com.google.android.apps.messaging:id/send_message_button_icon")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sendSmsButton;
    @AndroidFindBy(xpath = "(//android.support.v7.widget.RecyclerView[@content-desc='Message list']//android.widget.TextView[contains(@text,'Have you tried Sandboxx?')])[last()]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lastMessage;


    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && toLabel.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e) -> isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }

    public NewConversationPage() {
        waitForPage();
    }

    public void selectContact(String name) {
        WebElement contactCard = AppDriver.getDriver().findElement(By.xpath("//android.widget.TextView[@text='" + name + "']"));
        contactCard.click();
    }

    public void sendMessage(){
        sendSmsButton.click();
    }

    public void tapLinkInMessage(){
        PageActionsHelper.tapElementAt(lastMessage,0.4,0.8);
    }
}
