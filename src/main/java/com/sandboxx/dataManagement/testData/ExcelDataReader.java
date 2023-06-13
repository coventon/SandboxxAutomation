package com.sandboxx.dataManagement.testData;

import com.sandboxx.dataManagement.testDataModels.TestDataModel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ExcelDataReader {

    //static String filePath = "/Users/Roman_Nejouta/Documents/test_data1.xlsx";
   static String filePath = System.getProperty("user.dir")+ "/src/main/java/com/sandboxx/dataManagement/testData/test_data.xlsx";



    public static void getTestData1(){
        System.out.println("Start getting data from .numbers");
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(filePath))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                System.out.println("Entry: "+entry);
                if (entry.getName().equals("xl/workbook.xml")) {
                    System.out.println("XML file found");
                    Workbook workbook = new XSSFWorkbook(zipInputStream);
                    System.out.println("Workbook: "+workbook);
                    Sheet sheet = workbook.getSheetAt(0); // Assuming you want to read the first sheet
                    System.out.println("sheet: "+sheet);


                    for (Row row : sheet) {
                        for (Cell cell : row) {
                            switch (cell.getCellType()) {
                                case STRING:
                                    System.out.print(cell.getStringCellValue() + "\t");
                                    break;
                                case NUMERIC:
                                    System.out.print(cell.getNumericCellValue() + "\t");
                                    break;
                                case BOOLEAN:
                                    System.out.print(cell.getBooleanCellValue() + "\t");
                                    break;
                                case BLANK:
                                    System.out.print("[BLANK]\t");
                                    break;
                                default:
                                    System.out.print("[UNKNOWN]\t");
                                    break;
                            }
                        }
                        System.out.println(">>> End of row..."); // Move to the next line for the next row
                    }
                    System.out.println("Closing workbook");
                    workbook.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readData() throws IOException {
        try(FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis)){

            Sheet sheet = workbook.getSheetAt(0); // Assuming we read first sheet
            for(Row row: sheet){
                for(Cell cell:row){
                    switch (cell.getCellType()){
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                        case NUMERIC:
                            System.out.print(cell.getStringCellValue() + "\t");
                        case BOOLEAN:
                            System.out.print(cell.getStringCellValue() + "\t");
                        case BLANK:
                            System.out.print("[blank]\t");
                        default:
                            System.out.print("UNKNOWN\t");
                    }
                }
                System.out.println();// Move to next line
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void getRowData(String testCaseId){
        //String value="Test1";

        try(FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis)){

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row:sheet){
                Cell firstCell = row.getCell(0);

                if(firstCell != null && firstCell.getCellType() == CellType.STRING){
                    String cellValue = firstCell.getStringCellValue();
                    if(cellValue.equals(testCaseId)){
                        System.out.println("Found row by value: "+testCaseId);
                        for (Cell cell:row){
                            System.out.print(cell.getStringCellValue()+"\t");
                        }
                        System.out.println();
                        break;
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static TestDataModel getTestDataByTestId(String testCaseId){
        //String value="Test1";
        TestDataModel testData = null;
        try(FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis)){

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row:sheet){
                Cell firstCell = row.getCell(0);

                if(firstCell != null && firstCell.getCellType() == CellType.STRING){
                    String cellValue = firstCell.getStringCellValue();
                    if(cellValue.equals(testCaseId)){
                        System.out.println("Found row by value: "+testCaseId);
//                        for (Cell cell:row){
//                            System.out.print(cell.getStringCellValue()+"\t");
//                        }
//                        System.out.println();
                        String testId = row.getCell(0).getStringCellValue();
                        String username = row.getCell(1).getStringCellValue();
                        String password = row.getCell(2).getStringCellValue();
                        String email = row.getCell(3).getStringCellValue();
                        String firstName = row.getCell(4).getStringCellValue();
                        String lastName = row.getCell(5).getStringCellValue();
                        String phone = row.getCell(6).getStringCellValue();
                        String userData = row.getCell(7).getStringCellValue();

                        testData = new TestDataModel(testId,username,password,email,firstName,lastName,phone,userData);
                        break;
                    }
                }
                else if(firstCell == null || firstCell.getCellType() == CellType.BLANK){
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return testData;
    }

    public static Map<String, String> convertTestData(String testData){
        Map<String, String> result = new HashMap<>();
        String [] pairs = testData.split(",");
        for(String pair:pairs){
            String [] keyValue = pair.split(":");

            String key = keyValue[0];
            String value = keyValue[1];
            result.put(key,value);
        }
        return result;
    }


    public static void main(String [] args) throws IOException {
        //getTestData();
        //readData();
        //getRowData("Test2");
        TestDataModel testData =  getTestDataByTestId("Test1");
        System.out.println(">>>>> Printing test data >>>>>");
        System.out.println("Test ID:"+testData.testID);
        System.out.println("Test username:"+testData.username);
        System.out.println("Test password:"+testData.password);
        System.out.println("Test user data:"+testData.userData);

    }
}
