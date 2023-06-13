package com.sandboxx.dataManagement.testData;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;

public class DataProcessor {

    static Xls_Reader reader;
    static protected String filePath = System.getProperty("user.dir")+ "/src/main/java/com/sandboxx/dataManagement/testData/test_data.xlsx";

    public static  ArrayList<Object[]> getDataFromExcel(){
        ArrayList<Object[]> myData = new ArrayList<>();
        try{
            reader = new Xls_Reader(filePath);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        for(int rowNum =2;rowNum <= reader.getRowCount("Sheet 1");rowNum++){

            String testCaseID = reader.getCellData("Sheet 1","TestCaseID",rowNum);
            if(testCaseID.equals("")) break;
            String username = reader.getCellData("Sheet 1","Username",rowNum);
            String password = reader.getCellData("Sheet 1","Password",rowNum);
            String email = reader.getCellData("Sheet 1","Email",rowNum);
            String firstName = reader.getCellData("Sheet 1","FirstName",rowNum);
            String lastName = reader.getCellData("Sheet 1","LastName",rowNum);
            String phone = reader.getCellData("Sheet 1","Phone",rowNum);

            Object[] obj = {testCaseID,username,password,email,firstName,lastName,phone};
            myData.add(obj);
//            System.out.println("Printing Object properties");
//            for (Object o : obj) {
//                System.out.println("Object property: "+o.toString());
//            }
        }
        return myData;
    }

    @DataProvider
    public Iterator<Object[]> getTestData(){
        ArrayList<Object[]> testData = DataProcessor.getDataFromExcel();
        return testData.iterator();
    }

    public static void main(String [] args){
        getDataFromExcel();
    }
}
