package com.sandboxx.Android;

import com.sandboxx.framework.utils.TestListener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class ScreenshotTest extends AndroidBaseTest{

    @Test(priority = 1,groups = "Screenshots")
    public void takeScreenshotTest(){
        //Assert.assertEquals(true,false);

            System.out.printf("Test name:  %s\n",testName);
            //Assert.fail("Intentionally Failed test...");
        Assert.assertTrue(false, "--> Intentionally failed test");
    }

    @Test
    public void Test2(){
        Assert.assertTrue(false);
    }

    @Test
    public void Test3(){
        Assert.assertTrue(true);
    }
}
