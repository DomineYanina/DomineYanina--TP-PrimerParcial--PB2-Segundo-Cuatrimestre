package Intraconsulta;

import java.util.Date;

public class CicloLectivo {
	Integer Id;
	Date fechaInicioCicloLectivo, fechaFinCicloLectivo,fechaInicioInscripcion, fechaFinInscripcion;
	
	public CicloLectivo(Integer id, Date fechaInicioCicloLectivo, Date fechaFinCicloLectivo,
			Date fechaInicioInscripcion, Date fechaFinInscripcion) {
		super();
		Id = id;
		this.fechaInicioCicloLectivo = fechaInicioCicloLectivo;
		this.fechaFinCicloLectivo = fechaFinCicloLectivo;
		this.fechaInicioInscripcion = fechaInicioInscripcion;
		this.fechaFinInscripcion = fechaFinInscripcion;
	}
	
	public boolean fechaEstaEnRango(Date fecha) {
        return (fecha.after(fechaInicioCicloLectivo) || fecha.equals(fechaInicioCicloLectivo))
                && (fecha.before(fechaFinCicloLectivo) || fecha.equals(fechaFinCicloLectivo));
    }
	public boolean fechaEstaEnRangoDeInscripciones(Date fecha) {
        return (fecha.after(fechaInicioInscripcion) || fecha.equals(fechaInicioInscripcion))
                && (fecha.before(fechaFinInscripcion) || fecha.equals(fechaFinInscripcion));
    }
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Date getFechaInicioCicloLectivo() {
		return fechaInicioCicloLectivo;
	}
	public void setFechaInicioCicloLectivo(Date fechaInicioCicloLectivo) {
		this.fechaInicioCicloLectivo = fechaInicioCicloLectivo;
	}
	public Date getFechaFinCicloLectivo() {
		return fechaFinCicloLectivo;
	}
	public void setFechaFinCicloLectivo(Date fechaFinCicloLectivo) {
		this.fechaFinCicloLectivo = fechaFinCicloLectivo;
	}
	public Date getFechaInicioInscripcion() {
		return fechaInicioInscripcion;
	}
	public void setFechaInicioInscripcion(Date fechaInicioInscripcion) {
		this.fechaInicioInscripcion = fechaInicioInscripcion;
	}
	public Date getFechaFinInscripcion() {
		return fechaFinInscripcion;
	}
	public void setFechaFinInscripcion(Date fechaFinInscripcion) {
		this.fechaFinInscripcion = fechaFinInscripcion;
	}
	
	
}
