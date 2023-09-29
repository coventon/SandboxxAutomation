package com.sandboxx.Android.registrationTests;

import com.sandboxx.Android.AndroidBaseTest;
import com.sandboxx.dataManagement.constants.GoTo;
import com.sandboxx.dataManagement.testData.ExcelDataReader;
import com.sandboxx.dataManagement.testData.userModels.ActiveDuty;
import com.sandboxx.dataManagement.testData.userModels.Address;
import com.sandboxx.dataManagement.testData.userModels.Recipient;
import com.sandboxx.dataManagement.testDataModels.TestDataModel;
import com.sandboxx.framework.base.AppDriver;
import com.sandboxx.framework.utils.ApiHelper;
import com.sandboxx.framework.utils.PageActionsHelper;
import com.sandboxx.framework.utils.TestUtil;
import com.sandboxx.pages.homeView.giftCards.*;
import com.sandboxx.pages.homeView.letters.*;
import com.sandboxx.pages.homeView.letters.purchaseLetters.CardDetailsPage;
import com.sandboxx.pages.homeView.letters.purchaseLetters.CheckoutPage;
import com.sandboxx.pages.homeView.letters.purchaseLetters.SelectBundlePage;
import com.sandboxx.pages.loginPages.*;
import com.sandboxx.pages.LandingPage;
import com.sandboxx.pages.homeView.HomePage;
import com.sandboxx.pages.musterView.recruit.MusterPreparePage;
import com.sandboxx.pages.musterView.recruit.RecruitMusterPage;
import com.sandboxx.pages.musterView.recruiter.InviteCodePage;
import com.sandboxx.pages.musterView.recruiter.MusterChatInvitePage;
import com.sandboxx.pages.musterView.recruiter.MusterDashboardTab;
import com.sandboxx.pages.musterView.recruiter.MusterPage;
import com.sandboxx.pages.profileView.addressBook.AddressBookTab;
import com.sandboxx.pages.profileView.ProfilePage;
import com.sandboxx.pages.profileView.addressBook.currentContact.CurrentContactPage;
import com.sandboxx.pages.profileView.addressBook.newContact.*;
import com.sandboxx.pages.profileView.settings.ConfirmRelationshipsPage;
import com.sandboxx.pages.profileView.settings.ImportContactsPage;
import com.sandboxx.pages.profileView.settings.InviteFriendsPage;
import com.sandboxx.pages.profileView.settings.SettingsPage;
import com.sandboxx.pages.profileView.settings.deleteAccount.DeleteAccountPage;
import com.sandboxx.pages.profileView.settings.deleteAccount.VerifyAccountPage;
import com.sandboxx.pages.registration.*;
import com.sandboxx.pages.registration.onboarding.*;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;

public class RegistrationTest extends AndroidBaseTest {

    //Account Creation (Email) - Auto Connected Kin

    @Test(priority = 1, groups = "Registration")
    public void registerWithEmail_AutoConnectedKin() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        // ToDo: Delete account before test.
        //ApiHelper.deleteAccount(testData.phone,testData.email); This call deletes lead entity
        //TestUtil.loginAndDeleteAccountByEmailPassword(testData.email, testData.password);

        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();

        Assert.assertEquals("Sign Up", signUpPage.pageHeader.getText());
        Assert.assertEquals(signUpPage.welcomeHeader.getText(), "Welcome to Sandboxx!");
        Assert.assertEquals(signUpPage.emailLabel.getText(), "Sign up by entering your email address");
        Assert.assertTrue(signUpPage.emailInput.isDisplayed());

        signUpPage.submitEmail(testData.email);
        VerificationCodePage verificationCodePage = new VerificationCodePage();

        Assert.assertFalse(verificationCodePage.continueButton.isEnabled());
        verificationCodePage.submitVerificationCode("123456");

        FinishSignupMainPage finishSignupMainPage = new FinishSignupMainPage();
        finishSignupMainPage.finishSignUpButton.click();

        FinishSignUpForm finishSignUpForm = new FinishSignUpForm();
        Assert.assertTrue(finishSignUpForm.firstNameLabel.isDisplayed());
        finishSignUpForm.firstNameInput.sendKeys(testData.firstName);
        Assert.assertEquals(finishSignUpForm.firstNameInput.getText(), testData.firstName);

        Assert.assertTrue(finishSignUpForm.lastNameLabel.isDisplayed());
        finishSignUpForm.lastNameInput.sendKeys(testData.lastName);

        Assert.assertTrue(finishSignUpForm.emailLabel.isDisplayed());
        Assert.assertEquals(finishSignUpForm.emailInput.getText(), testData.email);

        Assert.assertTrue(finishSignUpForm.passwordLabel.isDisplayed());
        finishSignUpForm.passwordInput.sendKeys(testData.password);

        Assert.assertTrue(finishSignUpForm.passwordDisclaimer.isDisplayed());
        finishSignUpForm.signUpButton.click();

        SignUpSuccessPage signUpSuccessPage = new SignUpSuccessPage();
        signUpSuccessPage.continueButton.click();

        UseSandboxxPage useSandboxxPage = new UseSandboxxPage();
        Thread.sleep(4000);

        BranchSelectionPage branchSelectionPage = useSandboxxPage.selectMilitaryCareer();
        BranchServicePage branchServicePage = branchSelectionPage.tapActiveDuty();
        branchServicePage.selectMarineCorp();
        WelcomePage welcomePage = new WelcomePage();
        Thread.sleep(4000);

        HomePage homePage = welcomePage.enterSandboxx();
        homePage.navigateToProfile();
        Thread.sleep(4000);
        ProfilePage profilePage = new ProfilePage();
        Assert.assertEquals(profilePage.fullNameDisplay.getText(), testData.firstName + " " + testData.lastName);
        Assert.assertEquals(profilePage.userType.getText(), "Active Duty");
        AddressBookTab addressBookTab = profilePage.tapAddressBook();
        Assert.assertTrue(addressBookTab.importContactsIcon.isDisplayed());
        Assert.assertTrue(addressBookTab.importContactsLabel.isDisplayed());

