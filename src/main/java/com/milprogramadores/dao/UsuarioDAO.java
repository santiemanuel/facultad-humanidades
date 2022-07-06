package com.milprogramadores.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.milprogramadores.model.Usuario;

import com.milprogramadores.sql.DbConnection;


 public class UsuarioDAO {

   private final String INSERT_USUARIO  = "INSERT INTO usuarios VALUES ( default, ?, ?)";
   private final String DELETE_USUARIO  = "DELETE FROM usuarios WHERE id_usuario = ?"; 
   private final String UPDATE_USUARIO  = "UPDATE usuarios SET email_usuario = ?, rol_admin = ? WHERE id_usuario = ?";
   private final String GET_ONE_USUARIO = "SELECT * FROM usuarios WHERE id_usuario = ?";
   private final String GET_ONE_USUARIO_EMAIL = "SELECT * FROM usuarios WHERE email_usuario = ?";
   private final String GET_ALL_USUARIO = "SELECT * FROM usuarios";  
	
		
    public UsuarioDAO(){
 
    }
    
    public Usuario usuarioPorCorreo(String correo) {
    	
    	DbConnection conn = new DbConnection();
    	try {
    		PreparedStatement pstmt = conn.getConnection().prepareStatement(GET_ONE_USUARIO_EMAIL);
    
    		pstmt.setString(1, correo);
    		pstmt.execute();
    		
    		ResultSet rs = pstmt.getResultSet();
    		
    		if(rs.next()) {
    			Usuario usuario = new Usuario();
    			
    			usuario.setId_usuario(rs.getInt("id_usuario"));
    			usuario.setEmail_usuario(rs.getString("email_usuario"));
    			usuario.setRol_admin(rs.getBoolean("rol_admin"));
    			return usuario;
    		}
    		
    		pstmt.close();
    		conn.disconnect();
    	} catch (SQLException e) {
    		e.printStackTrace();	
    	}
    	
    	return null;
    }
   
    public void agregarUsuario(Usuario usuario) {
    	DbConnection conn = new DbConnection();
    	
    	try {
    		PreparedStatement pstmt = conn.getConnection().prepareStatement(INSERT_USUARIO);
    
    		pstmt.setString(1, usuario.getEmail_usuario());
    		pstmt.setBoolean(2, usuario.getRol_admin());
    	
    		pstmt.executeUpdate();
    		pstmt.close();
    		conn.disconnect();
    	} catch (SQLException e) {
    		e.printStackTrace();	
    	}
    }
  
    public void eliminarUsuario(int id) {
    	DbConnection conn = new DbConnection();
	
    	try {
    		PreparedStatement pstmt = conn.getConnection().prepareStatement(DELETE_USUARIO);
    		pstmt.setInt(1, id);
    		pstmt.executeUpdate();
    		pstmt.close();
    		conn.disconnect();
    	} catch(SQLException e) {
		 e.printStackTrace(); 
    	}
   }
   
   public void actualizarUsuario(Usuario usuario) {
	   DbConnection conn = new DbConnection();
	 
	   try {
		   PreparedStatement pstmt = conn.getConnection().prepareStatement(UPDATE_USUARIO); 
		   pstmt.setString(1, usuario.getEmail_usuario());
		   pstmt.setBoolean(2, usuario.getRol_admin());
		   pstmt.setInt(3, usuario.getId_usuario());
		
		   pstmt.executeUpdate();
		   pstmt.close();
		   conn.disconnect();
	   } catch (SQLException e) {
		e.printStackTrace(); 
	   }	 
   }
   
   public ArrayList<Usuario> listarUsuarios(){
	   ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	   DbConnection conn = new DbConnection();
	
	   try {
		   PreparedStatement pstmt = conn.getConnection().prepareStatement(GET_ALL_USUARIO);
		   pstmt.execute();
	  
		   ResultSet rs = pstmt.getResultSet();
	  
		   while(rs.next()) {
			   Usuario usuario = new Usuario();  
			   usuario.setId_usuario(rs.getInt("id_usuario"));
			   usuario.setEmail_usuario(rs.getString("email_usuario"));
			   usuario.setRol_admin(rs.getBoolean("rol_admin"));
			   usuarios.add(usuario);  
	  }
	  rs.close();
	  pstmt.close();
	  conn.disconnect();
	  
	} catch (SQLException e) {
		e.printStackTrace();
	}		
	return usuarios;	
				
   }
   
   public Usuario obtenerUsuario(int id) {
    	DbConnection conn = new DbConnection();
    	
    	try {
    		PreparedStatement pstmt = conn.getConnection().prepareStatement(GET_ONE_USUARIO);
    		pstmt.setInt(1, id);
    		pstmt.execute();
    		ResultSet rs = pstmt.getResultSet();
    		
    		if(rs.next()) {
    			Usuario usuario = new Usuario();
    			
    			usuario.setId_usuario(rs.getInt("id_usuario"));
    			usuario.setEmail_usuario(rs.getString("email_usuario"));
    			usuario.setRol_admin(rs.getBoolean("rol_admin"));
    			return usuario;
    		}
    	
    	rs.close();
    	pstmt.close();
    	conn.disconnect();
    }catch(SQLException e) {
        e.printStackTrace();	
    }
    return null;
   } 
}