package com.milprogramadores.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.milprogramadores.model.Carrera;
import com.milprogramadores.model.Materia;
import com.milprogramadores.model.MesaExamen;
import com.milprogramadores.sql.DbConnection;

public class ExamenDAO {
	
	/*
	 * El DAO se conectará con la tabla mesas_examen para establecer las mesas
	 * disponibles en las que se pueden inscribir los alumnos
	 */
	
	private final String COND_MAT_CARRERA = "mxc.materia_id = ? and mxc.carrera_id = ? ";
	private final String MXC_ID = "SELECT mxc_id from materiasxcarreras mxc WHERE " + COND_MAT_CARRERA;
	private final String INSERT_EXAMEN = "INSERT INTO mesas_examen VALUES ( default, ?, (" + MXC_ID + ") )";
	private final String DELETE_EXAMEN = "DELETE FROM mesas_examen WHERE mesa_examen_id = ?";
	private final String UPDATE_EXAMEN = "UPDATE mesas_examen SET fecha = ?, mxc_id = ?";
	private final String GET_ONE_EXAMEN = "SELECT * FROM mesas_examen WHERE mesa_examen_id = ?";
	private final String GET_ALL_EXAMEN = "SELECT * FROM mesas_examen";
	
	public ExamenDAO() {
		
	}
	
	public void agregarMesaExamen(MesaExamen mesa, Materia materia, Carrera carrera) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(INSERT_EXAMEN);
			pstmt.setDate(1, java.sql.Date.valueOf(mesa.getFecha()));
			pstmt.setInt(2, materia.getMateria_id());
			pstmt.setInt(3, carrera.getCarrera_id());
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
