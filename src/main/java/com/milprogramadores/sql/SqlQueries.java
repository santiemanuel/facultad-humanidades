package com.milprogramadores.sql;

public class SqlQueries {

	/*
	 * Consultas DAO Alumno
	 */
		public static String INSERT_ALUMNO = "INSERT INTO alumnos VALUES ( default, ?, ?, ?, ? )";
		public static String DELETE_ALUMNO = "DELETE FROM alumnos WHERE alumno_id = ?";
		public static String UPDATE_ALUMNO = "UPDATE alumnos SET alumno_lu = ?, alumno_nombre = ?, alumno_apellido = ? WHERE alumno_id = ?";
		public static String GET_ONE_ALUMNO = "SELECT * FROM alumnos WHERE alumno_id = ?";
		public static String GET_ALUMNO_USUARIO = "SELECT * FROM alumnos WHERE id_usuario = ?";
		public static String GET_ALL_ALUMNO = "SELECT * FROM alumnos";
		
		public static String INSERT_AXC = "INSERT INTO alumnosxcarreras (alumno_id, carrera_id) ";
		public static String INSERT_DETMAT = "INSERT INTO detallesmaterias (alumno_id, materia_id) ";
		public static String CARRERA_ID = "SELECT carrera_id from carreras c WHERE c.carrera_nombre = ?";
		public static String MATERIA_ID = "SELECT materia_id from materias c WHERE c.materia_nombre = ?";
		public static String CURSAR_CARRERA = INSERT_AXC + "VALUES (( ? " + "), ( " + CARRERA_ID  + "))";
		public static String CURSAR_MATERIA = INSERT_DETMAT + "VALUES (( ? " + "), ( " + MATERIA_ID + "))";
		public static String INSCRIBIR_EXAMEN = "INSERT INTO examenes VALUES ( default, ?, ?, ?)";
		public static String CANCELAR_EXAMEN = "DELETE FROM examenes WHERE alumno_id = ? and mesa_examen_id = ?";
		public static String RENDIR_EXAMEN = "UPDATE examenes SET nota = ? WHERE alumno_id = ? and mesa_examen_id = ?";
		public static String OBTENER_HISTORIAL = "SELECT * FROM examenes WHERE alumno_id = ?";
	/*
	 * Consultas DAO Carrera
	 */
		public static String INSERT_CARRERA = "INSERT INTO carreras VALUES ( default, ? )";
		public static String DELETE_CARRERA = "DELETE FROM carreras WHERE carrera_id = ?";
		public static String UPDATE_CARRERA = "UPDATE carreras SET carrera_nombre = ?";
		public static String GET_ONE_CARRERA = "SELECT * FROM carreras WHERE carrera_id = ?";
		public static String INNER_AXC = "INNER JOIN alumnosxcarreras ac ON ac.carrera_id = c.carrera_id ";
		public static String INNER_ALU = "INNER JOIN alumnos a ON a.alumno_id = ac.alumno_id WHERE a.alumno_id = ?";
		public static String GET_CARRERAS_ALUMNO = "SELECT c.carrera_id, c.carrera_nombre FROM carreras c " + INNER_AXC + INNER_ALU;
		public static String CARRERAS_C =  "SELECT c.carrera_id from carreras c ";
		public static String CARRERAS_N =  "SELECT c.carrera_id, c.carrera_nombre from carreras c ";
		public static String GET_CARRERAS_FALTANTES = CARRERAS_N + "WHERE c.carrera_id NOT IN (" + CARRERAS_C + INNER_AXC + INNER_ALU + ")";
		public static String GET_ALL_CARRERA = "SELECT * FROM carreras";
		public static String GET_MATERIAS_CARRERA = "SELECT * " +
													"FROM materias m " +
													"INNER JOIN materiasxcarreras mxc " +
													"ON m.materia_id = mxc.materia_id " +
													"INNER JOIN carreras c " +
													"ON c.carrera_id = mxc.carrera_id " +
													"WHERE c.carrera_nombre = ?";
	
