package com.sandboxx.framework.utils;

import com.sandboxx.framework.base.AppDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

public class TestUtil {

    public static String takeScreenshot() throws IOException, InterruptedException {
        Thread.sleep(300);
        System.out.println(">>> Starting Screen Capture.");
        File srcFile = ((TakesScreenshot) AppDriver.getDriver()).getScreenshotAs(OutputType.FILE);
        String fileNameID = UUID.randomUUID().toString(); // Unique name - UUID
        String fileName = String.valueOf( System.currentTimeMillis()); // Unique name - Data in milliseconds
        String fullPath = System.getenv("HOME")+"/TestScreenshots/"+fileNameID+".png";
        try{
            FileUtils.copyFile(srcFile,new File(fullPath));
            System.out.println("Screenshot Saved Successfully to: "+fullPath);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return fullPath;
    }

//    public static String takeScreenshotAsBase64_2() throws IOException, InterruptedException {
//        Thread.sleep(300);
//        TakesScreenshot takesScreenshot = (TakesScreenshot) AppDriver.getDriver();
//        String base64code = takesScreenshot.getScreenshotAs(OutputType.BASE64);
//        byte[] byteArr = Base64.getDecoder().decode(base64code);
//        String fileName = String.valueOf( System.currentTimeMillis()); // Unique name - Data in milliseconds
//        //String fullPath = System.getenv("HOME")+"/TestScreenshots/"+fileName+".png";
//        String fullPath = System.getProperty("user.dir")+"/testOutput/screenshots/"+fileName+".png";
//        File destFile = new File(fullPath);
//        FileOutputStream fos = new FileOutputStream(destFile);
//        fos.write(byteArr);
//        fos.close();
//        System.out.println("Screenshot Saved Successfully.");
//
//        return fullPath;
//    }

    public static String takeScreenshotAsBase64() throws IOException, InterruptedException {
        Thread.sleep(300);
        System.out.println(">>> Starting Screen Capture.");

        TakesScreenshot takesScreenshot = (TakesScreenshot) AppDriver.getDriver();
        byte [] screenshotBytes = takesScreenshot.getScreenshotAs(OutputType.BYTES);
        String base64Image = Base64.getEncoder().encodeToString(screenshotBytes);
        System.out.println("Screenshot Saved Successfully.");
        return base64Image;
    }
}
