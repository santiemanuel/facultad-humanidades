package com.milprogramadores.model;

import java.time.LocalDate;

public class Examen {
	
	private String mesa;
	private Byte nota;
	private LocalDate fecha;
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

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Boolean getAusente() {
		return ausente;
	}

	public void setAusente(Boolean ausente) {
		this.ausente = ausente;
	}
}
