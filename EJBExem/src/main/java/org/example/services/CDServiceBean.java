package org.example.services;

import org.example.Model.CD;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CDServiceBean {
    @PersistenceContext
    private EntityManager em;

    public List<CD> listAvailableCDs() {
        TypedQuery<CD> query = em.createQuery("SELECT c FROM CD c WHERE c.available = true", CD.class);
        return query.getResultList();
    }

    public String borrowCD(Long cdId, Long userId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null && cd.isAvailable()) {
            cd.setAvailable(false);
            em.merge(cd);
            return "CD emprunté avec succès.";
        }
        return "CD non disponible.";
    }

    public String returnCD(Long cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null && !cd.isAvailable()) {
            cd.setAvailable(true);
            em.merge(cd);
            return "CD retourné avec succès.";
        }
        return "CD non emprunté.";
    }
}
