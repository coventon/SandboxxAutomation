package com.sandboxx.pages.profileView.addressBook.newContact;

import com.sandboxx.dataManagement.testData.userModels.Address;
import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.framework.utils.CustomWait;
import com.sandboxx.framework.utils.PageActionsHelper;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MailingAddressPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.xpath("//android.widget.TextView[@text='Mailing Address']");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Mailing Address']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.ImageButton")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Next']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement nextButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Rank']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement rankLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/rank_spinner")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement rankDropdown;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='First Name']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement firstNameLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/first_name_et")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement firstNameInput;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Last Name']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lastNameLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/last_name_et")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lastNameInput;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='SELECT A BASE']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement selectBaseButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='ENTER BASE MANUALLY']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement enterBaseManuallyButton;

    // Rank list
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='MARINES RCT']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement marineRCT;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='MARINES PVT']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement marinePVT;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='MARINES PFC']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement marinePFC;

    // Manual Base Address form
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Address Line 1']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement contactAddress1Label;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/address1_et")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement contactAddress1Input;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Address Line 2']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement contactAddress2Label;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/address2_et")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement contactAddress2Input;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='City']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cityLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/city_et")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement cityInput;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='State']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement stateLabel;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Select State']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement stateInput;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Zip']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement zipLabel;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/zip_et")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement zipInput;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && backButton.isDisplayed()
                && rankLabel.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public MailingAddressPage(){waitForPage();}

    public void submitMailingAddress(String rank, String firstName, String lastName){
        rankDropdown.click();
        selectRank(rank);
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        selectBaseButton.click();
    }
    public void submitMailingAddressNoBase(String rank, String firstName, String lastName){
        rankDropdown.click();
        selectRank(rank);
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
    }

    public void selectRank(String rank){
        switch (rank){
            case "MARINES RCT":
                marineRCT.click();
                break;
            case "MARINES PVT":
                marinePVT.click();
                break;
            case "MARINE PFC":
                marinePFC.click();
                break;
        }
    }

    public void submitCustomMailingAddress(String firstName, String lastName, Address address){
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        enterBaseManuallyButton.click();
        enterAddress(address);
        nextButton.click();
    }

    public void enterAddress(Address address){
        PageActionsHelper.scrollDown();
        contactAddress1Input.sendKeys(address.getAddress());
        contactAddress2Input.sendKeys(address.getAddress2());
        cityInput.sendKeys(address.getCity());
        selectState(address.getState());
        zipInput.sendKeys(address.getZipCode());
    }

    public void selectState(String state){
        stateInput.click();
        findState(state).click();

        //WebElement stateOption = AppDriver.getDriver().findElement(By.xpath("//android.widget.TextView[@text='"+state+"']"));
        //stateOption.click();
    }

    public WebElement findState(String state){
        WebElement stateElement = null;
         while (stateElement == null){
             List<WebElement> allStates = AppDriver.getDriver().findElements(By.xpath("//android.widget.ListView/android.widget.TextView"));
             System.out.println("State elements found: "+allStates.size());
             // check all state for the desired state name
             for(WebElement e:allStates){
                 if(e.getText().equals(state)){
                     System.out.println("State found: "+state);
                     stateElement = e;
                     break;
                 }
             }
             if(stateElement == null){
                 PageActionsHelper.scrollDown();
             }
         }
        return stateElement;
         // If element is not found scroll down
    }

    public boolean stateVisible(String state){
        CustomWait customWait = new CustomWait(AppDriver.getDriver(),200);

        try{
            //new HomePage();
           return customWait.findElementWithCustomWait(By.xpath("//android.widget.TextView[@text='"+state+"']")).isDisplayed();
            //return invalidCredentialsAlert.isDisplayed(); //|| pageHeader.isDisplayed();
        }
        catch (NoSuchElementException | StaleElementReferenceException | TimeoutException e){
            return false;
        }
    }

    public void tapNext(){
        nextButton.click();
    }

}
