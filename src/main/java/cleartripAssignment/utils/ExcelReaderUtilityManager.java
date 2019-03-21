package cleartripAssignment.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cucumber.api.Scenario;

public class ExcelReaderUtilityManager {

	public static final String inputFilePath = System.getProperty("user.dir")
			+ "\\src\\test\\resources\\testdata\\TestData.xlsx";
	private static FileInputStream fis;
	private static XSSFSheet sheet;
	private static XSSFWorkbook workbook;
	private static XSSFRow row;

	public static void loadExcel(String sheetName) {

		File file = new File(inputFilePath);
		try {
			fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Map<String, Map<String, String>> getDataMap(String sheetName) {

		loadExcel(sheetName);
		Map<String, String> myMap = new HashMap<String, String>();
		Map<String, Map<String, String>> superMap = new HashMap<String, Map<String, String>>();

		for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {

			row = sheet.getRow(i);
			String keyCell = row.getCell(0).getStringCellValue();
			int colNum = row.getLastCellNum();

			for (int j = 1; j < colNum; j++) {

				XSSFCell cell = row.getCell(j);

				if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {

					String value = row.getCell(j).getStringCellValue();
					myMap.put(keyCell, value);
				}

				else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {

					int val = (int) row.getCell(j).getNumericCellValue();

					myMap.put(keyCell, String.valueOf(val));
				}
			}

			superMap.put(sheetName, myMap);

		}
		return superMap;
	}

	public static String getValue(String sheetName, String key) {

		Map<String, String> myVal = getDataMap(sheetName).get(sheetName);

		String retValue = myVal.get(key);

		return retValue;

	}

	

	public static String getValueFromExcel(Scenario scenario, String key) {

		String value = "";

		if (scenario.getName().contains("One-way")) {

			value = ExcelReaderUtilityManager.getValue("one-way", key);
		} else if (scenario.getName().contains("Round-trip")) {

			value = ExcelReaderUtilityManager.getValue("round-trip", key);
		}

		return value;
	}

}
