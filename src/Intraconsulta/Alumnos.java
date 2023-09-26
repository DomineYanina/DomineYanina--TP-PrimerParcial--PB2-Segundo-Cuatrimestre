package Intraconsulta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Alumnos {
	Integer Id;
	String nombre, apellido;
	Date fechaNacimiento, fechaIngreso;
	List <Materia> materiasAprobadas = new ArrayList<Materia>();
	List <Materia> inscripcionesVigentes = new ArrayList<Materia>();
	
	public Alumnos(Integer id, String nombre, String apellido, Date fechaNacimiento, Date fechaIngreso) {
		super();
		Id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaIngreso = fechaIngreso;
	}
	
	public Alumnos() {
		// TODO Auto-generated constructor stub
	}

	public List<Materia> getMateriasAprobadas() {
		return materiasAprobadas;
	}

	public void setMateriasAprobadas(List<Materia> materiasAprobadas) {
		this.materiasAprobadas = materiasAprobadas;
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
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public void agregarMateriaAprobada(Materia materia) {
		materiasAprobadas.add(materia);
		inscripcionesVigentes.remove(materia);
	}

	public List<Materia> getInscripcionesVigentes() {
		return inscripcionesVigentes;
	}

	public void setInscripcionesVigentes(List<Materia> inscripcionesVigentes) {
		this.inscripcionesVigentes = inscripcionesVigentes;
	}
	public void inscribirAlumnoAMateria(Materia materia) {
		inscripcionesVigentes.add(materia);
	}
	public boolean chequearMateriasEnCurso(Materia materia) {
		boolean materiaEnCurso=false;
		for(Materia mat : inscripcionesVigentes) {
			if(mat.getId()==materia.getId()) {
				materiaEnCurso=true;
				break;
			}
		}
		return materiaEnCurso;
	}
}
