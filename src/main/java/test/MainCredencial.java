package test;

import com.milprogramadores.model.Credencial;

public class MainCredencial {

	public static void main(String[] args) {
		
		Credencial cred = new Credencial();
		cred.setSalt_and_hash("passwordNuevo");
		System.out.println("Hash del password : " + cred.getSalt_and_hash());
		
		//Simulacion login
		
		String passIngresado = "passwordN";
		
		System.out.println("La contraseña es: " + cred.checkPassword(passIngresado));
	}

}
