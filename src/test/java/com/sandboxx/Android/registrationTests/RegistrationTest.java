package com.sandboxx.Android.registrationTests;

import com.beust.ah.A;
import com.sandboxx.Android.AndroidBaseTest;
import com.sandboxx.dataManagement.constants.GoTo;
import com.sandboxx.dataManagement.testData.ExcelDataReader;
import com.sandboxx.dataManagement.testData.userModels.ActiveDuty;
import com.sandboxx.dataManagement.testDataModels.TestDataModel;
import com.sandboxx.framework.utils.ApiHelper;
import com.sandboxx.framework.utils.PageActionsHelper;
import com.sandboxx.framework.utils.TestUtil;
import com.sandboxx.pages.homeView.letters.RecipientPage;
import com.sandboxx.pages.loginPages.*;
import com.sandboxx.pages.LandingPage;
import com.sandboxx.pages.homeView.HomePage;
import com.sandboxx.pages.profileView.addressBook.AddressBookTab;
import com.sandboxx.pages.profileView.ProfilePage;
import com.sandboxx.pages.profileView.addressBook.newContact.AddressReviewPage;
import com.sandboxx.pages.profileView.addressBook.newContact.BaseAddressPage;
import com.sandboxx.pages.profileView.addressBook.newContact.MailingAddressPage;
import com.sandboxx.pages.profileView.addressBook.newContact.SelectBasePage;
import com.sandboxx.pages.profileView.settings.ConfirmRelationshipsPage;
import com.sandboxx.pages.profileView.settings.ImportContactsPage;
import com.sandboxx.pages.profileView.settings.InviteFriendsPage;
import com.sandboxx.pages.profileView.settings.SettingsPage;
import com.sandboxx.pages.profileView.settings.deleteAccount.DeleteAccountPage;
import com.sandboxx.pages.profileView.settings.deleteAccount.EmailVerificationPage;
import com.sandboxx.pages.profileView.settings.deleteAccount.VerifyAccountPage;
import com.sandboxx.pages.registration.*;
import com.sandboxx.pages.registration.onboarding.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends AndroidBaseTest {

    //Account Creation (Email) - Auto Connected Kin

    @Test(priority = 1,groups = "Registration")
    public void registerWithEmail_AutoConnectedKin() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n",testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        // ToDo: Delete account before test.
        //ApiHelper.deleteAccount(testData.phone,testData.email); This call deletes lead entity
        TestUtil.loginAndDeleteAccountByEmailPassword(testData.email,testData.password);

        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();
        Thread.sleep(4000);
        Assert.assertEquals("Sign Up",signUpPage.pageHeader.getText());
        Assert.assertEquals(signUpPage.welcomeHeader.getText(),"Welcome to Sandboxx!");
        Assert.assertEquals(signUpPage.emailLabel.getText(),"Sign up by entering your email address");
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
        Assert.assertEquals(finishSignUpForm.lastNameInput.getText(),testData.firstName );

        Assert.assertTrue(finishSignUpForm.lastNameLabel.isDisplayed());
        finishSignUpForm.lastNameInput.sendKeys(testData.lastName);

        Assert.assertTrue(finishSignUpForm.emailLabel.isDisplayed());
        Assert.assertEquals(finishSignUpForm.emailInput.getText(),testData.email);

        Assert.assertTrue(finishSignUpForm.passwordLabel.isDisplayed());
        finishSignUpForm.passwordInput.sendKeys(testData.password);

        Assert.assertTrue(finishSignUpForm.passwordDisclaimer.isDisplayed());
        finishSignUpForm.signUpButton.click();

        SignUpSuccessPage signUpSuccessPage = new SignUpSuccessPage();
        signUpSuccessPage.continueButton.click();

        UseSandboxxPage useSandboxxPage =  new UseSandboxxPage();
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
        Assert.assertEquals(signUpPage.welcomeHeader.getText(),"Welcome to Sandboxx!");
        Assert.assertEquals(signUpPage.emailLabel.getText(),"Sign up by entering your email address");

        signUpPage.submitEmail(testData.email);

        VerificationCodePage verificationCodePage = new VerificationCodePage();
        Assert.assertFalse(verificationCodePage.continueButton.isEnabled());
        verificationCodePage.submitVerificationCode("123456");

        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isAt());

        //!!! Bug here  - error is not displayed. Confirmation Code page displayed

// ToDo: New email login flow - verify error.

        ////=========
