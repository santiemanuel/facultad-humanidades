package com.milprogramadores.tablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.milprogramadores.dao.DAOManager;
import com.milprogramadores.model.Alumno;
import com.milprogramadores.model.AlumnoExamen;
import com.milprogramadores.model.Carrera;

public class AlumnoExamenTableModel extends AbstractTableModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int OBJECT_COL = -1;
	private DAOManager dao = new DAOManager();
    private List<AlumnoExamen> examenes = new ArrayList<AlumnoExamen>();

    public AlumnoExamenTableModel() {
    	
    }

    public void updateModel(Alumno alumno, Carrera carrera) {
    	ArrayList<Integer> ids = dao.getExamenDAO().listarMateriasAlumno(carrera, alumno);
    	examenes = dao.getExamenDAO().examenesCompatibles(ids);
    }
    

    public String getColumnName(int columnIndex){
        switch(columnIndex){
            case 0:
                return "ID";
            case 1:
                return "Materia";
            case 2:
            	return "Fecha";
            default:
                return "";
        }
    }

    public int getRowCount() {
        return examenes.size();
    }

    public int getColumnCount() {
        return 3;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
    	AlumnoExamen seleccion = examenes.get(rowIndex);
        switch(columnIndex){
            case 0:
                return seleccion.getMesa_examen_id();
            case 1:
                return seleccion.getMateria_nombre();
            case 2:
            	return seleccion.getFecha();
            case OBJECT_COL : return seleccion;
            default:
                return "";
        }
    }

}
