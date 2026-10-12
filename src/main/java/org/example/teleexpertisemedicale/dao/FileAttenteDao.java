package org.example.teleexpertisemedicale.dao;

import jakarta.persistence.EntityManager;
import org.example.teleexpertisemedicale.entity.FileAttente;
import org.example.teleexpertisemedicale.util.JPAUtil;

import java.util.List;
import java.util.Optional;

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

    public List<FileAttente> findEnAttente() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery(
                            "SELECT f FROM FileAttente f "
                                    + "JOIN FETCH f.patient "
                                    + "LEFT JOIN FETCH f.signesVitaux "
                                    + "WHERE f.consulte = false "
                                    + "ORDER BY f.dateArrivee ASC",
                            FileAttente.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<FileAttente> findById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return Optional.ofNullable(em.find(FileAttente.class, id));
        } finally {
            em.close();
        }
    }
}
