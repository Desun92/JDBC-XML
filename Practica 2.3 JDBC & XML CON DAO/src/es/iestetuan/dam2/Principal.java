package es.iestetuan.dam2;

import java.util.List;

import es.iestetuan.dam2.dao.IAlumno;
import es.iestetuan.dam2.dao.imp.ImpTXT;
import es.iestetuan.dam2.dao.imp.ImpXML;
import es.iestetuan.dam2.dao.vo.Alumno;

public class Principal {
	
public static void main(String[] args) {
		
		IAlumno texto = new ImpTXT();
		IAlumno xml = new ImpXML();
		
		//Primero importamos los alumnos del fichero TXT y los volcamos en una List de alumnos
		List<Alumno> listaAlumnos = texto.consultarAlumnos();
		
		//Una vez hemos volcado la lista de alumnos, generamos un XML con dicha lista
		xml.guardarAlumnos(listaAlumnos);
		
		//Una vez tenemos el XML de referencia, insertamos los datos en la base de datos a partir de dicho XML

	}

}
