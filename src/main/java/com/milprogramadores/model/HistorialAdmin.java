package com.milprogramadores.model;

public class HistorialAdmin extends Historial {

	private String alumno_nombre;
	private String alumno_apellido;
	
	public HistorialAdmin() {
		super();
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

}
