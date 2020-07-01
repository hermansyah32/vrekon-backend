package com.mpc.vrekon.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UtilHelper {

	private static String _datePatternDB = "yyyy-MM-dd HH:mm:ss";
	private static String _datePattern = "dd-MM-yyyy";

	public static String convertNowDateWithFormat(String format){
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = new Date();
		return formatter.format(date);
	}
	
	public static String convertDateToDB() {
		SimpleDateFormat formatter = new SimpleDateFormat(_datePatternDB);
		Date date = new Date();
		return formatter.format(date);
	}
	
	public static String convertDateToDB(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat(_datePatternDB);
		Date dbDate = parseToDate(date);
		
		return formatter.format(dbDate);			
	}
	
	public static String convertDateToView() {
		SimpleDateFormat formatter = new SimpleDateFormat(_datePattern);
		Date date = new Date();
		return formatter.format(date);
	}

	public static String convertDateToView(String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		Date date = new Date();
		return formatter.format(date);
	}
	
	public static String convertDateToView(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(_datePattern);
		return formatter.format(date);
	}

	public static String convertDateToView(Date date, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}
	
	public static Date parseToDate(String data) {
		Date foormatEquals = null;
		List<String> formatTemplate = new ArrayList<String>();
		formatTemplate.add("dd/MM/yyyy");
		formatTemplate.add("dd-MM-yyyy");
		formatTemplate.add("dd MMM yyyy");
		formatTemplate.add("dd-MMM-yyyy HH:mm:ss");
		formatTemplate.add("dd-MM-yyyy HH:mm:ss");
		formatTemplate.add("dd/MM/yyyy HH:mm:ss");
		
		for (String value : formatTemplate) {
			try {
				foormatEquals = new SimpleDateFormat(value).parse(data);
				break;
			} catch (Exception e) {
			}
		}
		
		return foormatEquals;
	}
	
	public static String StrConvertToVar(String str){
		Integer index = 0;
		do {
			index = str.indexOf("_");
			String tmp = str.charAt(index+1)+"";
			str = str.replace("_"+str.charAt(index+1), tmp.toUpperCase());
		} while (index >= 0);
		
		return str;
	}
	
	public static String getFileExtension(String fileName) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
