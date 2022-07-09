package com.milprogramadores.tablemodel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.milprogramadores.dao.DAOManager;
import com.milprogramadores.model.Alumno;
import com.milprogramadores.model.Historial;

public class HistorialTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	public static final int OBJECT_COL = -1;
	private DAOManager dao = new DAOManager();
    private List<Historial> examenes = new ArrayList<Historial>();

    public HistorialTableModel() {
    	
    }

    public void updateModel(Alumno alumno, LocalDate fecha) {
    	examenes = dao.getAlumnoDAO().listarExamenes(alumno, fecha);
    }
    
    public void updateModel(Alumno alumno) {
    	examenes = dao.getAlumnoDAO().historialExamenes(alumno);
    }
    

    public String getColumnName(int columnIndex){
        switch(columnIndex){
            case 0:
                return "ID";
            case 1:
                return "Carrera";
            case 2:
            	return "Materia";
            case 3:
            	return "Fecha";
            case 4:
            	return "Nota";
            default:
                return "";
        }
    }

    public int getRowCount() {
        return examenes.size();
    }

    public int getColumnCount() {
        return 5;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
    	Historial seleccion = examenes.get(rowIndex);
        switch(columnIndex){
            case 0:
                return seleccion.getExamen_id();
            case 1:
                return seleccion.getCarrera_nombre();
            case 2:
            	return seleccion.getMateria_nombre();
            case 3:
            	return seleccion.getFecha();
            case 4: {
            	if (seleccion.getNota() == 0) return "Ausente";
            	return seleccion.getNota();
            }
            	
            case OBJECT_COL : return seleccion;
            default:
                return "";
        }
    }
	
}
