package es.iestetuan.dam2.dao.imp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import es.iestetuan.dam2.GestorConfiguracion;
import es.iestetuan.dam2.dao.IAlumno;
import es.iestetuan.dam2.dao.vo.Alumno;

public class TXTAlumno implements IAlumno{
	
	public void altaAlumno(Alumno alumno) {
		
	}
	public void modificarAlumno(Alumno alumno) {
		
	}
	public void bajaAlumno(int id) {
		
	}
	public void consultarAlumno(int id_alumno) {
		
	}
	public List<Alumno> consultarAlumnos() {
		
		List<Alumno> devolver=new ArrayList<Alumno>();
		BufferedReader bfin = null;
		boolean primeraLinea=true;
		String linea;
		String[] lineaPartida;
		
		try{
		
		bfin=new BufferedReader(new FileReader(GestorConfiguracion.getInfoAtributoConfiguracion("origenTXT")));
		
		while((linea=bfin.readLine())!=null) {
			if(primeraLinea)
				primeraLinea=false;
			else {
				Alumno alumno = new Alumno();
				lineaPartida=linea.split(",");
				alumno.setNia(Integer.parseInt(lineaPartida[0]));
				alumno.setNombre(lineaPartida[1]);
				alumno.setApellido1(lineaPartida[2]);
				alumno.setApellido2(lineaPartida[3]);
				devolver.add(alumno);
			}
		}
		}
		
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return devolver;
		
	}
	
	public void guardarAlumnos(List<Alumno> lista) {
		
	}

}
