package es.iestetuan.dam2.dao.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import es.iestetuan.dam2.dao.IAlumno;
import es.iestetuan.dam2.dao.vo.Alumno;

public class BDAlumno implements IAlumno{
	
	public static Connection getConexion() {
		Connection conexion = null;
        try
        {
        	Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ies", "aadd", "aadd");
            if (conexion != null)            
                System.out.println("Connected\n");           
            else          
                System.out.println("Not Connected");         
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return conexion;
	}
	
	public void altaAlumno(Alumno alumno) {
		
		int nia = alumno.getNia();
		String nombre = alumno.getNombre();
		String apellido1 = alumno.getApellido1();
		String apellido2 = alumno.getApellido2();
		String nie = alumno.getNie();
		String email = alumno.getEmail();
		int telefono = alumno.getTelefono();
		
		try {
			Connection conexion = getConexion();
			String sentencia = "insert into t_alumno values(?,?,?,?,?,?,?)";
			PreparedStatement statement = conexion.prepareStatement(sentencia);
			statement.setInt(1, nia);
			statement.setString(2, nombre);
			statement.setString(3, apellido1);
			statement.setString(4, apellido2);
			statement.setString(5, nie);
			statement.setString(6, email);
			statement.setInt(7, telefono);
			statement.executeUpdate();
			conexion.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void modificarAlumno(Alumno alumno) {
		
		int nia = alumno.getNia();
		String nombre = alumno.getNombre();
		String apellido1 = alumno.getApellido1();
		String apellido2 = alumno.getApellido2();
		String nie = alumno.getNie();
		String email = alumno.getEmail();
		int telefono = alumno.getTelefono();
		
		try {
			Connection conexion = getConexion();
			String sentencia = "update t_alumno set nombre = ?, apellido1 = ?, apellido2 = ?, nie = ?, email = ?, telefono = ? where nia = ?";
			PreparedStatement statement = conexion.prepareStatement(sentencia);
			statement.setString(1, nombre);
			statement.setString(2, apellido1);
			statement.setString(3, apellido2);
			statement.setString(4, nie);
			statement.setString(5, email);
			statement.setInt(6, telefono);
			statement.setInt(7, nia);
			statement.executeUpdate();
			conexion.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void bajaAlumno(int id_alumno) {
		
		try {
			Connection conexion = getConexion();
			String sentencia = "delete from t_alumno where nia = ?";
			PreparedStatement statement = conexion.prepareStatement(sentencia);
			statement.setInt(1, id_alumno);
			statement.executeUpdate();
			conexion.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void consultarAlumno(int id_alumno) {
		
		try {
			Connection conexion = getConexion();
			String sentencia = "select * from t_alumno where nia = ?";
			PreparedStatement statement = conexion.prepareStatement(sentencia);
			statement.setInt(1, id_alumno);
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()) {
				int nia = resultado.getInt("nia");
				String nombre = resultado.getString("nombre");
				String apellido1 = resultado.getString("apellido1");
				String apellido2 = resultado.getString("apellido2");
				String nie = resultado.getString("nie");
				String email = resultado.getString("email");
				int dept_no = resultado.getInt("telefono");
				
				Alumno alumno = new Alumno(nia,nombre,apellido1,apellido2,nie,email,dept_no);
				System.out.println(alumno.toString());
			}
			conexion.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Alumno> consultarAlumnos(){
		
		List<Alumno> listaAlumnos = new ArrayList<Alumno>();
		
		try {
			Connection conexion = getConexion();
			String sentencia = "select * from t_alumno";
			PreparedStatement statement = conexion.prepareStatement(sentencia);
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()) {
				int nia = resultado.getInt("nia");
				String nombre = resultado.getString("nombre");
				String apellido1 = resultado.getString("apellido1");
				String apellido2 = resultado.getString("apellido2");
				String nie = resultado.getString("nie");
				String email = resultado.getString("email");
				int dept_no = resultado.getInt("telefono");
				
				Alumno alumno = new Alumno(nia,nombre,apellido1,apellido2,nie,email,dept_no);
				listaAlumnos.add(alumno);
			}
			conexion.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return listaAlumnos;
		
	}	

}
