package com.mpc.vrekon.util.database.connection;

import com.mpc.vrekon.model.SourceConfig;
import org.hibernate.HibernateException;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {
    private Configuration configuration;

    public HibernateConfig(){

    }

    public HibernateConfig(Configuration configuration) {
        this.configuration = configuration;
    }
    public HibernateConfig(SourceConfig sourceConfig) {
        this.configuration = new Configuration();
        try{
            if (sourceConfig.getSourceType().equals("mysql")){
                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                configuration.setProperty("hibernate.connection.driver-class-name", "com.mysql.jdbc.Driver");
                configuration.setProperty("hibernate.connection.url", "jdbc:mysql://" + sourceConfig.getDbHost() + "/" + sourceConfig.getDbName());
            }else if (sourceConfig.getSourceType().equals("oracle")){
                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
                configuration.setProperty("hibernate.connection.driver-class-name", "oracle.jdbc.driver.OracleDriver");
                configuration.setProperty("hibernate.connection.url", "jdbc:oracle://" + sourceConfig.getDbHost() + "/" + sourceConfig.getDbName());
            }else {
                throw new HibernateException("unknown source type");
            }
            configuration.setProperty("hibernate.connection.username", sourceConfig.getDbUsername());
            configuration.setProperty("hibernate.connection.password", sourceConfig.getDbPassword());
        }catch (Exception e){

        }

    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public Configuration getCurrentConfiguration(){
        this.configuration = new AnnotationConfiguration()
                .configure("classpath:config/data/hibernate-config.xml");
        return configuration;
    }
}
