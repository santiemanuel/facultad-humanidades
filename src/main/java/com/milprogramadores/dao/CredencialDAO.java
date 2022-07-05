package com.milprogramadores.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.milprogramadores.model.Credencial;
import com.milprogramadores.model.Usuario;
import com.milprogramadores.sql.DbConnection;


public class CredencialDAO {
 
	private final String USUARIO_ID = "SELECT id_usuario FROM usuarios WHERE usuarios.email_usuario = ?";
	private final String INSERT_CREDENCIAL = "INSERT INTO credenciales VALUES (default, ?, ?, ( "+ USUARIO_ID +" ))";
	private final String DELETE_CREDENCIAL = "DELETE FROM credenciales WHERE usuario_id = ?";
	private final String UPDATE_CREDENCIAL = "UPDATE credenciales SET salt_and_hash = ?, pass_algo = ? WHERE id_usuario = ?";
	private final String GET_ONE_CREDENCIAL = "SELECT * FROM credenciales WHERE credencial_id = ?";
	private final String GET_ALL_CREDENCIAL = "SELECT * FROM credenciales";

	
	public CredencialDAO() {
	
	}
	
	public void agregarCredencial(Credencial credencial, Usuario usuario) {
		
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(INSERT_CREDENCIAL);
			
			pstmt.setString(1, credencial.getSalt_and_hash());
			pstmt.setString(2, credencial.getPass_algo());
			pstmt.setString(3, usuario.getEmail_usuario());
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
		     e.printStackTrace();
		}
	}
	
	public void eliminarCredencial(int id) {
		DbConnection conn = new DbConnection();
		
		try {
		    PreparedStatement pstmt = conn.getConnection().prepareStatement(DELETE_CREDENCIAL);
		    pstmt.setInt(1, id);
		    
		    pstmt.executeUpdate();
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void actualizarCredencial(Credencial credencial) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(UPDATE_CREDENCIAL);
			
			pstmt.setString(1, credencial.getSalt_and_hash());
			pstmt.setString(2, credencial.getPass_algo());
			pstmt.setInt(3, credencial.getId_credencial());
			
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Credencial> listarCredenciales(){
		ArrayList<Credencial> credenciales = new ArrayList<Credencial>();
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(GET_ALL_CREDENCIAL);
			pstmt.execute();
			
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				Credencial credencial = new Credencial();
			 
				credencial.setId_credencial(rs.getInt("id_credencial"));
				credencial.setSalt_and_hash(rs.getString("salt_and_hash_id"));
				credencial.setPass_algo(rs.getString("pass_algo"));
				credencial.setId_usuario(rs.getInt("id_usuario"));
				credenciales.add(credencial);
			}
			rs.close();
			pstmt.close();
			conn.disconnect();
		
		} catch(SQLException e) {
			e.printStackTrace();	
		}
		return credenciales;		
	}
	
	public Credencial obtenerCredencial(int id) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(GET_ONE_CREDENCIAL);
			pstmt.setInt(1, id);
			
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			
			if(rs.next()) {
				Credencial credencial = new Credencial();
				
				credencial.setId_credencial(rs.getInt("id_credencial"));
				credencial.setSalt_and_hash(rs.getString("salt_and_hash_id"));
				credencial.setPass_algo(rs.getString("pass_algo"));
				credencial.setId_usuario(rs.getInt("id_usuario"));
				return credencial;
			}
			rs.close();
			pstmt.close();
			conn.disconnect();		
		} catch(SQLException e) {
		e.printStackTrace();
		}
		return null;	
	}
}