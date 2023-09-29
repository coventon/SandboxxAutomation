package com.sandboxx.pages.musterView.recruit;

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

public class MusterPreparePage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.xpath("//android.widget.TextView[@text='MUSTER']");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='MUSTER']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Chat']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement chatTab;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Prepare']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement prepareTab;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SHIP DATE']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement shipDateLabel;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SHIP DATE']/following-sibling::android.widget.TextView[1]")
    @iOSXCUITFindBy(xpath = "")
    public WebElement shipDate;
    @AndroidFindBy(accessibility = "Edit Icon")
    @iOSXCUITFindBy(xpath = "")
    public WebElement editShipDate;
    @AndroidFindBy(accessibility = "Muster header image")
    @iOSXCUITFindBy(xpath = "")
    public WebElement musterHeaderImage;

    // # Document checklist
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='FEATURED']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement featuredTitle;
    // # Featured
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='THE BASICS']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement basicsTitle;
    // #Prepare
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='PREPARE']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement prepareTitle;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CONNECT WITH RECRUITER']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement connectWithRecruiterBtn;

    // Connect With Recruiter Modal
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Connect with your Recruiter']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement connectModalHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Enter the code you received in your email below to join Muster!']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement connectModalSubHeader;
    @AndroidFindBy(xpath = "//android.widget.EditText")
    @iOSXCUITFindBy(xpath = "")
    public WebElement condeInput;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='JOIN MUSTER']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement joinMusterButton;
    @AndroidFindBy(accessibility = "Connected to recruiter icon")
    @iOSXCUITFindBy(xpath = "")
    public WebElement recruiterIcon;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='MY RECRUITER']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement myRecruiterLabel;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='MY RECRUITER']/following-sibling::android.widget.TextView")
    @iOSXCUITFindBy(xpath = "")
    public WebElement myRecruiterName;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CANCEL']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement cancelButton;

    // # Success Modal
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Success!']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement successHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='You are connected and can begin chatting with your recruiter.']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement successSubHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CONTINUE']")
    @iOSXCUITFindBy(xpath = "")
    public WebElement continueButton;

    public MusterPreparePage() {
        waitForPage();
    }

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && shipDateLabel.isDisplayed() && shipDate.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e) -> isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }

    public void tapConnectWithRecruiter() {
        PageActionsHelper.scrollDown();
        connectWithRecruiterBtn.click();
    }

    public void enterCode(String code) {
        condeInput.sendKeys(code);
    }

    public void joinMuster(String code) {
        enterCode(code);
        joinMusterButton.click();
    }

    public void submitMusterCode(String code) {
        tapConnectWithRecruiter();
        joinMuster(code);
    }

    public boolean successModalDisplayed() {
        return successHeader.isDisplayed() &&
                successSubHeader.isDisplayed();
    }
    public void tapContinueOnSuccess(){
        continueButton.click();
    }

    public boolean myRecruiterCardDisplayed(){
        return recruiterIcon.isDisplayed() && myRecruiterLabel.isDisplayed();
    }
}
