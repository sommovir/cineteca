/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.lelecora.cine.luca.gui.abstracts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Luca Coraci <luca.coraci@istc.cnr.it> ISTC-CNR
 */
public abstract class AbstractLCTableModel<T>
        extends AbstractTableModel implements PropertyChangeListener {

    private static final long serialVersionUID = 1L;
    public static final String MOVE_UP = "move_up";
    public static final String MOVE_DOWN = "move_down";
    protected List<T> datas = new LinkedList<T>();
    protected String[] labels;

    public AbstractLCTableModel(String[] labels, List<T> datas) {
        if (datas != null) {
            this.datas = datas;
        }
        this.labels = labels;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void addRowElement(T element) {
        datas.add(element);
        fireTableRowsInserted(datas.size() - 1, datas.size() - 1);
    }

    @Override
    public int getRowCount() {
        return datas.size();
    }

    @Override
    public int getColumnCount() {
        return labels.length;
    }

    @Override
    public String getColumnName(int column) {
        return labels[column];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        fireTableCellUpdated(row, col);
    }

    public int indexOf(T t) {
        return datas.indexOf(t);
    }

    public void reorder(int fromIndex, int toIndex) {
        T element = datas.get(fromIndex);
        datas.set(fromIndex, datas.get(toIndex));
        datas.set(toIndex, element);

        fireTableRowsUpdated(toIndex, toIndex);
        fireTableRowsUpdated(fromIndex, fromIndex);
    }

    public void removeRowElement(int i) {
        datas.remove(i);
        fireTableRowsDeleted(i, i);
//        }
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(MOVE_UP)) {
            Integer index = datas.indexOf(evt.getNewValue());
            if (index == 0) {
                return;
            } else {
                reorder(index, index - 1);
            }
        }
        if (evt.getPropertyName().equals(MOVE_DOWN)) {
            Integer index = datas.indexOf(evt.getNewValue());
            if (index >= datas.size() - 1) {
                return;
            } else {
                reorder(index, index + 1);
            }
        }
    }

    @Override
    public abstract Object getValueAt(int rowIndex, int columnIndex);
}
