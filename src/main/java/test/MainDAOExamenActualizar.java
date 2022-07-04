package test;

import java.time.LocalDate;

import com.milprogramadores.dao.CarreraDAO;
import com.milprogramadores.dao.ExamenDAO;
import com.milprogramadores.dao.MateriaDAO;
import com.milprogramadores.model.Carrera;
import com.milprogramadores.model.Materia;
import com.milprogramadores.model.MesaExamen;

public class MainDAOExamenActualizar {

	public static void main(String[] args) {
		
		MesaExamen mesa = new MesaExamen();
		
		MateriaDAO daoM = new MateriaDAO();
		CarreraDAO daoC = new CarreraDAO();
		ExamenDAO daoEx = new ExamenDAO();
		
		mesa = daoEx.obtenerMesaExamen(2);
		
		//datos nuevos
		Materia materia = new Materia();
		materia = daoM.obtenerMateria(40);
		
		Carrera carrera = new Carrera();
		carrera = daoC.obtenerCarrera(3);
		
		LocalDate fecha = LocalDate.of(2022, 10, 7);
		
		mesa.setFecha(fecha);
		
		daoEx.actualizarMesaExamen(mesa, materia, carrera);
		
		

	}

}
