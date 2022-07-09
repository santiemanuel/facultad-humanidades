package com.milprogramadores.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.milprogramadores.model.Carrera;
import com.milprogramadores.model.Materia;
import com.milprogramadores.sql.DbConnection;
import com.milprogramadores.sql.SqlQueries;

public class CarreraDAO {


	
	public CarreraDAO() {
		 
	}
	
	public ArrayList<Carrera> carrerasFaltantes(int id){
		ArrayList<Carrera> carreras = new ArrayList<Carrera>();
		
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.GET_CARRERAS_FALTANTES);
			pstmt.setInt(1, id);
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
	
	public ArrayList<Carrera> carrerasAlumno(int id){
		ArrayList<Carrera> carreras = new ArrayList<Carrera>();
		
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.GET_CARRERAS_ALUMNO);
			pstmt.setInt(1, id);
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
	
	public void insertarMxc(Materia materia, Carrera carrera) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.INSERT_MXC);
			pstmt.setInt(1, materia.getMateria_id());
			pstmt.setInt(2, carrera.getCarrera_id());
			pstmt.execute();
			
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean existeMxc(Materia materia, Carrera carrera) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.MXC_EXISTE);
			pstmt.setInt(1, materia.getMateria_id());
			pstmt.setInt(2, carrera.getCarrera_id());
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			
			if(rs.next()) {
				return true;
			}
			
			rs.close();
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public ArrayList<Materia> materiasDeCarrera(Carrera carrera){
		ArrayList<Materia> materias = new ArrayList<Materia>();
		
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.GET_MATERIAS_CARRERA);
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
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.INSERT_CARRERA);
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
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.UPDATE_CARRERA);
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
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.GET_ONE_CARRERA);
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
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.GET_ALL_CARRERA);
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
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.DELETE_CARRERA);
			pstmt.setInt(1, id);	
			pstmt.executeUpdate();		
			conn.disconnect();		
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean existeCarrera(String nombre) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.CARERRA_EXISTE);
			pstmt.setString(1, nombre);	
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			if(rs.next()) {
				return true;
			}
			conn.disconnect();		
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
