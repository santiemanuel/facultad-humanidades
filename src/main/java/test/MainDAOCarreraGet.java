package test;

import java.util.ArrayList;

import com.milprogramadores.dao.CarreraDAO;
import com.milprogramadores.model.Carrera;
import com.milprogramadores.model.Materia;

public class MainDAOCarreraGet {

	public static void main(String[] args) {
		
		Carrera miCarrera = new Carrera();
		CarreraDAO dao = new CarreraDAO();
		
		miCarrera = dao.obtenerCarrera(1);
		
		ArrayList<Materia> materias = new ArrayList<Materia>();
		
		materias = dao.materiasDeCarrera(miCarrera);
		
		System.out.println("Materias encontradas: " + materias.size());
		for (Materia m : materias) {
			System.out.println("id: " + m.getMateria_id() + " " + m.getNombre());
		}
	}

}
