package hospital.personas;

public class EmpleadoPlanilla extends Empleado{
	
	/**
	 * caracteristicas de un empleado de planilla
	 */
	private double salarioMensual;
	private double porcentajeAdicional;
	
	public double getSalarioMensual () {
		return salarioMensual;
	}
	
	public void setSalarioMensual (double  salarioMensual) {
		this.salarioMensual = salarioMensual;
	}
	
	public double getPorcentajeAdicional () {
		return porcentajeAdicional;
	}
	
	public void setPorcentajeAdicional (double  porcentajeAdicional) {
		this.porcentajeAdicional = porcentajeAdicional;
	}
	
}
