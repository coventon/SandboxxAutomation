package com.sandboxx;

import com.sandboxx.dataManagement.ConfigProcessor;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

/**
 * Unit test for simple App.
 */
public class AppTest extends BaseTest{


   @Test
    public void SampleTest(){
       System.out.println("this is sample test");
       Assert.assertEquals(ConfigProcessor.getOS(),"ios");
       Assert.assertEquals(ConfigProcessor.getDatabaseUsername(),"myuser");
       Assert.assertEquals(ConfigProcessor.getDatabasePassword(),"mypassword");
       Assert.assertEquals(ConfigProcessor.getAppPackage(),"com.sandboxx.android.dev");

   }
}
