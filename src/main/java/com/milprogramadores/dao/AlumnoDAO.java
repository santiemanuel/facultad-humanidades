package com.milprogramadores.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.milprogramadores.model.Alumno;
import com.milprogramadores.model.Carrera;
import com.milprogramadores.model.Examen;
import com.milprogramadores.model.Historial;
import com.milprogramadores.model.Materia;
import com.milprogramadores.model.MesaExamen;
import com.milprogramadores.sql.DbConnection;
import com.milprogramadores.sql.SqlQueries;


public class AlumnoDAO {
		
	public AlumnoDAO() {
		 
	}
	
	public Integer nuevaLu() {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.GET_MAX_LU);
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			if (rs.next()) {
				return (rs.getInt("alumno_lu") + 1);
			}
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public Byte obtenerNota(Alumno alumno, Examen examen) {
		
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.OBTENER_NOTA);
			pstmt.setInt(1, alumno.getAlumno_id());
			pstmt.setInt(2, examen.getExamen_id());
			ResultSet rs = pstmt.getResultSet();
			if (rs.next()) {
				return rs.getByte("nota");
			}
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public ArrayList<Historial> historialExamenes(Alumno alumno, LocalDate today) {
		DbConnection conn = new DbConnection();
		ArrayList<Historial> examenes = new ArrayList<Historial>();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.HISTORIAL_EXAMENES);
			pstmt.setInt(1, alumno.getAlumno_id());
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				LocalDate fechaExamen = rs.getDate("fecha").toLocalDate();
				if (today.compareTo(fechaExamen) <= 0)
					continue;
				
