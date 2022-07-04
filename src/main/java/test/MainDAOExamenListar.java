package test;

import java.util.ArrayList;

import com.milprogramadores.dao.ExamenDAO;
import com.milprogramadores.model.MesaExamen;

public class MainDAOExamenListar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<MesaExamen> mesas = new ArrayList<MesaExamen>();
		
		ExamenDAO dao = new ExamenDAO();
		
		mesas = dao.listarMesaExamen();
		
		for (MesaExamen m : mesas) {
			
			System.out.println("Fecha: " + m.getFecha());
			
		}
		
	}

}
