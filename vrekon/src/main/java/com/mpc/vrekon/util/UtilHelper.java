package com.mpc.vrekon.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UtilHelper {
	public String convertNowDateWithFormat(String format){
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = new Date();
		return formatter.format(date);
	}
	
	public String convertDateToDB() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return formatter.format(date);
	}
	
	public String convertDateToDB(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dbDate = this.parseToDate(date);
		
		return formatter.format(dbDate);			
	}
	
	public String convertDateToView() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		return formatter.format(date);
	}
	
	public String convertDateToView(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		return formatter.format(date);
	}
	
	public Date parseToDate(String data) {
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
	
	public String getFileExtension(String fileName) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
