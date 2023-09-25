package Intraconsulta;

public class Aula {
	Integer Id, cantidadDeAlumnos;

	public Aula(Integer id, Integer cantidadDeAlumnos) {
		super();
		Id = id;
		this.cantidadDeAlumnos = cantidadDeAlumnos;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getCantidadDeAlumnos() {
		return cantidadDeAlumnos;
	}

	public void setCantidadDeAlumnos(Integer cantidadDeAlumnos) {
		this.cantidadDeAlumnos = cantidadDeAlumnos;
	}
	
}
