package com.itc.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelReader {

    private String path;
    private FileInputStream fis = null;
    private FileOutputStream fileOut = null;
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private XSSFRow row = null;
    private XSSFCell cell = null;

    public ExcelReader(String path) {
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

    // Returns the row count in a sheet
    public int getRowCount(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1)
            return 0;
        else {
            sheet = workbook.getSheetAt(index);
            return sheet.getLastRowNum() + 1;
        }
    }

    // Returns data from a cell by column name and row number (1-based)
    public String getCellData(String sheetName, String colName, int rowNum) {
        try {
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);

            int colNum = -1;
            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName.trim())) {
                    colNum = i;
                    break;
                }
            }
            if (colNum == -1)
                return "";

            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";

            cell = row.getCell(colNum);
            if (cell == null)
                return "";

            return getCellValueAsString(cell);

        } catch (Exception e) {
            e.printStackTrace();
            return "row " + rowNum + " or column " + colName + " does not exist in xls";
        }
    }

    // Returns data from a cell by column index and row number (1-based)
    public String getCellData(String sheetName, int colNum, int rowNum) {
        try {
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";

            cell = row.getCell(colNum);
            if (cell == null)
                return "";

            return getCellValueAsString(cell);

        } catch (Exception e) {
            e.printStackTrace();
            return "row " + rowNum + " or column " + colNum + " does not exist in xls";
        }
    }

    private String getCellValueAsString(Cell cell) {
        DataFormatter formatter = new DataFormatter();
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    // Format date as dd/MM/yy
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(cell.getDateCellValue());
                    return String.format("%02d/%02d/%02d",
                            cal.get(Calendar.DAY_OF_MONTH),
                            cal.get(Calendar.MONTH) + 1,
                            cal.get(Calendar.YEAR) % 100);
                } else {
                    return formatter.formatCellValue(cell);
                }

            case FORMULA:
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                CellValue cellValue = evaluator.evaluate(cell);
                switch (cellValue.getCellType()) {
                    case STRING:
                        return cellValue.getStringValue();
                    case NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell)) {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(cell.getDateCellValue());
                            return String.format("%02d/%02d/%02d",
                                    cal.get(Calendar.DAY_OF_MONTH),
                                    cal.get(Calendar.MONTH) + 1,
                                    cal.get(Calendar.YEAR) % 100);
                        } else {
                            return formatter.formatRawCellContents(cellValue.getNumberValue(), -1, null);
                        }
                    case BOOLEAN:
                        return String.valueOf(cellValue.getBooleanValue());
                    case BLANK:
                        return "";
                    default:
                        return "";
                }

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case BLANK:
                return "";

            default:
                return "";
        }
    }

    // Sets data in cell by column name and row number
    public boolean setCellData(String sheetName, String colName, int rowNum, String data) {
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);

            if (rowNum <= 0)
                return false;

            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return false;

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);

            int colNum = -1;
            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName.trim())) {
                    colNum = i;
                    break;
                }
            }
            if (colNum == -1)
                return false;

            sheet.autoSizeColumn(colNum);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                row = sheet.createRow(rowNum - 1);

            cell = row.getCell(colNum);
            if (cell == null)
                cell = row.createCell(colNum);

            cell.setCellValue(data);

            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
            fis.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Sets data with hyperlink in cell by column name and row number
    public boolean setCellData(String sheetName, String colName, int rowNum, String data, String url) {
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);

            if (rowNum <= 0)
                return false;

            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return false;

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);

            int colNum = -1;
            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName.trim())) {
                    colNum = i;
                    break;
                }
            }
            if (colNum == -1)
                return false;

            sheet.autoSizeColumn(colNum);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                row = sheet.createRow(rowNum - 1);

            cell = row.getCell(colNum);
            if (cell == null)
                cell = row.createCell(colNum);

            cell.setCellValue(data);

            XSSFCreationHelper createHelper = workbook.getCreationHelper();

            CellStyle hlinkStyle = workbook.createCellStyle();
            XSSFFont hlinkFont = workbook.createFont();
            hlinkFont.setUnderline(Font.U_SINGLE);
            hlinkFont.setColor(IndexedColors.BLUE.getIndex());
            hlinkStyle.setFont(hlinkFont);

            XSSFHyperlink link = createHelper.createHyperlink(HyperlinkType.FILE);
            link.setAddress(url);
            cell.setHyperlink(link);
            cell.setCellStyle(hlinkStyle);

            fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
            fis.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Adds a new sheet
    public boolean addSheet(String sheetName) {
        try (FileOutputStream fileOut = new FileOutputStream(path)) {
            workbook.createSheet(sheetName);
            workbook.write(fileOut);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Removes a sheet
    public boolean removeSheet(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1)
            return false;

        try (FileOutputStream fileOut = new FileOutputStream(path)) {
            workbook.removeSheetAt(index);
            workbook.write(fileOut);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Adds a column to the sheet
    public boolean addColumn(String sheetName, String colName) {
        try (FileInputStream fis = new FileInputStream(path);
             FileOutputStream fileOut = new FileOutputStream(path)) {

            workbook = new XSSFWorkbook(fis);
            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return false;

            sheet = workbook.getSheetAt(index);

            XSSFCellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            row = sheet.getRow(0);
            if (row == null)
                row = sheet.createRow(0);

            int colIndex = row.getLastCellNum() == -1 ? 0 : row.getLastCellNum();
            cell = row.createCell(colIndex);

            cell.setCellValue(colName);
            cell.setCellStyle(style);

            workbook.write(fileOut);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Removes a column and all its contents
    public boolean removeColumn(String sheetName, int colNum) {
        try (FileInputStream fis = new FileInputStream(path);
             FileOutputStream fileOut = new FileOutputStream(path)) {

            if (!isSheetExist(sheetName))
                return false;

            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);

            for (int i = 0; i < getRowCount(sheetName); i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    cell = row.getCell(colNum);
                    if (cell != null) {
                        row.removeCell(cell);
                    }
                }
            }

            workbook.write(fileOut);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Checks if a sheet exists
    public boolean isSheetExist(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1) {
            index = workbook.getSheetIndex(sheetName.toUpperCase());
            return index != -1;
        } else
            return true;
    }

    // Returns number of columns in a sheet
    public int getColumnCount(String sheetName) {
        if (!isSheetExist(sheetName))
            return -1;

        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);

        if (row == null)
            return -1;

        return row.getLastCellNum();
    }

    // Adds hyperlink to a cell based on test case name and screenshot column name
    public boolean addHyperLink(String sheetName, String screenShotColName, String testCaseName, int index, String url, String message) {
        url = url.replace('\\', '/');
        if (!isSheetExist(sheetName))
            return false;

        sheet = workbook.getSheet(sheetName);

        for (int i = 2; i <= getRowCount(sheetName); i++) {
            if (getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)) {
                setCellData(sheetName, screenShotColName, i + index, message, url);
                break;
            }
        }
        return true;
    }

    // Returns the row number of a cell matching a value in a given column name
    public int getCellRowNum(String sheetName, String colName, String cellValue) {
        for (int i = 2; i <= getRowCount(sheetName); i++) {
            if (getCellData(sheetName, colName, i).equalsIgnoreCase(cellValue)) {
                return i;
            }
        }
        return -1;
    }

    // Main method for testing
    public static void main(String[] args) throws IOException {
        ExcelReader datatable = new ExcelReader("C:\\CM3.0\\app\\test\\Framework\\AutomationBvt\\src\\config\\xlfiles\\Controller.xlsx");
        for (int col = 0; col < datatable.getColumnCount("TC5"); col++) {
            System.out.println(datatable.getCellData("TC5", col, 1));
        }
    }
}
