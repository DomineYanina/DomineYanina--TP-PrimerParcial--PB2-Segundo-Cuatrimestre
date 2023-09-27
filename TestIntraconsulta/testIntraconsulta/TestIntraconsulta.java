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
		//Ejecución
		intraconsulta.agregarMateria(PB, comi1);
		//Validación
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
		//Ejecución
		intraconsulta.agregarAlumno(Jaime);
		intraconsulta.agregarAlumno(Pepe);
		comi1.agregarAlumno(Pepe);
		//Validación
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
		//Ejecución
		intraconsulta.asignarDocentesAComision(Jaime, comi2);
		intraconsulta.asignarDocentesAComision(Pepe, comi1);
		//Validación
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
		//Ejecución
		comi1.agregarAlumno(Laura);
		comi1.agregarAlumno(Maria);
		comi1.agregarAlumno(Rachel);
		comi1.agregarAlumno(Caro);
		comi1.agregarAlumno(Betty);
		comi1.agregarProfesor(Pepe);
		//Validación
		assertFalse(intraconsulta.asignarDocentesAComision(Jaime, comi1));
	}
	@Test 
	public void queMeDevuelvaErrorAlIntentarRegistrarUnExamenSinHaberInscriptoAlAlumnoAUnaMateria() {
		//Datos de entrada
		Alumnos alumno = new Alumnos();
		Materia materia = new Materia(1,"PB");
		Examen SP = new Examen(alumno,8,2,TipoExamen.SegundoParcial);
		Intraconsulta intraconsulta = new Intraconsulta();
		//Ejecución
		boolean resultado = intraconsulta.registrarExamen(SP, alumno, materia);
		//Validación
		assertFalse(resultado);
	}
	
	@Test
	public void queSiIntentoRegistrarUnExamenConNota15NoMeLoPermita() {
		//Datos de entrada
		Intraconsulta intraconsulta = new Intraconsulta();
		Alumnos Walter = new Alumnos();
		Materia PB = new Materia(1, "PB");
		Examen PP = new Examen (Walter, 15,1,TipoExamen.PrimerParcial);
		//Ejecución
		Walter.getInscripcionesVigentes().add(PB);
		//Validación
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
		//Ejecución
		intraconsulta.registrarExamen(PP, Walter, PB);
		intraconsulta.registrarExamen(SP, Walter, PB);
		intraconsulta.registrarExamen(PR, Walter, PB);
		//Validación
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
		//Ejecución
		intraconsulta.registrarExamen(PP, Walter, PB);
		intraconsulta.registrarExamen(SP, Walter, PB);
		//Validación
		assertFalse(intraconsulta.registrarExamen(SR, Walter, PB));
	}
	
	@Test
	public void queSiInscriboAUnAlumnoAUnaMateriaMeDevuelvaVerdaderoAlBuscarlaEnLaListaDeLasMateriasEnLasQueElAlumnoSeEncuentraInscripto() {
		//Datos de entrada
		Alumnos Walter = new Alumnos();
		Materia PB = new Materia(1,"PB");
		Intraconsulta intraconsulta = new Intraconsulta();
		//Ejecución
		Walter.inscribirAlumnoAMateria(PB);
		boolean resultado=intraconsulta.chequearSiUnAlumnoSeEncuentraInscriptoEnUnaMateria(Walter, PB);
		//Validación
		assertTrue(resultado);
	}
	
	@Test
	public void queMePermitaAsignarUnAulaALaComisionSiLaComisionYElDocenteExisten() {
		//Datos de entrada
		Comision comi1 = new Comision();
		Materia materia = new Materia(1,"PB");
		Profesor Pepito = new Profesor();
		Aula aula = new Aula(1,1);
		Intraconsulta intraconsulta = new Intraconsulta();
		//Ejecución
		comi1.setMateria(materia);
		materia.setProfesor(Pepito);
		materia.setAula(aula);
		//Validación
		assertEquals(aula,intraconsulta.obtenerElAulaDeUnaComision(comi1));
	}
	
	@Test
	public void queAlInscribirDosAlumnosEnUnaMateriaConCincoLugaresDisponiblesAunHayaCupo() {
		//Datos de entrada
		Comision comi1 = new Comision();
		Alumnos Peter = new Alumnos();
		Alumnos Wendy = new Alumnos();
		Aula aula = new Aula(1,5);
		Materia materia = new Materia(1,"PB");
		Intraconsulta intraconsulta = new Intraconsulta();
		//Ejecución
		materia.setAula(aula);
		comi1.setMateria(materia);
		comi1.agregarAlumno(Wendy);
		comi1.agregarAlumno(Peter);
		//Validación
		assertFalse(intraconsulta.chequearSiUnaComisionYaEstaLlena(comi1));
	}
	
	@Test
    public void queAlAprobarUnaMateriaLuegoSePuedaConfirmarCorrectamente() {
		//Datos de entrada
        Intraconsulta intraconsulta = new Intraconsulta();
        Alumnos alumno = new Alumnos();
        Materia materia = new Materia(1,"PB");
        //Ejecución
        alumno.agregarMateriaAprobada(materia);
        boolean resultado = intraconsulta.saberSiUnAlumnoYaAproboUnaMateria(alumno, materia);
        //Validación
        assertFalse(resultado);
    }
	
	@Test
	public void queNoMePermitaRegistrarDosProfesoresConElMismoID() {
		//Datos de entrada
		Profesor Esteban = new Profesor (1,"Esteban","Quito");
		Profesor Aquiles = new Profesor (1,"Aquiles","Bailoyo");
		Intraconsulta intraconsulta = new Intraconsulta();
		//Ejecución
		intraconsulta.registrarProfesor(Esteban);
		//Validación
		assertFalse(intraconsulta.registrarProfesor(Aquiles));
	}
	
}
