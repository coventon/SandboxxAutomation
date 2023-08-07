package com.sandboxx.framework.utils;

import com.sandboxx.dataManagement.testData.userModels.ActiveDuty;
import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.pages.LandingPage;
import com.sandboxx.pages.homeView.HomePage;
import com.sandboxx.pages.profileView.settings.InviteFriendsPage;
import com.sandboxx.pages.registration.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
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

        EmailSignUpPage emailSignUpPage = signUpPage.clickContinueWithEmail();
        emailSignUpPage.fillEmailForm(user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword());
        emailSignUpPage.showPasswordIcon.click();
        emailSignUpPage.continueButton.click();

        PhoneConfirmationPage phoneConfirmationPage = new PhoneConfirmationPage();
        phoneConfirmationPage.submitPhoneConfirmation(user.getPhoneNumber());

        VerificationCodePage verificationCodePage = new VerificationCodePage();
        UseSandboxxPage useSandboxxPage =  verificationCodePage.submitVerificationCode("123456");

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

    public static void recruitDeleteAccountByEmail(){

    }

    public static void loginWithEmail(){

    }

    public static void logout(){

    }
}
