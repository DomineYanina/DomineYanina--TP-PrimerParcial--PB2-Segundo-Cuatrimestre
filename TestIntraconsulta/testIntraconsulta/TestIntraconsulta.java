package testIntraconsulta;

import static org.junit.Assert.*;

import org.junit.Test;

import Intraconsulta.Alumnos;
import Intraconsulta.Aula;
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
	public void queAlLlenarseElAulaSeLleneLaComisionQueLaEstaUsando() {
		//Datos de entrada
		Intraconsulta intraconsulta = new Intraconsulta();
		Comision comi1 = new Comision();
		Alumnos Jaime = new Alumnos();
		Alumnos Pepe = new Alumnos();
		Materia materia = new Materia(1,"PB");
		Aula aula = new Aula(1,1);
		comi1.setMateria(materia);
		comi1.getMateria().setAula(aula);
		//Ejecucion
		intraconsulta.agregarAlumno(Jaime);
		intraconsulta.agregarAlumno(Pepe);
		comi1.agregarAlumno(Pepe);
		
		//Validacion
		assertTrue(intraconsulta.chequearSiUnaComisionYaEstaLlena(comi1));
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
	@Test 
	public void queMeDevuelvaErrorAlIntentarRegistrarUnExamenSinHaberInscriptoAlAlumnoAUnaMateria() {
		Alumnos alumno = new Alumnos();
		Materia materia = new Materia(1,"PB");
		Examen SP = new Examen(alumno,8,2,TipoExamen.SegundoParcial);
		Intraconsulta intraconsulta = new Intraconsulta();
		boolean resultado = intraconsulta.registrarExamen(SP, alumno, materia);
		assertFalse(resultado);
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
	public void queSiInscriboAUnAlumnoAUnaMateriaMeDevuelvaVerdaderoAlBuscarlaEnLaListaDeLasMateriasEnLasQueElAlumnoSeEncuentraInscripto() {
		Alumnos Walter = new Alumnos();
		Materia PB = new Materia(1,"PB");
		Walter.inscribirAlumnoAMateria(PB);
		Intraconsulta intraconsulta = new Intraconsulta();
		boolean resultado=intraconsulta.chequearSiUnAlumnoSeEncuentraInscriptoEnUnaMateria(Walter, PB);
		assertTrue(resultado);
	}
	
	@Test
	public void queMePermitaAsignarUnAulaALaComisionSiLaComisionYElDocenteExisten() {
		Comision comi1 = new Comision();
		Materia materia = new Materia(1,"PB");
		Profesor Pepito = new Profesor();
		Aula aula = new Aula(1,1);
		Intraconsulta intraconsulta = new Intraconsulta();
		comi1.setMateria(materia);
		materia.setProfesor(Pepito);;
		materia.setAula(aula);
		assertEquals(aula,intraconsulta.obtenerElAulaDeUnaComision(comi1));
	}
	
	@Test
	public void queAlInscribirDosAlumnosEnUnaMateriaConCincoLugaresDisponiblesAunHayaCupo() {
		Comision comi1 = new Comision();
		Alumnos Peter = new Alumnos();
		Alumnos Wendy = new Alumnos();
		Aula aula = new Aula(1,5);
		Materia materia = new Materia(1,"PB");
		materia.setAula(aula);
		comi1.setMateria(materia);
		Intraconsulta intraconsulta = new Intraconsulta();
		comi1.agregarAlumno(Wendy);
		comi1.agregarAlumno(Peter);
		
		assertFalse(intraconsulta.chequearSiUnaComisionYaEstaLlena(comi1));
		
	}
	
	@Test
    public void queAlAprobarUnaMateriaLuegoSePuedaConfirmarCorrectamente() {
        Intraconsulta intraconsulta = new Intraconsulta();
        Alumnos alumno = new Alumnos();
        Materia materia = new Materia(1,"PB");
        alumno.agregarMateriaAprobada(materia);

        boolean resultado = intraconsulta.saberSiUnAlumnoYaAproboUnaMateria(alumno, materia);

        assertFalse(resultado);
    }
	
	@Test
	public void queNoMePermitaRegistrarDosProfesoresConElMismoID() {
		Profesor Esteban = new Profesor (1,"Esteban","Quito");
		Profesor Aquiles = new Profesor (1,"Aquiles","Bailoyo");
		Intraconsulta intraconsulta = new Intraconsulta();
		intraconsulta.registrarProfesor(Esteban);
		assertFalse(intraconsulta.registrarProfesor(Aquiles));
		
	}
}
