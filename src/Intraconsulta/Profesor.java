package Intraconsulta;

public class Profesor {
	Integer Id;
	String nombre, apellido;
	
	public Profesor(Integer id, String nombre, String apellido) {
		super();
		Id = id;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
	public Profesor() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getId() {
		return Id;
	}
	
	public void setId(Integer id) {
		Id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
}
