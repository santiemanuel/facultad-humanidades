package com.milprogramadores.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	private final String UPDATE_EXAMEN = "UPDATE mesas_examen SET fecha = ?, mxc_id = ( " + MXC_ID + ") WHERE mesa_examen_id = ?";
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
	
	public void eliminarMesaExamen(int id) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(DELETE_EXAMEN);
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
		
		System.out.println(UPDATE_EXAMEN);
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(UPDATE_EXAMEN);
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
			PreparedStatement pstmt = conn.getConnection().prepareStatement(GET_ONE_EXAMEN);
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
			PreparedStatement pstmt = conn.getConnection().prepareStatement(GET_ALL_EXAMEN);
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