        // ToDo: Delete account before test.
        //ApiHelper.deleteAccount(testData.phone, testData.email);
        TestUtil.deleteAccountByEmail(testData.email, testData.password);

    }

    @Test(priority = 2, groups = "Registration")
    public void registerWithEmail_ExistingEmail() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();
        Thread.sleep(4000);
        Assert.assertEquals("Sign Up", signUpPage.pageHeader.getText());
        Assert.assertEquals(signUpPage.welcomeHeader.getText(), "Welcome to Sandboxx!");
        Assert.assertEquals(signUpPage.emailLabel.getText(), "Sign up by entering your email address");

        signUpPage.submitEmail(testData.email);

        VerificationCodePage verificationCodePage = new VerificationCodePage();
        Assert.assertFalse(verificationCodePage.continueButton.isEnabled());
        verificationCodePage.submitVerificationCode("123456");

        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isAt());
    }

    @Test(priority = 3, groups = "Registration") // Deprecated Test: Phone login was removed
    public void registerWithEmail_PhoneInUse() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

// ToDo: Delete account before test.
        ApiHelper.deleteAccount(testData.phone, testData.email);

        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();
        Thread.sleep(4000);
        Assert.assertEquals("Sign Up", signUpPage.pageHeader.getText());

// ToDo: New test

    }

    @Test(priority = 4, groups = "Registration")
    public void registerAccount_ByEmail() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        // ToDo: Delete account before test
        //ApiHelper.deleteAccount(testData.phone, testData.email);

        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();

        signUpPage.submitEmail(testData.email);

        VerificationCodePage verificationCodePage = new VerificationCodePage();
        verificationCodePage.submitVerificationCode("123456");

        FinishSignupMainPage finishSignupMainPage = new FinishSignupMainPage();
        finishSignupMainPage.finishSignUpButton.click();

        FinishSignUpForm finishSignUpForm = new FinishSignUpForm();
        finishSignUpForm.submitForm(testData.firstName, testData.lastName, testData.email, testData.password);

        SignUpSuccessPage signUpSuccessPage = new SignUpSuccessPage();
        signUpSuccessPage.continueButton.click();

        UseSandboxxPage useSandboxxPage = new UseSandboxxPage();
        Thread.sleep(2000);

        BranchSelectionPage branchSelectionPage = useSandboxxPage.selectMilitaryCareer();
        BranchServicePage branchServicePage = branchSelectionPage.tapActiveDuty();
        branchServicePage.selectMarineCorp();
        WelcomePage welcomePage = new WelcomePage();
        Thread.sleep(2000);

        HomePage homePage = welcomePage.enterSandboxx();
        homePage.navigateToProfile();
        Thread.sleep(2000);
        ProfilePage profilePage = new ProfilePage();
        Assert.assertEquals(profilePage.fullNameDisplay.getText(), testData.firstName + " " + testData.lastName);
        Assert.assertEquals(profilePage.userType.getText(), "Active Duty");
        AddressBookTab addressBookTab = profilePage.tapAddressBook();
        Assert.assertTrue(addressBookTab.importContactsIcon.isDisplayed());
        Assert.assertTrue(addressBookTab.importContactsLabel.isDisplayed());
        // ToDo: Delete account after test
        //ApiHelper.deleteAccount(testData.phone,testData.email);
        TestUtil.deleteAccountByEmail(testData.email,testData.password);

    }

    @Test(priority = 5, groups = "Registration")
    public void deleteAccount_ByEmail() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        LandingPage landingPage = new LandingPage();
        EmailLoginPage emailLoginPage = landingPage.clickLoginButton();
        Thread.sleep(4000);
        emailLoginPage.submitEmailLogin(testData.email);
        CodeVerificationPage codeVerificationPage = new CodeVerificationPage();
        codeVerificationPage.submitVerificationCode("123456");

        HomePage homePage = new HomePage();
        Assert.assertEquals("Welcome to Sandboxx, " + testData.firstName, homePage.welcomeGreeting.getText());

        // ToDo: Add Delete Flow below
    }

    @Test(priority = 6, groups = "Registration")
    public void registerWithEmail_AddContactFromSettings() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        // ToDo: Delete account before test.
        //ApiHelper.deleteAccount(testData.phone,testData.email);


        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();
        Thread.sleep(2000);
        Assert.assertEquals("Sign Up", signUpPage.pageHeader.getText());
        Assert.assertEquals(signUpPage.welcomeHeader.getText(), "Welcome to Sandboxx!");
        Assert.assertEquals(signUpPage.emailLabel.getText(), "Sign up by entering your email address");

        signUpPage.submitEmail(testData.email);
        VerificationCodePage verificationCodePage = new VerificationCodePage();
        verificationCodePage.submitVerificationCode("123456");

        FinishSignupMainPage finishSignupMainPage = new FinishSignupMainPage();
        finishSignupMainPage.finishSignUpButton.click();

        FinishSignUpForm finishSignUpForm = new FinishSignUpForm();
        finishSignUpForm.submitForm(testData.firstName, testData.lastName, testData.email, testData.password);

        SignUpSuccessPage signUpSuccessPage = new SignUpSuccessPage();
        signUpSuccessPage.continueButton.click();

        // ====== Old Code =======
        UseSandboxxPage useSandboxxPage = new UseSandboxxPage();
        Thread.sleep(2000);

        BranchSelectionPage branchSelectionPage = useSandboxxPage.selectBasicTraining();
        BranchServicePage branchServicePage = branchSelectionPage.tapActiveDuty();
        Thread.sleep(2000);
        SelectRecruitingStationPage recruitingStation = branchServicePage.selectAirForce();
        Thread.sleep(2000);

        recruitingStation.backButton.click();
        Assert.assertTrue(branchServicePage.isAt());

        SelectRecruitingStationPage recruitingStation2 = branchServicePage.selectAirForce();
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

        Assert.assertEquals(relationshipsPage.contactList.size(), 3);
        Assert.assertTrue(relationshipsPage.isContactDisplayed("Contact One"));
        Assert.assertTrue(relationshipsPage.isContactDisplayed("Contact Two"));
        Assert.assertTrue(relationshipsPage.isContactDisplayed("Contact Three"));

        relationshipsPage.importButton.click();
        SettingsPage settingsPage = new SettingsPage();
        settingsPage.backButton.click();
        ProfilePage profilePage1 = new ProfilePage();

        // ToDo: Delete account after test.
    }

    @Test(priority = 7, groups = "Registration")
    public void deleteAccountByEmail() throws InterruptedException {

        //ApiHelper.deleteAccount("4057704109","jdoe1234@mail.com");
        //ApiHelper.deleteAccount2();

        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        // ToDo: Delete account before test.
        //ApiHelper.deleteAccount(testData.phone,testData.email);

        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();

        signUpPage.submitEmail(testData.email);
        VerificationCodePage verificationCodePage = new VerificationCodePage();
        verificationCodePage.submitVerificationCode("123456");

        FinishSignupMainPage finishSignupMainPage = new FinishSignupMainPage();
        finishSignupMainPage.finishSignUpButton.click();

        FinishSignUpForm finishSignUpForm = new FinishSignUpForm();
        finishSignUpForm.submitForm(testData.firstName, testData.lastName, testData.email, testData.password);

        SignUpSuccessPage signUpSuccessPage = new SignUpSuccessPage();
        signUpSuccessPage.continueButton.click();

        // ====== Old Code =====

        UseSandboxxPage useSandboxxPage = new UseSandboxxPage();

        BranchSelectionPage branchSelectionPage = useSandboxxPage.selectBasicTraining();
        BranchServicePage branchServicePage = branchSelectionPage.tapActiveDuty();
        SelectRecruitingStationPage recruitingStation = branchServicePage.selectAirForce();

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
        //EmailVerificationPage emailVerification = verifyAccountPage.continueWithEmail();
        Thread.sleep(2000);
        Assert.assertFalse(verifyAccountPage.continueButton.isEnabled());
        verifyAccountPage.emailInput.sendKeys(testData.email);

        // !!! Bug V
        //Assert.assertFalse(emailVerification.continueButton.isEnabled());
        verifyAccountPage.passwordInput.sendKeys(testData.password);
        Assert.assertTrue(verifyAccountPage.continueButton.isEnabled());
        verifyAccountPage.continueButton.click();

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

    @Test(priority = 8, groups = "Registration")
    public void loginUtilTest() throws InterruptedException {

        ActiveDuty recruit = new ActiveDuty("Jessie", "Kim", "jkim1234@mail.com", "6105965484", "Temp1234");
        TestUtil.recruitSignupWithEmail(recruit);
        Thread.sleep(2000);
        TestUtil.deleteAccountByEmail(recruit.getEmail(), recruit.getPassword());
    }

    @Test(priority = 9, groups = "Registration")
    public void testLoginAndDeleteAcct() throws InterruptedException {

        ActiveDuty recruit = new ActiveDuty("Jessie", "Kim", "jkim1234@mail.com", "6105965484", "Temp1234");
        //TestUtil.loginAndDeleteAccountByEmailPassword("asfasdf@mail.com","Temp1234");
        TestUtil.loginAndDeleteAccountByEmailPassword("jdoe1234@mail.com", "Temp1234");
        Thread.sleep(2000);
        //TestUtil.deleteAccountByEmail(recruit.getEmail(),recruit.getPassword());
    }

    @Test(priority = 10, groups = "Registration")
    public void registerWithGoogle_FamilyFriend_UnknownTrainingBase() throws InterruptedException {

        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);
        String contactRank = "MARINES PVT";
        String contactFirstName = "John";
        String contactLastName = "Doe";
        String contactBase = "PARRIS ISLAND";
        String contactBattalion = "first";
        String contactCompany = "alpha";
        String contactPlatoon = "1020";

        // ToDo: Delete account before test

        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();

        signUpPage.continueWithGoogle();
        signUpPage.chooseAccount(testData.email);
        UseSandboxxPage useSandboxxPage = new UseSandboxxPage();
        DescribeYouPage describeYouPage = useSandboxxPage.selectSendLetters();
        RecipientAddressPage recipientAddressPage = describeYouPage.selectOther();
        WelcomePage welcomePage = recipientAddressPage.selectNo();
        HomePage homePage = welcomePage.enterSandboxx();
        homePage.navigateToProfile();


        ProfilePage profilePage = new ProfilePage();
        AddressBookTab addressBookTab = profilePage.tapAddressBook();

        // Add contact
        MailingAddressPage mailingAddressPage = addressBookTab.addNewContact();
        mailingAddressPage.submitMailingAddress(contactRank, contactFirstName, contactLastName);
        SelectBasePage selectBasePage = new SelectBasePage();
        selectBasePage.selectBase(contactBase);
        BaseAddressPage baseAddressPage = new BaseAddressPage();
        baseAddressPage.submitBattalion(contactBattalion);
        baseAddressPage.submitCompany(contactCompany);
        baseAddressPage.submitPlatoonTraining(contactPlatoon);
        AddressReviewPage addressReviewPage = new AddressReviewPage();

        String expectedRankName = "PVT " + contactFirstName + " " + contactLastName;
        Assert.assertEquals(addressReviewPage.getRankName(), expectedRankName, "Contact's Rank, Name does not match expected");
        String expectedBase = "1ST RTBN ALPHA CO PLT " + contactPlatoon;
        Assert.assertEquals(addressReviewPage.getBaseInfo(), expectedBase, "Base info does not match expected");
        Assert.assertEquals(addressReviewPage.getAddress(), "BOX 16125", "Base address does not match expected");
        Assert.assertEquals(addressReviewPage.getCityStateZip(), contactBase + ", SC 29905-6125", "Base city, state & zip does not match expected");
        Thread.sleep(2000);
        addressReviewPage.confirmAddress();
        Thread.sleep(2000);
        AddressBookTab addressBookTab1 = new AddressBookTab();
        Assert.assertTrue(addressBookTab1.isContactDisplayed(expectedRankName), "New Contact is not displayed in Address Book");

        //mailingAddressPage.backButton.click();
        // ToDo: Create linked lead
        //Assert.assertTrue(addressBookTab.isContactDisplayed("Sandboxx Test1"));

        // Delete Account

        SettingsPage settings = profilePage.tapSettings();
        VerifyAccountPage verifyAccountPage = settings.tapDeleteAccount();
        verifyAccountPage.continueWithGoogle();
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage();
        deleteAccountPage.deleteAccountCheckBox.click();
        deleteAccountPage.deleteAccount();
    }

    @Test(priority = 11, groups = "Registration")
    public void registerWithGoogle_AutoConnectedKin() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        // ToDo: Delete account before test

        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();

        signUpPage.continueWithGoogle();
        signUpPage.chooseAccount(testData.email);
        UseSandboxxPage useSandboxxPage = new UseSandboxxPage();
        BranchSelectionPage branchSelectionPage = useSandboxxPage.selectBasicTraining();
        Thread.sleep(2000);
        branchSelectionPage.selectServiceBranch("Active Duty");
        BranchServicePage branchServicePage = new BranchServicePage();
        branchServicePage.selectNavy();
        NavyRecruitingStationPage navyStation = new NavyRecruitingStationPage();
        navyStation.searchInput.sendKeys("gold");
        navyStation.selectStation("Pacific • Gold Coast");
        ShipDateSelectPage shipDatePage = new ShipDateSelectPage();
        shipDatePage.noButton.click();
        InviteFriendsPage inviteFriendsPage = new InviteFriendsPage();
        inviteFriendsPage.tapInvite();
        ImportContactsPage importContactsPage = new ImportContactsPage();
        importContactsPage.searchInput.sendKeys("Sandboxx");
        importContactsPage.selectContactByName("Sandboxx Test1");
        ConfirmRelationshipsPage confirmRelationshipsPage = importContactsPage.tapImport();
        WelcomePage welcomePage = confirmRelationshipsPage.tapImport();
        HomePage homePage = welcomePage.enterSandboxx();
        homePage.navigateTo(GoTo.Sandboxx_Profile);
        ProfilePage profilePage = new ProfilePage();
        AddressBookTab addressBookTab = profilePage.tapAddressBook();
        Assert.assertTrue(addressBookTab.isContactDisplayed("Sandboxx Test1"));

        // Delete Account
        SettingsPage settings = profilePage.tapSettings();
        VerifyAccountPage verifyAccountPage = settings.tapDeleteAccount();
        verifyAccountPage.continueWithGoogle();
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage();
        deleteAccountPage.deleteAccountCheckBox.click();
        deleteAccountPage.deleteAccount();

        // ToDo: Delete Account
    }

    @Test(priority = 12, groups = "Registration")
    public void registerWithFacebook_AutoConnectedKin() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        // ToDo: Delete account before test

        LandingPage landingPage = new LandingPage();
        Thread.sleep(2000);
        SignUpPage signUpPage = landingPage.clickSignUpButton();

        signUpPage.continueWithFacebook();

        // ToDo: Onboarding
        UseSandboxxPage useSandboxxPage = new UseSandboxxPage();
        Thread.sleep(2000);

        BranchSelectionPage branchSelectionPage = useSandboxxPage.selectMilitaryCareer();
        BranchServicePage branchServicePage = branchSelectionPage.tapActiveDuty();
        branchServicePage.selectMarineCorp();
        WelcomePage welcomePage = new WelcomePage();
        Thread.sleep(4000);

        HomePage homePage = welcomePage.enterSandboxx();
        homePage.navigateToProfile();
        Thread.sleep(4000);
        ProfilePage profilePage = new ProfilePage();
        // ToDo: Add contacts

        // ToDo: Delete account
        SettingsPage settingsPage = profilePage.tapSettings();
        PageActionsHelper.scrollDown();
    }

    //https://api-stage.sandboxx.us/rest/test/automation/create/leads?phoneNumber=4328940918&emailAddress=rossvance247@gmail.comt&u=team@sandboxx.us&t=SandboxxTest2001!
    @Test(priority = 13, groups = "Registration")
    public void registerWithGoogle_SendLetter() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);
        String contactRank = "MARINES PVT";
        String contactFirstName = "John";
        String contactLastName = "Doe";
        String contactBase = "PARRIS ISLAND";
        String contactBattalion = "first";
        String contactCompany = "alpha";
        String contactPlatoon = "1020";

        // ToDo: Delete account before test

        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();

        signUpPage.continueWithGoogle();
        signUpPage.chooseAccount(testData.email);
        UseSandboxxPage useSandboxxPage = new UseSandboxxPage();
        DescribeYouPage describeYouPage = useSandboxxPage.selectSendLetters();
        RecipientAddressPage recipientAddressPage = describeYouPage.selectOther();
        WelcomePage welcomePage = recipientAddressPage.selectNo();
        HomePage homePage = welcomePage.enterSandboxx();

        // #Send letter
        // #Add Contact
        RecipientPage recipientPage = homePage.tapCompose();
        MailingAddressPage mailingAddressPage = recipientPage.tapNewContact();
        mailingAddressPage.submitMailingAddress(contactRank, contactFirstName, contactLastName);
        SelectBasePage selectBasePage = new SelectBasePage();
        selectBasePage.selectBase(contactBase);
        BaseAddressPage baseAddressPage = new BaseAddressPage();
        baseAddressPage.submitBattalion(contactBattalion);
        baseAddressPage.submitCompany(contactCompany);
        baseAddressPage.submitPlatoonTraining(contactPlatoon);
        AddressReviewPage addressReviewPage = new AddressReviewPage();

        String expectedRankName = "PVT " + contactFirstName + " " + contactLastName;
        Assert.assertEquals(addressReviewPage.getRankName(), expectedRankName, "Contact's Rank, Name does not match expected");
        String expectedBase = "1ST RTBN ALPHA CO PLT " + contactPlatoon;
        Assert.assertEquals(addressReviewPage.getBaseInfo(), expectedBase, "Base info does not match expected");
        Assert.assertEquals(addressReviewPage.getAddress(), "BOX 16125", "Base address does not match expected");
        Assert.assertEquals(addressReviewPage.getCityStateZip(), contactBase + ", SC 29905-6125", "Base city, state & zip does not match expected");
        Thread.sleep(2000);
        addressReviewPage.confirmAddress();
        Thread.sleep(3000);
        RecipientPage recipientPage1 = new RecipientPage();
        recipientPage1.addressBookTab.click();

        // #Send Letter
        CurrentContactPage currentContactPage = recipientPage1.selectContact(expectedRankName);
        currentContactPage.nextButton.click();
        ComposeLetterPage composeLetterPage = new ComposeLetterPage();
        composeLetterPage.enterMessage("This is an automated test: " + testName);
        PhotoUploadPage photoUploadPage = composeLetterPage.tapNext();
        LetterAddonsPage letterAddonsPage = photoUploadPage.goToNextPage();
        PageActionsHelper.scrollUp();
        letterAddonsPage.tapNext();
        EditYourAddressPage editYourAddressPage = new EditYourAddressPage();
        editYourAddressPage.submitAddress("123 Abcd st", "Dundee", "AZ", "20002");

        LetterReviewPage letterReviewPage = new LetterReviewPage();
        Assert.assertTrue(letterReviewPage.isLettersModalDisplayed(), "Buy Letters modal is not displayed");
        SelectBundlePage selectBundlePage = letterReviewPage.buyLetters();
        CheckoutPage checkoutPage = selectBundlePage.select5Letters();
        checkoutPage.selectPaymentMethod();
        checkoutPage.selectCreditCard();

        CardDetailsPage cardDetails = new CardDetailsPage();
        cardDetails.submitCardNumber("4111111111111111");
        cardDetails.submitExpirationCvv("022025", "226");
        CheckoutPage checkoutPage2 = new CheckoutPage();
        checkoutPage2.tapPurchase();
        checkoutPage2.continueButton.click();
        LetterReviewPage letterReviewPage2 = new LetterReviewPage();
        OrderCompletePage orderCompletePage = letterReviewPage2.sendLetter();
        HomePage homePage2 = orderCompletePage.goToDashboard();
        Assert.assertEquals(homePage2.lettersCreditCount.getText(), "4");

        TestUtil.deleteAccountGoogle();
        Thread.sleep(5000);

