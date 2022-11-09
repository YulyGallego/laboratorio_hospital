package hospital.personas;

public class Persona {
	
	/**
	 * caracteristicas de una persona
	 */
	private String dni; 
	private String nombre;
	private String apellido;
	private String fechaNacimiento;
	private String direccion;
	private String ciudad;
	
	public String getDni() {
		return dni;
	}
	public void setDni (String dni) {
		this.dni = dni;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre (String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return dni;
	}
	public void setApellido (String apellido) {
		this.apellido = apellido;
	}
	
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento (String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion (String direccion) {
		this.direccion = direccion;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad (String ciudad) {
		this.ciudad = ciudad;
	}
}
