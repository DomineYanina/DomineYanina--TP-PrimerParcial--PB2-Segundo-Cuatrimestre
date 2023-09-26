package Intraconsulta;

import java.util.ArrayList;
import java.util.List;

public class Materia {
	Integer Id;
	String nombre;
	Profesor profesor;
	Comision comision;
	Aula aula;
	List <Examen> Examenes = new ArrayList <Examen>();
 	List <Materia> Correlativa = new ArrayList <Materia>();
	
 	public Materia(Integer id, String nombre) {
 		this.Id=id;
 		this.nombre=nombre;
 	}
 	
	public Materia(Integer id, String nombre, Profesor profesor, Comision comision, Aula aula) {
		super();
		Id = id;
		this.nombre = nombre;
		this.profesor = profesor;
		this.comision = comision;
		this.aula = aula;
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
	
	public Profesor getProfesor() {
		return profesor;
	}
	
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
	public Comision getComision() {
		return comision;
	}
	
	public void setComision(Comision comision) {
		this.comision = comision;
	}
	
	public Aula getAula() {
		return aula;
	}
	
	public void setAula(Aula aula) {
		this.aula = aula;
	}
	
	public void agregarCorrelativa(Materia materia) {
		Correlativa.add(materia);
	}
	
	public void eliminarCorrelativa(Materia materia) {
		Correlativa.remove(materia);
	}
	
	public List<Examen> getExamenes() {
		return Examenes;
	}
	
	public void setExamenes(List<Examen> examenes) {
		Examenes = examenes;
	}
	
	public List<Materia> getCorrelativa() {
		return Correlativa;
	}
	
	public void setCorrelativa(List<Materia> correlativa) {
		Correlativa = correlativa;
	}
	
	public void registrarExamen(Examen examen) {
		Examenes.add(examen);
	}
}
