package Intraconsulta;

import java.util.ArrayList;
import java.util.List;

public class Comision {
	Integer Id;
	Materia materia;
	List <Alumnos> Alumnos = new ArrayList<Alumnos>();
	List <Profesor> Profesores = new ArrayList<Profesor>();
	String dia;
	CicloLectivo cicloLectivo;
	Turno turno;
	
	public Comision () {
		super();
	}
	public Comision(Integer id, Materia materia, CicloLectivo cicloLectivo, Turno turno) {
		super();
		Id = id;
		this.materia = materia;
		this.cicloLectivo = cicloLectivo;
		this.turno = turno;
	}
	
	
	public String getDia() {
		return dia;
	}


	public void setDia(String dia) {
		this.dia = dia;
	}


	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public CicloLectivo getCicloLectivo() {
		return cicloLectivo;
	}

	public void setCicloLectivo(CicloLectivo cicloLectivo) {
		this.cicloLectivo = cicloLectivo;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public List<Alumnos> getAlumnos() {
		return Alumnos;
	}

	public void setAlumnos(List<Alumnos> alumnos) {
		Alumnos = alumnos;
	}

	public List<Profesor> getProfesores() {
		return Profesores;
	}

	public void setProfesores(List<Profesor> profesores) {
		Profesores = profesores;
	}

	public void agregarProfesor(Profesor profesor) {
		Profesores.add(profesor);
	}
	
	public void agregarAlumno(Alumnos alumno) {
		Alumnos.add(alumno);	
	}
	
	
}
