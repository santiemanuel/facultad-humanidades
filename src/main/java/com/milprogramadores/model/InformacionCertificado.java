package com.milprogramadores.model;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class InformacionCertificado {
	
	private String nombre;
	private String apellido;
	private String carrera;
	private String alumno_lu;
	private String autoridad;
	private String fecha;
	
	public InformacionCertificado() {
		
	}

	public String getNombre() {
		return nombre;
	}
	
	public ArrayList<String> getListInfo() {
		ArrayList<String> lista = new ArrayList<String>();
		lista.add(nombre);
		lista.add(apellido);
		lista.add(carrera);
		lista.add(alumno_lu);
		lista.add(autoridad);
		lista.add(fecha);
		return lista;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getAlumno_lu() {
		return alumno_lu;
	}

	public void setAlumno_lu(String alumno_lu) {
		this.alumno_lu = alumno_lu;
	}

	public String getAutoridad() {
		return autoridad;
	}

	public void setAutoridad(String autoridad) {
		this.autoridad = autoridad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		
		SimpleDateFormat formatFecha = new SimpleDateFormat("dd 'días del mes de' MMMM 'del' yyyy");
		Instant instant = Instant.from(fecha.atStartOfDay(ZoneId.of("GMT-3")));			 
		String format = formatFecha.format(Date.from(instant));
		this.fecha = format;
	}
}
