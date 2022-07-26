package org.vtiger.Practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchDataFromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub
		
		DataFormatter dataFormat = new DataFormatter();
		FileInputStream fisExcel = new FileInputStream("./src/test/resources/testData.xlsx");
		Workbook workbook = WorkbookFactory.create(fisExcel);
		Sheet sheet = workbook.getSheet("Sheet1");
		int rowNum=sheet.getLastRowNum();
		int cellNum = sheet.getRow(0).getLastCellNum();
		String[][] str=new String[rowNum][cellNum];
		for(int i=1; i<=rowNum; i++) {
			for(int j=1; j<=cellNum; j++) {
				str[i-1][j] = dataFormat.formatCellValue(sheet.getRow(i).getCell(j));
				
			}
		}
		
		System.out.println(str[4][1]);
		
		workbook.close();
	}

}
