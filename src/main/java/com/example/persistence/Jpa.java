package com.example.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Jpa {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("starterPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
