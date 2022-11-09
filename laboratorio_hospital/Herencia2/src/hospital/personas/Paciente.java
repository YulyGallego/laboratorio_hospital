package hospital.personas;

import java.util.ArrayList;

public class Paciente extends Persona{
	
	/**
	 * caracteristicas de un paciente
	 */
	private String numhistoria;
	private String sexo;
	private String grupoSanguineo;
	private ArrayList<String>listaMedicamentos;
	
	public String getNumhistoria () {
		return numhistoria;
	}
	
	public void setNumHistoria (String numhistoria) {
		this.numhistoria = numhistoria;
	}
	
	public String getSexo () {
		return sexo;
	}
	
	public void setSexo (String sexo) {
		this.sexo = sexo;
	}
	
	public String getGrupoSanguineo () {
		return grupoSanguineo;
	}
	
	public void setGrupoSanguineo (String grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}
	
	public ArrayList<String> getListaMedicamentos () {
		return listaMedicamentos;
	}
	
	public void setListaMedicamentos (ArrayList<String>arregloMedicamentos) {
		this.listaMedicamentos = listaMedicamentos;
	}

}
