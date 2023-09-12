package com.sandboxx.pages.homeView.letters;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PhotoUploadPage extends BasePage {
    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    private final By pageHeaderLocator = By.xpath("//android.widget.TextView[@text='Photo Upload']");
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Photo Upload']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement backButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/action_next")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement nextButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/empty_ll")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement photoPreview;
    @AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement image1;
    @AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement image2;
    @AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[3]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement image3;
    @AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[4]")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement image4;

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/unlock_multi_photo_button")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement unlockMultiPhotoButton;

    // # Add Photo Modal
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/iv_dialog_image")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement modalImage;
    @AndroidFindBy(id = "//android.widget.TextView[@text='No photo?']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement modalTitle;
    @AndroidFindBy(id = "//android.widget.TextView[@text='Photos help a recruit feel more connected to home and stay motivated']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement modalSubtitle;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='ADD PHOTO']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement addPhotoButton;
    @AndroidFindBy(xpath = "//android.widget.Button[@text='SKIP']")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement skipButton;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && photoPreview.isDisplayed()
                && image1.isDisplayed();
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public PhotoUploadPage(){waitForPage();}

    public LetterAddonsPage goToNextPage(){
        nextButton.click();
        skipButton.click();
        return new LetterAddonsPage();
    }
}
