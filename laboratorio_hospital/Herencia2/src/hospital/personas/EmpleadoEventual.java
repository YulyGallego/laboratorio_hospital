package hospital.personas;

public class EmpleadoEventual extends Empleado{
	/**
	 * caracteristicas de un empleado eventual
	 */
	private double honorariosHora;
	private String fechaterminoContrato;
	
	public double getHonorariosHora () {
		return honorariosHora;
	}
	
	public void setHonorariosHora (double honorariosHora) {
		this.honorariosHora = honorariosHora;
	}
	
	public String getFechaterminoContrato () {
		return fechaterminoContrato;
	}
	
	public void setFechaTerminoContrato (String fechaterminoContrato) {
		this.fechaterminoContrato = fechaterminoContrato;
	}
	
}
