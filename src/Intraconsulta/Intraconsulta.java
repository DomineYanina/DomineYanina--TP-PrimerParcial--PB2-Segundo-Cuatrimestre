package Intraconsulta;

import java.util.ArrayList;
import java.util.List;

public class Intraconsulta {
	List <Alumnos> Alumnos = new ArrayList<Alumnos>();
	List <Profesor> Profesores = new ArrayList<Profesor>();
	List <Comision> Comisiones = new ArrayList<Comision>();
	List <Materia> Materias = new ArrayList<Materia>();
	List <Examen> Examenes = new ArrayList<Examen>();
	List <Aula> Aulas = new ArrayList <Aula>();
	
	public boolean agregarAlumno(Alumnos alumno) {
		boolean alumnoInscripto=false;
		if(!revisarSiUnAlumnoYaExiste(alumno)) {
			alumnoInscripto=true;
			Alumnos.add(alumno);
		}
		return alumnoInscripto;
	}
	
	public boolean registrarProfesor(Profesor profesor) {
		boolean profesorRegistrado=false;
		
		if(!revisarSiUnProfesorYaExiste(profesor)) {
			profesorRegistrado=true;
			Profesores.add(profesor);
		}
		return profesorRegistrado;
	}
	
	public boolean registrarComision(Comision comision) {
		boolean comisionRegistrada=false;
		
		if(!revisarSiUnaComisionYaExiste(comision)) {
			comisionRegistrada=true;
			Comisiones.add(comision);
		}
		return comisionRegistrada;
	}
	
	public boolean nuevaCorrelativa(Materia materia, Materia correlativa) {
		boolean correlativaCreada=false;
		
		if(!revisarSiUnaCorrelatividadYaExiste(materia,correlativa)) {
			materia.agregarCorrelativa(correlativa);
			correlativaCreada=true;
		}
		return correlativaCreada;
	}
	
	public boolean eliminarCorrelativa(Materia materia, Materia correlativa) {
		boolean correlativaEliminada=false;
		if((revisarSiUnaCorrelatividadYaExiste(materia,correlativa))&&(revisarSiUnaMateriaYaExiste(materia))) {
			materia.eliminarCorrelativa(materia);
			correlativaEliminada=true;
		}
		return correlativaEliminada;
	}

	public boolean agregarComision(Comision comision) {
		boolean comisionAgregada=false;
		if(!revisarSiUnaComisionYaExiste(comision)) {
			Comisiones.add(comision);
			comisionAgregada=true;
		}
		return comisionAgregada;
	}
	
	public boolean agregarMateria(Materia materia, Comision comision) {
		boolean materiaAgregada=false;
		if(!revisarSiUnaMateriaYaExiste(materia)) {
			Materias.add(materia);
			materiaAgregada=true;
		}
		return materiaAgregada;
	}
	
	public boolean asignarDocentesAComision(Profesor profesor, Comision comision) {
		boolean docenteAsignado = false;
		if((revisarSiUnaComisionYaExiste(comision))&&(!revisarSiUnDocenteyaPerteneceAUnaComision(profesor,comision))) {
			if(comision.getAlumnos().size()<21) {
				if(comision.getProfesores().size()==1) {
				}
				else {
					comision.agregarProfesor(profesor);
					docenteAsignado=true;
				}
			}
			if (comision.getAlumnos().size()>20) {
				if(comision.getProfesores().size()==2) {
				}
				else{
					comision.agregarProfesor(profesor);
					docenteAsignado=true;
				}
			}
		}	
		return docenteAsignado;
	}
	public boolean asignarAulaALaComision(Comision comision, Aula aula) {
		boolean aulaAsignada=false;
		if((revisarSiUnaAulaYaExiste(aula))&&(revisarSiUnaComisionYaExiste(comision))) {
			if(comision.getAlumnos().size()<=aula.getCantidadDeAlumnos()) {
				comision.getMateria().setAula(aula);
				aulaAsignada=true;
			}
		}
		return aulaAsignada;
	}
	public boolean saberSiUnAlumnoRindioAlgunRecuperatorio(Alumnos alumno) {
		boolean rindio=false;
		for(Examen exa : Examenes) {
			if((exa.getAlumno().getId()==alumno.getId())&&((exa.getTipoExamen()==TipoExamen.RecuPrimerParcial)||(exa.getTipoExamen()==TipoExamen.RecuSegundoParcial))) {
				rindio=true;
				break;
			}
		}
		return rindio;
	}
	public boolean saberSiUnAlumnoRindioElFinal(Alumnos alumno, Materia materia) {
		boolean rindio=false;
		for(Examen exa : Examenes) {
			if((exa.getAlumno().getId()==alumno.getId())&&(exa.getMateria().getId()==materia.getId())&&(exa.getTipoExamen()==TipoExamen.Final)) {
				rindio=true;
				break;
			}
		}
		return rindio;
	}
	
