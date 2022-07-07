package com.milprogramadores.tablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.milprogramadores.dao.DAOManager;
import com.milprogramadores.model.Alumno;
import com.milprogramadores.model.Carrera;
import com.milprogramadores.model.Materia;

public class MateriaTableModel extends AbstractTableModel{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DAOManager dao = new DAOManager();
    private List<Materia> materias = new ArrayList<Materia>();

    public MateriaTableModel() {
    	
    }

    public void updateModel(Alumno alumno) {
    	materias = dao.getMateriaDAO().materiasAlumno(alumno.getAlumno_id());
    }
    
    public void updateModel(){ 
    	materias = dao.getMateriaDAO().listarMaterias();
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
        return materias.size();
    }

    public int getColumnCount() {
        return 2;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
    	Materia seleccion = materias.get(rowIndex);
        switch(columnIndex){
            case 0:
                return seleccion.getMateria_id();
            case 1:
                return seleccion.getNombre();

            default:
                return "";
        }
    }

}
