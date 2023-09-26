package testIntraconsulta;

import static org.junit.Assert.*;

import org.junit.Test;

import Intraconsulta.Alumnos;
import Intraconsulta.Aula;
import Intraconsulta.CicloLectivo;
import Intraconsulta.Comision;
import Intraconsulta.Examen;
import Intraconsulta.Intraconsulta;
import Intraconsulta.Materia;
import Intraconsulta.Profesor;
import Intraconsulta.TipoExamen;

public class TestIntraconsulta {
	
	@Test
	public void queMeDevuelvaFalseSiQuieroCrearDosMateriasConElMismoID() {
		//Datos de entrada
		Intraconsulta intraconsulta = new Intraconsulta();
		Materia PB = new Materia (1,"PB");
		Materia BDD = new Materia (1,"BDD");
		Comision comi1 = new Comision();
		Comision comi2 = new Comision();
		
		//Ejecucion
		intraconsulta.agregarMateria(PB, comi1);
		//Validacion
		assertFalse(intraconsulta.agregarMateria(BDD,comi2));
	}
	
	@Test
	public void queNoMePermitaAgregarUnDocenteAUnaComisionEnLaQueYaEstaAgregado() {
		//Datos de entrada
		Intraconsulta intraconsulta = new Intraconsulta();
		Comision comi1 = new Comision();
		Comision comi2 = new Comision();
		Profesor Jaime = new Profesor();
		Profesor Pepe = new Profesor();
		//Ejecucion
		intraconsulta.asignarDocentesAComision(Jaime, comi2);
		intraconsulta.asignarDocentesAComision(Pepe, comi1);
		//Validacion
		assertFalse(intraconsulta.asignarDocentesAComision(Pepe, comi1));
	}

	@Test
	public void queEnUnaComisionDeCincoAlumnosNoMePermitaAgregarMasDeUnDocente() {
		//Datos de entrada
		Intraconsulta intraconsulta = new Intraconsulta();
		Profesor Jaime = new Profesor();
		Profesor Pepe = new Profesor();
		Comision comi1 = new Comision();
		Alumnos Betty = new Alumnos();
		Alumnos Caro = new Alumnos();
		Alumnos Rachel = new Alumnos();
		Alumnos Maria = new Alumnos();
		Alumnos Laura = new Alumnos();
		//Ejecucion
		comi1.agregarAlumno(Laura);
		comi1.agregarAlumno(Maria);
		comi1.agregarAlumno(Rachel);
		comi1.agregarAlumno(Caro);
		comi1.agregarAlumno(Betty);
		comi1.agregarProfesor(Pepe);
		//Validacion
		assertFalse(intraconsulta.asignarDocentesAComision(Jaime, comi1));
	}

	//@Test
	//public void queAlAprobarUnFinalElimineLaMateriaAprobadaDeLasMateriasEnLasQueElAlumnoSeEncuentraInscripto() {
		//Datos de entrada
		//Intraconsulta intraconsulta = new Intraconsulta();
		//Alumnos Walter = new Alumnos();
		//Materia PB = new Materia(1,"PB");
		//Examen PP = new Examen(Walter,9,1,TipoExamen.PrimerParcial);
		//Examen SP = new Examen(Walter,7,2,TipoExamen.SegundoParcial);
		//Examen F = new Examen(Walter,8,3,TipoExamen.Final);
		//Walter.getInscripcionesVigentes().add(PB);
		//Ejecucion
		//intraconsulta.registrarExamen(PP, Walter, PB);
		//intraconsulta.registrarExamen(SP, Walter, PB);
		//intraconsulta.registrarExamen(F, Walter, PB);
		//Validacion
		//assertTrue(intraconsulta.saberSiUnAlumnoYaAproboUnaMateria(Walter, PB));
	//}
	
	@Test 
	public void queNoMePermitaAgregarUnDocenteInexistenteAUnaComision() {
		
	}
	
