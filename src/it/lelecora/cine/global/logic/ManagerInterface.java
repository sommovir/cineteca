/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lelecora.cine.global.logic;

import it.lelecora.cine.global.entities.FilmEntity;
import it.lelecora.cine.global.entities.RegistaEntity;
import it.lelecora.cine.global.exceptions.AlreadyExistingException;
import java.util.List;

/**
 *
 * @author Luca Coraci <luca.coraci@istc.cnr.it>
 */
public interface ManagerInterface {
    
    /**
     * Restituisce un film specifico
     */
    public FilmEntity getFilm(String title);
    
    /**
     * Restituisce un film specifico
     */
    public FilmEntity getFilm(long filmID);
    
    /**
     * restituisce la lista di tutti i film presenti nel database
     * @return 
     */
    public List<FilmEntity> getAllFilm();
    
    /**
     * restituisce tutti i film avente per regista il regista con l'id in input
     * @param regista
     * @return 
     */
    public List<FilmEntity> getAllFilm(long registaID);
    
    /**
     * Salva nel DB il regista in input, lanciando un eccezione se il nome del regista è già contenuto nel db
     * @param regista
     * @throws AlreadyExistingException 
     */
    public void saveRegista(RegistaEntity regista) throws AlreadyExistingException;
    
    /**
     * Modifica i dati del regista in input, lanciando un eccezione se il nome editato del regista è già contenuto nel db
     * @param regista
     * @throws AlreadyExistingException 
     */
    public void editRegista(RegistaEntity regista) throws AlreadyExistingException;
    
    /**
     * restituisce la lista di tutti i registi salvati nel sistema
     * @return 
     */
    public List<RegistaEntity> getAllRegisti();
    
    /**
     * Rstituisce uno specifico regista
     * @return 
     */
    public RegistaEntity getRegista(String regista);
    
    /**
     * Rstituisce uno specifico regista
     * @return 
     */
    public RegistaEntity getRegista(long registaID);

}
