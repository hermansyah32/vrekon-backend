package com.mpc.vrekon.util.sourceconfig.spreadsheet;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

public class SpreadSheetWrapper {

	private SpreadSheetType spreadSheetType;
	private Workbook workbook;
	private String sourceFile;
	Logger log = Logger.getLogger(getClass());

	//Create new spreadsheet
	public SpreadSheetWrapper(SpreadSheetType spreadSheetType){
		this.spreadSheetType = spreadSheetType;
		switch (this.spreadSheetType){
			case XLS:
				workbook = new HSSFWorkbook();
				break;
			case XLSX:
				workbook = new XSSFWorkbook();
				break;
			default:
				log.debug("unknown file format");
				break;
		}
	}
	
	public SpreadSheetWrapper(String sourceFile) {
		try {
			this.sourceFile = sourceFile;
			workbook = WorkbookFactory.create(new File(sourceFile));
		} catch (Exception e) {
			log.debug("Error constructor: "+e);
		}
	}

	public SpreadSheetISheet CreateSheet(String sheetName){
		try {
			return new SpreadSheetISheet(workbook.createSheet(sheetName));
		}catch (Exception e){
			log.debug(e);
			return null;
		}
	}

	public SpreadSheetISheet CreateSheet(){
		try {
			return new SpreadSheetISheet(workbook.createSheet("Sheet1"));
		}catch (Exception e){
			log.debug(e);
			return null;
		}
	}

	public SpreadSheetISheet getSheet(){
		try
		{
			if (workbook.getNumberOfSheets() > 0)
			{
				Sheet sheet = workbook.getSheetAt(workbook.getActiveSheetIndex());
				return new SpreadSheetISheet(sheet);
			}
			else
				return new SpreadSheetISheet(workbook.createSheet("Sheet" + (workbook.getNumberOfSheets() + 1)));
		}
		catch (Exception e)
		{
			log.debug(e);
			return null;
		}
	}

	public SpreadSheetISheet getSheet(int index){
		try {
			return new SpreadSheetISheet(workbook.getSheetAt(index));
		}catch (Exception e){
			log.debug(e);
			return null;
		}
	}

	public SpreadSheetISheet getSheet(String sheetName){
		try {
			return new SpreadSheetISheet(workbook.getSheet(sheetName));
		}catch (Exception e){
			log.debug(e);
			return null;
		}
	}

	public Workbook getWorkbook(){
		return workbook;
	}

	public void Save(){
		try {
			if (sourceFile == null){
				throw new Exception("Please specify destination path");
			}
			FileOutputStream outputStream = new FileOutputStream(new File(sourceFile));
			workbook.write(outputStream);
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void SaveAs(String destination){
		try {
			FileOutputStream outputStream = new FileOutputStream(new File(destination));
			workbook.write(outputStream);
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