	@Test
	public void queSiIntentoRegistrarUnExamenConNota15NoMeLoPermita() {
		//Datos de entrada
		Intraconsulta intraconsulta = new Intraconsulta();
		Alumnos Walter = new Alumnos();
		Materia PB = new Materia(1, "PB");
		Examen PP = new Examen (Walter, 15,1,TipoExamen.PrimerParcial);
		Walter.getInscripcionesVigentes().add(PB);
		//Validacion
		assertFalse(intraconsulta.registrarExamen(PP, Walter, PB));
		
	}
	
	@Test
	public void queNoMePermitaRegistrarMasDeUnRecuperatorioParaUnMismoAlumnoEnUnaMateria() {
		//Datos de entrada
		Intraconsulta intraconsulta = new Intraconsulta();
		Alumnos Walter = new Alumnos();
		Materia PB = new Materia(1, "PB");
		Examen PP = new Examen (Walter, 2,1,TipoExamen.PrimerParcial);
		Examen SP = new Examen (Walter,1,2,TipoExamen.SegundoParcial);
		Examen PR = new Examen (Walter,7,3,TipoExamen.RecuPrimerParcial);
		Examen SR = new Examen (Walter,8,4,TipoExamen.RecuSegundoParcial);
		Walter.getInscripcionesVigentes().add(PB);
		//Ejecucion
		intraconsulta.registrarExamen(PP, Walter, PB);
		intraconsulta.registrarExamen(SP, Walter, PB);
		intraconsulta.registrarExamen(PR, Walter, PB);
		//Validacion
		assertFalse(intraconsulta.registrarExamen(SR, Walter, PB));
	}
	
	@Test
	public void queNoMePermitaRendirUnRecuperatorioSiTengoAmbosParcialesDesaprobados() {
		//Datos de entrada
				Intraconsulta intraconsulta = new Intraconsulta();
				Alumnos Walter = new Alumnos();
				Materia PB = new Materia(1, "PB");
				Examen PP = new Examen (Walter, 2,1,TipoExamen.PrimerParcial);
				Examen SP = new Examen (Walter,1,2,TipoExamen.SegundoParcial);
				Examen SR = new Examen (Walter,8,4,TipoExamen.RecuSegundoParcial);
				Walter.getInscripcionesVigentes().add(PB);
				//Ejecucion
				intraconsulta.registrarExamen(PP, Walter, PB);
				intraconsulta.registrarExamen(SP, Walter, PB);
				//Validacion
				assertFalse(intraconsulta.registrarExamen(SR, Walter, PB));
	}
	
	@Test
	public void queSiIntentoInscribirAlAlumnoAUnaMateriaInexistenteMeDeError() {
		
	}
	
	@Test 
	public void queSiLasNotasDeLasMateriasAprobadasDeUnAlumnoSonSieteYNueveElPromedioSeaOcho() {
		//Datos de entrada
		Double notaEsperada=8.0;
		Intraconsulta intraconsulta = new Intraconsulta();
		Alumnos Walter = new Alumnos();
		Materia PB = new Materia(1, "PB");
		Examen PP = new Examen (Walter, 7,1,TipoExamen.PrimerParcial);
		Examen SP = new Examen (Walter,9,2,TipoExamen.SegundoParcial);
		Walter.getInscripcionesVigentes().add(PB);
		//Ejecucion
		intraconsulta.agregarAlumno(Walter);
		intraconsulta.registrarExamen(PP, Walter, PB);
		intraconsulta.registrarExamen(SP, Walter, PB);
		//Validacion
		assertEquals(notaEsperada,intraconsulta.obtenerPromedioSinFinalDeUnAlumnoPorMateria(Walter, PB));
	}
	
	@Test
	public void queSiLaNotaDeUnFinalEsOchoMeLaDevuelvaCorrectamente() {

	}
	
	@Test
	public void queMePermitaAsignarUnAulaALaComisionSiLaComisionYElDocenteExisten() {
		
	}
	
	@Test
	public void queMePermitaIngresarDosProfesoresEnUnaComisionEnLaQueHayUnProfesorYTreintaAlumnos() {
		
	}
	
	@Test
	public void queNoMePermitaInscribirUnAlumnoAUnaMateriaQueHayaAprobadoPreviamente() {
		
	}
	
	@Test
	public void queMePermitaInscribirDosAlumnosEnUnaComisionConCincoLugaresDisponibles() {
		
	}
	
}
