package com.milprogramadores.model;

public class Usuario {

	private Integer id_usuario;
	
	private String email_usuario;
	private Boolean rol_admin;
	
	public Usuario(Integer id_usuario,String email_usuario,Boolean rol_admin) {
		this.id_usuario = id_usuario;
		this.email_usuario = email_usuario;
		this.rol_admin = rol_admin;	
	}

	public Usuario() {
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getEmail_usuario() {
		return email_usuario;
	}

	public Boolean setEmail_usuario(String email_usuario) {
		Boolean resultado = esEmailValido(email_usuario);
		
		if (resultado) {
			this.email_usuario = email_usuario;
			return resultado;
		}		
		return false;
	}
	
	private boolean esEmailValido(String email) {
        String ePattern = "^[a-zA-Z0-9._-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

	public Boolean getRol_admin() {
		return rol_admin;
	}

	public void setRol_admin(Boolean rol_admin) {
		this.rol_admin = rol_admin;
	}
}
