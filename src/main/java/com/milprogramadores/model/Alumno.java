package com.milprogramadores.model;

public class Alumno {
	
	private Facultad facultad;
	private Historial historial;
	
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
