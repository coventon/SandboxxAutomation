package com.sandboxx.Android.addressBookTests;

import com.sandboxx.Android.AndroidBaseTest;
import com.sandboxx.dataManagement.testData.ExcelDataReader;
import com.sandboxx.dataManagement.testData.userModels.Address;
import com.sandboxx.dataManagement.testData.userModels.Recipient;
import com.sandboxx.dataManagement.testDataModels.TestDataModel;
import com.sandboxx.framework.utils.TestUtil;
import com.sandboxx.pages.BasePage;
import com.sandboxx.pages.homeView.HomePage;
import com.sandboxx.pages.homeView.letters.RecipientPage;
import com.sandboxx.pages.homeView.letters.purchaseLetters.CheckoutPage;
import com.sandboxx.pages.homeView.letters.purchaseLetters.SelectBundlePage;
import com.sandboxx.pages.musterView.recruiter.MusterPage;
import com.sandboxx.pages.profileView.ProfilePage;
import com.sandboxx.pages.profileView.addressBook.AddressBookTab;
import com.sandboxx.pages.profileView.addressBook.currentContact.CurrentContactPage;
import com.sandboxx.pages.profileView.addressBook.newContact.AddressReviewPage;
import com.sandboxx.pages.profileView.addressBook.newContact.MailingAddressPage;
import com.sandboxx.pages.profileView.addressBook.newContact.RecipientBaseAddressPage;
import com.sandboxx.pages.profileView.addressBook.newContact.SelectBasePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class AddressBookTest extends AndroidBaseTest {
    @Test(priority = 1, groups = "Address Book")
    public void addressBook_ViewContactsAndConnections() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        Recipient contact1 = new Recipient("Callie","Ware");
        Recipient contact2 = new Recipient("David","Clark");

        TestUtil.loginWithPassword(testData.email, testData.password);

        HomePage homePage = new HomePage();
        homePage.navigateToProfile();

        ProfilePage profilePage = new ProfilePage();
       AddressBookTab addressBookTab = profilePage.tapAddressBook();
       Assert.assertTrue(addressBookTab.isContactDisplayed(contact1.getFullName()));
       Assert.assertTrue(addressBookTab.isContactDisplayed(contact2.getFullName()));
    }

    @Test(priority = 2, groups = "Address Book")
    public void createNewContactWithManualAddressEntry() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);

        String type = "MARINES";
        String rank = "RCT";
        String contactRank = type + " " + rank;
        Recipient contact = new Recipient("Helena","Barr");
        Address contactAddress = new Address("483 Maple st","389","Evan","NY","22879");

        TestUtil.loginWithPassword(testData.email, testData.password);

        HomePage homePage = new HomePage();
        homePage.navigateToProfile();

        ProfilePage profilePage = new ProfilePage();
        AddressBookTab addressBookTab =  profilePage.tapAddressBook();
        MailingAddressPage mailingAddressPage = addressBookTab.addNewContact();
        mailingAddressPage.submitMailingAddressNoBase(contactRank, contact.getFirstName(), contact.getLastName());
        mailingAddressPage.enterBaseManuallyButton.click();

        Assert.assertFalse(mailingAddressPage.nextButton.isEnabled());

        mailingAddressPage.enterAddress(contactAddress);
        Thread.sleep(2000);
        mailingAddressPage.nextButton.click();
        Thread.sleep(4000);
        CurrentContactPage currentContactPage = addressBookTab.selectContact(contact.getFullName());

        Assert.assertEquals(currentContactPage.actionBarName.getText(),contact.getFullName());
        Assert.assertEquals(currentContactPage.contactInitials.getText(),contact.getFirstName().charAt(0)+contact.getLastName().substring(0,1));
        Assert.assertEquals(currentContactPage.contactName.getText(),contact.getFullName());
        Assert.assertEquals(currentContactPage.addressLine1.getText(),contactAddress.getAddress());
        Assert.assertEquals(currentContactPage.addressLine2.getText(),contactAddress.getAddress2());
        Assert.assertEquals(currentContactPage.addressCity.getText(),contactAddress.getCity());
        Assert.assertEquals(currentContactPage.addressState.getText(),contactAddress.getState());
        Assert.assertEquals(currentContactPage.addressZip.getText(),contactAddress.getZipCode());


        // === Delete Contact ===

        currentContactPage.deleteContactButton.click();
        Assert.assertTrue(currentContactPage.deleteModalHeader.isDisplayed());
        Assert.assertTrue(currentContactPage.deleteModalSubtitle.isDisplayed());
        Assert.assertTrue(currentContactPage.deleteModalSubtitle.getText().contains(contact.getFullName()));
        Assert.assertTrue(currentContactPage.cancelButton.isDisplayed());

        currentContactPage.deleteButton.click();
        addressBookTab.isAt();

    }

    @Test(priority = 3, groups = "Address Book")
    public void createNewContactWithAddressFormatter() throws InterruptedException {
        System.out.printf("Begin Test:  %s\n", testName);
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);
        HashMap<String, String> recruitData = TestUtil.getTestData( testData.userData);

        String contactFirstName = recruitData.get("ContactFirst");
        String contactLastName = recruitData.get("ContactLast");
        String type = recruitData.get("RankType");
        String rank = recruitData.get("Rank");
        String contactRank = type + " " + rank;
        String contactBase = recruitData.get("Base");;
        String contactShip = recruitData.get("Ship");
        String contactDivision = recruitData.get("Division");
        Recipient contact = new Recipient(contactFirstName,contactLastName);

        TestUtil.loginWithPassword(testData.email, testData.password);

        HomePage homePage = new HomePage();
        homePage.navigateToProfile();

        ProfilePage profilePage = new ProfilePage();
        AddressBookTab addressBookTab =  profilePage.tapAddressBook();
        MailingAddressPage mailingAddressPage = addressBookTab.addNewContact();
        mailingAddressPage.submitMailingAddress(contactRank, contact.getFirstName(), contact.getLastName());

        SelectBasePage selectBasePage = new SelectBasePage();
        selectBasePage.selectBase(contactBase);
        RecipientBaseAddressPage recipientBaseAddressPage = new RecipientBaseAddressPage();
        recipientBaseAddressPage.selectShip(contactShip);
        //recipientBaseAddressPage.selectSeps(contactSeps);// Ship 5, 17 changed from Seps to Division
        recipientBaseAddressPage.enterDivision(contactDivision);
        recipientBaseAddressPage.nextButton.click();
        AddressReviewPage addressReviewPage = new AddressReviewPage();

        Assert.assertEquals(addressReviewPage.getRankName(),contact.getFullName());
        Assert.assertEquals(addressReviewPage.getBaseInfo(),"SHIP 5 DIV " + contactDivision);
        Assert.assertEquals(addressReviewPage.getAddress(),"3610 ILLINOIS STREET");
        Assert.assertEquals(addressReviewPage.getCityStateZip(),"GREAT LAKES, IL 60088");
        Thread.sleep(3000);
        addressReviewPage.confirmAddress();

        AddressBookTab addressBookTab2 = new AddressBookTab();
        // === Delete Contact ===

        CurrentContactPage currentContactPage = addressBookTab2.selectContact(contact.getFullName());
        currentContactPage.deleteContact();

        addressBookTab.isAt();

    }
}
