package com.milprogramadores.tablemodel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.milprogramadores.dao.DAOManager;
import com.milprogramadores.model.Alumno;
import com.milprogramadores.model.Historial;
import com.milprogramadores.model.HistorialAdmin;
import com.milprogramadores.model.Usuario;
import com.milprogramadores.ui.MainWindow;

public class HistorialTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	public static final int OBJECT_COL = -1;
	private DAOManager dao = new DAOManager();
    private List<Historial> examenes = new ArrayList<Historial>();
    private List<HistorialAdmin> examAdmin = new ArrayList<HistorialAdmin>();
    private LocalDate today = MainWindow.today;
    private Usuario usuario;
    

    public HistorialTableModel(Usuario usuario) {
    	this.setUsuario(usuario);
    }

    public void updateModel(Usuario usuario, LocalDate fecha) {
    	if (!usuario.getRol_admin()) {
    		Alumno alumno = dao.getAlumnoDAO().obtenerAlumnoUsuario(usuario.getId_usuario());
    		examenes = dao.getAlumnoDAO().listarExamenes(alumno, fecha);
    	} else {
    		
    	}
    	
    }
    
    public void updateModel(Usuario usuario) {
    	if (!usuario.getRol_admin()) {
    		Alumno alumno = dao.getAlumnoDAO().obtenerAlumnoUsuario(usuario.getId_usuario());
    		examenes = dao.getAlumnoDAO().historialExamenes(alumno, today);
    	} else {
    		examAdmin = dao.getAlumnoDAO().historialAdmin();
    	}
    	
    }
    

    public String getColumnName(int columnIndex){
        if (!usuario.getRol_admin()) {
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
        
        switch(columnIndex){
        case 0:
            return "ID";
        case 1:
            return "Apellido";
        case 2:
        	return "Nombre";
        case 3:
        	return "Carrera";
        case 4:
        	return "Materia";
        case 5:
        	return "Fecha";
        case 6:
        	return "Nota";
        default:
            return "";
    	}
    }

    public int getRowCount() {
    	if (usuario.getRol_admin()) {
    		return examAdmin.size();
    	}
        return examenes.size();
    }

    public int getColumnCount() {
        if (usuario.getRol_admin())
        	return 7;
        
        return 5;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
    	if (!usuario.getRol_admin()) {
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
    	HistorialAdmin seleccion = examAdmin.get(rowIndex);
        switch(columnIndex){
            case 0:
                return seleccion.getExamen_id();
            case 1:
            	return seleccion.getAlumno_apellido();
            case 2:
            	return seleccion.getAlumno_nombre();
            case 3:
                return seleccion.getCarrera_nombre();
            case 4:
            	return seleccion.getMateria_nombre();
            case 5:
            	return seleccion.getFecha();
            case 6: {
            	if (seleccion.getNota() == 0) return "Ausente";
            	return seleccion.getNota();
            }
            	
            case OBJECT_COL : return seleccion;
            default:
                return "";
        }
    	
    }

	public LocalDate getToday() {
		return today;
	}

	public void setToday(LocalDate today) {
		this.today = today;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
