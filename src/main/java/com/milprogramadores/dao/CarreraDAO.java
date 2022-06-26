package com.milprogramadores.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.milprogramadores.model.Carrera;
import com.milprogramadores.sql.DbConnection;

public class CarreraDAO {

	private final String INSERT_CARRERA = "INSERT INTO carreras VALUES ( default, ? )";
	
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
	
}
