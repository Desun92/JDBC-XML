package es.iestetuan.dam2;

import java.util.List;

import es.iestetuan.dam2.dao.IAlumno;
import es.iestetuan.dam2.dao.imp.*;
import es.iestetuan.dam2.dao.vo.Alumno;

public class Principal {
	
public static void main(String[] args) {
	
		IAlumno texto = new TXTAlumno();
		IAlumno xml = new XMLALumno();
		IAlumno bd = new BDAlumno();
		
		//Primero importamos los alumnos del fichero TXT y los volcamos en una List de alumnos
		//List<Alumno> listaAlumnos = texto.consultarAlumnos();
		
		//Una vez hemos volcado la lista de alumnos, generamos un XML con dicha lista
		//xml.guardarAlumnos(listaAlumnos);
		
		//Una vez tenemos el XML de referencia, insertamos los datos en la base de datos a partir de dicho XML
		/*List<Alumno> listaAlumnos = xml.consultarAlumnos();
		for(Alumno alumno : listaAlumnos) {
			bd.altaAlumno(alumno);
		}*/
		
		//OPERACIONES QUE SE PUEDEN REALIZAR DE FORMA CONJUNTA
		Alumno alumno = new Alumno(1000,"Prueba","De","Alumno",null,null,0);
		altaAlumnoConjunta(alumno); //ALTA
		
		Alumno alumnoMod = new Alumno(1000,"Prueba2","De","Alumno",null,null,1);
		modificarAlumnoConjunta(alumnoMod);
		
		bajaAlumnoConjunta(1000);
		
	}

	public static void altaAlumnoConjunta(Alumno alumno) {
		
		IAlumno xml = new XMLALumno();
		IAlumno bd = new BDAlumno();
		List<Alumno> listaAlumnos = xml.consultarAlumnos();
		boolean esta=false;
		
		for(Alumno alumnoComprobar : listaAlumnos) {
			if(alumnoComprobar.getNia()==alumno.getNia()) {
				esta=true;
				break;
			}
		}
		
		if(!esta) {
			xml.altaAlumno(alumno);
			bd.altaAlumno(alumno);
			System.out.println("El alumno "+alumno.getApellido1()+", "+alumno.getApellido2()+", "+alumno.getNombre()+" ha sido añadido con éxito");
		}
		else
			System.out.println("Alumno ya registrado");
	}
	
	public static void modificarAlumnoConjunta(Alumno alumno) {
		
		IAlumno xml = new XMLALumno();
		IAlumno bd = new BDAlumno();
		List<Alumno> listaAlumnos = xml.consultarAlumnos();
		boolean esta=false;
		
		for(Alumno alumnoComprobar : listaAlumnos) {
			if(alumnoComprobar.getNia()==alumno.getNia()) {
				esta=true;
				break;
			}
		}
		
		if(esta) {
			xml.modificarAlumno(alumno);
			bd.modificarAlumno(alumno);
			System.out.println("El alumno "+alumno.getApellido1()+", "+alumno.getApellido2()+", "+alumno.getNombre()+" ha sido modificado con éxito");
		}
		
	}
	
	public static void bajaAlumnoConjunta(int id) {
		
		IAlumno xml = new XMLALumno();
		IAlumno bd = new BDAlumno();
		List<Alumno> listaAlumnos = xml.consultarAlumnos();
		boolean esta=false;
		
		for(Alumno alumnoComprobar : listaAlumnos) {
			if(alumnoComprobar.getNia()==id) {
				esta=true;
				break;
			}
		}
		
		if(esta) {
			xml.bajaAlumno(id);
			bd.bajaAlumno(id);
			System.out.println("El alumno con nia "+id+" ha sido eliminado del registro");
		}
		else
			System.out.println("No se ha podido eliminar al alumno");
		
	}

}
