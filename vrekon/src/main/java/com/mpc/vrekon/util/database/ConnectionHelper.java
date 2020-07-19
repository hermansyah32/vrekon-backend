package com.mpc.vrekon.util.database;

import java.sql.*;
import java.util.List;

import com.mpc.vrekon.model.SourceConfig;
import com.mpc.vrekon.model.SourceTranslate;
import org.apache.log4j.Logger;
public class ConnectionHelper {
	private SourceConfig sourceConfig = null;
	private Logger log = Logger.getLogger(getClass());
	
	public ConnectionHelper(SourceConfig sourceConfig) {
		this.sourceConfig = sourceConfig;
	}

	public Connection SetConnnection(){
		Connection connection = null;
		try {
			if (sourceConfig.getSourceType().equals("mysql")) {
				Class.forName("com.mysql.jdbc.Driver");
				connection = (Connection) DriverManager.getConnection(
									"jdbc:mysql://"+sourceConfig.getDbHost()+"/"+sourceConfig.getDbName(),
						sourceConfig.getDbUsername(),
						sourceConfig.getDbPassword()
								  );
			} else if (sourceConfig.getSourceType().equals("oracle")) {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection = (Connection) DriverManager.getConnection(
									"jdbc:oracle:thin:@"+sourceConfig.getDbHost()+":"+sourceConfig.getDbName(),
						sourceConfig.getDbUsername(),
						sourceConfig.getDbPassword()
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
			Class.forName(sourceConfig.getSourceType());
			connection = (Connection) DriverManager.getConnection(
					sourceConfig.getDbHost(),
					sourceConfig.getDbUsername(),
					sourceConfig.getDbPassword()
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
	
	public String setSelectedField(List<SourceTranslate> dbTranslates){
		String translate = "";
		for (SourceTranslate dbTranslate : dbTranslates) {
			translate += dbTranslate.getOriginalFieldName();
			translate += " AS ";
			translate += dbTranslate.getTemporaryFieldName();
			translate += ", ";
		}
		translate += "-";
		translate = translate.replace(", -", "");
		return translate;
	}
}
