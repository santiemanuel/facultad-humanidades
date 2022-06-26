package test;

import com.milprogramadores.dao.CarreraDAO;
import com.milprogramadores.model.Carrera;

public class MainInsertCarrera {

	public static void main(String[] args) {

		Carrera carrera = new Carrera();
		carrera.setNombre("Comunicacion");
		
		CarreraDAO carreraDAO = new CarreraDAO();
		carreraDAO.agregarCarrera(carrera);
		
	}

}
