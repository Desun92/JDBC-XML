package es.iestetuan.dam2.dao.vo;

public class Alumno {
	
	private int nia;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String nie;
	private String email;
	private int telefono;
	
	public Alumno() {
		
	}
	
	public Alumno(int id_alumno, String nombre, String apellido1, String apellido2, String referencia, String email, int telefono) {
		this.nia=id_alumno;
		this.nombre=nombre;
		this.apellido1=apellido1;
		this.apellido2=apellido2;
		this.nie=referencia;
		this.email=email;
		this.telefono=telefono;
	}

	public int getNia() {
		return nia;
	}

	public void setNia(int id_alumno) {
		this.nia = id_alumno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getNie() {
		return nie;
	}

	public void setNie(String referencia) {
		this.nie = referencia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Alumno [id_alumno=" + nia + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2="
				+ apellido2 + ", referencia=" + nie + ", email=" + email + ", telefono=" + telefono + "]";
	}
	
}
