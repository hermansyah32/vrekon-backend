package com.mpc.vrekon.util.database;

import com.mpc.vrekon.model.TemporaryTable;
import com.mpc.vrekon.util.database.connection.HibernateConfig;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateHelper {


    public static SessionFactory getSessionFactory(HibernateConfig config) {
        Configuration configuration = config.getConfiguration();
        configuration.addAnnotatedClass(TemporaryTable.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory(Configuration configuration) {
        configuration.addAnnotatedClass(TemporaryTable.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }
}
