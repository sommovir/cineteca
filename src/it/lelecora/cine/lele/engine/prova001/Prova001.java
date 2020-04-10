/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lelecora.cine.lele.engine.prova001;

import it.lelecora.cine.global.controllers.FilmJpaController;
import it.lelecora.cine.global.controllers.RegistaJpaController;
import it.lelecora.cine.global.controllers.exceptions.NonexistentEntityException;
import it.lelecora.cine.global.entities.FilmEntity;
import it.lelecora.cine.global.entities.RegistaEntity;
import it.lelecora.cine.global.exceptions.AlreadyExistingException;
import it.lelecora.cine.global.logic.ManagerInterface;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author lele
 */
public class Prova001 implements ManagerInterface{
    private static Prova001 instance = null;
    private EntityManager entityManager;
    private FilmJpaController filmJpaController;
    private RegistaJpaController registaJpaController;
    
    public Prova001() {
        entityManager = 
            Persistence.createEntityManagerFactory("CinetecaPU2").createEntityManager();
        
        filmJpaController = new 
            FilmJpaController(Persistence.createEntityManagerFactory("CinetecaPU2"));
        
        registaJpaController = 
            new RegistaJpaController(Persistence.createEntityManagerFactory("CinetecaPU2"));
    }

    /* 
      da aggiungere alla FilmEntity
       @NamedQuery(name = "getId", query = "SELECT a FROM FilmEntity a WHERE a.titolo= :titolo")
    */     
    @Override
    public FilmEntity getFilm(String title) {
        EntityManager filmEM = entityManager;
        
        TypedQuery < FilmEntity > FilmTQ = 
            filmEM.createNamedQuery("getTitolo", FilmEntity.class);
        
        FilmTQ.setParameter("titolo", title);
        
        return FilmTQ.getSingleResult();
    }

    /* 
      da aggiungere alla FilmEntity
       @NamedQuery(name = "getId", query = "SELECT a FROM FilmEntity a WHERE a.id= :id")
    */     
    @Override
    public FilmEntity getFilm(long filmID) {
        EntityManager filmEM = entityManager;
        
        TypedQuery < FilmEntity > FilmTQ = 
            filmEM.createNamedQuery("getId", FilmEntity.class);
        
        FilmTQ.setParameter("id", filmID);
        return FilmTQ.getSingleResult();
    }

    @Override
    public List<FilmEntity> getAllFilm() {
        return filmJpaController.findFilmEntities();
    }

    /* 
      da aggiungere alla FilmEntity
       @NamedQuery(name = "getId", query = "SELECT a FROM FilmEntity a WHERE a.id= :id")
    */ 
    @Override
    public List<FilmEntity> getAllFilm(long registaID) {
        EntityManager filmEM = 
            Persistence.createEntityManagerFactory("CinetecaPU").createEntityManager();
        
        TypedQuery < FilmEntity > filmByRegistaTq = 
            filmEM.createNamedQuery("getId", FilmEntity.class);
        
        filmByRegistaTq.setParameter("Id", registaID);
        
        return filmByRegistaTq.getResultList();
    }

    @Override
    public void saveRegista(RegistaEntity regista) throws AlreadyExistingException {
        registaJpaController.create(regista); 
    }

    @Override
    public void editRegista(RegistaEntity regista) throws AlreadyExistingException {
        try {
            registaJpaController.edit(regista);
        } catch (Exception ex) {
            Logger.getLogger(Prova001.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<RegistaEntity> getAllRegisti() {
        return registaJpaController.findRegistaEntities();
    }

    /*
      da aggiungere alla RegistaEntity
       @NamedQuery(name = "getCognome", query = "SELECT a FROM RegistaEntity a WHERE a.cognome= :cognome")
    */      
    @Override
    public RegistaEntity getRegista(String regista) {
        EntityManager registaEM = entityManager;
        
        TypedQuery < RegistaEntity > registaTQ = 
            registaEM.createNamedQuery("getCognome", RegistaEntity.class);
        
        registaTQ.setParameter("getCognome", regista);
        return registaTQ.getSingleResult();
    }

    /* 
      da aggiungere alla RegistaEntity
       @NamedQuery(name = "getId", query = "SELECT a FROM FilmEntity a WHERE a.id= :id")
    */     
    @Override
    public RegistaEntity getRegista(long registaID) {
        EntityManager registaEM = entityManager;
        
        TypedQuery < RegistaEntity > registaTQ = 
            registaEM.createNamedQuery("getId", RegistaEntity.class);
        
        registaTQ.setParameter("id", registaID);
        return registaTQ.getSingleResult();
    }

    @Override
    public void deleteRegista(RegistaEntity regista) {
        try {
            registaJpaController.destroy(regista.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Prova001.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    
    public void deleteFilm(FilmEntity film) {
        try {
            filmJpaController.destroy(film.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Prova001.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* 
      da aggiungere alla FilmEntity
       @NamedQuery(name = "getRegista", query = "SELECT a FROM FilmEntity a WHERE a.regista= :regista")
    */  
    @Override
    public List<FilmEntity> getFilmsByRegista(long registaId) {
        EntityManager filmEM = entityManager;
        
        TypedQuery < FilmEntity > filmTQ = 
            filmEM.createNamedQuery("getRegista", FilmEntity.class);
        
        filmTQ.setParameter("regista", registaId);
        return filmTQ.getResultList();
    }
    
    
            
}
