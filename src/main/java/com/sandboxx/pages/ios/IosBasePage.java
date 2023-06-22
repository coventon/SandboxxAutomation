package com.sandboxx.pages.ios;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IosBasePage {

    WebDriverWait wait;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ImageView")
    WebElement sandboxxLogo;

}
