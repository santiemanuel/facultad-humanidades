package com.milprogramadores.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.milprogramadores.model.Carrera;
import com.milprogramadores.model.Materia;
import com.milprogramadores.sql.DbConnection;

public class CarreraDAO {

	private final String INSERT_CARRERA = "INSERT INTO carreras VALUES ( default, ? )";
	private final String DELETE_CARRERA = "DELETE FROM carreras WHERE carrera_id = ?";
	private final String UPDATE_CARRERA = "UPDATE carreras SET carrera_nombre = ?";
	private final String GET_ONE_CARRERA = "SELECT * FROM carreras WHERE carrera_id = ?";
	private final String GET_ALL_CARRERA = "SELECT * FROM carreras";
	private final String GET_MATERIAS_CARRERA = "SELECT * " +
												"FROM materias m " +
												"INNER JOIN materiasxcarreras mxc " +
												"ON m.materia_id = mxc.materia_id " +
												"INNER JOIN carreras c " +
												"ON c.carrera_id = mxc.carrera_id " +
												"WHERE c.carrera_nombre = ?";
	
	public CarreraDAO() {
		 
	}
	
	public ArrayList<Materia> materiasDeCarrera(Carrera carrera){
		ArrayList<Materia> materias = new ArrayList<Materia>();
		
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(GET_MATERIAS_CARRERA);
			pstmt.setString(1, carrera.getNombre());
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				Materia materia = new Materia();
				materia.setMateria_id(rs.getInt("materia_id"));
				materia.setNombre(rs.getString("materia_nombre"));
				materias.add(materia);
			}
			rs.close();
			pstmt.close();
			conn.disconnect();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return materias;
	}
	
	public void agregarCarrera(Carrera carrera) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(INSERT_CARRERA);
			pstmt.setString(1, carrera.getNombre());
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void actualizarCarrera(Carrera carrera) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(UPDATE_CARRERA);
			pstmt.setString(1, carrera.getNombre());
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Carrera obtenerCarrera(int id) {
		DbConnection conn = new DbConnection();
		
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(GET_ONE_CARRERA);
			pstmt.setInt(1, id);
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			
			if(rs.next()) {
				Carrera carrera = new Carrera();
				carrera.setCarrera_id(rs.getInt("carrera_id"));
				carrera.setNombre(rs.getString("carrera_nombre"));
				return carrera;
			}
			
			rs.close();
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<Carrera> listarCarreras(){
		ArrayList<Carrera> carreras = new ArrayList<Carrera>();
		
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(GET_ALL_CARRERA);
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				Carrera carrera = new Carrera();
				carrera.setCarrera_id(rs.getInt("carrera_id"));
				carrera.setNombre(rs.getString("carrera_nombre"));
				carreras.add(carrera);
			}
			rs.close();
			pstmt.close();
			conn.disconnect();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return carreras;
	}
	
	public void eliminarCarrera(int id) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(DELETE_CARRERA);
			pstmt.setInt(1, id);	
			pstmt.executeUpdate();		
			conn.disconnect();		
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
