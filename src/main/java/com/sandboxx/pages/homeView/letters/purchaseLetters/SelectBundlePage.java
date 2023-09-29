package com.sandboxx.pages.homeView.letters.purchaseLetters;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SelectBundlePage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.xpath("//android.widget.TextView[@text='Select Your Bundle']");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Select Your Bundle']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Send letters to service members stationed anywhere with ease. We'll securely print your message and ship it overnight to select locations.\"]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageSubHeader;
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Back']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='View a Letter']/following-sibling::android.widget.Button")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement viewLetterButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Overnight Shipping']/following-sibling::android.widget.Button")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement overnightShippingButton;

    // # 12 Letters Card
    //android.widget.TextView[@text='BEST VALUE']/parent::android.view.View
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='BEST VALUE']/parent::android.view.View")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement card12Letters;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='BEST VALUE']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement valueLabel;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sandboxx Plus']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sandboxxPlusHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='12 Letters\n" + "Add up to 4 photos per letter\n" + "Reply Postage']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sandboxxPlusSubTitle;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='$63.59']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement sandboxxPlusPrice;
    // # 1 Letter Card
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='1 Letter']/parent::android.view.View")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement card1Letter;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='1 Letter']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement letter1Header;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='$63.59']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement letter1Price;
    // # 5 Letters Card
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='5 Letters']/parent::android.view.View")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement card5Letter;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='5 Letters']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement letter5Header;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='$5.29']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement letter5Price;
    // # 20 Letters Card
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='20 Letters']/parent::android.view.View")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement card20Letter;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='20 Letters']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement letter20Header;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='$58.29']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement letter20Price;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Purchase']/following-sibling::android.widget.Button")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement purchaseButton;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Letters & Sandboxx Plus subscriptions \n" +
            "expire after 6 months from purchase.']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement lettersTermsMsg;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && pageSubHeader.isDisplayed()
                && viewLetterButton.isDisplayed() && lettersTermsMsg.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public SelectBundlePage(){waitForPage();}

    public CheckoutPage select5Letters(){
        card5Letter.click();
        purchaseButton.click();
        return new CheckoutPage();
    }
    public CheckoutPage select20Letters(){
        card20Letter.click();
        purchaseButton.click();
        return new CheckoutPage();
    }
}
