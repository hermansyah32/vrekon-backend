package com.mpc.vrekon.util.sourceconfig.excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SpreadSheetWrapper {


	String file = "";
	FileInputStream excelFile = null;
	Workbook workbook = null;
	Sheet datatypeSheet = null;
	Logger log = Logger.getLogger(getClass());
	
	public SpreadSheetWrapper(String file) {
		try {
			this.file = file;
			excelFile = new FileInputStream(new File(file));
	        //workbook = new XSSFWorkbook(excelFile);

			workbook = WorkbookFactory.create(excelFile);
	        datatypeSheet = workbook.getSheetAt(0);
		} catch (Exception e) {
			log.debug("Error constructor: "+e);
		}
	}
	
	public SpreadSheetWrapper(File file) {
		try {
			excelFile = new FileInputStream(file);
	        //workbook = new XSSFWorkbook(excelFile);
			workbook = WorkbookFactory.create(excelFile);
	        datatypeSheet = workbook.getSheetAt(0);
		} catch (Exception e) {
			log.debug("Error constructor: "+e);
		}
	}

	public Integer getRowCount(){

		Integer rowCount = 0;
		try {	        
            Iterator<Row> iterator = datatypeSheet.iterator();
            while (iterator.hasNext()) {
            	iterator.next();
            	rowCount++;
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
		return rowCount;
	}
	
	@SuppressWarnings("deprecation")
	public Integer getCellPosition(String coloumName, Integer rowStart){
		Integer coloumIndex = -1;
		try {
            Row row = datatypeSheet.getRow(rowStart);
            
            for (int i = 0; i < row.getLastCellNum(); i++) {
            	DataFormatter dataFormatter = new DataFormatter();
				Cell cell = row.getCell(i);
				
				if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
					continue;
				}
				
				String valueCell = dataFormatter.formatCellValue(cell).toLowerCase(); 
				if (valueCell.equals(coloumName.toLowerCase())) {
					coloumIndex = i;
					break;
				}
			}
            
		} catch (Exception e) {
			 log.debug("Error getCellPosition: "+e);
		}
		
		return coloumIndex;
	}
	
	public String getVelueUsingIndex(Integer indexRow, Integer indexColoum){
		String value = "";
		
		try {
            Row row = datatypeSheet.getRow(indexRow);
            
            if (indexColoum <= row.getLastCellNum()) {
				DataFormatter dataFormatter = new DataFormatter();
				Cell cell = row.getCell(indexColoum);
				
				value = dataFormatter.formatCellValue(cell);
			}else{
				value = "endOfFile";
			}
		} catch (Exception e) {
			 log.debug("Error getVelueUsingIndex: "+e);
		}
		
		return value;
	}
}
