package com.milprogramadores.model;

import java.util.ArrayList;
import java.util.List;

public class Carrera {
	
	private String nombre;
	private List<Materia> materias;
	
	public Carrera() {
		setMaterias(new ArrayList<Materia>());
	}

	public void agregarMateria(Materia materia) {
		
	}
	
	public void modificarMateria(Materia materia) {
		
	}
	
	public void eliminarMateria(Materia materia) {
		
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
}
