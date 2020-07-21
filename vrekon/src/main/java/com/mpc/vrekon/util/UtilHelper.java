package com.mpc.vrekon.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UtilHelper {

	private static String _datePatternDB = "yyyy-MM-dd HH:mm:ss";
	private static String _datePattern = "dd-MM-yyyy";

	public static String arrayToString(String[] strings){
		return Arrays.toString(strings).replace("[", "").replace("]", "");
	}

	public static String arrayToStringQuery(String[] strings){
		String result = "";
		for (int index = 0; index < strings.length; index++) {
			if (index != (strings.length -1)){
				if (strings[index].contains(":")){
					result += "'" + strings[index] + "', ";
				}else {
					result += "\"" + strings[index] + "\", ";
				}
			}else {
				if (strings[index].contains(":")){
					result += "'" + strings[index] + "'";
				}else {
					result += "\"" + strings[index] + "\"";
				}
			}
		}
		return result;
	}

	public static String arrayToStringSingleQuote(String[] strings){
		String result = "";
		for (int index = 0; index < strings.length; index++) {
			if (index != (strings.length -1)){
				result += "'" + strings[index] + "', ";
			}else {
				result += "'" + strings[index] + "'";
			}
		}
		return result;
	}

	public static String arrayToStringDoubleQuote(String[] strings){
		String result = "";
		for (int index = 0; index < strings.length; index++) {
			if (index != (strings.length -1)){
				result += "\"" + strings[index] + "\", ";
			}else {
				result += "\"" + strings[index] + "\"";
			}
		}
		return result;
	}

	public static String arrayToStringBacktick(String[] strings){
		String result = "";
		for (int index = 0; index < strings.length; index++) {
			if (index != (strings.length -1)){
				result += "`" + strings[index] + "`, ";
			}else {
				result += "`" + strings[index] + "`";
			}
		}
		return result;
	}

	public static Date convertStringToDate(String dateString, String format){
		DateFormat dateFormat = new SimpleDateFormat(format);
		try {
			return dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}

	public static Date convertStringToDate(String dateString){
		DateFormat dateFormat = new SimpleDateFormat(_datePatternDB);
		try {
			return dateFormat.parse(dateString);
		} catch (ParseException e) {
			System.out.println(dateString);
			e.printStackTrace();
			return new Date();
		}
	}

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
