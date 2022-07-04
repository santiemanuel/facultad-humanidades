package test;

import java.time.LocalDate;

import com.milprogramadores.dao.CarreraDAO;
import com.milprogramadores.dao.ExamenDAO;
import com.milprogramadores.dao.MateriaDAO;
import com.milprogramadores.model.Carrera;
import com.milprogramadores.model.Materia;
import com.milprogramadores.model.MesaExamen;

public class MainMesaExamenInsert {

	public static void main(String[] args) {
		
		MateriaDAO daoM = new MateriaDAO();
		CarreraDAO daoC = new CarreraDAO();
		ExamenDAO daoEx = new ExamenDAO();
		
		Materia materia = new Materia();
		materia = daoM.obtenerMateria(95);
		
		Carrera carrera = new Carrera();
		carrera = daoC.obtenerCarrera(1);
		
		LocalDate fecha = LocalDate.of(2022, 7, 4);
		
		MesaExamen mesa = new MesaExamen();
		mesa.setFecha(fecha);
		
		daoEx.agregarMesaExamen(mesa, materia, carrera);
		
	}

}
