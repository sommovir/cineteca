/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lelecora.cine.global.logic;

import it.lelecora.cine.global.entities.Film;
import it.lelecora.cine.global.entities.Regista;
import it.lelecora.cine.global.exceptions.AlreadyExistingException;
import java.util.List;

/**
 *
 * @author Luca Coraci <luca.coraci@istc.cnr.it>
 */
public interface ManagerInterface {
    
    /**
     * restituisce la lista di tutti i film presenti nel database
     * @return 
     */
    public List<Film> getAllFilm();
    
    /**
     * restituisce tutti i film avente per regista il regista con l'id in input
     * @param regista
     * @return 
     */
    public List<Film> getAllFilm(long registaID);
    
    /**
     * Salva nel DB il regista in input, lanciando un eccezione se il nome del regista è già contenuto nel db
     * @param regista
     * @throws AlreadyExistingException 
     */
    public void saveRegista(Regista regista) throws AlreadyExistingException;
    
    /**
     * Modifica i dati del regista in input, lanciando un eccezione se il nome editato del regista è già contenuto nel db
     * @param regista
     * @throws AlreadyExistingException 
     */
    public void editRegista(Regista regista) throws AlreadyExistingException;
    
    /**
     * restituisce la lista di tutti i registi salvati nel sistema
     * @return 
     */
    public List<Regista> getAllRegisti();
    
}
