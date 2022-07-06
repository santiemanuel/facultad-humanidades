package com.milprogramadores.tablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.milprogramadores.dao.DAOManager;
import com.milprogramadores.model.Carrera;

public class CarreraTableModel extends AbstractTableModel{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int OBJECT_COL = -1; 
	private DAOManager dao = new DAOManager();
    private List<Carrera> carreras = new ArrayList<Carrera>();

    public CarreraTableModel() {
    	
    }

    public void updateModel(){ 
    	carreras = dao.getCarreraDAO().listarCarreras();
    }

    public String getColumnName(int columnIndex){
        switch(columnIndex){
            case 0:
                return "ID";
            case 1:
                return "Nombre";
            default:
                return "";
        }
    }

    public int getRowCount() {
        return carreras.size();
    }

    public int getColumnCount() {
        return 2;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
    	Carrera seleccion = carreras.get(rowIndex);
        switch(columnIndex){
            case 0:
                return seleccion.getCarrera_id();
            case 1:
                return seleccion.getNombre();
            case OBJECT_COL : return seleccion;
            default:
                return "";
        }
    }

}
