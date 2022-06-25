package com.milprogramadores.model;

import java.util.ArrayList;
import java.util.List;

public class Facultad {
	
	private String nombre;
	private List<Carrera> carreras;
	
	public Facultad() {
		setCarreras(new ArrayList<Carrera>());
	}
	
	public void agregarMateria(Materia materia) {
		
	}
	
	public void agregarCarrera(Carrera carrera) {
		
	}
	
	public void inscribir(Alumno alumno, Carrera carrera) {
		
	}
	
	public void modificar(Alumno alumno) {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Carrera> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<Carrera> carreras) {
		this.carreras = carreras;
	}
	
	public String toString() {
		return "Esta es la facultad de " + getNombre();
	}
	
}
