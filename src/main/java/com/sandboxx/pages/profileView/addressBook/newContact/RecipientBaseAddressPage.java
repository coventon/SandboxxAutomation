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

public class RecipientBaseAddressPage extends BasePage {
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
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SHIP*']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement shipLabel;
    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Select SHIP']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement shipSelect;

    // # Ship Modal
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Select SHIP']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement shipModalTitle;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SHIP 1 (USS Pearl Harbor)']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement ship1;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SHIP 2 (USS Reuben B James)']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement ship2;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SHIP 3 (USS Hopper)']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement ship3;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SHIP 4 (USS Arleigh Burke)']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement ship4;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SHIP 5 (USS Theodore Roosevelt)']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement ship5;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='CONTINUE']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement continueButton;

    // # Selected Ship Section
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SHIP: ']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement SelectedShipLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/set_info_tv")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement shipValue;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Edit']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement editShipLink;

    // # Separations
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SEPARATIONS*']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement separationsLabel;
    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Select SEPARATIONS']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement separationsSelect;

    // # Separations Modal
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Select SEPARATIONS']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement separationsSelectModalHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SEPS MALE']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sepsMale;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SEPS FEMALE']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sepsFemale;

    // # Division Input * For Ship 5, 17 Separation changed to Division due to it is for Recruits training
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='DIV (eg. 000-999)*']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement divisionLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/detail_text")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement divisionInput;

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
    public RecipientBaseAddressPage(){waitForPage();}

    public void selectShip(String ship){
        shipSelect.click();
        getShip(ship);
    }

    public void getShip(String ship){
        switch (ship){
            case "ship1":
                ship1.click();
                break;
            case "ship2":
                ship2.click();
                break;
            case "ship3":
                ship3.click();
                break;
            case "ship4":
                ship4.click();
                break;
            case "ship5":
                ship5.click();
                break;
        }
    }

    public void selectSeps(String recipientSeps){
        separationsSelect.click();
        if(recipientSeps.equals("male")){
            sepsMale.click();
        }
        else if(recipientSeps.equals("female")){
            sepsFemale.click();
        }
    }

    public void enterDivision(String division){
        divisionInput.sendKeys(division);
    }
}
