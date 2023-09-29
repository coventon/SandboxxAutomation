package com.sandboxx.pages.registration;

import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.BasePage;
import com.sandboxx.pages.registration.onboarding.BranchSelectionPage;
import com.sandboxx.pages.registration.onboarding.DescribeYouPage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UseSandboxxPage extends BasePage {

    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    public String pageHeaderText = "How do you want to use Sandboxx?";
    private final By pageHeaderLocator = By.id("com.sandboxx.android.dev:id/heading_text");
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/heading_text")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageHeader;

    private final By pageSubHeaderLocator = By.id("com.sandboxx.android.dev:id/subheader_text");
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/subheader_text")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement pageSubHeader;

    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[1]")
    @iOSXCUITFindBy(xpath = "")
    public WebElement sendLettersCard;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Send letters')]")
    @iOSXCUITFindBy(id = "")
    public WebElement sendLettersHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,\"I'm writing to a recruit at basic training, or a service member stationed far away.\")]")
    @iOSXCUITFindBy(id = "")
    public WebElement sendLettersSubHeader;
    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[2]")
    @iOSXCUITFindBy(xpath = "")
    public WebElement basicTrainingCard;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Prepare for basic training')]")
    @iOSXCUITFindBy(id = "")
    public WebElement basicTrainingHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,\"I'm joining the military & want to begin preparing for my career.\")]")
    @iOSXCUITFindBy(id = "")
    public WebElement basicTrainingSubHeader;
    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[3]")
    @iOSXCUITFindBy(xpath = "")
    public WebElement manageRecruitsCard;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Manage my recruits')]")
    @iOSXCUITFindBy(id = "")
    public WebElement manageRecruitsHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,\"I’m a recruiter in the military and want to connect with my recruits.\")]")
    @iOSXCUITFindBy(id = "")
    public WebElement manageRecruitsSubHeader;
    @AndroidFindBy(xpath = "//androidx.cardview.widget.CardView[4]")
    @iOSXCUITFindBy(xpath = "")
    public WebElement militaryCareerCard;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Build my military career')]")
    @iOSXCUITFindBy(id = "")
    public WebElement militaryCareerHeader;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,\"I'm currently serving and want to use Sandboxx for career resources & tools.\")]")
    @iOSXCUITFindBy(id = "")
    public WebElement militaryCareerSubHeader;

    @Override
    public boolean isAt() {
        return pageHeader.isDisplayed() && pageSubHeader.isDisplayed()
                && pageHeader.getText().equals(pageHeaderText);
    }

    @Override
    public void waitForPage() {
        wait.until((e)->isAt());
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeaderLocator));
    }
    public UseSandboxxPage(){waitForPage();}

    public BranchSelectionPage selectMilitaryCareer() {
        militaryCareerHeader.click();
        return new BranchSelectionPage();
    }

    public BranchSelectionPage selectBasicTraining() {
        basicTrainingCard.click();
        return new BranchSelectionPage();
    }
    public DescribeYouPage selectSendLetters() {
        sendLettersCard.click();
        return new DescribeYouPage();
    }
    public BranchSelectionPage selectManageMyRecruits(){
        manageRecruitsCard.click();
        return new BranchSelectionPage();
    }
}
