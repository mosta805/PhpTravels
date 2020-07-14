package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {
	
	static FileInputStream fis = null ;
	public FileInputStream getFileInputStream() {
		
		String filePath = System.getProperty("user.dir")+"\\src\\test\\java\\data\\UserData.xlsx";	
		try {
			fis = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
		
			System.out.println("Test Data not Found , Ending Process !! : Check Data Path");
			System.exit(0);
		}
		return fis ;		
	}
	
	public Object[][] getExcelData() throws IOException{
		
		
		fis = getFileInputStream();
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);	
		int totalNumOfRows = (sheet.getLastRowNum()+1);
		int totalNumOfCol = 6;
		
		String[][] arrayExcelData = new String[totalNumOfRows][totalNumOfCol];
		
		for (int i = 0; i < totalNumOfRows; i++) {
			
			for (int j = 0; j < totalNumOfCol; j++) {
				
				XSSFRow row = sheet.getRow(i);
				arrayExcelData[i][j] = row.getCell(j).toString();				
			}
		}
		wb.close();
		return arrayExcelData;
	}

}
