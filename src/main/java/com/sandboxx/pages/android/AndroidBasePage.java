package com.sandboxx.pages.android;

import com.sandboxx.dataManagement.constants.GoTo;
import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.framework.interfaces.IPage;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract  class AndroidBasePage implements IPage {

    WebDriverWait wait = new WebDriverWait(AppDriver.getDriver(), Duration.ofSeconds(20));

    @Override
    public abstract boolean isAt();

    @Override
    public abstract void waitForPage();

    @Override
    public void navigateTo(GoTo goTo){

    }
}
