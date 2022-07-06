package com.milprogramadores.tablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.milprogramadores.model.Alumno;
import com.milprogramadores.dao.DAOManager;

public class AlumnoTableModel extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DAOManager dao;
    private List<Alumno> alumnos = new ArrayList<Alumno>();

    public AlumnoTableModel() {
    	
    }

    public void updateModel(){ 
        alumnos = dao.getAlumnoDAO().listarAlumnos();
    }

    public String getColumnName(int columnIndex){
        switch(columnIndex){
            case 0:
                return "ID";
            case 1:
                return "LU";
            case 2:
                return "Nombre";
            case 3:
                return "Apellido";
            case 4:
                return "Usuario ID";
            default:
                return "";
        }
    }

    public int getRowCount() {
        return alumnos.size();
    }

    public int getColumnCount() {
        return 5;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Alumno seleccion = alumnos.get(rowIndex);
        switch(columnIndex){
            case 0:
                return seleccion.getAlumno_id();
            case 1:
                return seleccion.getAlumno_lu();
            case 2:
                return seleccion.getAlumno_nombre();
            case 3:
                return seleccion.getAlumno_apellido();
            case 4:
                return seleccion.getId_usuario();
            default:
                return "";
        }
    }
}
