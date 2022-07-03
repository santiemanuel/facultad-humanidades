package test;

import java.util.ArrayList;

import com.milprogramadores.dao.MateriaDAO;
import com.milprogramadores.model.Carrera;
import com.milprogramadores.model.Materia;

public class MainDAOMateriaGet {

	public static void main(String[] args) {
		
		MateriaDAO dao = new MateriaDAO();
		
		//Obtenemos una materia dado un id
		Materia materia = new Materia();
		materia = dao.obtenerMateria(88);
		
		ArrayList<Carrera> carreras = new ArrayList<Carrera>();
		
		carreras = dao.carrerasDeMateria(materia);
		
		System.out.println("Carreras con la materia: " + materia.getNombre());
		for (Carrera c : carreras) {
			System.out.println(c.getCarrera_id() + " " + c.getNombre());
		}
	}

}
