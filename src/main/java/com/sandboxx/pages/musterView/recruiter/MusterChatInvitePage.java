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

public class MusterChatInvitePage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/title");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Invite Members']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/leftButton")
    @iOSXCUITFindBy(xpath = "")
    public WebElement backButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Invite']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement c;

    public MusterChatInvitePage() {
        waitForPage();
    }

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && backButton.isDisplayed() && backButton.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e) -> isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }

    // Format code to work with page
    public boolean isContactSelected(String contactName) {
        WebElement contactCard = AppDriver.getDriver()
                .findElement(By.xpath("//android.widget.TextView[@text='" + contactName + "']/parent::android.view.ViewGroup"));
        WebElement checkbox = contactCard.findElement(By.xpath("//android.widget.CheckBox"));
        String isChecked = checkbox.getAttribute("checked");
        System.out.println("Checkbox checked: " + isChecked);
        System.out.println("Checkbox checked bool: " + Boolean.parseBoolean(isChecked));
        return Boolean.parseBoolean(isChecked);
    }

    public boolean isContactDisplayed(String contactName) {
        WebElement contactCard = AppDriver.getDriver()
                .findElement(By.xpath("//android.widget.TextView[@text='" + contactName + "']/parent::android.view.ViewGroup"));
        //System.out.println("Checkbox checked bool: "+ Boolean.parseBoolean(isChecked));
        return contactCard.isDisplayed();
    }

    public void navigateBack() {
        backButton.click();
    }
}