	public double obtenerPromedioDeUnAlumnoPorMateriaSinRecuperatorios(Alumnos alumno, Materia materia) {
		double promedio=0.0;
		promedio=((obtenerNotaPrimerParcial(alumno,materia)+obtenerNotaSegundoParcial(alumno,materia))/2);
		return promedio;
	}
	
	public double obtenerPromedioDeUnAlumnoPorMateriaConRecuperatorios(Alumnos alumno, Materia materia) {
		double promedio=0.0;
		if(obtenerNotaPrimerParcial(alumno,materia)<4) {
			promedio=((obtenerNotaPrimerRecuperatorio(alumno,materia)+obtenerNotaSegundoParcial(alumno,materia))/2);
		}
		if(obtenerNotaSegundoParcial(alumno,materia)<4) {
			promedio=((obtenerNotaPrimerParcial(alumno,materia)+obtenerNotaSegundoRecuperatorio(alumno,materia))/2);
		}
		return promedio;
	}
	
	public Double obtenerPromedioSinFinalDeUnAlumnoPorMateria(Alumnos alumno, Materia materia) {
		Double nota = 0.0;
		if((revisarSiUnAlumnoYaExiste(alumno))&&(revisarSiUnaMateriaYaExiste(materia))) {
			if(saberSiUnAlumnoRindioAlgunRecuperatorio(alumno)) {
				nota=obtenerPromedioDeUnAlumnoPorMateriaConRecuperatorios(alumno,materia);
			} else {
				nota=obtenerPromedioDeUnAlumnoPorMateriaSinRecuperatorios(alumno,materia);
			}
		}
		return nota;
	}
	
	public boolean chequearCorrelativasAprobadas(Alumnos alumno, Materia materia) {
		boolean correlativasAprobadas=false;
		return correlativasAprobadas;
		
	}
	public boolean aprobarMateria(Alumnos alumno, Materia materia) {
		boolean materiaAprobada=false;
		if(obtenerNotaDeFinal(alumno,materia)>=4) {
			materiaAprobada=true;
			alumno.agregarMateriaAprobada(materia);
		}
		return materiaAprobada;
	}
	public Integer obtenerNotaDeFinal(Alumnos alumno, Materia materia) {
		Integer nota=null;
		if(saberSiUnAlumnoRindioElFinal(alumno,materia)) {
			for(Examen exa : Examenes) {
				if((exa.getAlumno().getId()==alumno.getId())&&(exa.getMateria().getId()==materia.getId())){
					nota=exa.getNota();
					break;
				}
			}
		}
		return nota;
	}
	
	public Integer obtenerNotaPrimerParcial(Alumnos alumno, Materia materia) {
		Integer nota=0;
		for(Examen exa:Examenes) {
			if((((exa.getAlumno().getId()==alumno.getId())&&(exa.getTipoExamen()==TipoExamen.PrimerParcial)))&&(exa.getMateria().getId()==materia.getId())) {
				nota=exa.getNota();
			}
		}
		return nota;
	}
	
