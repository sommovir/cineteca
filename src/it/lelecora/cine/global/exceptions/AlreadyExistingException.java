/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lelecora.cine.global.exceptions;

/**
 *
 * @author Luca Coraci <luca.coraci@istc.cnr.it>
 */
public class AlreadyExistingException extends Exception{

    public AlreadyExistingException() {
        super("L'entitàc che si vuole inserire è già esistente");
    }
    
    
    
}
