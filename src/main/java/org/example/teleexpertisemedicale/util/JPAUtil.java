package org.example.teleexpertisemedicale.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class JPAUtil {
    public static final EntityManagerFactory EMF =
            Persistence.createEntityManagerFactory("teleExpertisePU");

    private JPAUtil(){}

    public static EntityManager getEntityManager(){
        return EMF.createEntityManager();
    }

    public static void close(){
        if(EMF.isOpen()){
            EMF.close();
        }
    }
}