	/*
	 * Consulas DAO Credencial
	 */
	
		public static String USUARIO_ID = "SELECT id_usuario FROM usuarios WHERE usuarios.email_usuario = ?";
		public static String INSERT_CREDENCIAL = "INSERT INTO credenciales VALUES (default, ?, ?, ( "+ USUARIO_ID +" ))";
		public static String DELETE_CREDENCIAL = "DELETE FROM credenciales WHERE id_usuario = ?";
		public static String UPDATE_CREDENCIAL = "UPDATE credenciales SET salt_and_hash = ?, pass_algo = ? WHERE id_usuario = ?";
		public static String GET_CREDENCIAL_USUARIO = "SELECT * FROM credenciales WHERE id_usuario = ?";
		public static String GET_ONE_CREDENCIAL = "SELECT * FROM credenciales WHERE id_credencial = ?";
		public static String GET_ALL_CREDENCIAL = "SELECT * FROM credenciales";
		
		
	/*
	 * Consultas DAO Examen
	 */
	
		public static String COND_MAT_CARRERA = "mxc.materia_id = ? and mxc.carrera_id = ? ";
		public static String MXC_ID = "SELECT mxc_id from materiasxcarreras mxc WHERE " + COND_MAT_CARRERA;
		public static String INSERT_EXAMEN = "INSERT INTO mesas_examen VALUES ( default, ?, (" + MXC_ID + ") )";
		public static String DELETE_EXAMEN = "DELETE FROM mesas_examen WHERE mesa_examen_id = ?";
		public static String UPDATE_EXAMEN = "UPDATE mesas_examen SET fecha = ?, mxc_id = ( " + MXC_ID + ") WHERE mesa_examen_id = ?";
		public static String GET_ONE_EXAMEN = "SELECT * FROM mesas_examen WHERE mesa_examen_id = ?";
		public static String GET_ALL_EXAMEN = "SELECT * FROM mesas_examen";	
		
	/*
	 * Consultas DAO Materia
	 */
		public static String INSERT_MATERIA = "INSERT INTO materias VALUES ( default, ? )";
		public static String DELETE_MATERIA = "DELETE FROM materias WHERE materia_id = ?";
		public static String UPDATE_MATERIA = "UPDATE materias SET materia_nombre = ?";
		public static String GET_ONE_MATERIA = "SELECT * FROM materias WHERE materia_id = ?";
		
		public static String INNER_DM = "INNER JOIN detallesmaterias dm ON dm.materia_id = m.materia_id ";
		public static String GET_MATERIAS_ALUMNO = "SELECT m.materia_id, m.materia_nombre FROM materias m " + INNER_DM + INNER_ALU;
		public static String GET_ALL_MATERIA = "SELECT * FROM materias";
		public static String GET_CARRERAS_MATERIA = "SELECT * " +
													"FROM carreras c " +
													"INNER JOIN materiasxcarreras mxc " +
													"ON c.carrera_id = mxc.carrera_id " +
													"INNER JOIN materias m " +
													"ON m.materia_id = mxc.materia_id " +
													"WHERE m.materia_nombre = ?";
		
	/*
	 * Consultas DAO Usuario
	 */
	
		public static String INSERT_USUARIO  = "INSERT INTO usuarios VALUES ( default, ?, ?)";
		public static String DELETE_USUARIO  = "DELETE FROM usuarios WHERE id_usuario = ?"; 
		public static String UPDATE_USUARIO  = "UPDATE usuarios SET email_usuario = ?, rol_admin = ? WHERE id_usuario = ?";
		public static String GET_ONE_USUARIO = "SELECT * FROM usuarios WHERE id_usuario = ?";
		public static String GET_ONE_USUARIO_EMAIL = "SELECT * FROM usuarios WHERE email_usuario = ?";
		public static String GET_ALL_USUARIO = "SELECT * FROM usuarios";
		
		
}
