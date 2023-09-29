package com.sandboxx.Android.addressBookTests;

import com.sandboxx.Android.AndroidBaseTest;
import com.sandboxx.dataManagement.testData.ExcelDataReader;
import com.sandboxx.dataManagement.testData.userModels.Address;
import com.sandboxx.dataManagement.testData.userModels.Recipient;
import com.sandboxx.dataManagement.testDataModels.TestDataModel;
import com.sandboxx.framework.utils.PageActionsHelper;
import com.sandboxx.framework.utils.TestUtil;
import com.sandboxx.pages.homeView.HomePage;
import com.sandboxx.pages.homeView.letters.*;
import com.sandboxx.pages.homeView.letters.purchaseLetters.CheckoutPage;
import com.sandboxx.pages.homeView.letters.purchaseLetters.SelectBundlePage;
import com.sandboxx.pages.profileView.ProfilePage;
import com.sandboxx.pages.profileView.addressBook.AddressBookTab;
import com.sandboxx.pages.profileView.addressBook.bases.LacklandBase;
import com.sandboxx.pages.profileView.addressBook.currentContact.CurrentContactPage;
import com.sandboxx.pages.profileView.addressBook.currentContact.EditContactPage;
import com.sandboxx.pages.profileView.addressBook.newContact.AddressReviewPage;
import com.sandboxx.pages.profileView.addressBook.newContact.MailingAddressPage;
import com.sandboxx.pages.profileView.addressBook.newContact.RecipientBaseAddressPage;
import com.sandboxx.pages.profileView.addressBook.newContact.SelectBasePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.HashMap;

public class AddressBookViaLetterComposeTest extends AndroidBaseTest {
    @Test(priority = 1, groups = "Address Book")
    public void purchaseBundle_20Letters() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        TestUtil.loginWithPassword(testData.email, testData.password);

        HomePage homePage = new HomePage();
        homePage.navigateToProfile();

        ProfilePage profilePage = new ProfilePage();
        int initialLettersCount = Integer.parseInt(profilePage.lettersCount.getText());
        String finalExpectedLettersCount = String.valueOf(initialLettersCount + 20);

        SelectBundlePage selectBundlePage = profilePage.purchaseLetters();
        CheckoutPage checkoutPage =  selectBundlePage.select20Letters();

        Assert.assertTrue(checkoutPage.supportCommanderLabel.isDisplayed());
        Assert.assertEquals(checkoutPage.supportCommanderPrice.getText(),"$64.99");
        Assert.assertTrue(checkoutPage.savingsLabel.isDisplayed());
        Assert.assertEquals(checkoutPage.savingsAmount.getText(),"-$10");
        Assert.assertTrue(checkoutPage.taxLabel.isDisplayed());
        Assert.assertEquals(checkoutPage.taxPrice.getText(),"$0.00");
        Assert.assertTrue(checkoutPage.totalLabel.isDisplayed());
        Assert.assertEquals(checkoutPage.totalPrice.getText(),"$54.99");

        checkoutPage.tapPurchase();
        Assert.assertEquals(checkoutPage.successPurchaseMsg.getText(),"20 letters have been added to your account");

