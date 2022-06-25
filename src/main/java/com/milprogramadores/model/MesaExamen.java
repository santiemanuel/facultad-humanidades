package com.milprogramadores.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MesaExamen {
	
	private Materia materia;
	private List<Alumno> alumnos;
	private LocalDate fecha;
	
	public MesaExamen() {
		setAlumnos(new ArrayList<Alumno>());
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
}
