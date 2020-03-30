/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lelecora.cine.lele.engine;

import it.lelecora.cine.global.controllers.FilmJpaController;
import it.lelecora.cine.global.controllers.RegistaJpaController;
import it.lelecora.cine.global.entities.Film;
import it.lelecora.cine.global.entities.Regista;
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

    public prova001() {
        FilmJpaController filmJpaController = new 
            FilmJpaController(Persistence.createEntityManagerFactory("CinetecaPU"));
        
        RegistaJpaController registaJpaController = new 
            RegistaJpaController(Persistence.createEntityManagerFactory("CinetecaPU"));
    
        Regista registaEntity  = new Regista();
        registaEntity.setNome("Lele");
        
        Film filmEntity = new Film();
        filmEntity.setAnno(1979);
        filmEntity.setAttorePrincipale("Luca Coraci");
        filmEntity.setRegista(registaEntity);
        filmEntity.setTitolo("Non ce la posso fare!!!!");
        
        filmEntity.setTitoloOriginale("Non c'è");

        filmJpaController.create(filmEntity);
        registaJpaController.create(registaEntity);
    }

    
    // non sono sicuro di getTitolo
    @Override
    public List<Film> getAllFilm() {
        EntityManager filmEM = 
            Persistence.createEntityManagerFactory("CinetecaPU").createEntityManager();
        
        TypedQuery < Film > filmTQ = 
            filmEM.createNamedQuery("getTitolo", Film.class);
        
        return filmTQ.getResultList();
    }

    // NOn so come devo comportarmi devo fare due ricerche su di entity diverse forse qualcosa con one to many
    @Override
    public List<Film> getAllFilm(long registaID) {
        EntityManager filmEM = 
            Persistence.createEntityManagerFactory("CinetecaPU").createEntityManager();
        
        TypedQuery < Film > filmByRegistaTq = 
            filmEM.createNamedQuery("getTitolo", Film.class);
        
        filmByRegistaTq.setParameter("id", registaID);
        
        return filmByRegistaTq.getResultList();
    }

    @Override
    public void saveRegista(Regista regista) throws AlreadyExistingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editRegista(Regista regista) throws AlreadyExistingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Regista> getAllRegisti() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
