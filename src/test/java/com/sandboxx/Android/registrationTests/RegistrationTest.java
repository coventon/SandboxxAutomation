package com.sandboxx.Android.registrationTests;

import com.beust.ah.A;
import com.sandboxx.Android.AndroidBaseTest;
import com.sandboxx.dataManagement.testData.ExcelDataReader;
import com.sandboxx.dataManagement.testData.userModels.ActiveDuty;
import com.sandboxx.dataManagement.testDataModels.TestDataModel;
import com.sandboxx.framework.utils.ApiHelper;
import com.sandboxx.framework.utils.TestUtil;
import com.sandboxx.pages.loginPages.CodeVerificationPage;
import com.sandboxx.pages.LandingPage;
import com.sandboxx.pages.loginPages.LoginPage;
import com.sandboxx.pages.homeView.HomePage;
import com.sandboxx.pages.profileView.AddressBookTab;
import com.sandboxx.pages.profileView.ProfilePage;
import com.sandboxx.pages.profileView.settings.ConfirmRelationshipsPage;
import com.sandboxx.pages.profileView.settings.ImportContactsPage;
import com.sandboxx.pages.profileView.settings.InviteFriendsPage;
import com.sandboxx.pages.profileView.settings.SettingsPage;
import com.sandboxx.pages.profileView.settings.deleteAccount.DeleteAccountPage;
import com.sandboxx.pages.profileView.settings.deleteAccount.EmailVerificationPage;
import com.sandboxx.pages.profileView.settings.deleteAccount.VerifyAccountPage;
import com.sandboxx.pages.registration.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class RegistrationTest extends AndroidBaseTest {

    //Account Creation (Email) - Auto Connected Kin

    @Test(priority = 1,groups = "Registration")
    public void registerWithEmail_AutoConnectedKin() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n",testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        // ToDo: Delete account before test.
        ApiHelper.deleteAccount(testData.phone,testData.email);


        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();
        Thread.sleep(4000);
        Assert.assertEquals("Sign Up",signUpPage.pageHeader.getText());
        Assert.assertEquals(signUpPage.welcomeHader.getText(),"Welcome to Sandboxx!");
        Assert.assertEquals(signUpPage.phoneNumberLabel.getText(),"Enter your phone number below to signup");
        Assert.assertTrue(signUpPage.phoneNumberInput.isDisplayed());

        EmailSignUpPage emailSignUpPage = signUpPage.clickContinueWithEmail();
        Assert.assertFalse(emailSignUpPage.continueButton.isEnabled());
        emailSignUpPage.fillEmailForm(testData.firstName,testData.lastName,testData.email,testData.password);
        emailSignUpPage.showPasswordIcon.click();
        Assert.assertTrue(emailSignUpPage.continueButton.isEnabled());

        Thread.sleep(4000);

        emailSignUpPage.continueButton.click();
        PhoneConfirmationPage phoneConfirmationPage = new PhoneConfirmationPage();
        Assert.assertFalse(phoneConfirmationPage.sendConfirmationButton.isEnabled());
        phoneConfirmationPage.submitPhoneConfirmation(testData.phone);

        Thread.sleep(2000);
        VerificationCodePage verificationCodePage = new VerificationCodePage();
        Assert.assertFalse(verificationCodePage.verifyButton.isEnabled());
        UseSandboxxPage useSandboxxPage =  verificationCodePage.submitVerificationCode("123456");
        Thread.sleep(4000);

        BranchSelectionPage branchSelectionPage =  useSandboxxPage.selectMilitaryCareer();
        BranchServicePage branchServicePage = branchSelectionPage.tapActiveDuty();
        WelcomePage welcomePage =  branchServicePage.selectMarineCorp();
        Thread.sleep(4000);

        HomePage homePage = welcomePage.enterSandboxx();
        homePage.navigateToProfile();
        Thread.sleep(4000);
        ProfilePage profilePage = new ProfilePage();
        Assert.assertEquals(profilePage.fullNameDisplay.getText(),testData.firstName+" "+testData.lastName);
        Assert.assertEquals(profilePage.userType.getText(),"Active Duty");
        AddressBookTab addressBookTab = profilePage.tapAddressBook();
        Assert.assertTrue(addressBookTab.importContactsIcon.isDisplayed());
        Assert.assertTrue(addressBookTab.importContactsLabel.isDisplayed());

        // ToDo: Delete account before test.
        ApiHelper.deleteAccount(testData.phone,testData.email);

    }

    @Test(priority = 2,groups = "Registration")
    public void registerWithEmail_ExistingEmail() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n",testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();
        Thread.sleep(4000);
        Assert.assertEquals("Sign Up",signUpPage.pageHeader.getText());
        Assert.assertEquals(signUpPage.welcomeHader.getText(),"Welcome to Sandboxx!");
        Assert.assertEquals(signUpPage.phoneNumberLabel.getText(),"Enter your phone number below to signup");
        Assert.assertTrue(signUpPage.phoneNumberInput.isDisplayed());

        EmailSignUpPage emailSignUpPage = signUpPage.clickContinueWithEmail();
        Assert.assertFalse(emailSignUpPage.continueButton.isEnabled());
        emailSignUpPage.fillEmailForm(testData.firstName,testData.lastName,testData.email,testData.password);
        emailSignUpPage.showPasswordIcon.click();
        Assert.assertTrue(emailSignUpPage.continueButton.isEnabled());
        Thread.sleep(4000);

        emailSignUpPage.continueButton.click();
        RegistrationErrorModule errorModule = new RegistrationErrorModule();
        Assert.assertEquals(errorModule.errorTitle.getText(), "Email already in use");
        Assert.assertEquals(errorModule.errorSubTitle.getText(),"This email has previously been used to create a Sandboxx account. Would you like to log in using this email?");

    }

    @Test(priority = 3,groups = "Registration")
    public void registerWithEmail_PhoneInUse() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n",testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

