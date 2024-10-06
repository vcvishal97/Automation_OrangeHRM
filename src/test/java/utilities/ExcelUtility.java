package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	String filePath;
	File file;
	FileInputStream inputStream;
	FileOutputStream outputStream;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	
	public ExcelUtility(String filePath) throws Exception {
		file = new File(filePath);
		setWorkBook(file);
	}
	
	public void setWorkBook(File filePath) throws Exception {
		if(!file.exists())
			throw new Exception("File not found.");
		inputStream = new FileInputStream(file);
		workbook = new XSSFWorkbook(file);
		sheet = workbook.getSheetAt(0);
	}
	
	public String getCellValue(int rowNum, int columnNum) {
		row = sheet.getRow(rowNum);
		cell = row.getCell(columnNum);
		return cell.getStringCellValue();
	}
	
	public int getRowCount(int sheetNum) throws IOException {
		sheet = workbook.getSheetAt(sheetNum);
		return sheet.getLastRowNum();
	}
	
	public int getColumnCount() {
		if(row == null)
			row = sheet.getRow(0);
		return row.getLastCellNum();
	}
	
}
