package com.milprogramadores.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.milprogramadores.model.Carrera;
import com.milprogramadores.model.Materia;
import com.milprogramadores.sql.DbConnection;
import com.milprogramadores.sql.SqlQueries;

public class MateriaDAO {
	
		public MateriaDAO() {
			 
		}
		
		public Boolean existeMxc(Materia materia, Carrera carrera) {
			DbConnection conn = new DbConnection();
			
			try {
				PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.MXC_ID);
				pstmt.setInt(1, materia.getMateria_id());
				pstmt.setInt(2, carrera.getCarrera_id());
				pstmt.execute();
				ResultSet rs = pstmt.getResultSet();
				if (rs.next())
					return true;
				rs.close();
				pstmt.close();
				conn.disconnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return false;
		}
		
		public ArrayList<Materia> materiasFaltantes(int alumno_id, int carrera_id){
			ArrayList<Materia> materias = new ArrayList<Materia>();
			
			DbConnection conn = new DbConnection();
			
			try {
				PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.GET_MATERIAS_FALTANTES);
				pstmt.setInt(1, alumno_id);
				pstmt.setInt(2, carrera_id);
				pstmt.setInt(3, carrera_id);
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
		
		public ArrayList<Materia> materiasAlumno(int id_alumno, int id_carrera){
			ArrayList<Materia> materias = new ArrayList<Materia>();
			
			DbConnection conn = new DbConnection();
			
			try {
				PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.GET_MATERIAS_ALUMNO);
				pstmt.setInt(1, id_alumno);
				pstmt.setInt(2, id_carrera);
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
		
		
		public ArrayList<Carrera> carrerasDeMateria(Materia materia){
			ArrayList<Carrera> carreras = new ArrayList<Carrera>();
			
			DbConnection conn = new DbConnection();
			
			try {
				PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.GET_CARRERAS_MATERIA);
				pstmt.setString(1, materia.getNombre());
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
		
		public void agregarMateria(Materia materia) {
			DbConnection conn = new DbConnection();
			
			try {
				PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.INSERT_MATERIA);
				pstmt.setString(1, materia.getNombre());
				pstmt.executeUpdate();
				
				pstmt.close();
				conn.disconnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		public void actualizarMateria(Materia materia) {
			DbConnection conn = new DbConnection();
			
			try {
				PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.UPDATE_MATERIA);
				pstmt.setString(1, materia.getNombre());
				pstmt.executeUpdate();
				
				pstmt.close();
				conn.disconnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public Materia obtenerMateria(int id) {
			DbConnection conn = new DbConnection();
				
			try {
				PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.GET_ONE_MATERIA);
				pstmt.setInt(1, id);
				pstmt.execute();
				ResultSet rs = pstmt.getResultSet();
				
				if(rs.next()) {
					Materia materia = new Materia();
					materia.setMateria_id(rs.getInt("materia_id"));
					materia.setNombre(rs.getString("materia_nombre"));
					return materia;
				}
				
				rs.close();
				pstmt.close();
				conn.disconnect();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		public ArrayList<Materia> listarMaterias(){
			ArrayList<Materia> materias = new ArrayList<Materia>();
			
			DbConnection conn = new DbConnection();
			
			try {
				PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.GET_ALL_MATERIA);
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
		
		public Boolean existeMateria(String nombre) {
			DbConnection conn = new DbConnection();
			
			try {
				PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.MATERIA_EXISTE);
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
		
		public Integer materiaPorNombre(String nombre) {
			DbConnection conn = new DbConnection();
			
			try {
				PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.MATERIA_NOMBRE);
				pstmt.setString(1, nombre);	
				pstmt.execute();
				ResultSet rs = pstmt.getResultSet();
				if(rs.next()) {
					return (rs.getInt("materia_id"));
				}
				conn.disconnect();		
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			return -1;
		}
		
		public void eliminarMateria(int id) {
			DbConnection conn = new DbConnection();
			
			try {
				PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.DELETE_MATERIA);
				pstmt.setInt(1, id);
				pstmt.executeUpdate();
				conn.disconnect();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}

}
