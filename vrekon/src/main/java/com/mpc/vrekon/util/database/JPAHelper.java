package com.mpc.vrekon.util.database;

import com.mpc.vrekon.util.database.connection.JPAConnection;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAHelper {
    private JPAConnection connection;
    private EntityManagerFactory managerFactory = null;
    private EntityManager entityManager = null;

    Logger log = Logger.getLogger(getClass());

    public JPAHelper(JPAConnection connection) {
        this.connection = connection;
        try {
            managerFactory = createEntityManagerFactory();
            entityManager = managerFactory.createEntityManager();
        }catch (Exception e){
            log.error(e);
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void close(){
        try {
            this.entityManager.close();
            this.managerFactory.close();
        }catch (Exception e){
            log.error(e);
        }
    }

    private EntityManagerFactory createEntityManagerFactory() {
        return Persistence.createEntityManagerFactory(this.connection.getMap().get("url"), this.connection.getMap());
    }
}