        ProfilePage profilePage1 = checkoutPage.continueWithPurchase();
        Assert.assertEquals(profilePage1.lettersCount.getText(), finalExpectedLettersCount);
    }

    @Test(priority = 2, groups = "Address Book")
    public void sendLetterWithNewContact() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        String type = "MARINES";
        String rank = "RCT";
        String contactRank = type + " " + rank;
        String contactFirstName = "Freddy";
        String contactLastName = "Ramos";
        String contactFullName = contactFirstName + " " + contactLastName;
        String contactBase = "GREAT LAKES";
        String contactShip = "ship5";
        String contactSeps = "male";
        String contactPlatoon = "1020";

        TestUtil.loginWithPassword(testData.email, testData.password);

        HomePage homePage = new HomePage();
        homePage.navigateToProfile();
        ProfilePage profilePage = new ProfilePage();

        int initialLettersCount = Integer.parseInt(profilePage.lettersCount.getText());
        String finalExpectedLettersCount = String.valueOf(initialLettersCount -1);

        profilePage.sendLetter();
        RecipientPage recipientPage = new RecipientPage();
        MailingAddressPage mailingAddressPage = recipientPage.tapNewContact();
        mailingAddressPage.submitMailingAddress(contactRank,contactFirstName,contactLastName);
        SelectBasePage selectBasePage = new SelectBasePage();
        selectBasePage.selectBase(contactBase);
        RecipientBaseAddressPage recipientBaseAddressPage = new RecipientBaseAddressPage();
        recipientBaseAddressPage.selectShip(contactShip);
        recipientBaseAddressPage.selectSeps(contactSeps);
        AddressReviewPage addressReviewPage = new AddressReviewPage();

        Assert.assertEquals(addressReviewPage.getRankName(),contactFullName);
        Assert.assertEquals(addressReviewPage.getBaseInfo(),"SHIP 5 SEPS MALE");
        Assert.assertEquals(addressReviewPage.getAddress(),"3610 ILLINOIS STREET");
        Assert.assertEquals(addressReviewPage.getCityStateZip(),"GREAT LAKES, IL 60088");
        Thread.sleep(3000);
        addressReviewPage.confirmAddress();

        Thread.sleep(3000);
        RecipientPage recipientPage2 = new RecipientPage();
        Thread.sleep(3000);
        recipientPage2.tapAddressBookTab();
        CurrentContactPage currentContactPage = recipientPage.selectContact(contactFullName);
        Assert.assertEquals(currentContactPage.contactInitials.getText(),contactFirstName.substring(0,1)+contactLastName.charAt(0));
        Assert.assertEquals(currentContactPage.contactName.getText(), contactFullName);
        Assert.assertEquals(currentContactPage.addressLine1.getText(), "SHIP 5 SEPS MALE");
        currentContactPage.tapNext();

        ComposeLetterPage composeLetterPage = new ComposeLetterPage();
        composeLetterPage.enterMessage("This is an automated test: " + testName + " - " + LocalDate.now());
        PhotoUploadPage photoUploadPage = composeLetterPage.tapNext();
        LetterAddonsPage letterAddonsPage = photoUploadPage.goToNextPage();
        letterAddonsPage.tapNext();
        LetterReviewPage letterReviewPage = new LetterReviewPage();
        OrderCompletePage orderCompletePage = letterReviewPage.sendLetter();
        HomePage homePage2 = orderCompletePage.goToDashboard();

        Assert.assertEquals(homePage2.lettersCreditCount.getText(), finalExpectedLettersCount);

        // === Delete Contact ===
        homePage2.navigateToProfile();
        ProfilePage profilePage1 = new ProfilePage();
        AddressBookTab addressBookTab = profilePage1.tapAddressBook();
        CurrentContactPage contactPage = addressBookTab.selectContact(contactFullName);
        contactPage.deleteContact();

    }

    @Test(priority = 3, groups = "Address Book")
    public void sendLetterWithNewContact_AddressFormatter() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        String type = "MARINES";
        String rank = "RCT";
        String contactRank = type + " " + rank;
        String contactFirstName = "Andrew";
        String contactLastName = "Barrett";
        String contactFullName = contactFirstName + " " + contactLastName;
        String contactBase = "JBSA LACKLAND AFB";
        String squadron = "331";
        String flightNumber = "444";

        TestUtil.loginWithPassword(testData.email, testData.password);
        HomePage homePage = new HomePage();

        int initialLettersCount = Integer.parseInt(homePage.lettersCreditCount.getText());
        String finalExpectedLettersCount = String.valueOf(initialLettersCount -1);

        RecipientPage recipientPage = homePage.startWriting();
        MailingAddressPage mailingAddressPage = recipientPage.tapNewContact();
        mailingAddressPage.submitMailingAddress(contactRank,contactFirstName,contactLastName);

        SelectBasePage selectBasePage = new SelectBasePage();
        selectBasePage.selectBase(contactBase);

        LacklandBase lacklandBase = new LacklandBase();
        lacklandBase.selectSquadron(squadron);
        lacklandBase.enterFightNumber(flightNumber);
        AddressReviewPage addressReviewPage = lacklandBase.tapNext();
        // ToDo: Current step has a bug. Dorm selection should be displayed after lacklandBase.tapNext();
        Assert.assertEquals(addressReviewPage.getRankName(),contactFullName);
        Assert.assertEquals(addressReviewPage.getBaseInfo(),"331 TRS/FLT 444");
        Assert.assertEquals(addressReviewPage.getAddress(),"1320 TRUEMPER STREET");
        Assert.assertEquals(addressReviewPage.getCityStateZip(),"JBSA LACKLAND AFB, TX 78236");
        Thread.sleep(3000);
        addressReviewPage.confirmAddress();

        Thread.sleep(3000);
        RecipientPage recipientPage2 = new RecipientPage();
        Thread.sleep(3000);
        recipientPage2.tapAddressBookTab();
        CurrentContactPage currentContactPage = recipientPage.selectContact(contactFullName);
        Assert.assertEquals(currentContactPage.contactInitials.getText(),contactFirstName.substring(0,1)+contactLastName.charAt(0));
        Assert.assertEquals(currentContactPage.contactName.getText(), contactFullName);
        Assert.assertEquals(currentContactPage.addressLine1.getText(), "331 TRS/FLT 444");
        currentContactPage.tapNext();

        ComposeLetterPage composeLetterPage = new ComposeLetterPage();
        composeLetterPage.enterMessage("This is an automated test: " + testName + " - " + LocalDate.now());
        PhotoUploadPage photoUploadPage = composeLetterPage.tapNext();
        LetterAddonsPage letterAddonsPage = photoUploadPage.goToNextPage();
        letterAddonsPage.tapNext();
        LetterReviewPage letterReviewPage = new LetterReviewPage();
        OrderCompletePage orderCompletePage = letterReviewPage.sendLetter();
        HomePage homePage2 = orderCompletePage.goToDashboard();

        Assert.assertEquals(homePage2.lettersCreditCount.getText(), finalExpectedLettersCount);

        // === Delete Contact ===
        TestUtil.deleteContactFromAddressBook(contactFullName);
