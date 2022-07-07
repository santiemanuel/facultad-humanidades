package com.milprogramadores.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.milprogramadores.model.Carrera;
import com.milprogramadores.model.Materia;
import com.milprogramadores.model.MesaExamen;
import com.milprogramadores.sql.DbConnection;
import com.milprogramadores.sql.SqlQueries;

public class ExamenDAO {
	
	/*
	 * El DAO se conectará con la tabla mesas_examen para establecer las mesas
	 * disponibles en las que se pueden inscribir los alumnos
	 */
	
	public ExamenDAO() {
		
	}
	
	public void agregarMesaExamen(MesaExamen mesa, Materia materia, Carrera carrera) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.INSERT_EXAMEN);
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
	
	public void eliminarMesaExamen(int id) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.DELETE_EXAMEN);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void actualizarMesaExamen(MesaExamen mesa, Materia materia, Carrera carrera) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.UPDATE_EXAMEN);
			pstmt.setDate(1, java.sql.Date.valueOf(mesa.getFecha()));
			pstmt.setInt(2, materia.getMateria_id());
			pstmt.setInt(3, carrera.getCarrera_id());
			pstmt.setInt(4, mesa.getMesa_examen_id());
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public MesaExamen obtenerMesaExamen(int id) {
		
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.GET_ONE_EXAMEN);
			pstmt.setInt(1, id);
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			
			if(rs.next()) {
				MesaExamen mesa = new MesaExamen();
				mesa.setMesa_examen_id(rs.getInt("mesa_examen_id"));
				mesa.setFecha(rs.getDate("fecha").toLocalDate());
				mesa.setMxc_id(rs.getInt("mxc_id"));
				return mesa;
			}
			
			rs.close();
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<MesaExamen> listarMesaExamen() {
		
		DbConnection conn = new DbConnection();
		
		ArrayList<MesaExamen> mesas = new ArrayList<MesaExamen>();
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.GET_ALL_EXAMEN);
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				MesaExamen mesa = new MesaExamen();
				mesa.setMesa_examen_id(rs.getInt("mesa_examen_id"));
				mesa.setFecha(rs.getDate("fecha").toLocalDate());
				mesa.setMxc_id(rs.getInt("mxc_id"));
				mesas.add(mesa);
			}
			
			rs.close();
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mesas;
	}
	
}
