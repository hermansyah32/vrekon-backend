package com.mpc.vrekon.util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.mpc.vrekon.model.DBSetting;

public class GlobalHelper {	
	private Connection connection = null;
	private ResultSet resultSet = null;
	private Logger log = Logger.getLogger(getClass());
	
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
	
	@SuppressWarnings("deprecation")
	public void updateQuery(String sql, HttpServletRequest request){
		try {
			DBSetting dbSetting = new DBSetting();
			FileInputStream input = new FileInputStream(request.getRealPath("../resources/config/data/database.properties"));
			
			Properties prop = new Properties();
			prop.load(input);
			
			dbSetting.setDbType(prop.getProperty("database.driverClassName"));
			dbSetting.setDbHost(prop.getProperty("database.url"));
			dbSetting.setDbUsername(prop.getProperty("database.username"));
			dbSetting.setDbPassword(prop.getProperty("database.password"));
			
			ConnectionHelper connectionHelper = new ConnectionHelper(dbSetting);
			connection = connectionHelper.SetSpesificConnnection();
			connectionHelper.executeUpdate(connection, sql);
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
	}
	
	@SuppressWarnings("deprecation")
	public ResultSet executeQuery(String sql, HttpServletRequest request){
		try {
			DBSetting dbSetting = new DBSetting();
			FileInputStream input = new FileInputStream(request.getRealPath("../resources/config/data/database.properties"));
			
			Properties prop = new Properties();
			prop.load(input);
			
			dbSetting.setDbType(prop.getProperty("database.driverClassName"));
			dbSetting.setDbHost(prop.getProperty("database.url"));
			dbSetting.setDbUsername(prop.getProperty("database.username"));
			dbSetting.setDbPassword(prop.getProperty("database.password"));
			
			ConnectionHelper connectionHelper = new ConnectionHelper(dbSetting);
			connection = connectionHelper.SetSpesificConnnection();
			resultSet = connectionHelper.executeQuery(connection, sql);
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		
		return resultSet;		
	}
	
	public void closeConnection(){
		try {
			connection.close();			
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
	}
}
