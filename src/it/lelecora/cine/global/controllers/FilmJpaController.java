/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lelecora.cine.global.controllers;

import it.lelecora.cine.global.controllers.exceptions.NonexistentEntityException;
import it.lelecora.cine.global.entities.FilmEntity;
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
public class FilmJpaController implements Serializable {

    public FilmJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FilmEntity film) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(film);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FilmEntity film) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            film = em.merge(film);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = film.getId();
                if (findFilm(id) == null) {
                    throw new NonexistentEntityException("The film with id " + id + " no longer exists.");
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
            FilmEntity film;
            try {
                film = em.getReference(FilmEntity.class, id);
                film.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The film with id " + id + " no longer exists.", enfe);
            }
            em.remove(film);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FilmEntity> findFilmEntities() {
        return findFilmEntities(true, -1, -1);
    }

    public List<FilmEntity> findFilmEntities(int maxResults, int firstResult) {
        return findFilmEntities(false, maxResults, firstResult);
    }

    private List<FilmEntity> findFilmEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FilmEntity.class));
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

    public FilmEntity findFilm(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FilmEntity.class, id);
        } finally {
            em.close();
        }
    }

    public int getFilmCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FilmEntity> rt = cq.from(FilmEntity.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