//        homePage2.navigateToProfile();
//        ProfilePage profilePage1 = new ProfilePage();
//        AddressBookTab addressBookTab = profilePage1.tapAddressBook();
//        CurrentContactPage contactPage = addressBookTab.selectContact(contactFullName);
//        contactPage.deleteContact();
    }

    @Test(priority = 4, groups = "Address Book")
    public void sendLetterWithEditedContact() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        Recipient contact = new Recipient("Renee","Aguirre");
        Address contactAddress1 = new Address("405 Lexington","456","Freeport","CO","30235");
        Address contactAddress2 = new Address("105 North st","550","Greenville","AL","55066");

        TestUtil.loginWithPassword(testData.email, testData.password);
        HomePage homePage = new HomePage();

        int initialLettersCount = Integer.parseInt(homePage.lettersCreditCount.getText());
        String finalExpectedLettersCount = String.valueOf(initialLettersCount -1);

        RecipientPage recipientPage = homePage.startWriting();
        recipientPage.tapAddressBookTab();
        CurrentContactPage currentContactPage = recipientPage.selectContact(contact.getEditedFullName());
        EditContactPage editContact = currentContactPage.editContact();

        Address newAddress;
        if(editContact.contactAddress1Input.getText().equals(contactAddress1.getAddress())){
            newAddress = contactAddress2;
        }
        else {
            newAddress = contactAddress1;
        }

        editContact.editAddress(newAddress);
        Assert.assertEquals(currentContactPage.addressLine1.getText(),newAddress.getAddress());
        Assert.assertEquals(currentContactPage.addressLine2.getText(),newAddress.getAddress2());
        Assert.assertEquals(currentContactPage.addressCity.getText(),newAddress.getCity());
        Assert.assertEquals(currentContactPage.addressState.getText(),newAddress.getState());
        Assert.assertEquals(currentContactPage.addressZip.getText(),newAddress.getZipCode());

        currentContactPage.tapNext();

        ComposeLetterPage composeLetterPage = new ComposeLetterPage();
        composeLetterPage.enterMessage("This is an automated test: " + testName + " - " + LocalDate.now());
        PhotoUploadPage photoUploadPage = composeLetterPage.tapNext();
        LetterAddonsPage letterAddonsPage = photoUploadPage.goToNextPage();
        letterAddonsPage.tapNext();
        LetterReviewPage letterReviewPage = new LetterReviewPage();
        OrderCompletePage orderCompletePage = letterReviewPage.sendLetter();
        HomePage homePage2 = orderCompletePage.goToDashboard();

        Assert.assertEquals(homePage2.lettersCreditCount.getText(), finalExpectedLettersCount);
    }

    @Test(priority = 5, groups = "Address Book")
    public void sendLetter_DeleteContact() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        Recipient contact = new Recipient("Jeff","Jordan");
        Address contactAddress = new Address("205 Denton st","111","Lincoln","IN","22005");

        TestUtil.loginWithPassword(testData.email, testData.password);
        HomePage homePage = new HomePage();

        RecipientPage recipientPage = homePage.startWriting();
        MailingAddressPage mailingAddressPage = recipientPage.tapNewContact();
        mailingAddressPage.submitCustomMailingAddress(contact.getFirstName(),contact.getLastName(),contactAddress);
        Thread.sleep(3000);
        RecipientPage recipientPage2 = new RecipientPage();
        Thread.sleep(3000);
        recipientPage2.tapAddressBookTab();
        CurrentContactPage currentContactPage = recipientPage.selectContact(contact.getFullName());
        currentContactPage.deleteContactButton.click();
        Assert.assertTrue(currentContactPage.deleteModalHeader.isDisplayed());
        Assert.assertTrue(currentContactPage.deleteModalSubtitle.isDisplayed());
        Assert.assertTrue(currentContactPage.deleteModalSubtitle.getText().contains(contact.getFullName()));
        Assert.assertTrue(currentContactPage.cancelButton.isDisplayed());

        currentContactPage.deleteButton.click();
        Thread.sleep(3000);
        RecipientPage recipientPage3 = new RecipientPage();

        recipientPage3.backButton.click();
        Thread.sleep(3000);
        homePage.startWriting();
        Thread.sleep(3000);
        recipientPage3.tapAddressBookTab();
        Assert.assertFalse(recipientPage3.isContactCardDisplayed(contact.getFullName()));
    }

    @Test(priority = 6, groups = "Address Book")
    public void addAutoconnectedContact_SendLetter_ValidAddress() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);
        HashMap<String, String> recruitData = TestUtil.getTestData( testData.userData);
        String recruitFirstName = recruitData.get("ContactFirst");
        String recruitLastName = recruitData.get("ContactLast");

        Recipient contact = new Recipient(recruitFirstName,recruitLastName);

        TestUtil.loginWithPassword(testData.email, testData.password);
        HomePage homePage = new HomePage();

        int initialLettersCount = Integer.parseInt(homePage.lettersCreditCount.getText());
        String finalExpectedLettersCount = String.valueOf(initialLettersCount -1);

        RecipientPage recipientPage = homePage.startWriting();
        recipientPage.tapAddressBookTab();

        CurrentContactPage currentContactPage = recipientPage.selectContact(contact.getFullName());
        Assert.assertTrue(currentContactPage.addContactIcon.isDisplayed());
        Assert.assertTrue(currentContactPage.autoConnectedIcon.isDisplayed());
        Assert.assertEquals(currentContactPage.autoConnectedExplanation.getText(),currentContactPage.getAutoConnectedExplanationText(contact.getFullName()));
        //currentContactPage.addAsContact();
        MailingAddressPage mailingAddressPage = currentContactPage.addAsContact();
        mailingAddressPage.tapNext();
        currentContactPage.tapNext();

        ComposeLetterPage composeLetterPage = new ComposeLetterPage();
        composeLetterPage.enterMessage("This is an automated test: " + testName + " - " + LocalDate.now());
        PhotoUploadPage photoUploadPage = composeLetterPage.tapNext();
        LetterAddonsPage letterAddonsPage = photoUploadPage.goToNextPage();
        letterAddonsPage.tapNext();
        LetterReviewPage letterReviewPage = new LetterReviewPage();
        OrderCompletePage orderCompletePage = letterReviewPage.sendLetter();
        HomePage homePage2 = orderCompletePage.goToDashboard();

        Assert.assertEquals(homePage2.lettersCreditCount.getText(), finalExpectedLettersCount);

        // ==== Delete Contact ====
        TestUtil.deleteConnectedContactFromAddressBook(contact.getFullName());
    }

    @Test(priority = 7, groups = "Address Book")
    public void sendLetterToConnection_Fail_InvalidAddress() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);
        HashMap<String, String> recruitData = TestUtil.getTestData( testData.userData);
        String recruitFirstName = recruitData.get("ContactFirst");
        String recruitLastName = recruitData.get("ContactLast");

        Recipient contact = new Recipient(recruitFirstName,recruitLastName);

        TestUtil.loginWithPassword(testData.email, testData.password);
        HomePage homePage = new HomePage();

        RecipientPage recipientPage = homePage.startWriting();
        recipientPage.tapAddressBookTab();

        CurrentContactPage currentContactPage = recipientPage.selectContact(contact.getFullName());
        Assert.assertTrue(currentContactPage.addContactIcon.isDisplayed());
        Assert.assertTrue(currentContactPage.autoConnectedIcon.isDisplayed());
        Assert.assertEquals(currentContactPage.autoConnectedExplanation.getText(),
                currentContactPage.getAutoConnectedExplanationText(contact.getFullName()));

        currentContactPage.tapNext();
        Assert.assertTrue(currentContactPage.invalidAddressAlert.isDisplayed());
    }

    @Test(priority = 8, groups = "Address Book")
    public void sendLetterToNotConnectedRecipient() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);
        HashMap<String, String> recruitData = TestUtil.getTestData( testData.userData);
        String recruitFirstName = recruitData.get("ContactFirst");
        String recruitLastName = recruitData.get("ContactLast");

        Recipient contact = new Recipient(recruitFirstName,recruitLastName);

        TestUtil.loginWithPassword(testData.email, testData.password);
        HomePage homePage = new HomePage();

        int initialLettersCount = Integer.parseInt(homePage.lettersCreditCount.getText());
        String finalExpectedLettersCount = String.valueOf(initialLettersCount -1);

        RecipientPage recipientPage = homePage.startWriting();
        recipientPage.tapAddressBookTab();

        CurrentContactPage currentContactPage = recipientPage.selectContact(contact.getFullName());

        currentContactPage.tapNext();

        ComposeLetterPage composeLetterPage = new ComposeLetterPage();
        composeLetterPage.enterMessage("This is an automated test: " + testName + " - " + LocalDate.now());
        PhotoUploadPage photoUploadPage = composeLetterPage.tapNext();
        LetterAddonsPage letterAddonsPage = photoUploadPage.goToNextPage();
        letterAddonsPage.tapNext();
        LetterReviewPage letterReviewPage = new LetterReviewPage();
        OrderCompletePage orderCompletePage = letterReviewPage.sendLetter();
        HomePage homePage2 = orderCompletePage.goToDashboard();

        Assert.assertEquals(homePage2.lettersCreditCount.getText(), finalExpectedLettersCount);
    }
}
