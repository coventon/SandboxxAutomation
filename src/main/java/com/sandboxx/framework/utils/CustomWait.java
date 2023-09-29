package com.sandboxx.framework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomWait {
    private WebDriver driver;
    private WebDriverWait customWait;
    public CustomWait(WebDriver driver,int timeoutInMillis){
        this.driver = driver;
        this.customWait = new WebDriverWait(driver, Duration.ofMillis(timeoutInMillis));
    }

    public WebElement findElementWithCustomWait(By locator){
        //WebElement element = null;
        //try{
            return customWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        //}
        //catch (TimeoutException e){
          //  System.out.println(">>> Element is not present on the page");
       // }
        //return element;
    }
}
