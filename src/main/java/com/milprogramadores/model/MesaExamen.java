package com.milprogramadores.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MesaExamen {
	
	private Materia materia;
	private List<Alumno> alumnos;
	
	private Integer mesa_examen_id;
	private LocalDate fecha;
	private Integer mxc_id;
	
	public MesaExamen() {
		setAlumnos(new ArrayList<Alumno>());
	}
	
	public Integer getMesa_examen_id() {
		return mesa_examen_id;
	}

	public void setMesa_examen_id(Integer mesa_examen_id) {
		this.mesa_examen_id = mesa_examen_id;
	}

	public Integer getMxc_id() {
		return mxc_id;
	}

	public void setMxc_id(Integer mxc_id) {
		this.mxc_id = mxc_id;
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
