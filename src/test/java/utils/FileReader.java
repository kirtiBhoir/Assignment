package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileReader {
	private static String sheetName = "UI_Test";
	private static String inputFilepath = System.getProperty("user.dir")
			+ "\\src\\test\\resources\\testdata\\data.xlsx";
	

	private static Map<String, String> map = new HashMap<String, String>();

	/**
	 * Method to read data from excel sheet
	 * 
	 * @param testcasename
	 * @param columnname
	 * @return
	 * @throws IOException
	 */
	public String getData(String testcasename, String columnname) throws IOException {
		FileInputStream fis;
		int k = 0;
		try {
			fis = new FileInputStream(inputFilepath);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet ws = wb.getSheet(sheetName);
			int rows = ws.getPhysicalNumberOfRows();
			for (int i = 0; i < rows; i++) {
				int cols = ws.getRow(0).getPhysicalNumberOfCells();
				for (int j = 0; j < cols; j++) {
					if (ws.getRow(i).getCell(j, Row.CREATE_NULL_AS_BLANK).toString().replace(".0", "")
							.equalsIgnoreCase(columnname)) {
						k = j;
					}
					map.put(ws.getRow(i).getCell(0, Row.CREATE_NULL_AS_BLANK).toString().replace(".0", ""),
							ws.getRow(i).getCell(k, Row.CREATE_NULL_AS_BLANK).toString().replace(".0", ""));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map.get(testcasename);
	}

}
