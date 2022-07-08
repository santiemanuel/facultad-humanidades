package com.milprogramadores.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.milprogramadores.model.Alumno;
import com.milprogramadores.model.AlumnoExamen;
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
	
	public ArrayList<AlumnoExamen> examenesCompatibles(ArrayList<Integer> materias_id){
		ArrayList<AlumnoExamen> examenesAlumno = new ArrayList<AlumnoExamen>();
		
		if (materias_id.size() == 0) return examenesAlumno;
		
		DbConnection conn = new DbConnection();
		
		String queryExamenes = SqlQueries.GET_MESA_X_CARRERA;
		
		String query = String.format(queryExamenes, materias_id.stream()
				.map(v -> "?")
				.collect(Collectors.joining(", ")));

		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(query);
			for (int i = 0; i < materias_id.size() ; i++) {
				pstmt.setInt((i + 1), materias_id.get(i));
			}
			
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				AlumnoExamen examen = new AlumnoExamen();
				examen.setMesa_examen_id(rs.getInt("mesa_examen_id"));
				examen.setMateria_nombre(rs.getString("materia_nombre"));
				examen.setFecha(rs.getDate("fecha").toLocalDate());
				examenesAlumno.add(examen);
			}
			rs.close();
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return examenesAlumno;
	}
	
	public ArrayList<Integer> listarMateriasAlumno(Carrera carrera, Alumno alumno) {
		
		ArrayList<Integer> materiasId = new ArrayList<Integer>();
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.GET_MATS_CARRERA);
			pstmt.setInt(1, carrera.getCarrera_id());
			pstmt.setInt(2, alumno.getAlumno_id());
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				Integer mxc_id = Integer.valueOf(rs.getInt("mxc_id"));
				materiasId.add(mxc_id);
			}
			
			rs.close();
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return materiasId;
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
