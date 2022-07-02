package com.milprogramadores.model;

import java.util.ArrayList;
import java.util.List;

public class Carrera {
	
	private Integer carrera_id;
	private String nombre;
	private List<Materia> materias;
	
	public Carrera() {
		setMaterias(new ArrayList<Materia>());
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Materia> getMaterias() {
		return materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	public Integer getCarrera_id() {
		return carrera_id;
	}

	public void setCarrera_id(Integer carrera_id) {
		this.carrera_id = carrera_id;
	}
}
