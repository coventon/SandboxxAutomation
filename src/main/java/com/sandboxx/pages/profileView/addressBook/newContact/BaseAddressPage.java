package com.sandboxx.pages.profileView.addressBook.newContact;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseAddressPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.xpath("//android.widget.TextView[@text='Let’s find your recipient’s base address']");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Let’s find your recipient’s base address']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.ImageButton")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Cancel']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cancelButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Next']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement nextButton;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/base_et")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement baseDropdown;
    //android.widget.TextView[@text="Enter your recipient's information and we'll automatically generate their mailing address for you."]
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Enter your recipient's information and we'll automatically generate their mailing address for you.\"]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addressInfoLabel;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='BATTALION*']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement battalionLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/detail_spinner")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement battalionDropdown;

    // Select Battalion Modal
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Select BATTALION']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement selectBattalionHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='1ST RTBN']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement firstRtbn;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='2ND RTBN']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement secondRtbn;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='3RD RTBN']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement thirdRtbn;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SPT BN']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sptbn;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='CONTINUE']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueBtn;

    // Select Company Modal
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='COMPANY*']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement companyLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/detail_spinner")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement companyDropdown;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Select COMPANY']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement selectCompanyHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ALPHA']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement alpha;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='BRAVO']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement bravo;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CHARLIE']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement charlie;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='DELTA']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement delta;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='PLATOON - Training*']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement platoonTrainingLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/detail_text")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement platoonTrainingInput;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && backButton.isDisplayed()
                && baseDropdown.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public BaseAddressPage(){waitForPage();}

    public void submitBattalion(String battalion){
        battalionDropdown.click();
        selectBattalion(battalion);
    }

    public void selectBattalion(String battalion){
        switch (battalion){
            case "first":
                firstRtbn.click();
                break;
            case "second":
                secondRtbn.click();
                break;
            case "third":
                thirdRtbn.click();
                break;
            case "SPT":
                sptbn.click();
                break;
        }
    }

    public void submitCompany(String company){
        companyDropdown.click();
        selectCompany(company);
    }

    public void selectCompany(String company){
        switch (company){
            case "alpha":
                alpha.click();
                break;
            case "bravo":
                bravo.click();
                break;
            case "charlie":
                charlie.click();
                break;
            case "delta":
                delta.click();
                break;
        }
    }

    public void submitPlatoonTraining(String platoon){
        platoonTrainingInput.sendKeys(platoon);
        nextButton.click();
    }
}
