package utilities;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class Excelreader {
	 public static List<Map<String, String>> getData(String sheetName) {
	        List<Map<String, String>> data = new ArrayList<>();
	        try (InputStream fis = new FileInputStream("src/test/resources/data/TestCase.xlsx");
	             Workbook workbook = new XSSFWorkbook(fis)) {

	            Sheet sheet = workbook.getSheet(sheetName);
	            if (sheet == null) throw new RuntimeException("Sheet " + sheetName + " not found");

	            Row headerRow = sheet.getRow(0);
	            int colCount = headerRow.getLastCellNum();

	            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	                Map<String, String> rowData = new HashMap<>();
	                Row row = sheet.getRow(i);
	                if (row == null) continue;

	                for (int j = 0; j < colCount; j++) {
	                    Cell header = headerRow.getCell(j);
	                    Cell cell = row.getCell(j);
	                    String key = header != null ? header.toString() : "Col" + j;
	                    String value = cell != null ? cell.toString() : "";
	                    rowData.put(key.trim(), value.trim());
	                }
	                data.add(rowData);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("Failed to read Excel file: " + e.getMessage());
	        }

	        return data;
	    }
		
    public static Map<String, String> getTestDataByAction(String sheetname, String actionname) {
	    	// Map<String, String> data = Excelreader.getTestDataByAction(excelpath,sheetname, "login-valid");
	        List<Map<String, String>> allData = getData(sheetname);
	        for (Map<String, String> row : allData) {
	            if (row.containsKey("actionname") && row.get("actionname").equalsIgnoreCase(actionname)) {
	                return row;
	            }
	        }
	        throw new RuntimeException("No data found for action: " + actionname);
	    }
	}


