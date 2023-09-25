package Intraconsulta;

public class CicloLectivo {
	Integer Id;
	String fechaInicioCicloLectivo, fechaFinCicloLectivo,fechaInicioInscripcion, fechaFinInscripcion;
	
	public CicloLectivo(Integer id, String fechaInicioCicloLectivo, String fechaFinCicloLectivo,
			String fechaInicioInscripcion, String fechaFinInscripcion) {
		super();
		Id = id;
		this.fechaInicioCicloLectivo = fechaInicioCicloLectivo;
		this.fechaFinCicloLectivo = fechaFinCicloLectivo;
		this.fechaInicioInscripcion = fechaInicioInscripcion;
		this.fechaFinInscripcion = fechaFinInscripcion;
	}
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getFechaInicioCicloLectivo() {
		return fechaInicioCicloLectivo;
	}
	public void setFechaInicioCicloLectivo(String fechaInicioCicloLectivo) {
		this.fechaInicioCicloLectivo = fechaInicioCicloLectivo;
	}
	public String getFechaFinCicloLectivo() {
		return fechaFinCicloLectivo;
	}
	public void setFechaFinCicloLectivo(String fechaFinCicloLectivo) {
		this.fechaFinCicloLectivo = fechaFinCicloLectivo;
	}
	public String getFechaInicioInscripcion() {
		return fechaInicioInscripcion;
	}
	public void setFechaInicioInscripcion(String fechaInicioInscripcion) {
		this.fechaInicioInscripcion = fechaInicioInscripcion;
	}
	public String getFechaFinInscripcion() {
		return fechaFinInscripcion;
	}
	public void setFechaFinInscripcion(String fechaFinInscripcion) {
		this.fechaFinInscripcion = fechaFinInscripcion;
	}
	
	
}
