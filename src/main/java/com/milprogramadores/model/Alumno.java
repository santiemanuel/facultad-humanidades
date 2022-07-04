package com.milprogramadores.model;

public class Alumno {
	
	private Facultad facultad;
	private Historial historial;
	
	private Integer alumno_id;
	private Integer alumno_lu;
	private String alumno_nombre;
	private String alumno_apellido;
	private Integer id_usuario;
	
	public Integer getAlumno_id() {
		return alumno_id;
	}

	public void setAlumno_id(Integer alumno_id) {
		this.alumno_id = alumno_id;
	}

	public Integer getAlumno_lu() {
		return alumno_lu;
	}

	public void setAlumno_lu(Integer alumno_lu) {
		this.alumno_lu = alumno_lu;
	}

	public String getAlumno_nombre() {
		return alumno_nombre;
	}

	public void setAlumno_nombre(String alumno_nombre) {
		this.alumno_nombre = alumno_nombre;
	}

	public String getAlumno_apellido() {
		return alumno_apellido;
	}

	public void setAlumno_apellido(String alumno_apellido) {
		this.alumno_apellido = alumno_apellido;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Alumno() {
		
	}
	
	public Facultad getFacultad() {
		return facultad;
	}

	public void setFacultad(Facultad facultad) {
		this.facultad = facultad;
	}

	public Historial getHistorial() {
		return historial;
	}

	public void setHistorial(Historial historial) {
		this.historial = historial;
	}
}
