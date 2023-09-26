package Intraconsulta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Intraconsulta {
	List <Alumnos> Alumnos = new ArrayList<Alumnos>();
	List <Profesor> Profesores = new ArrayList<Profesor>();
	List <Comision> Comisiones = new ArrayList<Comision>();
	List <Materia> Materias = new ArrayList<Materia>();
	List <Examen> Examenes = new ArrayList<Examen>();
	List <Aula> Aulas = new ArrayList <Aula>();
	List <CicloLectivo> CiclosLectivos = new ArrayList <CicloLectivo>();
	
	
	
	public Intraconsulta() {
		super();
	}

	public boolean agregarAlumno(Alumnos alumno) {
		boolean alumnoInscripto=false;
		if(!revisarSiUnAlumnoYaExiste(alumno)) {
			alumnoInscripto=true;
			Alumnos.add(alumno);
		}
		return alumnoInscripto;
	}
	
	public boolean revisarSiExisteLaComisionParaLaMismaMateria(Comision comision) {
		boolean materiaYaOcupada=false;
		for(Materia mat : Materias) {
			if((comision.getMateria().getId()==mat.getId()) && (mat.getComision()!=null)){
				materiaYaOcupada=true;
				break;
			}
		}
		return materiaYaOcupada;
	}
	
	public boolean revisarSiElAlumnoYaEstaIncriptoEnOtraComisionElMismoDiaYTurno(Alumnos alumno, Comision comision) {
		boolean alumnoYaInscripto=false;
		if((revisarSiUnAlumnoYaExiste(alumno))&&(revisarSiUnaComisionYaExiste(comision))) {
			for(Materia mat: alumno.getInscripcionesVigentes()) {
				if((mat.getComision().getTurno()==comision.getTurno())||(mat.getComision().getDia()==comision.getDia())){
					alumnoYaInscripto=true;
					break;
				}
			}
		}
		return alumnoYaInscripto;
	}
	
	public boolean chequearSiLaFechaDeUnaInscripcionEsValidaONo(Date fechaInscripcion, Comision comision) {
		boolean inscripcionValida=false;
		if(comision.getCicloLectivo().fechaEstaEnRangoDeInscripciones(fechaInscripcion)) {
			inscripcionValida=true;
		}
		return inscripcionValida;
	}
	
	public boolean inscribirAlumnoAComision(Alumnos alumno, Comision comision, Date fechaInscripcion) { //En el condicional invoco a metodos que chequeen que las condiciones para que un alumno sea inscripto a una comision se cumplan correctamente
		boolean alumnoInscripto=false;
		if((!revisarSiElAlumnoYaEstaIncriptoEnOtraComisionElMismoDiaYTurno(alumno,comision))&&(revisarSiUnAlumnoYaExiste(alumno))&&(chequearSiLaFechaDeUnaInscripcionEsValidaONo(fechaInscripcion, comision))&&(!saberSiUnAlumnoYaAproboUnaMateria(alumno,comision.getMateria()))&&(revisarSiUnaComisionYaExiste(comision))) {
			comision.agregarAlumno(alumno);
			alumno.inscribirAlumnoAMateria(comision.getMateria());
			alumnoInscripto=true;
		}
		return alumnoInscripto;
	}
	
	public boolean saberSiUnAlumnoYaAproboUnaMateria(Alumnos alumno,Materia materia) {
		boolean materiaYaAprobada=false;
		if((revisarSiUnAlumnoYaExiste(alumno))&&(revisarSiUnaMateriaYaExiste(materia))) {
			for(Materia mat : alumno.getMateriasAprobadas()) {
				if(mat.getId()==materia.getId()) {
					materiaYaAprobada=true;
					break;
				}
			}
		}
		return materiaYaAprobada;
	}
	
	public boolean revisarSiExisteLaComisionParaElMismoTurno(Comision comision) {
		boolean comisionYaExistente=false;
		for(Comision com : Comisiones) {
			if((com.getId()==comision.getId())&&(com.getTurno()==comision.getTurno())){
				comisionYaExistente=true;
				break;
			}
		}
		return comisionYaExistente;
	}
	public boolean revisarSiExisteLaComisionParaElMismoCiclolectivo(Comision comision) {
		boolean comisionYaExistente=false;
		if(!revisarSiUnaComisionYaExiste(comision)) {
			for(CicloLectivo cl : CiclosLectivos) {
				if(comision.getCicloLectivo().getId()==cl.getId()) {
					comisionYaExistente=true;
				}
			}
		}
		return comisionYaExistente;
	}
	
	public boolean revisarSiYaExisteUnCicloLectivo(CicloLectivo cicloLectivo) {//Aca reviso que no se cree un ciclo lectivo con id repetido ni con fechas superpuestas
		boolean cicloLectivoYaExistente=false;
		for(CicloLectivo cl:CiclosLectivos) {
			if((cicloLectivo.getId()==cl.getId())||(cicloLectivo.fechaEstaEnRango(cl.getFechaInicioCicloLectivo()))) {
				cicloLectivoYaExistente=true;
			}
		}
		return cicloLectivoYaExistente;
	}
	
	public boolean registrarProfesor(Profesor profesor) {
		boolean profesorRegistrado=false;
		
		if(!revisarSiUnProfesorYaExiste(profesor)) {
			profesorRegistrado=true;
			Profesores.add(profesor);
		}
		return profesorRegistrado;
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
		if((!revisarSiUnaComisionYaExiste(comision))&&(!revisarSiExisteLaComisionParaLaMismaMateria(comision))&&(!revisarSiExisteLaComisionParaElMismoTurno(comision))&&(!revisarSiExisteLaComisionParaElMismoCiclolectivo(comision))) {
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
	
	public double obtenerPromedioDeUnAlumnoPorMateriaConRecuperatorios(Alumnos alumno, Materia materia) {//Realiza el calculo del promedio de un alumno que haya rendido un recuperatorio
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
	
	public boolean aprobarMateria(Alumnos alumno, Materia materia) { //Aca corrobora que todas las condiciones se cumplan antes de dar por aprobada una materia
		boolean materiaAprobada=false;
		if((obtenerNotaDeFinal(alumno,materia)>=7)&&(analizarSiLasCorrelativasEstanAprobadas(alumno,materia))) {
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
		for(Examen exa:Examenes)	 {
			if(((exa.getAlumno().getId()==alumno.getId())&&(exa.getMateria().getId()==materia.getId())&&(exa.getTipoExamen()==TipoExamen.RecuSegundoParcial))) {
				nota=exa.getNota();
			}
		}
		return nota;
	}
	
	public boolean analizarSiLasCorrelativasEstanAprobadas(Alumnos alumno, Materia materia) { //Aca controlo si todas las materias que aparecen como correlativas de la materia a analizar aparecen en la lista de materias aprobadas del alumno, caso contrario faltan correlativas
		boolean correlativasAprobadas=false;
		List<Materia> controlador = new ArrayList<Materia>();
		for(Materia corr : materia.getCorrelativa()) {
			for(Materia mat: alumno.getMateriasAprobadas()) {
				if((corr.getId()==mat.getId())&&(obtenerNotaDeFinal(alumno,mat)>=7)) {
					controlador.add(mat);	
					break;
				}
			}
		
		}
		if(materia.getCorrelativa().size()==controlador.size()) {
			correlativasAprobadas=true;
		}
		return correlativasAprobadas;
	}
	
	public boolean analizarSiAlumnoCalificaParaExamenFinal(Alumnos alumno, Materia materia) { //Analiza que todas las condiciones se cumplan para que el alumno rinda un final
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
	
	public boolean analizarSiElAlumnoYaRindioAlgunRecuperatorio(Alumnos alumno, Materia materia) { //Controlo que el alumno solo rinda un recuperatorio
		boolean yaHaRendido=false;
		for(Examen exa:Examenes) {
			if((exa.getMateria().getId()==materia.getId())&&(exa.getAlumno().getId()==alumno.getId())&&((exa.getTipoExamen()==TipoExamen.RecuPrimerParcial)||(exa.getTipoExamen()==TipoExamen.RecuSegundoParcial))) {
				yaHaRendido=true;
				break;
			}
		}
		return yaHaRendido;
	}
	
	public boolean analizarSiElAlumnoYaRindioEseParcial(Alumnos alumno, Materia materia, Examen examen) { //Se asegura que no se registre un parcial repetido para el mismo alumno en la misma materia
		boolean yaHaRendido=false;
		for(Examen exa:Examenes) {
			if((exa.getMateria().getId()==materia.getId())&&(exa.getAlumno().getId()==alumno.getId())&&(exa.getTipoExamen()==examen.getTipoExamen())){
				yaHaRendido=true;
				break;
			}
		}
		return yaHaRendido;
	}
	
	public boolean registrarExamen(Examen examen, Alumnos alumno, Materia materia) { //Evalua las condiciones del examen que se quiere registrar y se llama a los metodos indicados para cada caso
		boolean examenRegistrado=false;
			if((alumno.chequearMateriasEnCurso(materia))&&(!revisarSiUnExamenYaExiste(examen))&&(!revisarSiUnAlumnoYaExiste(alumno))&&(!revisarSiUnaMateriaYaExiste(materia))&&(analizarNotaDeExamen(examen))) {
				if(((examen.tipoExamen==TipoExamen.RecuPrimerParcial)&&(!analizarSiElAlumnoYaRindioAlgunRecuperatorio(alumno,materia))&&((obtenerNotaPrimerParcial(alumno,materia)<8)&&(obtenerNotaPrimerParcial(alumno,materia)>0)))||((examen.tipoExamen==TipoExamen.RecuSegundoParcial)&&((obtenerNotaSegundoParcial(alumno,materia)<8)&&(obtenerNotaSegundoParcial(alumno,materia)>0)))) {
					materia.registrarExamen(examen);
					examenRegistrado=true;
				} else if(((examen.tipoExamen==TipoExamen.PrimerParcial)||(examen.tipoExamen==TipoExamen.SegundoParcial))&&(!analizarSiElAlumnoYaRindioEseParcial(alumno,materia,examen))) {
					materia.registrarExamen(examen);
					examenRegistrado=true;
				} else {
					if(analizarSiAlumnoCalificaParaExamenFinal(alumno,materia)) {
						materia.registrarExamen(examen);
						if(examen.getNota()>=7) {
							examenRegistrado=true;
							aprobarMateria(alumno,materia);
						}
					}
				}
			}
		return examenRegistrado;
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
			if(mat.getId()==materia.getId()) {
				materiaYaExistente=true;
				break;
			}
		}
		return materiaYaExistente;
	}
	
	public boolean revisarSiUnaMateriaYaExiste1(Materia materia) {
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
	
	public boolean analizarNotaDeExamen(Examen examen) { //Se asegura que la nota que se intenta poner en un examen sea valida
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
	
	public double obtenerPromedioTotalDelAlumno(Alumnos alumno) { //Realizo la sumatoria de las notas de los finales del alumno(Aprobados o desaprobados) y realizo el promedio
		double promedio=0.0;
		if((revisarSiUnAlumnoYaExiste(alumno))&(alumno.getMateriasAprobadas().size()>0)) { //En este condicional chequeo que el alumno exista y que al menos tenga una materia aprobada
			for(Materia mat:alumno.getMateriasAprobadas()) {
				if(saberSiUnAlumnoRindioElFinal(alumno,mat)) {
					promedio=(promedio+(obtenerNotaDeFinal(alumno,mat)));
				}
			}
			promedio=(promedio/alumno.getMateriasAprobadas().size());
		}
		return promedio;
	}
	
	public List<Materia> obtenerMateriasQueFaltanCursarParaUnAlumno(Alumnos alumno){
		boolean materiaEncontrada=false;
		List<Materia> materiasRestantes = new ArrayList<Materia>();
		if(revisarSiUnAlumnoYaExiste(alumno)) {
			for(Materia mat:Materias) {
				for(Materia aprobadas:alumno.getMateriasAprobadas()) {
					if(mat.getId()==aprobadas.getId()) {
						materiaEncontrada=true; //Si la materia se encuentra dentro de las materias que el alumno ya encontro, materiaEncontrada pasa a valer true, por lo que luego no la agrega a la lista de materias faltantes
						break;
					}
				}
				if(!materiaEncontrada) {
					materiasRestantes.add(mat);
				}
				materiaEncontrada=false;
			}
		}
		return materiasRestantes;
	}
}
