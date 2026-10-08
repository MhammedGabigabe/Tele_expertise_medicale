package org.example.teleexpertisemedicale.dao;

import jakarta.persistence.EntityManager;
import org.example.teleexpertisemedicale.entity.FileAttente;
import org.example.teleexpertisemedicale.util.JPAUtil;

import java.util.List;

public class FileAttenteDao {

    public List<FileAttente> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                            "SELECT f FROM FileAttente f "
                                    + "JOIN FETCH f.patient "
                                    + "LEFT JOIN FETCH f.signesVitaux",
                            FileAttente.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}
