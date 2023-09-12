package com.sandboxx.Android.referralTests;

import com.sandboxx.Android.AndroidBaseTest;
import com.sandboxx.dataManagement.ConfigProcessor;
import com.sandboxx.dataManagement.testData.ExcelDataReader;
import com.sandboxx.dataManagement.testDataModels.TestDataModel;
import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.framework.base.AppiumServer;
import com.sandboxx.framework.utils.PageActionsHelper;
import com.sandboxx.framework.utils.TestUtil;
import com.sandboxx.pages.LandingPage;
import com.sandboxx.pages.androidApps.messages.NewConversationPage;
import com.sandboxx.pages.homeView.HomePage;
import com.sandboxx.pages.inviteView.InvitePage;
import com.sandboxx.pages.profileView.ProfilePage;
import com.sandboxx.pages.profileView.addressBook.AddressBookTab;
import com.sandboxx.pages.registration.*;
import com.sandboxx.pages.registration.onboarding.BranchSelectionPage;
import com.sandboxx.pages.registration.onboarding.BranchServicePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

public class ReferralTests extends AndroidBaseTest {

    @Test(priority = 1, groups = "Referral")
    public void generateReferralLink_noKin() throws InterruptedException, MalformedURLException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        // ToDo: Delete account before test.

        LandingPage landingPage = new LandingPage();
        HomePage homePage = TestUtil.loginWithEmail(testData.email);
        Thread.sleep(6000);
        PageActionsHelper.scrollDown();

        Assert.assertTrue(homePage.referralTitle.isDisplayed());

        InvitePage invitePage = homePage.tapReferFriend();
        invitePage.referralCopyIcon.click();

        String clipBoardText = ((AndroidDriver)AppDriver.getDriver()).getClipboardText();
        System.out.println("Referral link: " + clipBoardText);

        // ===Close app===
        //((AndroidDriver) AppDriver.getDriver()).runAppInBackground(Duration.ofMillis(5000));
        ((AndroidDriver) AppDriver.getDriver()).terminateApp(ConfigProcessor.getAppPackage());

        // ===Delete app===
        //((AndroidDriver) AppDriver.getDriver()).removeApp(ConfigProcessor.getAppPackage());

        // ===Open Chrome===
        ((AndroidDriver) AppDriver.getDriver()).activateApp("com.android.chrome");

        WebElement searchInput = AppDriver.getDriver().findElement(By.id("com.android.chrome:id/url_bar"));
        searchInput.click();
        searchInput.clear();
        searchInput.sendKeys(clipBoardText);
        ((AndroidDriver) AppDriver.getDriver()).pressKey( new KeyEvent( AndroidKey.ENTER));

        WebElement installBtn = AppDriver.getDriver().findElement(By.xpath("//android.widget.Button[@text='Install']"));
        installBtn.click();

        // Sign In banner
        WebElement signInTitle = AppDriver.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Please sign in']"));
        WebElement signInSubTitle = AppDriver.getDriver().findElement(By.xpath("//android.widget.TextView[@text='In order to continue, you must sign in.']"));
        WebElement signInButton = AppDriver.getDriver().findElement(By.xpath("//android.widget.Button[@text='Sign in']"));
        Assert.assertTrue(signInTitle.isDisplayed());
        Assert.assertTrue(signInSubTitle.isDisplayed());
        Assert.assertTrue(signInButton.isDisplayed());

