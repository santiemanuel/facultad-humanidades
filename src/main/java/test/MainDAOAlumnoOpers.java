package test;

import java.util.ArrayList;

import com.milprogramadores.dao.AlumnoDAO;
import com.milprogramadores.model.Alumno;

public class MainDAOAlumnoOpers {

	public static void main(String[] args) {
		
		Alumno alumno = new Alumno();
		
		
		AlumnoDAO dao = new AlumnoDAO();
		
		alumno = dao.obtenerAlumno(5);
		
		alumno.setAlumno_nombre("Aldo");
		
		dao.actualizarAlumno(alumno);
		
		//Alumno result = dao.obtenerAlumno(5);
		
		//System.out.println(result.getAlumno_lu() + " " + result.getAlumno_nombre() + " " + result.getAlumno_apellido());
	}

}
