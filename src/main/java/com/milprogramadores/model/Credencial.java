package com.milprogramadores.model;

import com.lambdaworks.crypto.SCryptUtil;

public class Credencial {

	private Integer id_credencial;
	
	private String salt_and_hash;
	private String pass_algo;
	private Integer id_usuario;
	
	public Credencial(Integer id_usuario, String salt_and_hash, String pass_algo, Integer id_credencial) {
		this.id_usuario = id_usuario;
		this.salt_and_hash = salt_and_hash;
		this.pass_algo = pass_algo;
		this.id_credencial = id_credencial;
	}

   public Credencial() {
   }

   public Integer getId_usuario() {
	   return id_usuario;
   }

   public void setId_usuario(Integer id_usuario) {
	  this.id_usuario = id_usuario;
   }

   public String getSalt_and_hash() {
	  return salt_and_hash;
   }

   public void setSalt_and_hash(String pass) {
	   this.salt_and_hash = SCryptUtil.scrypt(pass, 16384, 8, 1);
	   this.pass_algo = "scrypt";
   }
   
   public void setHash(String pass) {
	      this.salt_and_hash = pass;
   }
   
   public boolean checkPassword(String pass) {
	   return SCryptUtil.check(pass, salt_and_hash);
   }

   public String getPass_algo() {
	  return pass_algo;
   }

   public void setPass_algo(String pass_algo) {
	  this.pass_algo = pass_algo;
   }

   public Integer getId_credencial() {
	   return id_credencial;
   }

   public void setId_credencial(Integer id_credencial) {
	   this.id_credencial = id_credencial;
   }
	
}
