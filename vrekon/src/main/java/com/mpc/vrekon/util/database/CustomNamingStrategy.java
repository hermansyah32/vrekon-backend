package com.mpc.vrekon.util.database;

import org.hibernate.cfg.DefaultNamingStrategy;

public class CustomNamingStrategy extends DefaultNamingStrategy {

    @Override
    public String tableName(String tableName) {
        return super.tableName(tableName);
    }

}
