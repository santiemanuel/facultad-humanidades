package com.milprogramadores.model;

import java.util.ArrayList;
import java.util.List;

public class Certificado {
	
	private String descRegular = "Se certifica que %s %s esta inscripto(/a) como alumno(/a) regular en la propuesta de %s, legajo Nro: %s. A su pedido y para su presentación ante %s se le extiende la presente en SALTA, Salta a los %s.";;
	private List<String> params = new ArrayList<String>();
	private List<Historial> estadoCurricular = new ArrayList<Historial>();
	
	public Certificado(List<String> params) {
		this.params = params;
	}
	
	public Certificado() {
		
	}
	
	public String generarAlumnoRegular() {
		String format = String.format(getDescRegular(), getParams().toArray());
		return format;
	}
	
	public void generarEstadoCurricular(ArrayList<Historial> estadoCurricular) {
		this.estadoCurricular = estadoCurricular;
	}

	public String getDescRegular() {
		return descRegular;
	}

	public void setDescRegular(String descRegular) {
		this.descRegular = descRegular;
	}
	
	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}

	public List<Historial> getEstadoCurricular() {
		return estadoCurricular;
	}

	public void setEstadoCurricular(List<Historial> estadoCurricular) {
		this.estadoCurricular = estadoCurricular;
	}

}
