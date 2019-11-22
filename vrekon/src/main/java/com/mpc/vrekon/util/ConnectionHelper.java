package com.mpc.vrekon.util;

import java.sql.*;
import java.util.List;

import org.apache.log4j.Logger;

import com.mpc.vrekon.model.DBSetting;
import com.mpc.vrekon.model.DBTranslate;

public class ConnectionHelper {
	private DBSetting dbSetting = null;
	private Logger log = Logger.getLogger(getClass());
	
	public ConnectionHelper(DBSetting dbSetting) {
		this.dbSetting = dbSetting;
	}

	public Connection SetConnnection(){
		Connection connection = null;
		try {
			if (dbSetting.getDbType().equals("mysql")) {
				Class.forName("com.mysql.jdbc.Driver");
				connection = (Connection) DriverManager.getConnection(
									"jdbc:mysql://"+dbSetting.getDbHost()+"/"+dbSetting.getDbName(),
									dbSetting.getDbUsername(),
									dbSetting.getDbPassword()
								  );
			} else if (dbSetting.getDbType().equals("oracle")) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection = (Connection) DriverManager.getConnection(
									"jdbc:oracle:thin:@"+dbSetting.getDbHost()+":"+dbSetting.getDbName(),
									dbSetting.getDbUsername(),
									dbSetting.getDbPassword()
								  );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public Connection SetSpesificConnnection(){
		Connection connection = null;
		try {
			Class.forName(dbSetting.getDbType());
			connection = (Connection) DriverManager.getConnection(
								dbSetting.getDbHost(),
								dbSetting.getDbUsername(),
								dbSetting.getDbPassword()
							  );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public ResultSet executeQuery(Connection connection,String sql){
		ResultSet resultSet = null;
		try {
			Statement statement = (Statement) connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		return resultSet;
	}
	
	public ResultSet executeQuery(Connection connection, String sql, List<String> params) {
		ResultSet resultSet = null;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			Integer i = 1;
			for (String param : params) {
				statement.setObject(i, param);
				i++;
			}
			statement.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultSet;
	}
	
	public Integer executeUpdate(Connection connection,String sql){
		Integer status = null;
		try {
			Statement statement = (Statement) connection.createStatement();
			status = statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	public Integer executeUpdate(Connection connection,String sql, List<String> params){
		Integer status = null;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			Integer i = 1;
			for (String param : params) {
				statement.setObject(i, param);
				i++;
			}
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	public String setSelectedField(List<DBTranslate> dbTranslates){
		String translate = "";
		for (DBTranslate dbTranslate : dbTranslates) {
			translate += dbTranslate.getOriginTableName();
			translate += " AS ";
			translate += dbTranslate.getTargetTableName();
			translate += ", ";
		}
		translate += "-";
		translate = translate.replace(", -", "");
		return translate;
	}
}
