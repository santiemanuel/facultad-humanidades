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

	private final String INSERT_ALUMNO = "INSERT INTO alumnos VALUES ( default, ?, ?, ?, ?  )";
	private final String DELETE_ALUMNO = "DELETE FROM alumno WHERE alumno_id = ?)";
	private final String UPDATE_ALUMNO = "UPDATE alumno SET alumno_lu=? alumno_nombre=? alumno_apellido=? id_usuario=?";
	private final String GET_ONE_ALUMNO = "SELECT * FROM carreras WHERE carrera_id = ?";
	private final String GET_ALL_ALUMNO = "SELECT * FROM carreras";
	
	
	
	public AlumnoDAO() {
		 
	}
	
//-----------------------------------------------------------------------------------------------------------------------------------------	
	
	public void agregarAlumno(Alumno alumno) {
		DbConnection conn = new DbConnection();
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(INSERT_ALUMNO);
//----------------------------------------------------------------------			
			pstmt.setInt(1, alumno.getAlumno_lu());
//-----------------------------------------------------------------------			
			pstmt.setString(2,alumno.getAlumno_nombre());
//------------------------------------------------------------------------
			pstmt.setString(3,alumno.getAlumno_apellido());
//----------------------------------------------------------
			pstmt.setInt(4,alumno.getId_usuario());
//-------------------------------------------------------------------		
			pstmt.executeUpdate();
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------	
	
	public void eliminarAlumno(int id) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(DELETE_ALUMNO);
//--------------------------------------------------------------------------------------------------------			
			pstmt.setInt(1, id);	
//--------------------------------------------------------------------------------------------------------
			pstmt.executeUpdate();	
			pstmt.close();
			conn.disconnect();		
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
//----------------------------------------------------------------------------------------------------------------------------------------------	
	
	public void actualizarAlumno(Alumno alumno) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(UPDATE_ALUMNO);
			pstmt.setInt(1, alumno.getAlumno_lu());
			
//-----------------------------------------------------------------------------------
			pstmt.setString(2, alumno.getAlumno_nombre());
//-------------------------------------------------------------------------------------
			pstmt.setString(3, alumno.getAlumno_apellido());
//---------------------------------------------------------------------------------
			pstmt.setInt(4, alumno.getAlumno_id());
//------------------------------------------------------------------------------------
			pstmt.executeUpdate();
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//--------------------------------------------------------------------------------------------------------------------------------------	
	
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
				alumno.setAlumno_nombre(rs.getString("alumno_nombre"));
				alumno.setAlumno_apellido(rs.getString("apellido_nombre"));
				alumno.setAlumno_lu(rs.getInt("alumno_lu"));
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
	
//---------------------------------------------------------------------------------	
	
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
				alumno.setAlumno_nombre(rs.getString("alumno_nombre"));
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