package com.sandboxx.Android;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AndroidTest2   {

    SoftAssert softAssert = new SoftAssert();
    @Test(priority = 1,groups = "Assertion")
    public void testSoftAssert1(){
        //System.out.printf("Test name:  %s\n",testName);

        System.out.println("Start Appp");

        Assert.assertEquals(true,true);

        System.out.println("Navigate Login page");
        System.out.println("Enter username");
        System.out.println("Enter password");
        System.out.println("Click on login button");
        System.out.println("validate Home Page");
        Assert.assertEquals("str","str");
        System.out.println("Validate main header");
        System.out.println("Scroll down");

        softAssert.assertEquals(false,true,"tittle is missing"); // soft Assert

        System.out.println("Validate button displayed");

        Assert.assertEquals(true,true);

        System.out.println("Validate button2 displayed");

        softAssert.assertEquals(false,true,"Button is not displayed"); // soft Assert

        System.out.println("Validate button3 displayed");

        softAssert.assertAll();
    }

    @Test(priority = 1,groups = "Assertion")
    public void testSoftAssert2(){
        //System.out.printf("Test name:  %s\n",testName);

        System.out.println("Start Appp");

        Assert.assertEquals(true,true);

        System.out.println("Navigate Login page");
        System.out.println("Enter username");
        System.out.println("Enter password");


        softAssert.assertEquals(false,true,"tittle is missing"); // soft Assert

        System.out.println("Validate button displayed");

        Assert.assertEquals(true,true);

        System.out.println("Validate button2 displayed");

        softAssert.assertEquals(false,true,"Button is not displayed"); // soft Assert

        System.out.println("Validate button3 displayed");

        softAssert.assertAll();
    }
}
