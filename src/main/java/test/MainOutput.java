package test;

import com.milprogramadores.model.Carrera;
import com.milprogramadores.model.Facultad;

public class MainOutput {

	public static void main(String[] args) {
		Facultad facultad = new Facultad();
	
		facultad.setNombre("Humanidades");
		facultad.agregarCarrera(new Carrera());
		
		System.out.println(facultad);
	}

}