				Historial entrada = new Historial();
				entrada.setExamen_id(rs.getInt("examen_id"));
				entrada.setCarrera_nombre(rs.getString("carrera_nombre"));
				entrada.setMateria_nombre(rs.getString("materia_nombre"));
				entrada.setFecha(fechaExamen);
				entrada.setNota(rs.getByte("nota"));
				examenes.add(entrada);
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		}
	
		return examenes;
	}
	
	public ArrayList<Historial> listarExamenes(Alumno alumno, LocalDate fecha) {
		DbConnection conn = new DbConnection();
		ArrayList<Historial> examenes = new ArrayList<Historial>();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.LISTAR_EXAMENES);
			pstmt.setInt(1, alumno.getAlumno_id());
			pstmt.setDate(2, java.sql.Date.valueOf(fecha));
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				Historial entrada = new Historial();
				entrada.setExamen_id(rs.getInt("examen_id"));
				entrada.setCarrera_nombre(rs.getString("carrera_nombre"));
				entrada.setMateria_nombre(rs.getString("materia_nombre"));
				entrada.setFecha(rs.getDate("fecha").toLocalDate());
				entrada.setNota(rs.getByte("nota"));
				examenes.add(entrada);
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		}
	
		return examenes;
	}
	
	public Alumno obtenerAlumnoUsuario(int id) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.GET_ALUMNO_USUARIO);
			pstmt.setInt(1, id);
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			
			if(rs.next()) {
				Alumno alumno = new Alumno();
				
				alumno.setAlumno_id(rs.getInt("alumno_id"));
				alumno.setAlumno_lu(rs.getInt("alumno_lu"));
				alumno.setAlumno_nombre(rs.getString("alumno_nombre"));
				alumno.setAlumno_apellido(rs.getString("alumno_apellido"));
				alumno.setId_usuario(rs.getInt("id_usuario"));
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
	
	public ArrayList<Examen> obtenerHistorial(Alumno alumno) {
		ArrayList<Examen> examenes = new ArrayList<Examen>();
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.OBTENER_HISTORIAL);
			pstmt.setInt(1, alumno.getAlumno_id());
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				Examen examen = new Examen();
				examen.setExamen_id(rs.getInt("examen_id"));
				examen.setNota(rs.getByte("nota"));
				examen.setMesa_examen_id(rs.getInt("mesa_examen_id"));
				examen.setAlumno_id(rs.getInt("alumno_id"));
				examenes.add(examen);
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		}
	
		return examenes;
	}
	
	public Boolean buscarExamenId(ArrayList<Examen> examenes, int idPorInscribir) {
		
		for (Examen e: examenes) {
			if (e.getMesa_examen_id() == idPorInscribir)
				return true;
		}
		return false;
	}
	
	public void rendirExamen(Alumno alumno, Integer examen_id, int nota) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.RENDIR_EXAMEN);
			pstmt.setInt(1, nota);
			pstmt.setInt(2, alumno.getAlumno_id());
			pstmt.setInt(3, examen_id);
			pstmt.executeUpdate();
		}  catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void inscribirExamen(Alumno alumno, MesaExamen mesa) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.INSCRIBIR_EXAMEN);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, mesa.getMesa_examen_id());
			pstmt.setInt(3, alumno.getAlumno_id());
			pstmt.executeUpdate();
		}  catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void cancelarExamen(Alumno alumno, Integer examen_id) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.CANCELAR_EXAMEN);
			pstmt.setInt(1, alumno.getAlumno_id());
			pstmt.setInt(2, examen_id);
			pstmt.executeUpdate();
		}  catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void inscribirCarrera(Alumno alumno, Carrera carrera) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.CURSAR_CARRERA);
			pstmt.setInt(1, alumno.getAlumno_id());
			pstmt.setString(2, carrera.getNombre());
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void inscribirMateria(Alumno alumno, Materia materia) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.CURSAR_MATERIA);
			pstmt.setInt(1, alumno.getAlumno_id());
			pstmt.setString(2, materia.getNombre());
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Integer alumnoPorLu(Integer lu) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.ALUMNO_POR_LU);
			pstmt.setInt(1, lu);
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			if (rs.next()) {
				return (rs.getInt("alumno_id"));
			}
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public void agregarAlumno(Alumno alumno) {
		DbConnection conn = new DbConnection();
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.INSERT_ALUMNO);
			
			pstmt.setInt(1, alumno.getAlumno_lu());
			pstmt.setString(2, alumno.getAlumno_nombre());
			pstmt.setString(3, alumno.getAlumno_apellido());
			pstmt.setInt(4, alumno.getId_usuario());
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Requisitos para eliminar un alumno de la base de datos:
	 * - Eliminar su entrada en detallesMaterias
	 * - Eliminar su historial en examenes
	 * - Eliminar su entrada en alumnosXcarreras
	 * - Ya se puede eliminar al alumno de la base
	 * -- Opcional: Eliminar el usuario asociado al alumno
	 */
	public void eliminarAlumno(int id) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.DELETE_ALUMNO);
			pstmt.setInt(1, id);	
			pstmt.executeUpdate();
			pstmt.close();
			conn.disconnect();		
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
		
	public void actualizarAlumno(Alumno alumno) {
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.UPDATE_ALUMNO);
			
			pstmt.setInt(1, alumno.getAlumno_lu());
			pstmt.setString(2, alumno.getAlumno_nombre());
			pstmt.setString(3, alumno.getAlumno_apellido());
			pstmt.setInt(4, alumno.getAlumno_id());
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Alumno> listarAlumnos(){
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		
		DbConnection conn = new DbConnection();
		
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.GET_ALL_ALUMNO);
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			
			while(rs.next()) {
				Alumno alumno = new Alumno();
				
				alumno.setAlumno_id(rs.getInt("alumno_id"));
				alumno.setAlumno_lu(rs.getInt("alumno_lu"));
				alumno.setAlumno_nombre(rs.getString("alumno_nombre"));
				alumno.setAlumno_apellido(rs.getString("alumno_apellido"));
				alumno.setId_usuario(rs.getInt("id_usuario"));
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
		
	public Alumno obtenerAlumno(int id) {
		DbConnection conn = new DbConnection();
				
		try {
			PreparedStatement pstmt = conn.getConnection().prepareStatement(SqlQueries.GET_ONE_ALUMNO);
			pstmt.setInt(1, id);
			pstmt.execute();
			ResultSet rs = pstmt.getResultSet();
			
			if(rs.next()) {
				Alumno alumno = new Alumno();
				
				alumno.setAlumno_id(rs.getInt("alumno_id"));
				alumno.setAlumno_lu(rs.getInt("alumno_lu"));
				alumno.setAlumno_nombre(rs.getString("alumno_nombre"));
				alumno.setAlumno_apellido(rs.getString("alumno_apellido"));
				alumno.setId_usuario(rs.getInt("id_usuario"));
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