package hospital;
/*
 * clase padre
 */
import hospital.personas.Medico;
import hospital.personas.Paciente;

public class CitaMedica {
	
	/*
	 * creamos las caracteristicas para crear las citas medicas
	 */
	private String servicio;
	private Paciente paciente;
	private Medico medico;
	private String fecha;
	private String hora;

	public String getServicio () {
		return servicio;
	}
	
	public void setServicio (String servicio) {
		this.servicio = servicio;
	}
	
	public Paciente getPaciente () {
		return paciente;
	}
	
	public void setPaciente (Paciente paciente) {
		this.paciente = paciente;
	}
	
	public Medico getMedico () {
		return medico;
	}
	
	public void setMedico (Medico medico) {
		this.medico = medico;
	}
	
	public String getFecha () {
		return fecha;
	}
	
	public void setFecha (String fecha) {
		this.fecha = fecha;
	}
	
	public String getHora () {
		return hora;
	}
	
	public void setHora (String hora) {
		this.hora = hora;
	}
}
