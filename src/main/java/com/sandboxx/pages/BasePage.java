package com.sandboxx.pages;

import com.sandboxx.dataManagement.constants.GoTo;
import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.framework.interfaces.IPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage implements IPage {
    protected AppiumDriver driver;

    private static final By MainNavLocator = By.xpath("");

    public MainNavigation mainNavigation = new MainNavigation(AppDriver.getDriver().findElement(MainNavLocator));

    public BasePage(AppiumDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @Override
    public abstract boolean isAt();

    @Override
    public abstract void waitForPage();

    @Override
    public void navigateTo(GoTo goTo) {

        switch (goTo){
            case Sandboxx_Home :
                System.out.println("Clicking Home on Main Menu");
                mainNavigation.homeButton.click();
                break;

            case Sandboxx_Profile:
                System.out.println("Clicking Profile on Main Menu");
                mainNavigation.profileButton.click();
            break;
            case Sandboxx_Squad:
                System.out.println("Clicking Squad on Main Menu");
                mainNavigation.squadButton.click();
                break;
            case Sandboxx_News:
                System.out.println("Clicking New on Main Menu");
                mainNavigation.newsButton.click();
                break;
            case Sandboxx_Shop:
                System.out.println("Clicking Shop on Main Menu");
                mainNavigation.shopButton.click();
                break;
            case Sandboxx_Muster:
                System.out.println("Clicking Shop on Main Menu");
                mainNavigation.musterButton.click();
                break;

            default: throw new IllegalArgumentException(String.format("There is no implementation for %s",goTo));
        }
    }
}
