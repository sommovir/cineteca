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
        @NamedQuery(name = "getTitolo", query = "SELECT a FROM FilmEntity a WHERE a.titolo= :titolo")
    */
    @Override
    public List<FilmEntity> getAllFilm() {
        EntityManager filmEM = 
            Persistence.createEntityManagerFactory("CinetecaPU").createEntityManager();
        
        TypedQuery < FilmEntity > filmTQ = 
            filmEM.createNamedQuery("getTitolo", FilmEntity.class);
        
        return filmTQ.getResultList();
    }

    public boolean isFilmAlreadyExits(FilmEntity film){
        if (getFilm(film) == null){
            return false;
        }
        return true;
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
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.        
    }

    @Override
    public void editRegista(RegistaEntity regista) throws AlreadyExistingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RegistaEntity> getAllRegisti() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RegistaEntity getResista(RegistaEntity registaEntity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
