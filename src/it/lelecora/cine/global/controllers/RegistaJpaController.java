/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lelecora.cine.global.controllers;

import it.lelecora.cine.global.controllers.exceptions.NonexistentEntityException;
import it.lelecora.cine.global.entities.Regista;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Luca Coraci <luca.coraci@istc.cnr.it>
 */
public class RegistaJpaController implements Serializable {

    public RegistaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Regista regista) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(regista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Regista regista) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            regista = em.merge(regista);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = regista.getId();
                if (findRegista(id) == null) {
                    throw new NonexistentEntityException("The regista with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Regista regista;
            try {
                regista = em.getReference(Regista.class, id);
                regista.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The regista with id " + id + " no longer exists.", enfe);
            }
            em.remove(regista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Regista> findRegistaEntities() {
        return findRegistaEntities(true, -1, -1);
    }

    public List<Regista> findRegistaEntities(int maxResults, int firstResult) {
        return findRegistaEntities(false, maxResults, firstResult);
    }

    private List<Regista> findRegistaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Regista.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Regista findRegista(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Regista.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Regista> rt = cq.from(Regista.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
