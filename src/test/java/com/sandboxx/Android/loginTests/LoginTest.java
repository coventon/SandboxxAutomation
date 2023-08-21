package com.sandboxx.Android.loginTests;

import com.sandboxx.Android.AndroidBaseTest;
import com.sandboxx.dataManagement.testData.ExcelDataReader;
import com.sandboxx.dataManagement.testDataModels.TestDataModel;
import com.sandboxx.framework.utils.PageActionsHelper;
import com.sandboxx.framework.utils.TestUtil;
import com.sandboxx.pages.loginPages.CodeVerificationPage;
import com.sandboxx.pages.LandingPage;
import com.sandboxx.pages.loginPages.EmailLoginPage;
import com.sandboxx.pages.loginPages.LoginPage;
import com.sandboxx.pages.homeView.HomePage;
import com.sandboxx.pages.profileView.ProfilePage;
import com.sandboxx.pages.profileView.settings.SettingsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends AndroidBaseTest {


    @Test(priority = 1,groups = "Login")
    public void logOutSuccess() throws InterruptedException {
        System.out.printf("Begin test: %s",testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        TestUtil.loginWithEmail(testData.email);
        HomePage homePage = new HomePage();
        Thread.sleep(2000);

        homePage.navigateToProfile();
        Thread.sleep(2000);

        ProfilePage profilePage = new ProfilePage();
        SettingsPage settings = profilePage.tapSettings();
        PageActionsHelper.scrollDown();
        Assert.assertTrue(settings.logoutButton.isDisplayed());

        Thread.sleep(2000);
        LandingPage landing =  settings.tapLogout();
        Assert.assertTrue(landing.loginButton.isDisplayed());


        // Login with phone deprecated
        /*
        CodeVerificationPage codeVerificationPage =  loginPage.signInWithPhone(testData.phone);
        Thread.sleep(2000);
        codeVerificationPage.submitVerificationCode("123456");
        HomePage homePage = new HomePage();
        homePage.navigateToProfile();
        Thread.sleep(2000);

        ProfilePage profilePage = new ProfilePage();
        SettingsPage settings = profilePage.tapSettings();
        Thread.sleep(2000);

        //Assert.assertFalse(settings.logoutButton.isDisplayed());
        PageActionsHelper.scrollDown();
        Assert.assertTrue(settings.logoutButton.isDisplayed());

        Thread.sleep(2000);
        LandingPage landing =  settings.tapLogout();
        Assert.assertTrue(landing.loginButton.isDisplayed());
        */
    }

    @Test(priority = 2,groups = "Login")
    public void loginWithEmail() throws InterruptedException {
        System.out.printf("Begin test: %s",testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        LandingPage landingPage = new LandingPage();
        EmailLoginPage loginPage = landingPage.clickLoginButton();
        Thread.sleep(2000);

        loginPage.submitEmailLogin(testData.email);
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.welcomeGreeting.isDisplayed());
    }
}
