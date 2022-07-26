package org.tyss.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


	/**
	 * 
	 * @author Divyalochan Sahoo
	 *
	 */
	public final class ExcelUtility {

		private Workbook workbook;
		/**
		 * This method is used to initialize the excel file
		 * @param excelPath
		 * @param sheetName
		 */
		public void initializeExcelFile(String excelPath){
			try {
				FileInputStream fisExcel=new FileInputStream(excelPath);
				try {
					workbook=WorkbookFactory.create(fisExcel);
				} catch (EncryptedDocumentException | IOException e) {

					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
		}
		/**
		 * This method is used to fetch data from excel
		 * @param rowNumber
		 * @param cellNumber
		 * @param SheetName
		 * @return
		 */
		public String getDataFromExcel(int rowNumber,int cellNumber, String SheetName) {
			Sheet sheet = workbook.getSheet(SheetName);
			return new DataFormatter().formatCellValue(sheet.getRow(rowNumber).getCell(cellNumber));
		}
	}
