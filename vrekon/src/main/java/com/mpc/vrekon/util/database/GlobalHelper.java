package com.mpc.vrekon.util.database;

import com.mpc.vrekon.model.SourceConfig;
import org.apache.log4j.Logger;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Properties;


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
	
	public void updateQuery(String sql){
		try {
			SourceConfig sourceConfig = new SourceConfig();
			File file = ResourceUtils.getFile(realPath);
			FileInputStream input = new FileInputStream(file);
			
			Properties prop = new Properties();
			prop.load(input);

			sourceConfig.setSourceType(prop.getProperty("database.driverClassName"));
			sourceConfig.setDbHost(prop.getProperty("database.url"));
			sourceConfig.setDbUsername(prop.getProperty("database.username"));
			sourceConfig.setDbPassword(prop.getProperty("database.password"));
			
			ConnectionHelper connectionHelper = new ConnectionHelper(sourceConfig);
			this.connection = connectionHelper.SetSpesificConnnection();
			connectionHelper.executeUpdate(this.connection, sql);
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	public ResultSet executeQuery(String sql){
		try {
			SourceConfig sourceConfig = new SourceConfig();
			File file = ResourceUtils.getFile(realPath);
			FileInputStream input = new FileInputStream(file);
			
			Properties prop = new Properties();
			prop.load(input);

			sourceConfig.setSourceType(prop.getProperty("database.driverClassName"));
			sourceConfig.setDbHost(prop.getProperty("database.url"));
			sourceConfig.setDbUsername(prop.getProperty("database.username"));
			sourceConfig.setDbPassword(prop.getProperty("database.password"));
			
			ConnectionHelper connectionHelper = new ConnectionHelper(sourceConfig);
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
