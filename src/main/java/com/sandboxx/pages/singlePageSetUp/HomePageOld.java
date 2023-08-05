package com.sandboxx.pages.singlePageSetUp;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePageOld extends BasePage {

    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    @FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Echo Box\"]/android.view.ViewGroup")
    @iOSXCUITFindBy(accessibility = "Echo Box")
    public WebElement echoBox;

    @AndroidFindBy(accessibility = "messageInput")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name='messageInput']")
    public WebElement echoBoxTextInput;

    @AndroidFindBy(accessibility = "messageSaveBtn")
    @iOSXCUITFindBy(accessibility = "messageSaveBtn")
    public WebElement saveBtn;

    @AndroidFindBy(accessibility = "savedMessage")
    @iOSXCUITFindBy(accessibility = "savedMessage")
    public WebElement savedMsg;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView[1]")
    @iOSXCUITFindBy(accessibility = "Here's what you said before:")
    public WebElement echoInputLabel;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate Up']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='TheApp']")
    public WebElement theAppBackBtn;

    @Override
    public boolean isAt() {
        return echoBox.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
    }

    public void navigateToEchoBox(){
        echoBox.click();
    }
    public void enterValueIntoEcho(String value){
        echoBoxTextInput.sendKeys(value);
    }

    public void saveValue() throws InterruptedException {
        saveBtn.click();
        Thread.sleep(2000);
    }


    // public HomePage() {
       // PageFactory.initElements(new AppiumFieldDecorator(AppDriver.getDriver()), this);
    //}

    //public HomePage(){
    // PageFactory.initElements(AppDriver.getDriver(),this);
    // }

//////////////////////
    /*
    WebDriver driver = AppDriver.getDriver();
    //@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Echo Box\"]/android.view.ViewGroup")
    //@iOSXCUITFindBy(accessibility = "Echo Box")
    private final  By echoBoxLocator = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"Echo Box\"]/android.view.ViewGroup");
    public WebElement echoBox = driver.findElement(echoBoxLocator);

    //@AndroidBy(id = "messageInput")
    //@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name='messageInput']")
    private final By echBoxTextInputLocator = AppiumBy.xpath("//android.widget.EditText[@content-desc=\"messageInput\"]");
    public WebElement echoBoxTextInput = AppDriver.getDriver().findElement(echBoxTextInputLocator);

    //@AndroidBy(id = "messageSaveBtn")
    //@iOSXCUITFindBy(accessibility = "messageSaveBtn")
   // public WebElement saveBtn = AppDriver.getDriver().findElement(AppiumBy.accessibilityId("messageSaveBtn"));

    //@AndroidBy(id="savedMessage")
    //@iOSXCUITFindBy(accessibility = "savedMessage")
   // public WebElement savedMsg = driver.findElement(AppiumBy.accessibilityId("savedMessage"));

   // @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView[1]")
    //@iOSXCUITFindBy(accessibility = "Here's what you said before:")
    public WebElement echoInputLabel;

    //@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate Up']")
    //@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='TheApp']")
    public WebElement theAppBackBtn;



//    public HomePage(){
////        PageFactory.initElements(new AppiumFieldDecorator(AppDriver.getDriver()),this);
////    }

    public HomePage(){
       // PageFactory.initElements(AppDriver.getDriver(),this);
        wait.until(ExpectedConditions.visibilityOfElementLocated(echoBoxLocator));
    }

     */
}
