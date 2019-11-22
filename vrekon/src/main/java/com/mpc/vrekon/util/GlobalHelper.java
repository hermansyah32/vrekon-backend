package com.mpc.vrekon.util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.util.ResourceUtils;

import com.mpc.vrekon.model.DBSetting;

public class GlobalHelper {	
	private Connection connection = null;
	private ResultSet resultSet = null;
	private String realPath = "classpath:config/data/database.properties";
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
	
	public void updateQuery(String sql){
		try {
			DBSetting dbSetting = new DBSetting();
			File file = ResourceUtils.getFile(realPath);
			FileInputStream input = new FileInputStream(file);
			
			Properties prop = new Properties();
			prop.load(input);
			
			dbSetting.setDbType(prop.getProperty("database.driverClassName"));
			dbSetting.setDbHost(prop.getProperty("database.url"));
			dbSetting.setDbUsername(prop.getProperty("database.username"));
			dbSetting.setDbPassword(prop.getProperty("database.password"));
			
			ConnectionHelper connectionHelper = new ConnectionHelper(dbSetting);
			this.connection = connectionHelper.SetSpesificConnnection();
			connectionHelper.executeUpdate(this.connection, sql);
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	public ResultSet executeQuery(String sql){
		try {
			DBSetting dbSetting = new DBSetting();
			File file = ResourceUtils.getFile(realPath);
			FileInputStream input = new FileInputStream(file);
			
			Properties prop = new Properties();
			prop.load(input);
			
			dbSetting.setDbType(prop.getProperty("database.driverClassName"));
			dbSetting.setDbHost(prop.getProperty("database.url"));
			dbSetting.setDbUsername(prop.getProperty("database.username"));
			dbSetting.setDbPassword(prop.getProperty("database.password"));
			
			ConnectionHelper connectionHelper = new ConnectionHelper(dbSetting);
			this.connection = connectionHelper.SetSpesificConnnection();
			resultSet = connectionHelper.executeQuery(this.connection, sql);
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
		
		return resultSet;		
	}
	
	public void closeConnection(){
		try {
			this.connection.close();			
		} catch (Exception e) {
			log.debug(e.getMessage());
		}
	}
}