//        EmailSignUpPage emailSignUpPage = signUpPage.clickContinueWithEmail();
//        Assert.assertFalse(emailSignUpPage.continueButton.isEnabled());
//        emailSignUpPage.fillEmailForm(testData.firstName,testData.lastName,testData.email,testData.password);
//        emailSignUpPage.showPasswordIcon.click();
//        Assert.assertTrue(emailSignUpPage.continueButton.isEnabled());
//        Thread.sleep(4000);
//
//        emailSignUpPage.continueButton.click();
//        RegistrationErrorModule errorModule = new RegistrationErrorModule();
//        Assert.assertEquals(errorModule.errorTitle.getText(), "Email already in use");
//        Assert.assertEquals(errorModule.errorSubTitle.getText(),"This email has previously been used to create a Sandboxx account. Would you like to log in using this email?");

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
//        Assert.assertEquals(signUpPage.welcomeHader.getText(),"Welcome to Sandboxx!");
//        Assert.assertEquals(signUpPage.phoneNumberLabel.getText(),"Enter your phone number below to signup");
//        Assert.assertTrue(signUpPage.phoneNumberInput.isDisplayed());
//
//        EmailSignUpPage emailSignUpPage = signUpPage.clickContinueWithEmail();
//        Assert.assertFalse(emailSignUpPage.continueButton.isEnabled());
//        emailSignUpPage.fillEmailForm(testData.firstName,testData.lastName,testData.email,testData.password);
//        emailSignUpPage.showPasswordIcon.click();
//        Assert.assertTrue(emailSignUpPage.continueButton.isEnabled());
//        Thread.sleep(4000);
//        emailSignUpPage.continueButton.click();
//
//        PhoneConfirmationPage phoneConfirmationPage = new PhoneConfirmationPage();
//        Assert.assertFalse(phoneConfirmationPage.sendConfirmationButton.isEnabled());
//        phoneConfirmationPage.submitPhoneConfirmation(testData.phone);
//
//        RegistrationErrorModule errorModule = new RegistrationErrorModule();
//        Assert.assertEquals(errorModule.errorTitle.getText(), "Phone Number In Use");
//        Assert.assertEquals(errorModule.errorSubTitle.getText(),"This phone number has already been associated with a Sandboxx account. Would you like to enter a new phone number? You can also skip this step.");
//        errorModule.enterNewNumberButton.click();
//
//        phoneConfirmationPage.submitPhoneConfirmation(testData.phone);
//        Assert.assertEquals(errorModule.errorTitle.getText(), "Phone Number In Use");
//        Assert.assertEquals(errorModule.errorSubTitle.getText(),"This phone number has already been associated with a Sandboxx account. Would you like to enter a new phone number? You can also skip this step.");
//        errorModule.enterNewNumberButton.click();

        // ToDo: Delete account before test

    }

    @Test(priority = 4,groups = "Registration")
    public void registerAccount_ByEmail() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n",testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        // ToDo: Delete account before test
        ApiHelper.deleteAccount(testData.phone,testData.email);

        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();

        signUpPage.submitEmail(testData.email);

        VerificationCodePage verificationCodePage = new VerificationCodePage();
        verificationCodePage.submitVerificationCode("123456");

        FinishSignupMainPage finishSignupMainPage = new FinishSignupMainPage();
        finishSignupMainPage.finishSignUpButton.click();

        FinishSignUpForm finishSignUpForm = new FinishSignUpForm();
        finishSignUpForm.submitForm(testData.firstName,testData.lastName,testData.email,testData.password);

        SignUpSuccessPage signUpSuccessPage = new SignUpSuccessPage();
        signUpSuccessPage.continueButton.click();

        UseSandboxxPage useSandboxxPage =  new UseSandboxxPage();
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
        //ApiHelper.deleteAccount(testData.phone,testData.email);

    }

    @Test(priority = 5,groups = "Registration")
    public void deleteAccount_ByEmail() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n",testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        LandingPage landingPage = new LandingPage();
        EmailLoginPage emailLoginPage = landingPage.clickLoginButton();
        Thread.sleep(4000);
        emailLoginPage.submitEmailLogin(testData.email);
        CodeVerificationPage codeVerificationPage = new CodeVerificationPage();
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
        Assert.assertEquals(signUpPage.welcomeHeader.getText(),"Welcome to Sandboxx!");
        Assert.assertEquals(signUpPage.emailLabel.getText(),"Sign up by entering your email address");

        signUpPage.submitEmail(testData.email);
        VerificationCodePage verificationCodePage = new VerificationCodePage();
        verificationCodePage.submitVerificationCode("123456");

        FinishSignupMainPage finishSignupMainPage = new FinishSignupMainPage();
        finishSignupMainPage.finishSignUpButton.click();

        FinishSignUpForm finishSignUpForm = new FinishSignUpForm();
        finishSignUpForm.submitForm(testData.firstName,testData.lastName,testData.email,testData.password);

        SignUpSuccessPage signUpSuccessPage = new SignUpSuccessPage();
        signUpSuccessPage.continueButton.click();

        // ====== Old Code =======
        UseSandboxxPage useSandboxxPage =  new UseSandboxxPage();
        Thread.sleep(2000);

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

        signUpPage.submitEmail(testData.email);
        VerificationCodePage verificationCodePage = new VerificationCodePage();
        verificationCodePage.submitVerificationCode("123456");

        FinishSignupMainPage finishSignupMainPage = new FinishSignupMainPage();
        finishSignupMainPage.finishSignUpButton.click();

        FinishSignUpForm finishSignUpForm = new FinishSignUpForm();
        finishSignUpForm.submitForm(testData.firstName, testData.lastName, testData.email,testData.password);

        SignUpSuccessPage signUpSuccessPage = new SignUpSuccessPage();
        signUpSuccessPage.continueButton.click();

        // ====== Old Code =====

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

    @Test(priority = 8,groups = "Registration")
    public void loginUtilTest() throws InterruptedException {

        ActiveDuty recruit = new ActiveDuty("Jessie","Kim","jkim1234@mail.com","6105965484","Temp1234");
        TestUtil.recruitSignupWithEmail(recruit);
        Thread.sleep(2000);
        TestUtil.deleteAccountByEmail(recruit.getEmail(),recruit.getPassword());
    }

    @Test(priority = 9,groups = "Registration")
    public void testLoginAndDeleteAcct() throws InterruptedException {

        ActiveDuty recruit = new ActiveDuty("Jessie","Kim","jkim1234@mail.com","6105965484","Temp1234");
        //TestUtil.loginAndDeleteAccountByEmailPassword("asfasdf@mail.com","Temp1234");
        TestUtil.loginAndDeleteAccountByEmailPassword("jdoe1234@mail.com","Temp1234");
        Thread.sleep(2000);
        //TestUtil.deleteAccountByEmail(recruit.getEmail(),recruit.getPassword());
    }

    @Test(priority = 10,groups = "Registration")
    public void registerWithGoogle_FamilyFriend_UnknownTrainingBase() throws InterruptedException {

        System.out.printf("Begin Test:  %s\n",testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);
        String contactRank = "MARINES PVT";
        String contactFirstName = "John";
        String contactLastName = "Doe";
        String contactBase = "PARRIS ISLAND";
        String contactBattalion ="first";
        String contactCompany = "alpha";
        String contactPlatoon = "1020";

        // ToDo: Delete account before test

        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();

        signUpPage.continueWithGoogle();
        signUpPage.chooseAccount(testData.email);
        UseSandboxxPage useSandboxxPage = new UseSandboxxPage();
        DescribeYouPage describeYouPage =  useSandboxxPage.selectSendLetters();
        RecipientAddressPage recipientAddressPage = describeYouPage.selectOther();
        WelcomePage welcomePage = recipientAddressPage.selectNo();
        HomePage homePage = welcomePage.enterSandboxx();
        homePage.navigateToProfile();


        ProfilePage profilePage = new ProfilePage();
        AddressBookTab addressBookTab = profilePage.tapAddressBook();

        // Add contact
        MailingAddressPage mailingAddressPage = addressBookTab.addNewContact();
        mailingAddressPage.submitMailingAddress(contactRank,contactFirstName,contactLastName);
        SelectBasePage selectBasePage = new SelectBasePage();
        selectBasePage.selectBase(contactBase);
        BaseAddressPage baseAddressPage = new BaseAddressPage();
        baseAddressPage.submitBattalion(contactBattalion);
        baseAddressPage.submitCompany(contactCompany);
        baseAddressPage.submitPlatoonTraining(contactPlatoon);
        AddressReviewPage addressReviewPage = new AddressReviewPage();

        String expectedRankName = "PVT "+contactFirstName+" "+contactLastName;
        Assert.assertEquals(addressReviewPage.getRankName(),expectedRankName,"Contact's Rank, Name does not match expected");
        String expectedBase = "1ST RTBN ALPHA CO PLT "+contactPlatoon;
        Assert.assertEquals(addressReviewPage.getBaseInfo(),expectedBase,"Base info does not match expected");
        Assert.assertEquals(addressReviewPage.getAddress(),"BOX 16125","Base address does not match expected");
        Assert.assertEquals(addressReviewPage.getCityStateZip(),contactBase+", SC 29905-6125","Base city, state & zip does not match expected");
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

    @Test(priority = 11,groups = "Registration")
    public void registerWithGoogle_AutoConnectedKin() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n",testName);
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

    @Test(priority = 12,groups = "Registration")
    public void registerWithFacebook_AutoConnectedKin() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n",testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        // ToDo: Delete account before test

        LandingPage landingPage = new LandingPage();
        Thread.sleep(2000);
        SignUpPage signUpPage = landingPage.clickSignUpButton();

        signUpPage.continueWithFacebook();

        // ToDo: Onboarding
        UseSandboxxPage useSandboxxPage =  new UseSandboxxPage();
        Thread.sleep(2000);

        BranchSelectionPage branchSelectionPage =  useSandboxxPage.selectMilitaryCareer();
        BranchServicePage branchServicePage = branchSelectionPage.tapActiveDuty();
        WelcomePage welcomePage =  branchServicePage.selectMarineCorp();
        Thread.sleep(4000);

        HomePage homePage = welcomePage.enterSandboxx();
        homePage.navigateToProfile();
        Thread.sleep(4000);
        ProfilePage profilePage = new ProfilePage();
        // ToDo: Add contacts

        // ToDo: Delete account
        SettingsPage settingsPage =  profilePage.tapSettings();
        PageActionsHelper.scrollDown();
    }

