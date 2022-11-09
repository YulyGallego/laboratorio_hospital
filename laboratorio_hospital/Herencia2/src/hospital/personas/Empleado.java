package hospital.personas;

public class Empleado extends Persona{
	/**
	 * caracteristicas de un empledo
	 */
	private String codEmpleado;
	private String numHorasExtras;
	private String fechaIngreso;
	private String area;
	private String Cargo;
	
	public String getCodEmpleado () {
		return codEmpleado;
	}
	
	public void setCodEmpleado (String codEmpleado) {
		this.codEmpleado = codEmpleado; 
	}
	
	public String getNumHorasExtras () {
		return numHorasExtras;
	}
	
	public void setNumHorasExtras (String numHorasExtras) {
		this.numHorasExtras = numHorasExtras; 
	}
	
	public String getFechaIngreso () {
		return fechaIngreso;
	}
	
	public void setFechaIngreso (String fechaIngreso) {
		this.fechaIngreso = fechaIngreso; 
	}
	
	public String getArea () {
		return area;
	}
	
	public void setArea (String area) {
		this.area = area; 
	}
	
	public String getCargo () {
		return Cargo;
	}
	
	public void setCargo (String Cargo) {
		this.Cargo = Cargo; 
	}
	
	
	
}

