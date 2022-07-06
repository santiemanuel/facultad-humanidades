package com.milprogramadores.dao;

public class DAOManager {
	
	private static AlumnoDAO alumnos = null;
	private static CarreraDAO carreras = null;
	private static CredencialDAO credenciales = null;
	private static ExamenDAO examenes = null;
	private static MateriaDAO materias = null;
	private static UsuarioDAO usuarios = null;
	
	public DAOManager() {
		
	}
	
	public AlumnoDAO getAlumnoDAO() {
		if (alumnos == null)
			alumnos = new AlumnoDAO();
		return alumnos;
	}

	public CarreraDAO getCarreraDAO() {
		if (carreras == null)
			carreras = new CarreraDAO();
		return carreras;
	}

	public CredencialDAO getCredencialDAO() {
		if (credenciales == null)
			credenciales = new CredencialDAO();
		return credenciales;
	}

	public ExamenDAO getExamenDAO() {
		if (examenes == null)
			examenes = new ExamenDAO();
		return examenes;
	}

	public MateriaDAO getMateriDAO() {
		if (materias == null)
			materias = new MateriaDAO();
		return materias;
	}

	public UsuarioDAO getUsuarioDAO() {
		if (usuarios == null)
			usuarios = new UsuarioDAO();
		return usuarios;
	}
}
