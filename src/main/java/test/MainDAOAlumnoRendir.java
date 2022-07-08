package test;

import com.milprogramadores.dao.AlumnoDAO;
import com.milprogramadores.dao.ExamenDAO;
import com.milprogramadores.model.Alumno;
import com.milprogramadores.model.MesaExamen;

public class MainDAOAlumnoRendir {

	public static void main(String[] args) {
		Alumno alumno = new Alumno();
		AlumnoDAO alumnodao = new AlumnoDAO();
		MesaExamen mesa = new MesaExamen();
		ExamenDAO mesadao = new ExamenDAO();
		
		alumno = alumnodao.obtenerAlumno(5);
		mesa = mesadao.obtenerMesaExamen(2);
		
	}

}
