package com.sandboxx.dataManagement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigProcessor {
    private static Properties properties;

    public static void loadConfig() throws FileNotFoundException {
        properties = new Properties();
        try(FileInputStream fis = new FileInputStream("src/main/resources/config.properties")){
            properties.load(fis);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String getDatabaseURL(){
        return properties.getProperty("db.url");
    }
    public static String getDatabaseUsername(){
        return properties.getProperty("db.username");
    }

    public static String getDatabasePassword() {
        return properties.getProperty("db.password");
    }

    public static String getEnvironment() {
        return properties.getProperty("environment");
    }

    public static int getTimeout() {
        return Integer.parseInt(properties.getProperty("DRIVER_IMPLICIT_TIMEOUT"));
    }

    public static String getOS(){
        return properties.getProperty("platform");
    }
    public static String getAppPackage(){
        return properties.getProperty("appPackage");
    }
    public static String getAndroidApp(){return properties.getProperty("androidApp");}
    public static String getIosApp(){return properties.getProperty("iosApp");}

    /// # Devices
    /// #IOS
    public static String getIOSDeviceType(){return properties.getProperty("iOSDeviceType");}
    public static String getIosSimulatorName(){return properties.getProperty("iOSSimulatorName");}
    public static String getIosPlatformVersion(){return properties.getProperty("iOSPlatformVersion");}
    /// #Android
    public static String getAndroidDevice(){return properties.getProperty("androidDevice");}
    public static String getEmulatorName(){return properties.getProperty("emulatorName");}
    public static String getEmulatorWithPlayName(){return properties.getProperty("emulatorPlayName");}
    public static String getAndroidRealDeviceName(){return properties.getProperty("AndroidRealDeviceName");}
    public static String getAndroidRealDevicePlatformVersion(){return properties.getProperty("AndroidRealDevicePlatformVersion");}

    // # Api
    public static String getSandboxxApiEmail(){return properties.getProperty("sandboxxApiEmail");}
    public static String getSandboxxApiPwd(){return properties.getProperty("apiPassword");}

}