        Thread.sleep(5000);
    }

    @Test(priority = 2, groups = {"Referral","GooglePlay"})
    public void generateReferralLink_WithKin() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);
        String contact = "Test One";
        String referralLink = "Have you tried Sandboxx?\nSign up with my link and you'll get 1 letter after you send your first one.\nhttps://sandboxx.test-app.link/referral/QE26MPAZ";

        TestUtil.loginWithPassword(testData.email,testData.password);
        HomePage homePage = new HomePage();
        Thread.sleep(6000);
        PageActionsHelper.scrollDown();

        InvitePage invitePage = homePage.tapReferFriend();
        invitePage.clickSmsButton();
        NewConversationPage newConversationPage = new NewConversationPage();
        newConversationPage.selectContact(contact);
        newConversationPage.sendMessage();
        Assert.assertEquals(newConversationPage.lastMessage.getText(),referralLink);
        newConversationPage.tapLinkInMessage();

       /*
        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();
        signUpPage.continueWithGoogle();
        signUpPage.chooseAccount(testData.email);
        UseSandboxxPage useSandboxxPage = new UseSandboxxPage();
        DescribeYouPage describeYouPage = useSandboxxPage.selectSendLetters();
        RecipientAddressPage recipientAddressPage = describeYouPage.selectOther();
        MailingAddressPage mailingAddressPage = recipientAddressPage.selectYes();
        mailingAddressPage.submitMailingAddress(contactRank, contactFirstName, contactLastName);
        SelectBasePage selectBasePage = new SelectBasePage();
        selectBasePage.selectBase(contactBase);
        RecipientBaseAddressPage recipientBaseAddressPage = new RecipientBaseAddressPage();
        recipientBaseAddressPage.selectShip(contactShip);
        recipientBaseAddressPage.selectSeps(contactSeps);
        AddressReviewPage addressReviewPage = new AddressReviewPage();

        Assert.assertEquals(addressReviewPage.getRankName(),contactFirstName + " " + contactLastName);
        Assert.assertEquals(addressReviewPage.getBaseInfo(),"SHIP 5 SEPS MALE");
        Assert.assertEquals(addressReviewPage.getAddress(),"3610 ILLINOIS STREET");
        Assert.assertEquals(addressReviewPage.getCityStateZip(),"GREAT LAKES, IL 60088");

        addressReviewPage.confirmAddress();
        WelcomePage welcomePage = new WelcomePage();
        Assert.assertTrue(welcomePage.pageHeader.getText().contains(testData.firstName));
        Assert.assertEquals(welcomePage.pageSubHeader.getText(),welcomePage.familySubHeaderText);
        HomePage homePage = welcomePage.enterSandboxx();

        // Delete Account
        TestUtil.deleteAccountGoogle();

        */
    }


    //@Test(priority = 1, groups = "Registration") --- Test for Chrome browser with WebContext adhoc
    public void createReferral_NoKin2() throws InterruptedException, MalformedURLException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        // ToDo: Delete account before test.

        /*
        LandingPage landingPage = new LandingPage();
        HomePage homePage = TestUtil.loginWithEmail(testData.email);
        PageActionsHelper.scrollDown();

        Assert.assertTrue(homePage.referralTitle.isDisplayed());

        InvitePage invitePage = homePage.tapReferFriend();
        invitePage.referralCopyIcon.click();
        */

        // ===Close app===
        //((AndroidDriver) AppDriver.getDriver()).runAppInBackground(Duration.ofMillis(5000));
        ((AndroidDriver) AppDriver.getDriver()).terminateApp(ConfigProcessor.getAppPackage());
        // ===Delete app===
        //((AndroidDriver) AppDriver.getDriver()).removeApp(ConfigProcessor.getAppPackage());

        // Open Chrome
        //UiAutomator2Options options = new UiAutomator2Options();
        //options.setAppPackage("com.android.chrome");

        //options.setNoReset(true);
        //options.withBrowserName("chrome");
        //options.setAutoGrantPermissions(true);
        //options.setAppActivity("com.google.android.apps.chrome.Main");

        //WebDriver driver = AppDriver.getDriver();
        //AndroidDriver driver = new AndroidDriver(new URL(AppiumServer.getAppiumServerUrl()),options);
        ((AndroidDriver) AppDriver.getDriver()).activateApp("com.android.chrome");
        //driver.startActivity(new Activity("com.android.chrome", "com.google.android.apps.chrome.Main"));
        //driver.get("https://www.google.com/");
        //https://sandboxx.test-app.link/referral/WU89WTPN
        //driver.navigate().to("https://sandboxx.test-app.link/referral/WU89WTPN");
        WebElement searchInput = AppDriver.getDriver().findElement(By.id("com.android.chrome:id/url_bar"));
        searchInput.click();
        searchInput.clear();
        searchInput.sendKeys("https://sandboxx.test-app.link/referral/WU89WTPN");
        ((AndroidDriver) AppDriver.getDriver()).pressKey( new KeyEvent( AndroidKey.ENTER));

        String currentContext = ((AndroidDriver) AppDriver.getDriver()).getContext();
        System.out.println("Current Context: "+currentContext);
        System.out.println("Handles: "+((AndroidDriver) AppDriver.getDriver()).getContextHandles());

        Set<String> handles = ((AndroidDriver)AppDriver.getDriver()).getContextHandles(); // Get all handles/ contexts
        ((AndroidDriver)AppDriver.getDriver()).context((String) handles.toArray()[1]);// Switch to web view
        Thread.sleep(2000);
        String afterClickContext = ((AndroidDriver)AppDriver.getDriver()).getContext();
        System.out.println("After Switching to Web Context: "+afterClickContext);

        String pageSource = AppDriver.getDriver().getPageSource();
        //System.out.println("Page Source: "+pageSource);
        AppDriver.getDriver().findElement(By.xpath("//button[@aria-label='Install']")).click();
        Thread.sleep(5000);

        //driver.quit();

        //driver = new AndroidDriver(new URL(serverUrl),options);


    }
}
