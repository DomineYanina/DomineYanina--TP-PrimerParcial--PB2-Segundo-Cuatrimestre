package Intraconsulta;

import java.time.LocalDate;

public class CicloLectivo {
	Integer Id;
	LocalDate fechaInicioCicloLectivo, fechaFinCicloLectivo,fechaInicioInscripcion, fechaFinInscripcion;
	
	public CicloLectivo(Integer id, LocalDate fechaInicioCicloLectivo, LocalDate fechaFinCicloLectivo,
			LocalDate fechaInicioInscripcion, LocalDate fechaFinInscripcion) {
		super();
		Id = id;
		this.fechaInicioCicloLectivo = fechaInicioCicloLectivo;
		this.fechaFinCicloLectivo = fechaFinCicloLectivo;
		this.fechaInicioInscripcion = fechaInicioInscripcion;
		this.fechaFinInscripcion = fechaFinInscripcion;
	}
	
	public boolean fechaEstaEnRango(LocalDate fecha) {
        return (fecha.isAfter(fechaInicioCicloLectivo) || fecha.equals(fechaInicioCicloLectivo))
                && (fecha.isBefore(fechaFinCicloLectivo) || fecha.equals(fechaFinCicloLectivo));
    }
	public boolean fechaEstaEnRangoDeInscripciones(LocalDate fecha) {
        return (fecha.isAfter(fechaInicioInscripcion) || fecha.equals(fechaInicioInscripcion))
                && (fecha.isBefore(fechaFinInscripcion) || fecha.equals(fechaFinInscripcion));
    }
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public LocalDate getFechaInicioCicloLectivo() {
		return fechaInicioCicloLectivo;
	}
	public void setFechaInicioCicloLectivo(LocalDate fechaInicioCicloLectivo) {
		this.fechaInicioCicloLectivo = fechaInicioCicloLectivo;
	}
	public LocalDate getFechaFinCicloLectivo() {
		return fechaFinCicloLectivo;
	}
	public void setFechaFinCicloLectivo(LocalDate fechaFinCicloLectivo) {
		this.fechaFinCicloLectivo = fechaFinCicloLectivo;
	}
	public LocalDate getFechaInicioInscripcion() {
		return fechaInicioInscripcion;
	}
	public void setFechaInicioInscripcion(LocalDate fechaInicioInscripcion) {
		this.fechaInicioInscripcion = fechaInicioInscripcion;
	}
	public LocalDate getFechaFinInscripcion() {
		return fechaFinInscripcion;
	}
	public void setFechaFinInscripcion(LocalDate fechaFinInscripcion) {
		this.fechaFinInscripcion = fechaFinInscripcion;
	}
	
	
}
