package com.sandboxx.Android;

import com.sandboxx.dataManagement.testData.DataProcessor;
import com.sandboxx.dataManagement.testData.ExcelDataReader;
import com.sandboxx.dataManagement.testDataModels.TestDataModel;
import com.sandboxx.framework.utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.ObjLongConsumer;

public class AndroidTest1 extends AndroidBaseTest {


    @Test (priority = 1,groups = "Landing Page")
    public void landingPageTest1(String username,String password,String firstName, String lastName){
        System.out.printf("Test name:  %s\n",testName);
        System.out.println("Username: " + username + " | Password: " + password);
        System.out.println("First Name: " + firstName + " | Last Name: " + lastName);
    }

    @Test (priority = 2,groups = "Landing Page")
    public void landingPageTest2(){
        System.out.printf("Test name:  %s\n",testName);
        Assert.fail("Intentionally Failed test...");
    }
    @Test (priority = 3,groups = "Login")
    public void landingPageTest3(){
        System.out.printf("Test name:  %s\n",testName);
    }
    @Test(priority = 4,groups = "Login",dependsOnMethods = "landingPageTest2")// invocationCount = 3
    public void homePageTest(){
        System.out.printf("Test name: %s. Depends on landingPageTest3\n",testName);
    }

    @Test(priority = 5,groups = "Registration",invocationCount = 2,expectedExceptions = NumberFormatException.class)
    public void homePageTest2(){
        System.out.println("Test name: "+ testName + ". test with invocations count =2, Expected exceptions");
    }

//    @DataProvider
//    public Iterator<Object[]> getTestData(){
//        ArrayList<Object[]> testData = DataProcessor.getDataFromExcel();
//        return testData.iterator();
//    }
    @Test(priority = 6,groups = "Registration", dataProvider = "getTestData",dataProviderClass = DataProcessor.class)
    public void TestDataProvider(String testID, String username, String password, String email,String firstName, String lastName, String phone){
        System.out.println("<<<< Printing Data from Data provider >>>>>");
        System.out.printf("Test id: %s | Username: %s | password: %s | email: %s |  First Name: %s | Last name: %s | Phone: %s \n"
        ,testID,username,password,email,firstName,lastName,phone);
    }

    @Test(priority = 7,groups = "Letters")//retryAnalyzer = RetryAnalyzer.class - Retry logic on the test level
    public void Test1(){
        System.out.println("Test name: "+ testName + ". test with invocations count =2, Expected exceptions");
        TestDataModel testData = ExcelDataReader.getTestDataByTestId(testName);
        System.out.println("Test ID:"+testData.testID);
        System.out.println("Test username:"+testData.username);
        System.out.println("Test password:"+testData.password);
        System.out.println("Test user data:"+testData.userData);
    }

    @Test(priority = 8,groups = "Letters",enabled = false)//retryAnalyzer = RetryAnalyzer.class - will call retryAnalyser retry method
    public void testIgnoredTest(){
        System.out.println("Test should be ignored");
    }
}