	public Integer obtenerNotaSegundoParcial(Alumnos alumno, Materia materia) {
		Integer nota=0;
		for(Examen exa:Examenes) {
			if(((exa.getAlumno().getId()==alumno.getId())&&(exa.getMateria().getId()==materia.getId())&&(exa.getTipoExamen()==TipoExamen.SegundoParcial))) {
				nota=exa.getNota();
			}
		}
		return nota;
	}
	
	public Integer obtenerNotaPrimerRecuperatorio(Alumnos alumno, Materia materia) {
		Integer nota=0;
		for(Examen exa:Examenes) {
			if(((exa.getAlumno().getId()==alumno.getId())&&(exa.getMateria().getId()==materia.getId())&&(exa.getTipoExamen()==TipoExamen.RecuPrimerParcial))) {
				nota=exa.getNota();
			}
		}
		return nota;
	}
	
	public Integer obtenerNotaSegundoRecuperatorio(Alumnos alumno, Materia materia) {
		Integer nota=0;
		for(Examen exa:Examenes) {
			if(((exa.getAlumno().getId()==alumno.getId())&&(exa.getMateria().getId()==materia.getId())&&(exa.getTipoExamen()==TipoExamen.RecuSegundoParcial))) {
				nota=exa.getNota();
			}
		}
		return nota;
	}
	public boolean analizarSiAlumnoCalificaParaExamenFinal(Alumnos alumno, Materia materia) {
		boolean califica=false;
		if(obtenerNotaPrimerParcial(alumno,materia)>3) {
			if(obtenerNotaSegundoParcial(alumno,materia)>3) {
				califica=true;
			} else if(obtenerNotaSegundoRecuperatorio(alumno,materia)>3) {
				califica=true;
			}
		} else if(obtenerNotaSegundoParcial(alumno,materia)>3) {
			if(obtenerNotaPrimerRecuperatorio(alumno,materia)>3) {
				califica=true;
			}
		}
		return califica;
	}
	
	public boolean analizarSiElAlumnoYaRindioAlgunRecuperatorio(Alumnos alumno, Materia materia) {
		boolean yaHaRendido=false;
		for(Examen exa:Examenes) {
			if((exa.getMateria().getId()==materia.getId())&&(exa.getAlumno().getId()==alumno.getId())&&((exa.getTipoExamen()==TipoExamen.RecuPrimerParcial)||(exa.getTipoExamen()==TipoExamen.RecuSegundoParcial))) {
				yaHaRendido=true;
				break;
			}
		}
		return yaHaRendido;
	}
	public boolean analizarSiElAlumnoYaRindioEseParcial(Alumnos alumno, Materia materia, Examen examen) {
		boolean yaHaRendido=false;
		for(Examen exa:Examenes) {
			if((exa.getMateria().getId()==materia.getId())&&(exa.getAlumno().getId()==alumno.getId())&&(exa.getTipoExamen()==examen.getTipoExamen())){
				yaHaRendido=true;
				break;
			}
		}
		return yaHaRendido;
	}
	public void registrarExamen(Examen examen, Alumnos alumno, Materia materia) {
		try {
			if((!revisarSiUnExamenYaExiste(examen))&&(!revisarSiUnAlumnoYaExiste(alumno))&&(!revisarSiUnaMateriaYaExiste(materia))&&(analizarNotaDeExamen(examen))) {
				if(((examen.tipoExamen==TipoExamen.RecuPrimerParcial)&&(!analizarSiElAlumnoYaRindioAlgunRecuperatorio(alumno,materia))&&((obtenerNotaPrimerParcial(alumno,materia)<8)&&(obtenerNotaPrimerParcial(alumno,materia)>0)))||((examen.tipoExamen==TipoExamen.RecuSegundoParcial)&&((obtenerNotaSegundoParcial(alumno,materia)<8)&&(obtenerNotaSegundoParcial(alumno,materia)>0)))) {
					materia.registrarExamen(examen);
				} else if(((examen.tipoExamen==TipoExamen.PrimerParcial)||(examen.tipoExamen==TipoExamen.SegundoParcial))&&(!analizarSiElAlumnoYaRindioEseParcial(alumno,materia,examen))) {
					materia.registrarExamen(examen);
				} else {
					if(analizarSiAlumnoCalificaParaExamenFinal(alumno,materia)) {
						materia.registrarExamen(examen);
					}
				}
			}
		} catch (Exception e) {
			
		}
	}
	
