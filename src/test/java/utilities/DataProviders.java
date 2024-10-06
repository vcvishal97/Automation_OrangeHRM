package utilities;

import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.DataProvider;

public class DataProviders {

	String filePath = ".\\testData\\loginCredentials.xlsx";
	
	@DataProvider(name = "validSearchTerms")
	Object[] getValidSearchTerms() {
		return new Object[] {"Admin", "Admin"};
	}
	
	@DataProvider(name = "invalidSearchTerms")
	Object[] getInvalidSearchTerms() {
		return new Object[] {"AAA", "BBB"};
	}
	
	@DataProvider(name = "validCredentials")
	String[][] getValidCredentials() throws Exception {
		return getCredentials("valid");
	}
	
	@DataProvider(name = "invalidCredentials")
	String[][] getInvalidCredentials() throws Exception {
		return getCredentials("invalid");
	}
	
	@DataProvider(name = "loginCredentials")
	String[][] getCredentials_notUsed() throws Exception {
		int sheetNum = 0;
		ExcelUtility excel = new ExcelUtility(filePath);
		int rowCount = excel.getRowCount(sheetNum);
		int columnCount = excel.getColumnCount();
		
		String loginData[][] = new String[rowCount][columnCount];
		for(int i = 1; i <= rowCount; i++)
			for(int j = 0; j < 3; j++)
				loginData[i - 1][j] = excel.getCellValue(i, j);
		
		return loginData;
	}
	
	String[][] getCredentials(String validOrInvalid) throws Exception {
		String loginCredentials[][] = null;
		List<String[]> list = new ArrayList<>();
		int sheetNum = 0;
		ExcelUtility excel = new ExcelUtility(filePath);
		int rowCount = excel.getRowCount(sheetNum);
		
		for(int i = 1; i <= rowCount; i++) {
			String result = excel.getCellValue(i, 2);
			if(result.equalsIgnoreCase(validOrInvalid)) {
				String username = excel.getCellValue(i, 0);
				String password = excel.getCellValue(i, 1);
				list.add(new String[] {username, password});
			}
		}
		loginCredentials = new String[list.size()][2];
		for(int i = 0; i < list.size(); i++)
			loginCredentials[i] = list.get(i);
		return loginCredentials;
	}
	
}