// ToDo: Delete account before test.
        ApiHelper.deleteAccount(testData.phone,testData.email);

        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();
        Thread.sleep(4000);
        Assert.assertEquals("Sign Up",signUpPage.pageHeader.getText());
        Assert.assertEquals(signUpPage.welcomeHader.getText(),"Welcome to Sandboxx!");
        Assert.assertEquals(signUpPage.phoneNumberLabel.getText(),"Enter your phone number below to signup");
        Assert.assertTrue(signUpPage.phoneNumberInput.isDisplayed());

        EmailSignUpPage emailSignUpPage = signUpPage.clickContinueWithEmail();
        Assert.assertFalse(emailSignUpPage.continueButton.isEnabled());
        emailSignUpPage.fillEmailForm(testData.firstName,testData.lastName,testData.email,testData.password);
        emailSignUpPage.showPasswordIcon.click();
        Assert.assertTrue(emailSignUpPage.continueButton.isEnabled());
        Thread.sleep(4000);
        emailSignUpPage.continueButton.click();

        PhoneConfirmationPage phoneConfirmationPage = new PhoneConfirmationPage();
        Assert.assertFalse(phoneConfirmationPage.sendConfirmationButton.isEnabled());
        phoneConfirmationPage.submitPhoneConfirmation(testData.phone);

        RegistrationErrorModule errorModule = new RegistrationErrorModule();
        Assert.assertEquals(errorModule.errorTitle.getText(), "Phone Number In Use");
        Assert.assertEquals(errorModule.errorSubTitle.getText(),"This phone number has already been associated with a Sandboxx account. Would you like to enter a new phone number? You can also skip this step.");
        errorModule.enterNewNumberButton.click();

        phoneConfirmationPage.submitPhoneConfirmation(testData.phone);
        Assert.assertEquals(errorModule.errorTitle.getText(), "Phone Number In Use");
        Assert.assertEquals(errorModule.errorSubTitle.getText(),"This phone number has already been associated with a Sandboxx account. Would you like to enter a new phone number? You can also skip this step.");
        errorModule.enterNewNumberButton.click();

        // ToDo: Delete account before test
        ApiHelper.deleteAccount(testData.phone,testData.email);

    }

    @Test(priority = 4,groups = "Registration")
    public void registerAccount_ByEmail() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n",testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        // ToDo: Delete account before test
        ApiHelper.deleteAccount(testData.phone,testData.email);

        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();
        Thread.sleep(4000);

        Assert.assertEquals("Sign Up",signUpPage.pageHeader.getText());

        EmailSignUpPage emailSignUpPage = signUpPage.clickContinueWithEmail();
        emailSignUpPage.fillEmailForm(testData.firstName,testData.lastName,testData.email,testData.password);
        emailSignUpPage.showPasswordIcon.click();
        Assert.assertTrue(emailSignUpPage.continueButton.isEnabled());
        Thread.sleep(4000);
        emailSignUpPage.continueButton.click();

        PhoneConfirmationPage phoneConfirmationPage = new PhoneConfirmationPage();
        Assert.assertFalse(phoneConfirmationPage.sendConfirmationButton.isEnabled());
        phoneConfirmationPage.submitPhoneConfirmation(testData.phone);

        Thread.sleep(2000);
        VerificationCodePage verificationCodePage = new VerificationCodePage();
        Assert.assertFalse(verificationCodePage.verifyButton.isEnabled());
        UseSandboxxPage useSandboxxPage =  verificationCodePage.submitVerificationCode("123456");
        Thread.sleep(4000);

        BranchSelectionPage branchSelectionPage =  useSandboxxPage.selectMilitaryCareer();
        BranchServicePage branchServicePage = branchSelectionPage.tapActiveDuty();
        WelcomePage welcomePage =  branchServicePage.selectMarineCorp();
        Thread.sleep(4000);

        HomePage homePage = welcomePage.enterSandboxx();
        homePage.navigateToProfile();
        Thread.sleep(4000);
        ProfilePage profilePage = new ProfilePage();
        Assert.assertEquals(profilePage.fullNameDisplay.getText(),testData.firstName+" "+testData.lastName);
        Assert.assertEquals(profilePage.userType.getText(),"Active Duty");
        AddressBookTab addressBookTab = profilePage.tapAddressBook();
        Assert.assertTrue(addressBookTab.importContactsIcon.isDisplayed());
        Assert.assertTrue(addressBookTab.importContactsLabel.isDisplayed());
        // ToDo: Delete account before test
        ApiHelper.deleteAccount(testData.phone,testData.email);

    }

    @Test(priority = 5,groups = "Registration")
    public void deleteAccount_ByEmail() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n",testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        LandingPage landingPage = new LandingPage();
        LoginPage loginPage = landingPage.clickLoginButton();
        Thread.sleep(4000);

        /// ========== To DO ==============
        Assert.assertFalse(loginPage.continueWithPhoneButton.isEnabled());
        CodeVerificationPage codeVerificationPage =  loginPage.signInWithPhone(testData.phone);
        Thread.sleep(4000);
        codeVerificationPage.submitVerificationCode("123456");

        HomePage homePage = new HomePage();
        Assert.assertEquals("Welcome to Sandboxx, "+testData.firstName,homePage.welcomeGreeting.getText());
    }

    @Test(priority = 6,groups = "Registration")
    public void registerWithEmail_AddContactFromSettings() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n",testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        // ToDo: Delete account before test.
        //ApiHelper.deleteAccount(testData.phone,testData.email);


        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();
        Thread.sleep(2000);
        Assert.assertEquals("Sign Up",signUpPage.pageHeader.getText());
        Assert.assertEquals(signUpPage.welcomeHader.getText(),"Welcome to Sandboxx!");
        Assert.assertEquals(signUpPage.phoneNumberLabel.getText(),"Enter your phone number below to signup");
        Assert.assertTrue(signUpPage.phoneNumberInput.isDisplayed());

        EmailSignUpPage emailSignUpPage = signUpPage.clickContinueWithEmail();
        Assert.assertFalse(emailSignUpPage.continueButton.isEnabled());
        emailSignUpPage.fillEmailForm(testData.firstName,testData.lastName,testData.email,testData.password);
        emailSignUpPage.showPasswordIcon.click();
        Assert.assertTrue(emailSignUpPage.continueButton.isEnabled());

        Thread.sleep(2000);

        emailSignUpPage.continueButton.click();
        PhoneConfirmationPage phoneConfirmationPage = new PhoneConfirmationPage();
        Assert.assertFalse(phoneConfirmationPage.sendConfirmationButton.isEnabled());
        phoneConfirmationPage.submitPhoneConfirmation(testData.phone);

        Thread.sleep(2000);
        VerificationCodePage verificationCodePage = new VerificationCodePage();
        Assert.assertFalse(verificationCodePage.verifyButton.isEnabled());
        UseSandboxxPage useSandboxxPage =  verificationCodePage.submitVerificationCode("123456");
        Thread.sleep(4000);

        BranchSelectionPage branchSelectionPage =  useSandboxxPage.selectBasicTraining();
        BranchServicePage branchServicePage = branchSelectionPage.tapActiveDuty();
        Thread.sleep(2000);
        SelectRecruitingStationPage recruitingStation =  branchServicePage.selectAirForce();
        Thread.sleep(2000);

        recruitingStation.backButton.click();
        Assert.assertTrue(branchServicePage.isAt());

        SelectRecruitingStationPage recruitingStation2 =  branchServicePage.selectAirForce();
        ShipDateSelectPage shipDateSelectPage = recruitingStation2.selectSquadron(SelectRecruitingStationPage.RecruitingStation.RS339);
        Thread.sleep(2000);

        shipDateSelectPage.backButton.click();
        Assert.assertTrue(recruitingStation2.isAt());
        Thread.sleep(2000);

        SelectRecruitingStationPage recruitingStation3 = new SelectRecruitingStationPage();
        ShipDateSelectPage shipDatePage = recruitingStation3.selectSquadron(SelectRecruitingStationPage.RecruitingStation.RS318);
        shipDatePage.noButton.click();

        InviteFriendsPage inviteFriendsPage = new InviteFriendsPage();
        WelcomePage welcomePage = inviteFriendsPage.tapNoThanks();
        Assert.assertEquals(welcomePage.pageSubHeader.getText(), welcomePage.basicTrainingSubHeader);

        HomePage homePage = welcomePage.enterSandboxx();
        homePage.navigateToProfile();

        ProfilePage profilePage = new ProfilePage();
        SettingsPage settings = profilePage.tapSettings();
        settings.addFamilyAndFriendsBtn.click();

        InviteFriendsPage inviteFriendsPage1 = new InviteFriendsPage();
        ImportContactsPage importContactsPage = inviteFriendsPage1.tapInvite();

        importContactsPage.selectContactByName("Contact One");
        Assert.assertTrue(importContactsPage.isContactSelected("Contact One"));
        importContactsPage.selectContactByName("Contact Two");
        Assert.assertTrue(importContactsPage.isContactSelected("Contact Two"));
        importContactsPage.selectContactByName("Contact Three");
        Assert.assertTrue(importContactsPage.isContactSelected("Contact Three"));

        ConfirmRelationshipsPage relationshipsPage = importContactsPage.tapImport();

        Assert.assertEquals(relationshipsPage.contactList.size(),3);
        Assert.assertTrue(relationshipsPage.isContactDisplayed("Contact One"));
        Assert.assertTrue(relationshipsPage.isContactDisplayed("Contact Two"));
        Assert.assertTrue(relationshipsPage.isContactDisplayed("Contact Three"));

        relationshipsPage.importButton.click();
        SettingsPage settingsPage = new SettingsPage();
        settingsPage.backButton.click();
        ProfilePage profilePage1 = new ProfilePage();

        // ToDo: Delete account after test.
    }

    @Test(priority = 7,groups = "Registration")
    public void deleteAccountByEmail() throws InterruptedException {

        //ApiHelper.deleteAccount("4057704109","jdoe1234@mail.com");
        //ApiHelper.deleteAccount2();

        System.out.printf("Begin Test:  %s\n",testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        // ToDo: Delete account before test.
        //ApiHelper.deleteAccount(testData.phone,testData.email);

        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();

        EmailSignUpPage emailSignUpPage = signUpPage.clickContinueWithEmail();
        emailSignUpPage.fillEmailForm(testData.firstName,testData.lastName,testData.email,testData.password);
        emailSignUpPage.showPasswordIcon.click();
        emailSignUpPage.continueButton.click();

        PhoneConfirmationPage phoneConfirmationPage = new PhoneConfirmationPage();
        phoneConfirmationPage.submitPhoneConfirmation(testData.phone);

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

        // === Delete Account ===
        HomePage homePage = welcomePage.enterSandboxx();
        Thread.sleep(2000);

        homePage.navigateToProfile();

        ProfilePage profilePage = new ProfilePage();
        SettingsPage settings = profilePage.tapSettings();

        VerifyAccountPage verifyAccountPage = settings.tapDeleteAccount();
        EmailVerificationPage emailVerification = verifyAccountPage.continueWithEmail();
        Thread.sleep(2000);
        Assert.assertFalse(emailVerification.continueButton.isEnabled());
        emailVerification.emailInput.sendKeys(testData.email);

        // !!! Bug V
        //Assert.assertFalse(emailVerification.continueButton.isEnabled());
        emailVerification.passwordInput.sendKeys(testData.password);
        Assert.assertTrue(emailVerification.continueButton.isEnabled());
        emailVerification.continueButton.click();

        DeleteAccountPage deleteAccountPage = new DeleteAccountPage();
        Assert.assertTrue(deleteAccountPage.accountStats.isDisplayed());
        Assert.assertFalse(deleteAccountPage.deleteAccountBtn.isEnabled());

        deleteAccountPage.deleteAccountCheckBox.click();
        Assert.assertTrue(deleteAccountPage.isDeleteAcctChecked());
        Assert.assertTrue(deleteAccountPage.deleteAccountBtn.isEnabled());

        deleteAccountPage.deleteAccountBtn.click();
        LandingPage landingPageLoggedOut = new LandingPage();
        landingPageLoggedOut.isAt();

    }

    @Test
    public void loginUtilTest() throws InterruptedException {

        ActiveDuty recruit = new ActiveDuty("Jessie","Kim","jkim1234@mail.com","6105965484","Temp1234");
        TestUtil.recruitSignupWithEmail(recruit);
        Thread.sleep(2000);
        TestUtil.deleteAccountByEmail(recruit.getEmail(),recruit.getPassword());
    }


}
