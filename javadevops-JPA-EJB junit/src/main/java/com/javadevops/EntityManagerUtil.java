package com.javadevops;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
    private static EntityManagerFactory entityManagerFactory;

    public static void initialize() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-example");
    }

    public static void close() {
        entityManagerFactory.close();
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
