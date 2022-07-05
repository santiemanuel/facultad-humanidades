package com.milprogramadores.sql;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {

		Connection conn = null;
		
		public DbConnection() {
			
			Properties prop = new Properties();
			
			String propFile = new String("JBDCConnection.properties");
			
			try {
				FileReader reader = new FileReader(propFile);
				prop.load(reader);
			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String dbConnUrl = prop.getProperty("db.conn.url");
			String dbUser = prop.getProperty("db.username");
			String dbPassword = prop.getProperty("db.password");
			
			try {
				conn = DriverManager.getConnection(dbConnUrl, dbUser, dbPassword);
				
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
