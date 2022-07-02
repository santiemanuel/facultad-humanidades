package com.milprogramadores.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

		static String DRIVER = "com.mysql.cj.jdbc.Driver";	
		static String DB = "universidad";
		static String LOGIN = "root";
		static String PASSWORD = "mysqlJava2022!";
		
		static String URL = "jdbc:mysql://localhost/" + DB;
		
		Connection conn = null;
		
		public DbConnection() {
			try {
				conn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public Connection getConnection() {
			return conn;
		}
		
		public void disconnect() {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
		
		
		
}
