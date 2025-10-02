package utilities;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.*;

public class Excelreader {
	public static String filepath = "src/test/resources/data/testdata.xlsx";
	public static List<Map<String, String>> getData(String sheetName) {
        List<Map<String, String>> excelData = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filepath);
        		
             Workbook workbook = new XSSFWorkbook(fis)) {
        	System.out.println("Excel file loaded: " + filepath);
            System.out.println("Available sheets:");
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                System.out.println(" - " + workbook.getSheetName(i));
                System.out.println(" The list of all column names as "+workbook.getAllNames());
            }
        	
            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);
            int colCount = headerRow.getLastCellNum();
            int rowCount = sheet.getPhysicalNumberOfRows();
            for (int r = 1; r < rowCount; r++) {
                Row currentRow = sheet.getRow(r);
                Map<String, String> rowData = new HashMap<>();
                for (int c = 0; c < colCount; c++) {
                    String columnName = headerRow.getCell(c).getStringCellValue();
                    Cell cell = currentRow.getCell(c);
                    String cellValue = (cell == null) ? "" : cell.toString().trim();
                    rowData.put(columnName, cellValue);
                }
                excelData.add(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        
	 }
    return excelData;
}
// Fetch single row by TestCaseID from a sheet
//public static Map<String, String> getRowByTestCaseId(String sheetName, String actionname) {
	public static Map<String, String> getTestDataByAction(String sheetName, String actionname) {
	    List<Map<String, String>> allData = getData(sheetName);

	    for (Map<String, String> row : allData) {
	        String action = row.get("actionname");
	        if (action != null && action.equalsIgnoreCase(actionname)) {
	            return row;
	        }
	    }

	    System.out.println("No matching actionname found for: " + actionname);
	    return new HashMap<>();
	}

}