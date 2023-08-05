package com.sandboxx.pages;

import com.sandboxx.framework.base.AppDriver;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class MainNavigation { //implements WrapsElement

    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(3));

    @AndroidFindBy(id = "com.sandboxx.android.dev:id/action_home")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement homeButton;
    @AndroidFindBy(id = "com.sandboxx.android.dev:id/action_profile")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement profileButton;
    @AndroidFindBy(accessibility = "Squads")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement squadButton;
    @AndroidFindBy(accessibility = "Shop")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement shopButton;
    @AndroidFindBy(accessibility = "News")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement newsButton;
    @AndroidFindBy(accessibility = "Muster")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement musterButton;
    @AndroidFindBy(accessibility = "Invite")
    @iOSXCUITFindBy(accessibility = "")
    public WebElement inviteButton;

    public MainNavigation(){
    PageFactory.initElements(new AppiumFieldDecorator(AppDriver.getDriver()),this);
    }
    ///==================

//    public WebElement navigationContainer;
//    private final By homeButtonLocator = AppiumBy.id("com.sandboxx.android.dev:id/action_home");
//    private final By profileButtonLocator = By.id("com.sandboxx.android.dev:id/action_profile");
//    private final By squadButtonLocator = AppiumBy.accessibilityId("Squads");
//    private final By shopButtonLocator = AppiumBy.accessibilityId("Shop");
//    private final By newsButtonLocator = AppiumBy.accessibilityId("News");
//    private final By musterButtonLocator = AppiumBy.accessibilityId("Muster");
//    private final By inviteButtonLocator = AppiumBy.accessibilityId("Invite");
//
//    //public WebElement wrappedElement;
//
//   /// @Override
//    //public WebElement getWrappedElement() {
//       // return wrappedElement;
//    //}
//
//    public WebElement homeButton = AppDriver.getDriver().findElement(homeButtonLocator);
//    public WebElement profileButton = AppDriver.getDriver().findElement(profileButtonLocator);
//    public WebElement squadButton = AppDriver.getDriver().findElement(squadButtonLocator);
//    public WebElement shopButton = AppDriver.getDriver().findElement(shopButtonLocator);
//    public WebElement newsButton = AppDriver.getDriver().findElement(newsButtonLocator);
//    public WebElement musterButton = AppDriver.getDriver().findElement(musterButtonLocator);
//    public WebElement inviteButton = AppDriver.getDriver().findElement(inviteButtonLocator);
//
//
//    public MainNavigation(WebElement wrappedElement) {
//        if (!wrappedElement.getAttribute("id").equals("com.sandboxx.android.dev:id/bottom_navigation")) {
//            throw new UnexpectedTagNameException("com.sandboxx.android.dev:id/bottom_navigation", wrappedElement.getAttribute("id"));
//        }
//        this.navigationContainer = wrappedElement;
//    }


}
