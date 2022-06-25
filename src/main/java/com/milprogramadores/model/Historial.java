package com.milprogramadores.model;

import java.util.ArrayList;
import java.util.List;

public class Historial {
	
	private List<Examen> examenes;
	
	public Historial() {
		setExamenes(new ArrayList<Examen>());
	}

	public List<Examen> getExamenes() {
		return examenes;
	}

	public void setExamenes(List<Examen> examenes) {
		this.examenes = examenes;
	}
}