//        String pageSource = ((AndroidDriver)AppDriver.getDriver()).getPageSource();
//        System.out.println(">>>>> PAGE SOURCE: ");
    }

    @Test(priority = 14, groups = "Registration")
    public void registerWithGoogle_DelayedEntryProgram() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();
        signUpPage.continueWithGoogle();
        signUpPage.chooseAccount(testData.email);
        UseSandboxxPage useSandboxxPage = new UseSandboxxPage();
        BranchSelectionPage branchSelectionPage = useSandboxxPage.selectBasicTraining();
        branchSelectionPage.selectServiceBranch("Active Duty");
        BranchServicePage branchServicePage = new BranchServicePage();
        branchServicePage.selectNavy();
        NavyRecruitingStationPage navyStation = new NavyRecruitingStationPage();
        navyStation.searchInput.sendKeys("gold");
        navyStation.selectStation("Pacific • Gold Coast");
        ShipDateSelectPage shipDatePage = new ShipDateSelectPage();
        shipDatePage.noButton.click();
        InviteFriendsPage inviteFriendsPage = new InviteFriendsPage();
        inviteFriendsPage.tapInvite();
        ImportContactsPage importContactsPage = new ImportContactsPage();
        importContactsPage.searchInput.sendKeys("Sandboxx");
        importContactsPage.selectContactByName("Sandboxx Test1");
        ConfirmRelationshipsPage confirmRelationshipsPage = importContactsPage.tapImport();
        WelcomePage welcomePage = confirmRelationshipsPage.tapImport();
        HomePage homePage = welcomePage.enterSandboxx();
        homePage.navigateTo(GoTo.Sandboxx_Profile);
        ProfilePage profilePage = new ProfilePage();
        AddressBookTab addressBookTab = profilePage.tapAddressBook();
        Assert.assertTrue(addressBookTab.isContactDisplayed("Sandboxx Test1"));

        // Delete Account
        TestUtil.deleteAccountGoogle();
    }

    @Test(priority = 15, groups = "Registration")
    public void registerWithGoogle_DelayedEntryProgram_Trainee_Navy_GL() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();
        signUpPage.continueWithGoogle();
        signUpPage.chooseAccount(testData.email);
        UseSandboxxPage useSandboxxPage = new UseSandboxxPage();
        BranchSelectionPage branchSelectionPage = useSandboxxPage.selectBasicTraining();
        branchSelectionPage.selectServiceBranch("Active Duty");
        BranchServicePage branchServicePage = new BranchServicePage();
        branchServicePage.selectNavy();
        NavyRecruitingStationPage navyStation = new NavyRecruitingStationPage();
        navyStation.searchInput.sendKeys("Great Lakes");
        navyStation.selectStation("Great Lakes • Chicago");
        ShipDateSelectPage shipDatePage = new ShipDateSelectPage();
        shipDatePage.noButton.click();
        InviteFriendsPage inviteFriendsPage = new InviteFriendsPage();
        inviteFriendsPage.tapInvite();
        ImportContactsPage importContactsPage = new ImportContactsPage();
        importContactsPage.searchInput.sendKeys("Sandboxx");
        importContactsPage.selectContactByName("Sandboxx Test1");
        ConfirmRelationshipsPage confirmRelationshipsPage = importContactsPage.tapImport();
        WelcomePage welcomePage = confirmRelationshipsPage.tapImport();
        HomePage homePage = welcomePage.enterSandboxx();
        homePage.navigateTo(GoTo.Sandboxx_Profile);
        ProfilePage profilePage = new ProfilePage();
        AddressBookTab addressBookTab = profilePage.tapAddressBook();
        Assert.assertTrue(addressBookTab.isContactDisplayed("Sandboxx Test1"));

        // Delete Account
        TestUtil.deleteAccountGoogle();
    }

    @Test(priority = 16, groups = "Registration")
    public void registerWithGoogle_ForceQuitAtBranchOfService() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();
        signUpPage.continueWithGoogle();
        signUpPage.chooseAccount(testData.email);
        UseSandboxxPage useSandboxxPage = new UseSandboxxPage();
        ((AndroidDriver) AppDriver.getDriver()).runAppInBackground(Duration.ofMillis(5000));

        BranchSelectionPage branchSelectionPage = useSandboxxPage.selectMilitaryCareer();
        ((AndroidDriver) AppDriver.getDriver()).runAppInBackground(Duration.ofMillis(5000));
        HomePage homePage = new HomePage();
        // Delete Account
        TestUtil.deleteAccountGoogle();
    }

    @Test(priority = 17, groups = "Registration")
    public void registerWithGoogle_Trainee_Marine_MA_GiftCards() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();
        signUpPage.continueWithGoogle();
        signUpPage.chooseAccount(testData.email);
        UseSandboxxPage useSandboxxPage = new UseSandboxxPage();
        BranchSelectionPage branchSelectionPage = useSandboxxPage.selectBasicTraining();
        branchSelectionPage.selectServiceBranch("Active Duty");
        BranchServicePage branchServicePage = new BranchServicePage();
        branchServicePage.selectMarineCorp();
        MarinesRecruitingStationPage marineStation = new MarinesRecruitingStationPage();
        marineStation.selectStationByLocation("Massachusetts • Boston");

        ShipDateSelectPage shipDatePage = new ShipDateSelectPage();
        shipDatePage.noButton.click();
        InviteFriendsPage inviteFriendsPage = new InviteFriendsPage();
        WelcomePage welcomePage = inviteFriendsPage.tapNoThanks();

        HomePage homePage = welcomePage.enterSandboxx();
        Assert.assertTrue(homePage.giftCardsTitle.isDisplayed(), "Gift Cards title is not displayed on the home page.");
        Assert.assertTrue(homePage.giftCardsImage.isDisplayed(), "Gift Cards Image is not displayed on the home page.");
        GiftCardsPage giftCards = homePage.navigateToGiftCards();
        ManageGiftCardsPage manageCardsPage = giftCards.manageGiftCard();
        Assert.assertEquals(manageCardsPage.pageHeader.getText(), "Welcome to Sandboxx, " + testData.firstName);
        Assert.assertTrue(manageCardsPage.sentGiftCardsTab.isDisplayed());
        Assert.assertTrue(manageCardsPage.isNoGiftCardTextPresent());

        GiftCardsPage giftCardsPage = manageCardsPage.navigateBack();
        giftCardsPage.backButton.click();

        // Delete Account
        TestUtil.deleteAccountGoogle();
    }

    @Test(priority = 18, groups = "Registration")
    public void registerWithGoogle_Trainee_Marine_MA_BuyGiftCard() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);
        Recipient cardRecipient = new Recipient("James", "Wilson", "jwilson1234@mail.com");
        Address recipientAddress = new Address("123 Main st.", "", "Wheeling", "FL", "31254");
        cardRecipient.setAddress(recipientAddress);

        System.out.println(">>>> Email: " + testData.email);
        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();
        signUpPage.continueWithGoogle();

        System.out.println(">>>> Email: " + testData.email);
        signUpPage.chooseAccount(testData.email);
        UseSandboxxPage useSandboxxPage = new UseSandboxxPage();
        BranchSelectionPage branchSelectionPage = useSandboxxPage.selectBasicTraining();
        branchSelectionPage.selectServiceBranch("Active Duty");
        BranchServicePage branchServicePage = new BranchServicePage();
        branchServicePage.selectMarineCorp();
        MarinesRecruitingStationPage marineStation = new MarinesRecruitingStationPage();
        marineStation.selectStationByLocation("Massachusetts • Boston");

        ShipDateSelectPage shipDatePage = new ShipDateSelectPage();
        shipDatePage.noButton.click();
        InviteFriendsPage inviteFriendsPage = new InviteFriendsPage();
        WelcomePage welcomePage = inviteFriendsPage.tapNoThanks();

        HomePage homePage = welcomePage.enterSandboxx();
        Assert.assertTrue(homePage.giftCardsTitle.isDisplayed(), "Gift Cards title is not displayed on the home page.");
        Assert.assertTrue(homePage.giftCardsImage.isDisplayed(), "Gift Cards Image is not displayed on the home page.");

        GiftCardsPage giftCards = homePage.navigateToGiftCards();

        CardPurchasePage cardPurchasePage = giftCards.buyGiftCards();

        CardRecipientPage recipientPage = cardPurchasePage.select25Card();

        CardSenderPage cardSenderPage = recipientPage.submitNewContact(cardRecipient);
        BillingDetailsPage billingDetailsPage = cardSenderPage.submitSenderForm(testData.firstName, testData.email, "This is test Gift Message.");
        billingDetailsPage.addNewPaymentMethod();
        CardDetailsPage cardDetailsPage = new CardDetailsPage();
        cardDetailsPage.submitCardNumber("4111111111111111");
        cardDetailsPage.submitExpirationCvv("022025", "226");
        BillingDetailsPage billingDetailsPage1 = new BillingDetailsPage();
        OrderReviewPage orderReviewPage = billingDetailsPage1.selectPaymentMethodAndContinue("4111111111111111");
        Assert.assertEquals(orderReviewPage.cardAmountTotal.getText(), "$25");
        Assert.assertEquals(orderReviewPage.activationFeeTotal.getText(), "$2.95");
        Assert.assertEquals(orderReviewPage.totalCost.getText(), "$27.95");
        Assert.assertEquals(orderReviewPage.fromValue.getText(), testData.firstName);
        Assert.assertEquals(orderReviewPage.toValue.getText(), cardRecipient.getFormattedSenderInfo());
        Assert.assertTrue(orderReviewPage.placeOrderButton.getText().contains("$27.95"));

        OrderDetailsPage orderDetailsPage = orderReviewPage.placeOrder();
        Assert.assertEquals(orderDetailsPage.successThanks.getText(), "Thanks " + testData.firstName + "!");
        Assert.assertTrue(orderDetailsPage.successText.isDisplayed());
        Assert.assertTrue(orderDetailsPage.successOrderNumber.isDisplayed());
        Assert.assertTrue(orderDetailsPage.cardAmountLabel.isDisplayed());
        Assert.assertEquals(orderDetailsPage.cardAmountTotal.getText(), "$25.00");
        Assert.assertEquals(orderDetailsPage.activationFeeTotal.getText(), "$2.95");
        Assert.assertEquals(orderDetailsPage.totalCost.getText(), "$27.95");
        Assert.assertEquals(orderDetailsPage.fromValue.getText(), testData.firstName);
        Assert.assertEquals(orderDetailsPage.toValue.getText(), cardRecipient.getFormattedSenderInfo());
        PageActionsHelper.scrollUp();
        Assert.assertEquals(orderDetailsPage.messageContent.getText(), "This is test Gift Message.");
        Assert.assertEquals(orderDetailsPage.paymentInfo.getText(), "**** **** ****1111");

        HomePage homePage1 = orderDetailsPage.tapBackToDashboards();

        //GiftCardsPage giftCardsPage = manageCardsPage.navigateBack();
        //giftCardsPage.backButton.click();

        // Delete Account
        TestUtil.deleteAccountGoogle();
    }

    @Test(priority = 18, groups = "Registration")
    public void registerWithGoogle_FamilyAccount_AddRecipient() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        String type = "MARINES";
        String rank = "RCT";
        String contactRank = type + " " + rank;
        String contactFirstName = "Tom";
        String contactLastName = "Doe";
        String contactBase = "GREAT LAKES";
        String contactShip = "ship5";
        String contactSeps = "male";
        String contactPlatoon = "1020";

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
    }

    @Test(priority = 19, groups = {"Registration"})
    public void registerJoiningMilitaryAccountWithEmail() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        String type = "MARINES";
        String rank = "RCT";
        String contactRank = type + " " + rank;
        String contactFirstName = "Test";
        String contactLastName = "One";
        String contactFullName = contactFirstName +" "+ contactLastName;
        String contactBase = "GREAT LAKES";
        String contactShip = "ship5";
        String contactSeps = "male";
        String contactPlatoon = "1020";

        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();
        signUpPage.submitEmail(testData.email);

        VerificationCodePage verificationCodePage = new VerificationCodePage();
        verificationCodePage.submitVerificationCode("123456");

        FinishSignupMainPage finishSignupMainPage = new FinishSignupMainPage();
        finishSignupMainPage.finishSignUpButton.click();

        FinishSignUpForm finishSignUpForm = new FinishSignUpForm();
        finishSignUpForm.submitForm(testData.firstName, testData.lastName, testData.email, testData.password);

        SignUpSuccessPage signUpSuccessPage = new SignUpSuccessPage();
        signUpSuccessPage.continueButton.click();

        UseSandboxxPage useSandboxxPage = new UseSandboxxPage();
        BranchSelectionPage branchSelectionPage = useSandboxxPage.selectBasicTraining();
        BranchServicePage branchServicePage = branchSelectionPage.tapActiveDuty();
        branchServicePage.selectMarineCorp();
        MarinesRecruitingStationPage marinesRecruitingStationPage = new MarinesRecruitingStationPage();
        marinesRecruitingStationPage.selectStation("South Carolina • Columbia");
        ShipDateSelectPage shipDateSelectPage = new ShipDateSelectPage();
        shipDateSelectPage.tapYes();
        shipDateSelectPage.tapDatePickerOK();
        InviteFriendsPage inviteFriendsPage = new InviteFriendsPage();
        ImportContactsPage importContactsPage = inviteFriendsPage.tapInvite();
        importContactsPage.selectContactByName(contactFullName);
        ConfirmRelationshipsPage confirmRelationshipsPage = importContactsPage.tapImport();
        WelcomePage welcomePage = confirmRelationshipsPage.tapImport();
        HomePage homePage = welcomePage.enterSandboxx();

        // Verify Contact
        homePage.navigateToProfile();
        ProfilePage profilePage = new ProfilePage();
        AddressBookTab addressBookTab = profilePage.tapAddressBook();
        Assert.assertTrue(addressBookTab.isContactDisplayed(contactFullName), "New Contact is not displayed in Address Book");

        Thread.sleep(2000);

       TestUtil.logout();
       TestUtil.loginWithPassword(testData.email,testData.password);

        // Delete Account
        TestUtil.deleteAccountByEmail(testData.email,testData.password);
    }

    @Test(priority = 20, groups = "Registration")
    public void registerNewRecruiter(ITestContext context) throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);
        HashMap<String, String> recruitData = TestUtil.getTestData( testData.userData);
        String recruitFirstName = recruitData.get("RecruitFirst");
        String recruitLastName = recruitData.get("RecruitLast");
        String recruitFullName = recruitFirstName + " " + recruitLastName;
        String recruitEmail = recruitData.get("RecruitEmail");


        UseSandboxxPage useSandboxxPage = null;
        try {
            useSandboxxPage = TestUtil.signupWithEmail(testData.email,testData.password,testData.firstName,testData.lastName);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        BranchSelectionPage branchSelectionPage = useSandboxxPage.selectManageMyRecruits();
        BranchServicePage branchServicePage = branchSelectionPage.tapActiveDuty();
        branchServicePage.selectArmy();

        WelcomePage welcomePage = new WelcomePage();
        Assert.assertTrue(welcomePage.pageHeader.getText().contains(testData.firstName));
        Assert.assertEquals(welcomePage.pageSubHeader.getText(), welcomePage.recruiterSubHeaderText);

        HomePage homePage = welcomePage.enterSandboxx();
        homePage.navigateToMuster();
        MusterPage musterPage = new MusterPage();
        Assert.assertTrue(musterPage.chatIcon.isDisplayed());
        Assert.assertTrue(musterPage.noChatsMsg.isDisplayed());

        musterPage.tapDashboardTab();
        MusterDashboardTab dashboardTab = new MusterDashboardTab();
        InviteCodePage inviteCodePage = dashboardTab.tapInviteRecruits();
        inviteCodePage.copyInviteCode();
        String inviteCode = ((AndroidDriver)AppDriver.getDriver()).getClipboardText();
        context.setAttribute("inviteCode", inviteCode); // Value is accessible across tests
        Assert.assertEquals(inviteCodePage.inviteCode.getText(),inviteCode);
        inviteCodePage.closeInvitePage();

        TestUtil.logout();

        // ========Login as recruit and connect to recruiter========

        String recruiterName = testData.firstName + " " + testData.lastName;
        //String inviteCode = (String) context.getAttribute("inviteCode");
        //String inviteCode = ((AndroidDriver)AppDriver.getDriver()).getClipboardText();

        UseSandboxxPage useSandboxxPage2 = TestUtil.signupWithEmail(recruitEmail,testData.password,recruitFirstName,recruitLastName);
        BranchSelectionPage branchSelectionPage2 = useSandboxxPage2.selectBasicTraining();
        BranchServicePage branchServicePage2 = branchSelectionPage2.tapActiveDuty();
        branchServicePage2.selectArmy();
        ArmyRecruitingStationPage armyRecruitingStationPage = new ArmyRecruitingStationPage();
        armyRecruitingStationPage.selectStation("1st MRBN • Boston");
        ShipDateSelectPage shipDateSelectPage = new ShipDateSelectPage();
        InviteFriendsPage inviteFriendsPage =  shipDateSelectPage.tapNo();
        WelcomePage welcomePage2 = inviteFriendsPage.tapNoThanks();
        HomePage homePage2 = welcomePage2.enterSandboxx();
        homePage2.navigateToMuster();

        RecruitMusterPage musterPage2 = new RecruitMusterPage();
        MusterPreparePage musterPreparePage = musterPage2.tapPrepareTab();
        musterPreparePage.tapConnectWithRecruiter();
        musterPreparePage.joinMuster(inviteCode);
        Assert.assertTrue(musterPreparePage.successModalDisplayed());

        musterPreparePage.tapContinueOnSuccess();
        Assert.assertTrue(musterPreparePage.myRecruiterCardDisplayed());
        Assert.assertEquals(musterPreparePage.myRecruiterName.getText(),recruiterName);

        TestUtil.logout();

        // ========= Recruiter Verify Recruit is added to contacts ========
        TestUtil.loginWithPassword(testData.email,testData.password);

        HomePage homePage3 = new HomePage();
        homePage3.navigateToMuster();

        MusterPage musterPage3 = new MusterPage();
        MusterChatInvitePage inviteToChatPage = musterPage3.startNewChat();
        Assert.assertTrue(inviteToChatPage.isContactDisplayed(recruitFullName));
        inviteToChatPage.navigateBack();

        // =================== Delete recruiter =======================
        TestUtil.deleteAccountByEmail(testData.email,testData.password);

        // ===================== Delete recruit =======================
        TestUtil.loginWithPassword(recruitEmail,testData.password);
        TestUtil.deleteAccountByEmail(recruitEmail,testData.password);
    }

    @Test (priority = 21, groups = "Registration")
    public void registerNewRecruitWithEmail_ConnectRecruiter(ITestContext context) throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);
        String recruiterName = "Lillie Byrne";
        String inviteCode = (String) context.getAttribute("inviteCode");
        //String inviteCode = ((AndroidDriver)AppDriver.getDriver()).getClipboardText();

        UseSandboxxPage useSandboxxPage = TestUtil.signupWithEmail(testData.email,testData.password,testData.firstName,testData.lastName);
        BranchSelectionPage branchSelectionPage = useSandboxxPage.selectBasicTraining();
        BranchServicePage branchServicePage = branchSelectionPage.tapActiveDuty();
        branchServicePage.selectArmy();
        ArmyRecruitingStationPage armyRecruitingStationPage = new ArmyRecruitingStationPage();
        armyRecruitingStationPage.selectStation("1st MRBN • Boston");
        ShipDateSelectPage shipDateSelectPage = new ShipDateSelectPage();
        InviteFriendsPage inviteFriendsPage =  shipDateSelectPage.tapNo();
        WelcomePage welcomePage = inviteFriendsPage.tapNoThanks();
        HomePage homePage = welcomePage.enterSandboxx();
        homePage.navigateToMuster();

        RecruitMusterPage musterPage = new RecruitMusterPage();
        MusterPreparePage musterPreparePage = musterPage.tapPrepareTab();
        musterPreparePage.tapConnectWithRecruiter();
        musterPreparePage.joinMuster(inviteCode); //YM6L4RLE
        Assert.assertTrue(musterPreparePage.successModalDisplayed());

        musterPreparePage.tapContinueOnSuccess();
        Assert.assertTrue(musterPreparePage.myRecruiterCardDisplayed());
        Assert.assertEquals(musterPreparePage.myRecruiterName.getText(),recruiterName);

        //H0FTH82N
        TestUtil.logout();
    }

    @Test (priority = 22, groups = "Registration")
    public void recruiterVerifyConnectedRecruit() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);
        String recruitName = "Brian Carter";
        String recruitEmail = "bcarter1234@mail.com";


        TestUtil.loginWithPassword(testData.email,testData.password);

        HomePage homePage = new HomePage();
        homePage.navigateToMuster();

        MusterPage musterPage = new MusterPage();
        MusterChatInvitePage inviteToChatPage = musterPage.startNewChat();
        Assert.assertTrue(inviteToChatPage.isContactDisplayed(recruitName));
        inviteToChatPage.navigateBack();

        //H0FTH82N
        //TestUtil.logout();
        // Delete recruiter
        TestUtil.deleteAccountByEmail(testData.email,testData.password);

        // Delete recruit
        TestUtil.loginWithPassword(recruitEmail,testData.password);
        TestUtil.deleteAccountByEmail(recruitEmail,testData.password);

    }

    @Test
    public void closePorts() throws InterruptedException {

        System.out.println("Closing ports");
    }


}
