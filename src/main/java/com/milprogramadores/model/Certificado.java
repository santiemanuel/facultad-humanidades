package com.milprogramadores.model;

public class Certificado {
	
	private String descRegular;
	private String descEstadoCurricular;
	private Alumno alumno;
	
	public Certificado(){
		
	}
	
	public void generarAlumnoRegular() {
		
	}
	
	public void generarEstadoCurricular() {
		
	}

	public String getDescRegular() {
		return descRegular;
	}

	public void setDescRegular(String descRegular) {
		this.descRegular = descRegular;
	}

	public String getDescEstadoCurricular() {
		return descEstadoCurricular;
	}

	public void setDescEstadoCurricular(String descEstadoCurricular) {
		this.descEstadoCurricular = descEstadoCurricular;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
}
