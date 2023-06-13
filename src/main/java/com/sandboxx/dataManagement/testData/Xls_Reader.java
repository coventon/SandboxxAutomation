package com.sandboxx.dataManagement.testData;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;

public class Xls_Reader {
    public String path;
    public FileInputStream fis = null;
    public FileOutputStream fileOut = null;
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private XSSFRow row = null;
    private XSSFCell cell = null;

    public Xls_Reader(String path) {
        this.path = path;
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // #Return the row count in a sheet
    public int getRowCount(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1) {
            return 0;
        } else {
            sheet = workbook.getSheetAt(index);
            int number = sheet.getLastRowNum() + 1;
            return number;
        }
    }

    // #Returns data from a cell
    public String getCellData(String sheetName, String colName, int rowNum) {
        try {
            if (rowNum <= 0) {
                return "";
            }
            int index = workbook.getSheetIndex(sheetName);
            int colNum = -1;
            if (index == -1) {
                return "";
            }

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                System.out.println("Sheet Header: "+row.getCell(i).getStringCellValue().trim());
                if (row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
                    colNum = i;
                }
            }
            if (colNum == -1) {
                return "";
            }
            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null) {
                return "";
            }

            cell = row.getCell(colNum);

            if (cell == null) {
                return "";
            }

            System.out.println(cell.getCellType());
            if (cell.getCellType() == CellType.STRING) {
                return cell.getStringCellValue();
            } else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
                String cellText = String.valueOf(cell.getNumericCellValue());
                if (DateUtil.isCellDateFormatted(cell)) {
                    // format in form of M/D/YY
                    double d = cell.getNumericCellValue();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(DateUtil.getJavaDate(d));
                    cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                    cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
                            cal.get(Calendar.MONTH) + 1 + "/" +
                            cellText;
                    System.out.println(cellText);
                }
                return cellText;
            } else if (cell.getCellType() == CellType.BLANK) {
                return "";
            }
            else{
                return String.valueOf(cell.getBooleanCellValue());
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return "row " + rowNum + " or column " + colName + " does not exist in xls";
        }
    }

    // #Return data from the cell
    public String getCellData(String sheetName, int colNum, int rowNum){
        try{
            if(rowNum <= 0){
                return"";
            }

            int index = workbook.getSheetIndex(sheetName);

            if(index == -1){
                return "";
            }

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum-1);
            if(row==null){
                return"";
            }
            cell = row.getCell(colNum);
            if(cell == null){
                return "";
            }

            if(cell.getCellType() ==CellType.STRING){
                return cell.getStringCellValue();
            }
            else if(cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA){

                String cellText = String.valueOf(cell.getNumericCellValue());
                if(DateUtil.isCellDateFormatted(cell)){
                    // format if form of M/D/YY
                    double d = cell.getNumericCellValue();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(DateUtil.getJavaDate(d));
                    cellText = cal.get(Calendar.MONTH) +1 +"/" +
                            cal.get(Calendar.DAY_OF_MONTH) + "/" +
                            cellText;

                    System.out.println(cellText);
                }

                return cellText;
            }
            else if(cell.getCellType()== CellType.BLANK){
                return "";
            }
            else{
                return String.valueOf(cell.getBooleanCellValue());
            }
        }
        catch(Exception e){

            e.printStackTrace();
            return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
        }
    }

    // return true if data is set successfuly else false
    public boolean setCellData(String sheetName, String colName, int rowNum, String data){
        try{
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);

            if(rowNum <=0){
                return false;
            }

            int index = workbook.getSheetIndex(sheetName);
            int colNum = -1;
            if(index == -1){
                return false;
            }

            sheet = workbook.getSheetAt(index);

            row = sheet.getRow(0);
            for(int i=0;i<row.getLastCellNum();i++){
                System.out.println(row.getCell(i).getStringCellValue().trim());
                if(row.getCell(i).getStringCellValue().trim().equals(colName)){
                    colNum = i;
                }
                if(colNum == -1){
                    return false;
                }
               sheet.autoSizeColumn(colNum);
                row = sheet.getRow(rowNum-1);
                cell = row.getCell(colNum);
                if(cell == null){
                    cell = row.createCell(colNum);
                }

                cell.setCellValue(data);
                fileOut = new FileOutputStream(path);
                workbook.write(fileOut);
                fileOut.close();
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // #Return true if data set successfully else false
    public boolean setCellData(String sheetName, String colName, int rowNum, String data, String url){
        System.out.println(">>> settCellData <<<");
        try{
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);

            if(rowNum <= 0){
                return false;
            }

            int index = workbook.getSheetIndex(sheetName);
            int colNum = -1;
            if(index == -1){
                return false;
            }

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            for(int i=0; i < row.getLastCellNum();i++){
                System.out.println(row.getCell(i).getStringCellValue().trim());
                if(row.getCell(i).getStringCellValue().equalsIgnoreCase(colName)){
                    colNum = i;
                }

                if(colNum == -1){
                    return false;
                }

                sheet.autoSizeColumn(colNum);
                row = sheet.getRow(rowNum -1);
                if(row == null){
                    row = sheet.createRow(colNum);
                }
                cell = row.getCell(colNum);
                if(cell == null){
                    cell = row.createCell(colNum);
                }

                cell.setCellValue(data);
                XSSFCreationHelper createHelper = workbook.getCreationHelper();

                // cell style for hyperlinks
                // by default hyperlinks are blue and underlined
                CellStyle hlink_style = workbook.createCellStyle();
                XSSFFont hlink_font = workbook.createFont();
                hlink_font.setUnderline(XSSFFont.U_SINGLE);
                hlink_font.setColor(IndexedColors.BLUE.getIndex());
                hlink_style.setFont(hlink_font);

                XSSFHyperlink link = createHelper.createHyperlink(HyperlinkType.FILE);
                link.setAddress(url);
                cell.setHyperlink(link);
                cell.setCellStyle(hlink_style);

                fileOut = new FileOutputStream(path);
                workbook.write(fileOut);

                fileOut.close();
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // #Reurn true if sheet is created successfully else false
    public boolean addSheet(String sheetName){
        FileOutputStream fileOut;
        try{
            workbook.createSheet(sheetName);
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // #Returns true if sheet is remove successfully else false if sheet does not exist
    public boolean removeSheet(String sheetName){
        int index = workbook.getSheetIndex(sheetName);
        if(index == -1){
            return false;
        }
        FileOutputStream fileOut;
        try{
            workbook.removeSheetAt(index);
            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /// #Returns true if column created successfully else false
    public boolean addColumn(String sheetName, String colName){
        System.out.println("=============== Add Column =================");

        try{
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            int index = workbook.getSheetIndex(sheetName);
            if(index == -1){
                return false;
            }

            XSSFCellStyle style =  workbook.createCellStyle();
            style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_40_PERCENT.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);
            if(row == null){
                row = sheet.createRow(0);
            }
            if(row.getLastCellNum() == -1){
                cell = row.createCell(0);
            }
            else{
                cell = row.createCell(row.getLastCellNum());
            }
            cell.setCellValue(colName);
            cell.setCellStyle(style);

            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();

        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // #Removes column and all content
    public boolean removeColun(String sheetName, int colNum){
        try{
            if(! isSheetExist(sheetName)){
                return false;
            }
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_40_PERCENT.getIndex());
            XSSFCreationHelper createHelper = workbook.getCreationHelper();
            style.setFillPattern(FillPatternType.NO_FILL);

            for(int i=0;i<getRowCount(sheetName); i++){
                row =sheet.getRow(i);
                if(row != null){
                    cell = row.getCell(colNum);
                    if(cell != null){
                        cell.setCellStyle(style);
                        row.removeCell(cell);
                    }
                }
            }

            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // #Find if sheet exist
    public boolean isSheetExist(String sheetName){
        int index = workbook.getSheetIndex(sheetName);
        if(index == -1){
            index = workbook.getSheetIndex(sheetName.toUpperCase());
            if(index == -1){
                return false;
            }
            else{
                return true;
            }
        }
        else {
            return true;
        }
    }

    // #Returns number of columns in a sheet
    public int  getColumnCount(String sheetName){
        // check if sheet eixists
        if(!isSheetExist(sheetName)){
            return -1;
        }
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);

        if(row == null){
            return -1;
        }
        return row.getLastCellNum();
    }

    public boolean addHyperLink(String sheetName,String screenShotColName,String testCaseName, int index, String url, String message){
        System.out.println("============ Adding Hyperlink ==============");

        url = url.replace('\\','/');
        if(!isSheetExist(sheetName)){
            return false;
        }

        sheet = workbook.getSheet(sheetName);
        for(int i=0;i<=getRowCount(sheetName);i++){
            if(getCellData(sheetName,0,i).equalsIgnoreCase(testCaseName)){
                System.out.println("i + index: "+ (i + index));
                setCellData(sheetName,screenShotColName, i+index,message,url);
                break;
            }

        }
        return true;
    }

    public int getCellRowNum(String sheetName,String colName, String cellValue){

        for(int i=0;i<getRowCount(sheetName);i++){
            if(getCellData(sheetName,colName,i).equalsIgnoreCase(cellValue)){
                return i;
            }
        }
        return -1;
    }

    public static void main(String [] args){
         String filePath = System.getProperty("user.dir")+ "/src/main/java/com/sandboxx/dataManagement/testData/test_data.xlsx";

        Xls_Reader xlsReader = new Xls_Reader(filePath);
        for(int col = 0; col < xlsReader.getRowCount("Sheet 1");col++){
            System.out.println("Cell data at col " + col +" : "+xlsReader.getCellData("Sheet 1", col, 3));
        }
    }
}
