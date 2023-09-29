package com.sandboxx.pages.profileView.addressBook.bases;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.framework.utils.CustomWait;
import com.sandboxx.framework.utils.PageActionsHelper;
import com.sandboxx.pages.BasePage;
import com.sandboxx.pages.profileView.addressBook.newContact.AddressReviewPage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LacklandBase extends BasePage {
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
    public WebElement baseInput;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Enter your recipient's information and we'll automatically generate their mailing address for you.\"]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement recipientInfoHeader;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='TRAINING SQUADRON*']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement trainingSquadronHeader;

    // Training Squadron Select
    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Select TRAINING SQUADRON']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement trainingSquadronDropdown;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Select TRAINING_SQUADRON']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement squadronDropdownTitle;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='319']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement squadron_319;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='320']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement squadron_320;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='321']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement squadron_321;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='322']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement squadron_322;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='323']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement squadron_323;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='CONTINUE']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueButton;
    // Selected Squadron
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='TRAINING SQUADRON: ']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement squadronLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/set_info_tv")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement squadronValue;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Edit']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement squadronEditBtn;

    // Flight input
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='FLIGHT*']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement flightHeader;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/detail_text")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement flightInput;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && baseInput.isDisplayed()
                && recipientInfoHeader.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public LacklandBase(){waitForPage();}


    public void selectSquadron(String squadron){
        trainingSquadronDropdown.click();
        findAndTapSquadron(squadron);
    }
    public void findAndTapSquadron(String squadronName){
        CustomWait customWait = new CustomWait(AppDriver.getDriver(),1000);
        WebElement squadronEl = null;
        int maxScrolls = 2;
        for (int i = 0; i < maxScrolls; i++) {
            try{
                squadronEl = customWait.findElementWithCustomWait(By.xpath("//android.widget.TextView[@text='"+ squadronName +"']"));
                //stationEl = AppDriver.getDriver().findElement(By.xpath("//android.widget.TextView[@text='"+ stationName +"']"));
                squadronEl.click();
                break;
            }
            catch (NoSuchElementException | StaleElementReferenceException | TimeoutException ex){
                //PageActionsHelper.scrollDown();
                PageActionsHelper.scroll(PageActionsHelper.ScrollDirection.UP,0.6);
            }
        }
        if(squadronEl == null){
            System.out.printf(">>>> Station: %s could not be found\n", squadronName);
        }
    }
    public void enterFightNumber(String flightNumber){
        flightInput.sendKeys(flightNumber);
    }
    public AddressReviewPage tapNext(){
        nextButton.click();
        return new AddressReviewPage();
    }
    public AddressReviewPage submitBaseInfo(String squadron, String flightNumber){
        trainingSquadronDropdown.click();
        findAndTapSquadron(squadron);
        enterFightNumber(flightNumber);
        nextButton.click();
        return new AddressReviewPage();
    }
}
