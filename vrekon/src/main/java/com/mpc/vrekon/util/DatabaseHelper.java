package com.mpc.vrekon.util;

import javax.sql.DataSource;

public class DatabaseHelper {
    private DataSource dataSource;

    public DatabaseHelper(DataSource dataSource) {
        this.dataSource = dataSource;
    }


}
