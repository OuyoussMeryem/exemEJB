package org.example.services;

import org.example.Model.CD;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateful
public class AdminCDServiceBean {

    @PersistenceContext
    private EntityManager em;

    public void addCD(String title) {
        CD cd = new CD();
        cd.setTitle(title);
        cd.setAvailable(true);
        em.persist(cd);
    }

    public void removeCD(Long cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null) {
            em.remove(cd);
        }
    }

    public void updateCD(Long cdId, String newTitle) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null) {
            cd.setTitle(newTitle);
            em.merge(cd);
        }
    }

    public List<CD> getAllCDs() {
        return em.createQuery("SELECT c FROM CD c", CD.class).getResultList();
    }
}