	public boolean revisarSiUnDocenteyaPerteneceAUnaComision(Profesor profesor, Comision comision) {
		boolean profesorRepetido=false;
		for(Profesor prof:comision.getProfesores()) {
			if(prof.getId()==profesor.getId()) {
				profesorRepetido=true;
				break;
			}
		}
		return profesorRepetido;
	}
	
	public boolean revisarSiUnExamenYaExiste(Examen examen) {
		boolean examenYaExistente=false;
		for(Examen exa:Examenes) {
			if(examen.getId()==exa.getId()) {
				examenYaExistente=true;
				break;
			}
		}
		return examenYaExistente;
	}
	
	public boolean revisarSiUnAlumnoYaExiste(Alumnos alumno) {
		boolean alumnoYaExistente=false;
		for(Alumnos alu:Alumnos) {
			if(alu.getId()==alumno.getId()) {
				alumnoYaExistente=true;
				break;
			}
		}
		return alumnoYaExistente;
	}
	
	public boolean revisarSiUnaMateriaYaExiste(Materia materia) {
		boolean materiaYaExistente=false;
		for(Materia mat:Materias) {
			if((mat.getId()==materia.getId())&&(materia.getComision().getTurno().equals(mat.getComision().getTurno()))) {
				materiaYaExistente=true;
				break;
			}
		}
		return materiaYaExistente;
	}
	
	public boolean revisarSiUnaAulaYaExiste(Aula aula) {
		boolean aulaYaExiste=false;
		for(Aula au : Aulas) {
			if(au.getId()==aula.getId()) {
				aulaYaExiste=true;
				break;
			}
		}
		return aulaYaExiste;
	}
	
	public boolean revisarSiUnProfesorYaExiste(Profesor profesor) {
		boolean profesorYaExiste=false;
		for(Profesor prof:Profesores) {
			if(prof.getId()==profesor.getId()) {
				profesorYaExiste=true;
				break;
			}
		}
		return profesorYaExiste;
	}
	
	public boolean revisarSiUnaComisionYaExiste(Comision comision) {
		boolean comisionYaExiste=false;
		for(Comision com:Comisiones) {
			if(com.getId()==comision.getId()) {
				comisionYaExiste=true;
				break;
			}
		}
		return comisionYaExiste;
	}
	
	public boolean revisarSiUnaCorrelatividadYaExiste(Materia materia, Materia correlativa) {
		boolean correlatividadYaExiste=false;
		for (Materia mat : Materias) {
			if(mat.getId()==materia.getId()) {
				for(Materia corr : mat.Correlativa) {
					if(corr.getId()==correlativa.getId()) {
						correlatividadYaExiste=true;
						break;
					}
				}
			}
		}
		return correlatividadYaExiste;
	}
	
	public boolean analizarNotaDeExamen(Examen examen) {
		boolean notasCorrectas=false;
		if((examen.getNota()<11)&&(examen.getNota()>0)) {
			notasCorrectas=true;
		}
		return notasCorrectas;
	}
	
	public List<Materia> obtenerMateriasAprobadasParaUnAlumno(Alumnos alumno) {
		try {
			return alumno.getMateriasAprobadas();
		} catch (Exception e) {
			return null;
		}
	}
}