//https://api-stage.sandboxx.us/rest/test/automation/create/leads?phoneNumber=4328940918&emailAddress=rossvance247@gmail.comt&u=team@sandboxx.us&t=SandboxxTest2001!
    @Test(priority = 13,groups = "Registration")
    public void registerWithGoogle_SendLetter() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n",testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);
        String contactRank = "MARINES PVT";
        String contactFirstName = "John";
        String contactLastName = "Doe";
        String contactBase = "PARRIS ISLAND";
        String contactBattalion ="first";
        String contactCompany = "alpha";
        String contactPlatoon = "1020";

        // ToDo: Delete account before test

        LandingPage landingPage = new LandingPage();
        SignUpPage signUpPage = landingPage.clickSignUpButton();

        signUpPage.continueWithGoogle();
        signUpPage.chooseAccount(testData.email);
        UseSandboxxPage useSandboxxPage = new UseSandboxxPage();
        DescribeYouPage describeYouPage =  useSandboxxPage.selectSendLetters();
        RecipientAddressPage recipientAddressPage = describeYouPage.selectOther();
        WelcomePage welcomePage = recipientAddressPage.selectNo();
        HomePage homePage = welcomePage.enterSandboxx();

        // #Send letter
        // #Add Contact
        RecipientPage recipientPage = homePage.tapCompose();
        MailingAddressPage mailingAddressPage = recipientPage.tapNewContact();
        mailingAddressPage.submitMailingAddress(contactRank,contactFirstName,contactLastName);
        SelectBasePage selectBasePage = new SelectBasePage();
        selectBasePage.selectBase(contactBase);
        BaseAddressPage baseAddressPage = new BaseAddressPage();
        baseAddressPage.submitBattalion(contactBattalion);
        baseAddressPage.submitCompany(contactCompany);
        baseAddressPage.submitPlatoonTraining(contactPlatoon);
        AddressReviewPage addressReviewPage = new AddressReviewPage();

        String expectedRankName = "PVT "+contactFirstName+" "+contactLastName;
        Assert.assertEquals(addressReviewPage.getRankName(),expectedRankName,"Contact's Rank, Name does not match expected");
        String expectedBase = "1ST RTBN ALPHA CO PLT "+contactPlatoon;
        Assert.assertEquals(addressReviewPage.getBaseInfo(),expectedBase,"Base info does not match expected");
        Assert.assertEquals(addressReviewPage.getAddress(),"BOX 16125","Base address does not match expected");
        Assert.assertEquals(addressReviewPage.getCityStateZip(),contactBase+", SC 29905-6125","Base city, state & zip does not match expected");
        Thread.sleep(2000);
        addressReviewPage.confirmAddress();
        Thread.sleep(2000);
        RecipientPage recipientPage1 = new RecipientPage();
        recipientPage1.addressBookTab.click();
        recipientPage1.selectContact(expectedRankName);

        // #Send Letter

    }


    @Test
    public void closePorts() throws InterruptedException {

        System.out.println("Closing ports");
    }


}
