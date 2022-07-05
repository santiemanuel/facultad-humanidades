package test;

import com.milprogramadores.dao.CredencialDAO;
import com.milprogramadores.model.Credencial;

public class MainDAOComprobarPass {

	public static void main(String[] args) {
		
		String pass = "Aldo123";
		
		Credencial credencial = new Credencial();
		CredencialDAO dao = new CredencialDAO();
		credencial = dao.obtenerCredencial(1);
		
		System.out.println(credencial.checkPassword(pass));

	}
}
