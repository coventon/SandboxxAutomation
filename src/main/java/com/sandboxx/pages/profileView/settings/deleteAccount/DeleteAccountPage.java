package com.sandboxx.pages.profileView.settings.deleteAccount;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DeleteAccountPage extends BasePage {

    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/text_delete_your_account");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete Your Account?']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='This action is irreversible and your account will not be able to be recovered once deleted.']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageSubHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='In addition to your personal information, the below items will be permanently deleted:']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement dataInfoMsg;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/text_account_stats")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement accountStats;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/checkbox_delete_account")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement deleteAccountCheckBox;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_delete_account")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement deleteAccountBtn;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/button_cancel")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cancelBtn;
    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && pageSubHeader.isDisplayed() && dataInfoMsg.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }

    public DeleteAccountPage(){
        waitForPage();
    }

    public String getSentLettersCount(){
        String statsText = accountStats.getText();
        return statsText.substring(0,statsText.indexOf(" Sent Letters"));
    }
    public String getAvailableLettersCount(){
        String statsText = accountStats.getText();
        return statsText.substring((statsText.indexOf("Sent Letters")+13),statsText.indexOf(" Available Letters"));
    }
    public String getContactsCount(){
        String statsText = accountStats.getText();
        return statsText.substring(statsText.indexOf("Available Letters")+18,statsText.indexOf(" Contacts"));
    }

    public boolean isDeleteAcctChecked(){

        String isChecked = deleteAccountCheckBox.getAttribute("checked");
        System.out.println("Delete Account checked: "+ isChecked);
        return Boolean.parseBoolean(isChecked);
    }
}
