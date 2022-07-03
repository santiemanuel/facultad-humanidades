package com.milprogramadores.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.milprogramadores.model.Carrera;
import com.milprogramadores.model.Materia;
import com.milprogramadores.sql.DbConnection;

public class MateriaDAO {
	

		private final String INSERT_MATERIA = "INSERT INTO materias VALUES ( default, ? )";
		private final String DELETE_MATERIA = "DELETE FROM materias WHERE carrera_id = ?";
		private final String UPDATE_MATERIA = "UPDATE materias SET materia_nombre = ?";
		private final String GET_ONE_MATERIA = "SELECT * FROM materias WHERE materia_id = ?";
		private final String GET_ALL_MATERIA = "SELECT * FROM materias";
		private final String GET_MATERIA_MATERIA = "SELECT * " +
													"FROM materias m " +
													"INNER JOIN materiasxcarreras mxc " +
													"ON m.materia_id = mxc.materia_id " +
													"INNER JOIN carreras c " +
													"ON c.carrera_id = mxc.carrera_id " +
													"WHERE c.carrera_nombre = ?";
		
		public MateriaDAO() {
			 
		}
		
		public ArrayList<Materia> materiasDeMateria(Materia materia){
			ArrayList<Materia> materias = new ArrayList<Materia>();
			
			DbConnection conn = new DbConnection();
			
			try {
				PreparedStatement pstmt = conn.getConnection().prepareStatement(GET_MATERIA_MATERIA);
				pstmt.setString(1, materia.getNombre());
				pstmt.execute();
				ResultSet rs = pstmt.getResultSet();
				
				while(rs.next()) {
					Materia carrera = new Materia();
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
		
		public void agregarMateria(Materia materia) {
			DbConnection conn = new DbConnection();
			
			try {
				PreparedStatement pstmt = conn.getConnection().prepareStatement(INSERT_MATERIA);
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
				PreparedStatement pstmt = conn.getConnection().prepareStatement(UPDATE_MATERIA);
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
				PreparedStatement pstmt = conn.getConnection().prepareStatement(GET_ONE_MATERIA);
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
		
		public ArrayList<Materia> listarMateria(){
			ArrayList<Materia> materias = new ArrayList<Materia>();
			
			DbConnection conn = new DbConnection();
			
			try {
				PreparedStatement pstmt = conn.getConnection().prepareStatement(GET_ALL_MATERIA);
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
		
		public void eliminarMateria(int id) {
			DbConnection conn = new DbConnection();
			
			try {
				PreparedStatement pstmt = conn.getConnection().prepareStatement(DELETE_MATERIA);
				pstmt.setInt(1, id);	
				pstmt.executeUpdate();		
				conn.disconnect();		
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}

}
