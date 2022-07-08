package com.milprogramadores.model;

import java.time.LocalDate;

public class Historial {
	
	private Integer examen_id;
	private String carrera_nombre;
	private String materia_nombre;
	private LocalDate fecha;
	private Byte nota;
	
	public Historial() {
		
	}

	public Integer getExamen_id() {
		return examen_id;
	}

	public void setExamen_id(Integer examen_id) {
		this.examen_id = examen_id;
	}

	public String getCarrera_nombre() {
		return carrera_nombre;
	}

	public void setCarrera_nombre(String carrera_nombre) {
		this.carrera_nombre = carrera_nombre;
	}

	public String getMateria_nombre() {
		return materia_nombre;
	}

	public void setMateria_nombre(String materia_nombre) {
		this.materia_nombre = materia_nombre;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Byte getNota() {
		return nota;
	}

	public void setNota(Byte nota) {
		this.nota = nota;
	}
}
