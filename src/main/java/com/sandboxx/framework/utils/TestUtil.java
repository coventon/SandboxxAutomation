package com.sandboxx.framework.utils;

import com.sandboxx.dataManagement.testData.userModels.ActiveDuty;
import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.LandingPage;
import com.sandboxx.pages.MainNavigation;
import com.sandboxx.pages.homeView.HomePage;
import com.sandboxx.pages.loginPages.CodeVerificationPage;
import com.sandboxx.pages.loginPages.EmailLoginPage;
import com.sandboxx.pages.profileView.ProfilePage;
import com.sandboxx.pages.profileView.addressBook.AddressBookTab;
import com.sandboxx.pages.profileView.addressBook.currentContact.CurrentContactPage;
import com.sandboxx.pages.profileView.settings.InviteFriendsPage;
import com.sandboxx.pages.profileView.settings.SettingsPage;
import com.sandboxx.pages.profileView.settings.deleteAccount.DeleteAccountPage;
import com.sandboxx.pages.profileView.settings.deleteAccount.EmailVerificationPage;
import com.sandboxx.pages.profileView.settings.deleteAccount.VerifyAccountPage;
import com.sandboxx.pages.registration.*;
import com.sandboxx.pages.registration.onboarding.BranchSelectionPage;
import com.sandboxx.pages.registration.onboarding.BranchServicePage;
import com.sandboxx.pages.registration.onboarding.SelectRecruitingStationPage;
import com.sandboxx.pages.registration.onboarding.ShipDateSelectPage;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class TestUtil {

    public static String takeScreenshot() throws IOException, InterruptedException {
        Thread.sleep(300);
        System.out.println(">>> Starting Screen Capture.");
        File srcFile = ((TakesScreenshot) AppDriver.getDriver()).getScreenshotAs(OutputType.FILE);
        String fileNameID = UUID.randomUUID().toString(); // Unique name - UUID
        String fileName = String.valueOf( System.currentTimeMillis()); // Unique name - Data in milliseconds
        String fullPath = System.getenv("HOME")+"/TestScreenshots/"+fileNameID+".png";
        try{
            FileUtils.copyFile(srcFile,new File(fullPath));
            System.out.println("Screenshot Saved Successfully to: "+fullPath);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return fullPath;
    }

//    public static String takeScreenshotAsBase64_2() throws IOException, InterruptedException {
//        Thread.sleep(300);
//        TakesScreenshot takesScreenshot = (TakesScreenshot) AppDriver.getDriver();
//        String base64code = takesScreenshot.getScreenshotAs(OutputType.BASE64);
//        byte[] byteArr = Base64.getDecoder().decode(base64code);
//        String fileName = String.valueOf( System.currentTimeMillis()); // Unique name - Data in milliseconds
//        //String fullPath = System.getenv("HOME")+"/TestScreenshots/"+fileName+".png";
//        String fullPath = System.getProperty("user.dir")+"/testOutput/screenshots/"+fileName+".png";
//        File destFile = new File(fullPath);
//        FileOutputStream fos = new FileOutputStream(destFile);
//        fos.write(byteArr);
//        fos.close();
//        System.out.println("Screenshot Saved Successfully.");
//
//        return fullPath;
//    }

    public static String takeScreenshotAsBase64() throws IOException, InterruptedException {
        Thread.sleep(300);
        System.out.println(">>> Starting Screen Capture.");

        TakesScreenshot takesScreenshot = (TakesScreenshot) AppDriver.getDriver();
        byte [] screenshotBytes = takesScreenshot.getScreenshotAs(OutputType.BYTES);
        String base64Image = Base64.getEncoder().encodeToString(screenshotBytes);
        System.out.println("Screenshot Saved Successfully.");
        return base64Image;
    }

    public static void recruitSignupWithEmail(ActiveDuty user) throws InterruptedException {

        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();
        signUpPage.submitEmail(user.getEmail());

        VerificationCodePage verificationCodePage = new VerificationCodePage();
        verificationCodePage.submitVerificationCode("123456");

        FinishSignupMainPage finishSignupMainPage = new FinishSignupMainPage();
        finishSignupMainPage.finishSignUpButton.click();

        FinishSignUpForm finishSignUpForm = new FinishSignUpForm();
        finishSignUpForm.submitForm(user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword());

        SignUpSuccessPage signUpSuccessPage = new SignUpSuccessPage();
        signUpSuccessPage.continueButton.click();

        UseSandboxxPage useSandboxxPage =  new UseSandboxxPage();
        BranchSelectionPage branchSelectionPage =  useSandboxxPage.selectBasicTraining();

        BranchServicePage branchServicePage = branchSelectionPage.tapActiveDuty();
        SelectRecruitingStationPage recruitingStation =  branchServicePage.selectAirForce();

        ShipDateSelectPage shipDateSelectPage = recruitingStation.selectSquadron(SelectRecruitingStationPage.RecruitingStation.RS339);

        shipDateSelectPage.noButton.click();
        Thread.sleep(2000);

        InviteFriendsPage inviteFriendsPage = new InviteFriendsPage();
        WelcomePage welcomePage = inviteFriendsPage.tapNoThanks();
        Thread.sleep(2000);

        HomePage homePage = welcomePage.enterSandboxx();
    }

    public static UseSandboxxPage signupWithEmail(String email, String password, String firstName, String lastName) throws InterruptedException {

        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();
        signUpPage.submitEmail(email);

        VerificationCodePage verificationCodePage = new VerificationCodePage();
        verificationCodePage.submitVerificationCode("123456");

        FinishSignupMainPage finishSignupMainPage = new FinishSignupMainPage();
        finishSignupMainPage.finishSignUpButton.click();

        FinishSignUpForm finishSignUpForm = new FinishSignUpForm();
        finishSignUpForm.submitForm(firstName, lastName, email, password);

        SignUpSuccessPage signUpSuccessPage = new SignUpSuccessPage();
        signUpSuccessPage.continueButton.click();

        return new UseSandboxxPage();
    }

    public static void deleteAccountByEmail(String email, String password) throws InterruptedException {

        System.out.println(">>> Begin Delete Account By Email Flow.");
        MainNavigation mainNavigation = new MainNavigation();
        mainNavigation.profileButton.click();

        ProfilePage profilePage = new ProfilePage();
        SettingsPage settings = profilePage.tapSettings();

        VerifyAccountPage verifyAccountPage = settings.tapDeleteAccount();
        Thread.sleep(2000);

        verifyAccountPage.emailInput.sendKeys(email);
        verifyAccountPage.passwordInput.sendKeys(password);
        verifyAccountPage.continueButton.click();

        DeleteAccountPage deleteAccountPage = new DeleteAccountPage();

        deleteAccountPage.deleteAccountCheckBox.click();
        deleteAccountPage.deleteAccountBtn.click();
        //LandingPage landingPageLoggedOut = new LandingPage();
        //landingPageLoggedOut.isAt();
    }

    public static HomePage loginWithEmail(String email) throws InterruptedException {
        System.out.println(">>> Begin Login with Email flow");

        LandingPage landingPage = new LandingPage();

        EmailLoginPage loginPage = landingPage.clickLoginButton();
        loginPage.submitEmailLogin(email);

        CodeVerificationPage codeVerificationPage = new CodeVerificationPage();
        codeVerificationPage.submitVerificationCode("123456");

        return new HomePage();
    }
    public static boolean loginWithPassword(String email,String password) throws InterruptedException {
        System.out.println(">>> Begin Login with Email & Password flow");

        LandingPage landingPage = new LandingPage();

        EmailLoginPage loginPage = landingPage.clickLoginButton();
        loginPage.continueWithPassword(email,password);
//        if(loginPage.loginFailed()){
//            AppDriver.getDriver().navigate().back();
//           return false;
//        }

        //HomePage homePage = new HomePage();
        return true;
    }

    public static void logout(){
        System.out.println(">>> Begin Log Out Flow.");
        MainNavigation mainNavigation = new MainNavigation();
        mainNavigation.profileButton.click();

        ProfilePage profilePage = new ProfilePage();
        SettingsPage settings = profilePage.tapSettings();

        PageActionsHelper.scrollDown();
        settings.tapLogout();
    }

    public static void loginAndDeleteAccountByEmailPassword(String email, String password) throws InterruptedException {
        System.out.println(">>> Begin loginAndDeleteAccountByEmail");

        if(loginWithPassword(email,password)){
            deleteAccountByEmail(email,password);
        }

        // asfasdf@mail.com

//        EmailLoginPage emailLoginPage = loginPage.continueWithEmail();
//        emailLoginPage.loginWithEmail(email,password);
//        if(!emailLoginPage.loginFailed()){
//            deleteAccountByEmail(email,password);
//        }
    }

    public static void deleteAccountGoogle(){
        System.out.println(">>> Begin Delete Account By Google.");
        MainNavigation mainNavigation = new MainNavigation();
        mainNavigation.profileButton.click();

        ProfilePage profilePage = new ProfilePage();
        SettingsPage settings = profilePage.tapSettings();
        VerifyAccountPage verifyAccountPage = settings.tapDeleteAccount();
        verifyAccountPage.continueWithGoogle();
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage();
        deleteAccountPage.deleteAccountCheckBox.click();
        deleteAccountPage.deleteAccount();
    }

    public static void deleteContactFromAddressBook(String contactFullName){
        System.out.println(">>> Begin Delete Contact Flow.");
        MainNavigation mainNavigation = new MainNavigation();
        mainNavigation.profileButton.click();

        ProfilePage profilePage1 = new ProfilePage();
        AddressBookTab addressBookTab = profilePage1.tapAddressBook();
        CurrentContactPage contactPage = addressBookTab.selectContact(contactFullName);
        contactPage.deleteContact();
    }

    public static void deleteConnectedContactFromAddressBook(String contactFullName){
        System.out.println(">>> Begin Delete Contact Flow.");
        MainNavigation mainNavigation = new MainNavigation();
        mainNavigation.profileButton.click();

        ProfilePage profilePage1 = new ProfilePage();
        AddressBookTab addressBookTab = profilePage1.tapAddressBook();
        CurrentContactPage contactPage = addressBookTab.selectAddedContact(contactFullName);
        contactPage.deleteContact();
    }

    public static HashMap<String, String> getTestData(String testData){
        HashMap<String, String> formattedData = new HashMap<>();
        String[] keyValuePair = testData.split(",");
        for(String pair : keyValuePair){
            String[] keyValue = pair.split(":");
            if(keyValue.length == 2){
                String key = keyValue[0];
                String value = keyValue[1];
                formattedData.put(key,value);
            }
        }
        return formattedData;
    }

    public static void switchToWebContext() throws InterruptedException {
        // Under development !!!

        // Switch to Web Context
        String currentContext = ((AndroidDriver)AppDriver.getDriver()).getContext();
        System.out.println(">>> Current Context: "+currentContext);
        System.out.println(">>> Context Handles: "+((AndroidDriver)AppDriver.getDriver()).getContextHandles());

        //signUpPage.continueWithFacebook(); Perform Action (Click button that opens web context/page)

        String afterClickContext = ((AndroidDriver)AppDriver.getDriver()).getContext(); // Get current context
        System.out.println("After Click Context: "+afterClickContext);
        System.out.println("After Click Handles: "+((AndroidDriver)AppDriver.getDriver()).getContextHandles());

        Set<String> handles = ((AndroidDriver)AppDriver.getDriver()).getContextHandles(); // Get all handles/ context
        for(String contextName : handles){
            System.out.println("context name: "+contextName); // print all context names
        }

        ((AndroidDriver)AppDriver.getDriver()).context((String) handles.toArray()[1]);// Switch to web view
        Thread.sleep(2000);
        System.out.println("After Switching to Web Context: "+afterClickContext);

        String pageSource = AppDriver.getDriver().getPageSource();
        System.out.println("Page Source: "+pageSource);
        AppDriver.getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("email");
        Thread.sleep(1000);
        AppDriver.getDriver().findElement(By.xpath("//input[@name='pass']")).sendKeys("password");
        AppDriver.getDriver().findElement(By.xpath("//button[@name='login']")).click();

        //driver.findElement(By.xpath("//a[text()='Get Started']")).click();
        Thread.sleep(1000);
        System.out.println("Switching Back to NATIVE APP");
        ((AndroidDriver)AppDriver.getDriver()).context("NATIVE_APP"); //Switching back to Native view
    }

    public void switchToWebContext2(){
        Set<String>  handles = ((AndroidDriver)AppDriver.getDriver()).getContextHandles();
        System.out.println(handles);
        if (((AndroidDriver)AppDriver.getDriver()).getContext().equals("NATIVE_APP")){
            ((AndroidDriver)AppDriver.getDriver()).context("CHROMIUM");
            System.out.println("Switched to WebView");
        } else if (((AndroidDriver)AppDriver.getDriver()).getContext().equals("CHROMIUM")){
            System.out.println("Was Already On WebView");
        }
    }

    public void switchToNativeContext(){
        Set a = ((AndroidDriver)AppDriver.getDriver()).getContextHandles();
        System.out.println(a);
        if (((AndroidDriver)AppDriver.getDriver()).getContext().equals("NATIVE_APP")){
            System.out.println("Was Already On Native");
        } else if (((AndroidDriver)AppDriver.getDriver()).getContext().equals("CHROMIUM")){
            ((AndroidDriver)AppDriver.getDriver()).context("NATIVE_APP");
            System.out.println("Switched to Native");
        }
    }

    public void getElementBackgroundColor(){
//        MobileElement elem = (MobileElement) driver.findElement(By.id("loginButton"));
//
//        org.openqa.selenium.Point point = elem.getCenter();
//        int centerX = point.getX();
//        int centerY = point.getY();
//
//        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//
//        BufferedImage image = ImageIO.read(scrFile);
//// Getting pixel color by position x and y
//        int clr = image.getRGB(centerX,centerY);
//        int red   = (clr & 0x00ff0000) >> 16;
//        int green = (clr & 0x0000ff00) >> 8;
//        int blue  =  clr & 0x000000ff;
//        System.out.println("Red Color value = "+ red);
//        System.out.println("Green Color value = "+ green);
//        System.out.println("Blue Color value = "+ blue);
    }
}
