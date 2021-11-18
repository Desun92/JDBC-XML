package es.iestetuan.dam2.dao;

import java.util.List;

import es.iestetuan.dam2.dao.vo.Alumno;

public interface IAlumno {
	
	public void altaAlumno(Alumno alumno);
	public void modificarAlumno(Alumno alumno);
	public void bajaAlumno(int id);
	public void consultarAlumno(int id_alumno);
	public List<Alumno> consultarAlumnos();

}
