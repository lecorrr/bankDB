package org.bankservice.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryProvider {
    private static final EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("bankDB");
    }

    private EntityManagerFactoryProvider() { }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void closeFactory() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
