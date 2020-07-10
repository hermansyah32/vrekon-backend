package com.mpc.vrekon.util.database.connection;

import java.util.HashMap;
import java.util.Map;

public class JPAConnection {
    //Always has default value for mysql
    private String url = "jdbc\\:mysql";
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String database;

    public JPAConnection(String host, Integer port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public JPAConnection(String host, Integer port, String username, String password, String database) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.database = database;
    }

    public JPAConnection(String url, String host, Integer port, String username, String password, String database) {
        this.url = url;
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.database = database;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return this.port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public Map<String, String> getMap(){
        Map<String, String> result = new HashMap<>();
        result.put("driverClassName", "com.mysql.jdbc.Driver");
        if (database != null)
            this.url = this.url + "\\://" + this.host +"\\: "+this.port+"/" + this.database;
        result.put("url", this.url);
        result.put("username", this.username);
        result.put("password", this.password);
        return result;
    }
}
