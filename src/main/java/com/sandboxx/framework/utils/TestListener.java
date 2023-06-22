package com.sandboxx.framework.utils;

import com.sandboxx.framework.base.AppDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.UUID;

public class TestListener implements ITestListener{
    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println(">>> FAILED Test");
        System.out.println("Method failed: "+ result.getName());
        try {
            TestUtil.takeScreenshot();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }

//    public void takeScreenshot() throws IOException, InterruptedException {
//        Thread.sleep(300);
//        File srcFile = ((TakesScreenshot) AppDriver.getDriver()).getScreenshotAs(OutputType.FILE);
//        String fileNameID = UUID.randomUUID().toString(); // Unique name - UUID
//        String fileName = String.valueOf( System.currentTimeMillis()); // Unique name - Data in milliseconds
//        String fullPath = System.getenv("HOME")+"/TestScreenshots/"+fileNameID+".jpg";
//        try{
//            FileUtils.copyFile(srcFile,new File(fullPath));
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//    }
}
