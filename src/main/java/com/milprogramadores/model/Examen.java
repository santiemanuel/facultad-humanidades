package com.milprogramadores.model;

public class Examen {
	
	private Integer examen_id;
	private String mesa;
	private Byte nota;
	private Integer mesa_examen_id;
	private Integer alumno_id;
	
	private Boolean ausente;
	
	public Examen() {
		setAusente(false);
	}

	public String getMesa() {
		return mesa;
	}

	public void setMesa(String mesa) {
		this.mesa = mesa;
	}

	public Byte getNota() {
		return nota;
	}

	public void setNota(Byte nota) {
		this.nota = nota;
		if (nota == 0)
			setAusente(true);
	}

	public Boolean getAusente() {
		return ausente;
	}

	public void setAusente(Boolean ausente) {
		this.ausente = ausente;
	}

	public Integer getExamen_id() {
		return examen_id;
	}

	public void setExamen_id(Integer examen_id) {
		this.examen_id = examen_id;
	}

	public Integer getMesa_examen_id() {
		return mesa_examen_id;
	}

	public void setMesa_examen_id(Integer mesa_examen_id) {
		this.mesa_examen_id = mesa_examen_id;
	}

	public Integer getAlumno_id() {
		return alumno_id;
	}

	public void setAlumno_id(Integer alumno_id) {
		this.alumno_id = alumno_id;
	}
}
