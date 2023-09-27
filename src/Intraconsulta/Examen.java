package Intraconsulta;

public class Examen {
	Alumnos Alumno;
	Materia materia;
	Integer nota, Id;
	TipoExamen tipoExamen;
	
	public Examen(Alumnos alumno, Integer nota, Integer id, TipoExamen tipoExamen) {
		super();
		this.Alumno = alumno;
		this.nota = nota;
		this.Id = id;
		this.tipoExamen=tipoExamen;
	}
	
	public Alumnos getAlumno() {
		return Alumno;
	}
	public void setAlumno(Alumnos alumno) {
		Alumno = alumno;
	}
	public Integer getNota() {
		return nota;
	}
	public void setNota(Integer nota) {
		this.nota = nota;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}

	public TipoExamen getTipoExamen() {
		return tipoExamen;
	}

	public void setTipoExamen(TipoExamen tipoExamen) {
		this.tipoExamen = tipoExamen;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
}
