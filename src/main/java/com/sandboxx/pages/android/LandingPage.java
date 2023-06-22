package com.sandboxx.pages.android;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

public class LandingPage {

    @AndroidFindBy(xpath = "//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ImageView")
    @CacheLookup
    public WebElement sanboxxLogo ;
}
