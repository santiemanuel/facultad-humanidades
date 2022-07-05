package com.milprogramadores.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.milprogramadores.model.Alumno;
import com.milprogramadores.model.Carrera;
import com.milprogramadores.model.Materia;
import com.milprogramadores.sql.DbConnection;


public class AlumnoDAO {

	private final String INSERT_ALUMNO = "INSERT INTO alumnos VALUES ( default, ?, ?, ?, ? )";
	private final String DELETE_ALUMNO = "DELETE FROM alumnos WHERE alumno_id = ?";
	private final String UPDATE_ALUMNO = "UPDATE alumnos SET alumno_lu = ?, alumno_nombre = ?, alumno_apellido = ? WHERE alumno_id = ?";
	private final String GET_ONE_ALUMNO = "SELECT * FROM alumnos WHERE alumno_id = ?";
	private final String GET_ALL_ALUMNO = "SELECT * FROM alumnos";
	
	private final String INSERT_AXC = "INSERT INTO alumnosxcarreras (alumno_id, carrera_id) ";
	private final String INSERT_DETMAT = "INSERT INTO detallesmaterias (alumno_id, materia_id) ";
	private final String CARRERA_ID = "SELECT carrera_id from carreras c WHERE c.carrera_nombre = ?";
	private final String MATERIA_ID = "SELECT materia_id from materias c WHERE c.materia_nombre = ?";
	private final String CURSAR_CARRERA = INSERT_AXC + "VALUES (( ? " + "), ( " + CARRERA_ID  + "))";
	private final String CURSAR_MATERIA = INSERT_DETMAT + "VALUES (( ? " + "), ( " + MATERIA_ID + "))";
	private final String INSCRIBIR_EXAMEN = "";
	private final String CANCELAR_EXAMEN = "";
	private final String RENDIR_EXAMEN = "";
	private final String OBTENER_HISTORIAL = "";
		
	public AlumnoDAO() {
		 
	}
	
	public void inscribirCarrera(Alumno alumno, Carrera carrera) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(CURSAR_CARRERA);
			pstmt.setInt(1, alumno.getAlumno_id());
			pstmt.setString(2, carrera.getNombre());
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void inscribirMateria(Alumno alumno, Materia materia) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(CURSAR_MATERIA);
			pstmt.setInt(1, alumno.getAlumno_id());
			pstmt.setString(2, materia.getNombre());
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void agregarAlumno(Alumno alumno) {
		DbConnection conn = new DbConnection();
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(INSERT_ALUMNO);
			
			pstmt.setInt(1, alumno.getAlumno_lu());
			pstmt.setString(2, alumno.getAlumno_nombre());
			pstmt.setString(3, alumno.getAlumno_apellido());
			pstmt.setInt(4, alumno.getId_usuario());
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Requisitos para eliminar un alumno de la base de datos:
	 * - Eliminar su entrada en detallesMaterias
	 * - Eliminar su historial en examenes
	 * - Eliminar su entrada en alumnosXcarreras
	 * - Ya se puede eliminar al alumno de la base
	 * -- Opcional: Eliminar el usuario asociado al alumno
	 */
	public void eliminarAlumno(int id) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(DELETE_ALUMNO);
			pstmt.setInt(1, id);	
			pstmt.executeUpdate();
			pstmt.close();
			conn.disconnect();		
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
		
	public void actualizarAlumno(Alumno alumno) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(UPDATE_ALUMNO);
			
			pstmt.setInt(1, alumno.getAlumno_lu());
			pstmt.setString(2, alumno.getAlumno_nombre());
			pstmt.setString(3, alumno.getAlumno_apellido());
			pstmt.setInt(4, alumno.getAlumno_id());
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Alumno> listarAlumnos(){
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(GET_ALL_ALUMNO);
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				Alumno alumno = new Alumno();
				
				alumno.setAlumno_id(rs.getInt("alumno_id"));
				alumno.setAlumno_lu(rs.getInt("alumno_lu"));
				alumno.setAlumno_nombre(rs.getString("alumno_nombre"));
				alumno.setAlumno_apellido(rs.getString("alumno_apellido"));
				alumno.setId_usuario(rs.getInt("id_usuario"));
				alumnos.add(alumno);
			}
			rs.close();
			pstmt.close();
			conn.disconnect();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return alumnos;
	}
		
	public Alumno obtenerAlumno(int id) {
		DbConnection conn = new DbConnection();
				
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(GET_ONE_ALUMNO);
			pstmt.setInt(1, id);
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			
			if(rs.next()) {
				Alumno alumno = new Alumno();
				
				alumno.setAlumno_id(rs.getInt("alumno_id"));
				alumno.setAlumno_lu(rs.getInt("alumno_lu"));
				alumno.setAlumno_nombre(rs.getString("alumno_nombre"));
				alumno.setAlumno_apellido(rs.getString("alumno_apellido"));
				alumno.setId_usuario(rs.getInt("id_usuario"));
				return alumno;
			}
			
			rs.close();
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}	
}