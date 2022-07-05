package test;

import com.milprogramadores.dao.AlumnoDAO;
import com.milprogramadores.dao.CredencialDAO;
import com.milprogramadores.dao.UsuarioDAO;
import com.milprogramadores.model.Alumno;
import com.milprogramadores.model.Credencial;
import com.milprogramadores.model.Usuario;

public class MainDAOCrearCuentaAlumno {

	public static void main(String[] args) {
		
		Alumno alumno = new Alumno();
		Usuario usuario = new Usuario();
		Credencial credencial = new Credencial();
		
		AlumnoDAO daoAlumno = new AlumnoDAO();
		UsuarioDAO daoUsuario = new UsuarioDAO();
		CredencialDAO daoCredencial = new CredencialDAO();
		
		alumno = daoAlumno.obtenerAlumno(5);
		usuario = daoUsuario.obtenerUsuario(2);
		
		credencial.setSalt_and_hash("Aldo123");
		
		daoCredencial.agregarCredencial(credencial, usuario);
	}

}
