/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lelecora.cine.luca.gui.panels.films.mr;

import it.lelecora.cine.global.entities.FilmEntity;
import it.lelecora.cine.lele.engine.prova001.Prova001;
import it.lelecora.cine.luca.gui.abstracts.AbstractLCTableModel;
import java.util.List;

/**
 *
 * @author Luca Coraci <luca.coraci@istc.cnr.it>
 */
public class FilmTableModel extends AbstractLCTableModel<FilmEntity>{

    public FilmTableModel() {
        super(new String[]{"Titolo","Regista","Titolo originale","Anno"},new Prova001().getAllFilm());
    }
    
    
    
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0: return this.getDatas().get(rowIndex).getTitolo();
            case 1: return this.getDatas().get(rowIndex).getRegista().getCognome();
            case 2: return this.getDatas().get(rowIndex).getTitoloOriginale();
            case 3: return this.getDatas().get(rowIndex).getAnno();
            default: return "";
        }
    }
    
}
