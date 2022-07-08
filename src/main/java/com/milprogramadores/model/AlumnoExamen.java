package com.milprogramadores.model;

import java.time.LocalDate;

public class AlumnoExamen {

	private Integer mesa_examen_id;
	private String materia_nombre;
	private LocalDate fecha;
	
	public AlumnoExamen() {
		
	}

	public Integer getMesa_examen_id() {
		return mesa_examen_id;
	}

	public void setMesa_examen_id(Integer mesa_examen_id) {
		this.mesa_examen_id = mesa_examen_id;
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
}
