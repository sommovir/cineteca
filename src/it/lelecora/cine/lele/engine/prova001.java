/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lelecora.cine.lele.engine;

import it.lelecora.cine.global.controllers.FilmJpaController;
import it.lelecora.cine.global.controllers.RegistaJpaController;
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
public class prova001 implements ManagerInterface{

    // se l'idea ti piacie poi faccio getInstance
    public prova001() {
        FilmJpaController filmJpaController = new 
            FilmJpaController(Persistence.createEntityManagerFactory("CinetecaPU"));
        
        RegistaJpaController registaJpaController = new 
            RegistaJpaController(Persistence.createEntityManagerFactory("CinetecaPU"));
    
        RegistaEntity registaEntity  = new RegistaEntity();
        registaEntity.setNome("Lele");
        
        FilmEntity filmEntity = new FilmEntity();
        filmEntity.setAnno(1979);
        filmEntity.setAttorePrincipale("Luca Coraci");
        filmEntity.setRegista(registaEntity);
        filmEntity.setTitolo("Non ce la posso fare!!!!");
        
        filmEntity.setTitoloOriginale("Non c'è");

        filmJpaController.create(filmEntity);
        registaJpaController.create(registaEntity);
    }

    /*
       da aggiungere alla FilmEntity
        @NamedQuery(name = "getTitolo", query = "SELECT a FROM FilmEntity a WHERE a.titolo= :titolo")
    */    
    @Override
    public FilmEntity getFilm(FilmEntity film) {
        EntityManager filmEM = 
            Persistence.createEntityManagerFactory("CinetecaPU").createEntityManager();
        
        TypedQuery < FilmEntity > FilmTQ = 
            filmEM.createNamedQuery("getTitolo", FilmEntity.class);
        
        return FilmTQ.getSingleResult();
    }    
    
    /*
       da aggiungere alla FilmEntity
        @NamedQuery(name = "getTitolo", query = "SELECT a FROM FilmEntity a")
    */
    @Override
    public List<FilmEntity> getAllFilm() {
        EntityManager filmEM = 
            Persistence.createEntityManagerFactory("CinetecaPU").createEntityManager();
        
        TypedQuery < FilmEntity > filmTQ = 
            filmEM.createNamedQuery("getTitolo", FilmEntity.class);
        
        return filmTQ.getResultList();
    }

    public void checkFilmAlreadyExits(FilmEntity film) throws AlreadyExistingException{
        if (getFilm(film) == null){
            throw new AlreadyExistingException();
        }
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
        checkRegistaAlreadyExits(regista);
        
        RegistaJpaController registaJpaController = 
            new RegistaJpaController(Persistence.createEntityManagerFactory("CinetecaPU"));
        
        registaJpaController.create(regista);    
    }

    public void checkRegistaAlreadyExits(RegistaEntity regista) throws AlreadyExistingException{
        if (getResista(regista) == null){
            throw new AlreadyExistingException();
        }
    }    
    
    @Override
    public void editRegista(RegistaEntity regista) throws AlreadyExistingException {
        try {
            checkRegistaAlreadyExits(regista);
            
            RegistaJpaController registaJpaController =
                    new RegistaJpaController(Persistence.createEntityManagerFactory("CinetecaPU"));
            
            registaJpaController.edit(regista);

        } catch (Exception ex) {
            Logger.getLogger(prova001.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
       da aggiungere alla RegistaEntity
        @NamedQuery(name = "getCognome", query = "SELECT a FROM RegistaEntity a")
    */
    @Override
    public List<RegistaEntity> getAllRegisti() {
        EntityManager registaEM = 
            Persistence.createEntityManagerFactory("CinetecaPU").createEntityManager();
        
        TypedQuery < RegistaEntity > registaTQ = 
            registaEM.createNamedQuery("getCognome", RegistaEntity.class);
        return registaTQ.getResultList();
    }

    /*
       da aggiungere alla RegistaEntity
        @NamedQuery(name = "getCognome", query = "SELECT a FROM RegistaEntity a WHERE a.cognome= :cognome")
    */    
    @Override
    public RegistaEntity getResista(RegistaEntity registaEntity) {
        EntityManager registaEM = 
            Persistence.createEntityManagerFactory("CinetecaPU").createEntityManager();
        
        TypedQuery < RegistaEntity > registaTQ = 
            registaEM.createNamedQuery("getCognome", RegistaEntity.class);
        
        registaTQ.setParameter("getCognome", registaEntity.getCognome());
        return registaTQ.getSingleResult();
    }

}
