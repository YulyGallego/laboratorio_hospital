package logica;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import hospital.CitaMedica;
import hospital.personas.EmpleadoEventual;
import hospital.personas.EmpleadoPlanilla;
import hospital.personas.Medico;
import hospital.personas.Paciente;

public class Procesos {
	
	String  dni,nombre,apellido,fechaNacimiento,direccion,ciudad,codEmpleado,numHorasExt,fechaIngreso,area,cargo;
	
//	Paciente arregloPacientes [];
//	EmpleadoPlanilla arregloEmpleadosPlanilla [];
//	EmpleadoEventual arregloEmpleadosEventual [];
//	Medico arregloMedicos [];
//	CitaMedica arregloCitas [];
	
	ArrayList<Paciente> arregloPacientes = new ArrayList<Paciente>();
	ArrayList<EmpleadoPlanilla> arregloEmpleadosPlanilla = new ArrayList<EmpleadoPlanilla>();
	ArrayList<EmpleadoEventual> arregloEmpleadosEventual = new ArrayList<EmpleadoEventual>();
	ArrayList<Medico> arregloMedicos = new ArrayList<Medico>();
	ArrayList<CitaMedica> arregloCitas = new ArrayList<CitaMedica>();
	
	int cantCitas = 0;
	
	public Procesos () {
		int numCitas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la catidad de citas medicas por dia"));
//		arregloCitas = new CitaMedica [numCitas];
		presentarMenu ();
	}

	/**
	 * Metodo para el las opciones(menu) 
	 */
	
	private void presentarMenu() {
		int opc;
		String cad="SISTEMA HOSPITALARIO \nREGISTRO\n\n";
		cad+="1. Registrar Usuarios\n";
		cad+="2. Registrar Cita Medica\n";
		cad+="3. Imprimir Datos\n";
		cad+="4. Salir\n\n";
		
		do {
			opc=Integer.parseInt(JOptionPane.showInputDialog(cad+"Ingrese una opcion"));
			
			/**
			 * swuith case para asignar cada caso
			 */
			switch (opc) {
			case 1:
				/**
				 * se ejecuta el metodo para registro de empleados 
				 */
				cargarMenuRegistro();
				break;

			case 2:
				/**
				 *  se valida que, cantidad citas es menor que el tamaño del arreglo y si ya tiene 
				 *  previamente registrado pacinete, medico y empleado planilla, podrá hacer el 
				 *  registro, llamando el metodo registrarCitaMedica, si no se cumple, apareceran mensages de error
				 */
				System.out.println(cantCitas+"<"+arregloCitas.size());
				if (cantCitas <arregloCitas.size()) {
					if (validaRegistrosPrevios()) {
						registrarCitaMedica();
					}else {
						JOptionPane.showMessageDialog(null, "No se puedde registrar la cita, verifique que los empleados por planilla,\n"+"medicos y pacientes se encuentren previamente \nregistrados en el sistema","Advertencia",JOptionPane.WARNING_MESSAGE);
					}
				}else{      
					JOptionPane.showMessageDialog(null, "No se puede registrar la cita, Ha superado el numero de citas por dia","Advertencia",JOptionPane.WARNING_MESSAGE); 
				}
				break;

			case 3:
				/**
				 * se valida que hallan empleaados registrados (validaRegistrosEmpleados) y que 
				 * el documento sea valido (validarEmpleado) para asi imprimirDatos;  
				 */
				if (validaRegistrosEmpleados()) {
					imprimirDatos();
				}
				break;
				
			case 4:
				JOptionPane.showMessageDialog(null, "Chao");
				break;
				
			default:
				break;
			}
		/**
		 * solo se repite el ciclo si el valor ingresado es diferente a la opcion slair 
		 */
		}while (opc!=4);
		JOptionPane.showMessageDialog(null, "salio");
	}
	
	/**
	 * metodo para imprimir las opciones del menu
	 */
	private void imprimirDatos () {
		String menuMsj="Menu Reportes\n\n";
		menuMsj+="1. Imprimir Empleados de planilla\n";
		menuMsj+="2. Imprimir Empleados Eventuales\n";
		menuMsj+="3. Imprimir Medicos\n";
		menuMsj+="4. Imprimir Pacientes\n";
		menuMsj+="5. Imprimir Citas\n";
		menuMsj+="6. Salir\n\n";
		
		int cod = Integer.parseInt(JOptionPane.showInputDialog(menuMsj+"Seleccione una opcion"));
		
		/**
		 * se hace un swith case para captuar el valor ingresado, en casa opcion (case) se hace la devida 
		 * validacion, para verificar que si hallan datos regisrados, posteriormente se hace la imprecion segun corresponda 
		 */
		switch (cod) {
		case 1:
			if (arregloEmpleadosPlanilla!=null) {
				System.out.println("***************************************Empleados**************************************");
				
				for (int i= 0; i< arregloEmpleadosPlanilla.size(); i++) {
					System.out.println("Numero DNI: "+arregloEmpleadosPlanilla.get(i).getDni());
					System.out.println("Nombre: "+arregloEmpleadosPlanilla.get(i).getNombre()+""+arregloEmpleadosPlanilla.get(i).getApellido());
					System.out.println("Codigo Empleado: "+arregloEmpleadosPlanilla.get(i).getCodEmpleado());
					System.out.println("Cargo: "+arregloEmpleadosPlanilla.get(i).getCargo());
					System.out.println("Salario Mensual: "+arregloEmpleadosPlanilla.get(i).getSalarioMensual());
					System.out.println();
				}
				System.out.println("**************************************************************************************");
			}else {
				JOptionPane.showMessageDialog(null, "No existe informacion registrada","Error",JOptionPane.ERROR_MESSAGE);
			}
			
			break;
			
		case 2:
			if (arregloEmpleadosEventual!=null) {
				System.out.println("****************************************Empleados*************************************");
				
				for (int i=0; i < arregloEmpleadosEventual.size(); i++) {
					System.out.println("Numero DNI: "+arregloEmpleadosEventual.get(i).getDni());
					System.out.println("Nombre: "+arregloEmpleadosEventual.get(i).getNombre()+""+arregloEmpleadosEventual.get(i).getApellido());
					System.out.println("Codigo Empleado: "+arregloEmpleadosEventual.get(i).getCodEmpleado());
					System.out.println("Cargo: "+arregloEmpleadosEventual.get(i).getCargo());
					System.out.println("Horarios por Hora: "+arregloEmpleadosEventual.get(i).getHonorariosHora());
					System.out.println("Fecha termino de contrato: "+arregloEmpleadosEventual.get(i).getFechaterminoContrato());
					System.out.println();
				}
				System.out.println("**************************************************************************************");
			}else {
				JOptionPane.showMessageDialog(null, "No existe informacion registrada","Error",JOptionPane.ERROR_MESSAGE);
			}
			
			break;
			
		case 3:
			if (arregloMedicos!=null) {
				System.out.println("*************************************Medicos******************************************");
				
				for (int i=0; i < arregloMedicos.size(); i++) {
					System.out.println("Numero DNI: "+arregloMedicos.get(i).getDni());
					System.out.println("Nombre: "+arregloMedicos.get(i).getNombre()+""+arregloMedicos.get(i).getApellido());
					System.out.println("Codigo Empleado: "+arregloMedicos.get(i).getCodEmpleado());
					System.out.println("Cargo: "+arregloMedicos.get(i).getCargo());
					System.out.println("Especialidad: "+arregloMedicos.get(i).getEspecialidad());
					System.out.println("Numero de consultorio: "+arregloMedicos.get(i).getNumConsultorio());
					System.out.println();
				}
				System.out.println("**************************************************************************************");
			}else {
				JOptionPane.showMessageDialog(null, "No existe informacion registrada","Error",JOptionPane.ERROR_MESSAGE);
			}
			
			break;
			
		case 4:
			if (arregloPacientes!=null) {
				
				System.out.println("*************************************Pacientes******************************************");
				for (int i=0; i < arregloPacientes.size(); i++) {
					System.out.println("Numero DNI: "+arregloPacientes.get(i).getDni());
					System.out.println("Nombre: "+arregloPacientes.get(i).getNombre()+" "+arregloPacientes.get(i).getApellido());
					System.out.println("Numero Historia Clinica: "+arregloPacientes.get(i).getNumhistoria());
					System.out.println("Sexo: "+arregloPacientes.get(i).getSexo());
					System.out.println("Grupo Sanguineo: "+arregloPacientes.get(i).getGrupoSanguineo());
					System.out.println("Lista Medicamentos");
					//hay un array
					for (int j=0; j < arregloPacientes.get(i).getListaMedicamentos().size(); j++) {
						System.out.println(arregloPacientes.get(i).getListaMedicamentos().get(i
								)+" / ");
					}
					System.out.println();
				}
				System.out.println("**************************************************************************************");
			}else {
				JOptionPane.showMessageDialog(null, "No existe informacion registrada","Error",JOptionPane.ERROR_MESSAGE);
			}
			
			break;
			
		case 5:
			if (arregloCitas != null) {
				System.out.println("*************************************Citas******************************************");
				
				for (int i=0; i < arregloCitas.size(); i++) {
					System.out.println("Servicio: "+arregloCitas.get(i).getServicio());
					System.out.println("Paciente: "+arregloCitas.get(i).getPaciente().getNombre()+""+arregloCitas.get(i).getPaciente().getApellido());
					System.out.println("Medico: "+arregloCitas.get(i).getMedico().getNombre()+""+arregloCitas.get(i).getMedico().getApellido());
					System.out.println("Fecha: "+arregloCitas.get(i).getFecha());
					System.out.println("Hora: "+arregloCitas.get(i).getHora());
					System.out.println();
				}
			System.out.println("**************************************************************************************");
			}else {
				JOptionPane.showMessageDialog(null, "No existe informacion registrada","Error",JOptionPane.ERROR_MESSAGE);
			}
			
			break;
			
		default:
			break;
		}
	}
	
	/**
	 * 'validaRegistrosEmpleados' para retornar true si hay empleados (de Planilla o Eventuales) 
	 * registrados, de lo contrario retornara false y enviara un mensage de error
	 */
	private boolean validaRegistrosEmpleados () {
		boolean retorno = false;
		
		if (arregloEmpleadosPlanilla!= null || arregloEmpleadosEventual!= null) {
			if (validarEmpleado()) {
				retorno = true;
			}else {
				JOptionPane.showMessageDialog(null, "No existen empleados con ese documento","Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}else {
			JOptionPane.showMessageDialog(null, "No existen empleados Registrados","Error",JOptionPane.ERROR_MESSAGE);
		}
		
		return retorno;
		
	}
	
	/**
	 * @return retorna true si el numero de documento se encuentra en el arreglo 
	 */
	private boolean validarEmpleadoPlanilla () {
		
		String documentoEmpleado =JOptionPane.showInputDialog("Ingrese el documento del empleado por planilla");
		boolean retorna = false;
		for (int i=0; i < arregloEmpleadosPlanilla.size(); i++) {
			if (documentoEmpleado.equals(arregloEmpleadosPlanilla.get(i).getDni())) {
				retorna = true;
			}
		}
		return retorna;
	}
	
	/**
	 * @return retorna true si el documento ingresado se encuentra en el arreglo si no se encuentra retorna false
	 */
	private boolean validarEmpleado () {
		String documentoEmpleado =JOptionPane.showInputDialog("Ingrese el documento del empleado por planilla");
		boolean retorna = false;
		if (arregloEmpleadosPlanilla != null) {
			for (int i=0; i < arregloEmpleadosPlanilla.size(); i++) {
				if (documentoEmpleado.equals(arregloEmpleadosPlanilla.get(i).getDni())) {
					retorna = true;
				}
			}
		}
		
		if (retorna == false) {
			if (arregloEmpleadosEventual !=null) {
				for (int i=0; i < arregloEmpleadosEventual.size(); i++) {
					if (documentoEmpleado.equals(arregloEmpleadosEventual.get(i).getDni())) {
						retorna = true;
					}
				}
			}
		}
		
		return retorna;
	}
	
	/**
	 * @return retorna true si hay registro en los arreglos 'arregloPacientes','arregloMedicos' y 
	 * 'arregloEmpleadosPlanilla' de lo contrario retornara false 
	 */
	private boolean validaRegistrosPrevios () {
		boolean retorno = false;
		
		if (arregloPacientes != null && arregloMedicos!= null && arregloEmpleadosPlanilla != null) {
			retorno = true;
		}
		return retorno;
	}
	
	/**
	 * se hace una validacion para verificar el documento de empleado de planilla, posteriormente 
	 * se procede a la incercion de datos para registrar cita medica 
	 */
	private void registrarCitaMedica() {
		if (validarEmpleadoPlanilla()) {
			CitaMedica  miCita = new CitaMedica ();
			miCita.setServicio(JOptionPane.showInputDialog("Ingrese el Servicio"));
			miCita.setPaciente(asignarPaciente());
			miCita.setMedico(asignaMedico());
			
			miCita.setFecha(JOptionPane.showInputDialog("Ingrese la fecha"));
			miCita.setHora(JOptionPane.showInputDialog("Ingrese la hora"));
			
			JOptionPane.showMessageDialog(null, "La cita se ha registrado exitosamente!!!");
			arregloCitas.add(miCita);
			cantCitas++;
		}else {
			JOptionPane.showMessageDialog(null, "El documento no corresponde a un empleado por planilla","Advertencia",JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/**
	 * @return retorna true cuando el documento es incorrecto, reitiendo el ciclo hasta que llegue un false, antes 
	 * se verifica en un ciclo for que el documento coincida  
	 */
	private Medico asignaMedico () {
		boolean repiteCiclo = false;
		Medico miMedico = null;
		do {
			String documentoPaciente = JOptionPane.showInputDialog("Ingrese el documento del Medico");
			for (int i=0; i <arregloMedicos.size(); i++) {
				if (documentoPaciente.equals(arregloMedicos.get(i).getDni())) {
					miMedico=arregloMedicos.get(i);
				}
				
			}
			
			if (miMedico != null) {
				repiteCiclo = false;
			}else {
				repiteCiclo = true;
				JOptionPane.showMessageDialog(null, "El documento no corresponde a un Medico\n" + "por favor ingrese un documento valido","Advertencia",JOptionPane.WARNING_MESSAGE);
			}
		}while (repiteCiclo == true);
		return miMedico;
	}
	
	private Paciente asignarPaciente () {
		boolean repiteCiclo = false;
		Paciente miPaciente = null;
		do {
			String documentoPaciente = JOptionPane.showInputDialog("Ingrese el documento del Paciente");
			for (int i=0; i <arregloPacientes.size(); i++) {
				if (documentoPaciente.equals(arregloPacientes.get(i).getDni())) {
					miPaciente=arregloPacientes.get(i);
				}
				
			}
			
			if (miPaciente != null) {
				repiteCiclo = false;
			}else {
				repiteCiclo = true;
				JOptionPane.showMessageDialog(null, "El documento no corresponde a un Medico\n" + "por favor ingrese un documento valido","Advertencia",JOptionPane.WARNING_MESSAGE);
			}
		}while (repiteCiclo == true);
		return miPaciente;
	}
	
	/**
	 * metodo para registro de personas 
	 */
	private void cargarMenuRegistro() {
		String cad ="REGISTRO DE USUARIOS\nregistro\n\n";
		cad+="1. Registrar Empleado\n";
		cad+="2. Registrar Paciente\n";
		cad+="3. Regresar\n\n";
		
		int opc = Integer.parseInt(JOptionPane.showInputDialog(cad+"ingrese una opcion"));
		
		switch (opc) {
		case 1:
			/**
			 * se crea un String con una lista la cual se envia por parametro, en el metodo 'registrarEmpleado'
			 */
			String tipoEmpleado="Tipo Emplado\n\n";
			tipoEmpleado += "1. Empleado Planilla\n";
			tipoEmpleado += "2. Empleado Eventual\n";
			tipoEmpleado += "3. Medico\n\n";
			
			int tipo= Integer.parseInt(JOptionPane.showInputDialog(tipoEmpleado+ "Seleccione el tipo de empleado"));
			registrarEmpleado (tipo);
			
			break;
			
		case 2:
			/**
			 * se realiza el registro del paciente, mediante el metodo 'registrarPaciente'
			 */
			registrarPaciente();
			
			break;
			
		case 3:
			/**
			 * se regresa a la lista anterior 
			 */
			break;

		default:
			break;
		}
	}
	
	/**
	 * si el arreglo es null entoces se hace la devida incercion de datos, la cual se hace desde los hijos, llamando 
	 * los atributos heredados, luego son guardados los datos en el obgeto.
	 */
	private void registrarPaciente() {
		if (arregloPacientes.size()==0) {
			int cantPaciente = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de Pacientes a registrar"));
//			arregloPacientes=new Paciente [cantPaciente];
			for (int i=0; i < cantPaciente; i++) {
				JOptionPane.showMessageDialog(null, "Registro de datos paciente "+(i+1));
				Paciente miPaciente = new Paciente ();
				
				miPaciente.setDni(JOptionPane.showInputDialog("Ingrese el DNI del Paciente "+(i+1)));
				miPaciente.setNombre(JOptionPane.showInputDialog("Ingrese el Nombre del Paciente "+(i+1)));
				miPaciente.setApellido(JOptionPane.showInputDialog("Ingrese el Apellido del Paciente "+(i+1)));
				miPaciente.setFechaNacimiento(JOptionPane.showInputDialog("Ingrese la fecha de Nacimiento del Paciente "+(i+1)));
				miPaciente.setDireccion(JOptionPane.showInputDialog("Ingrese la Direcci�n del Paciente "+(i+1)));
				miPaciente.setCiudad(JOptionPane.showInputDialog("Ingrese la Ciudad del Paciente "+(i+1)));
				miPaciente.setNumHistoria(JOptionPane.showInputDialog("Ingrese el Numero de la Historia Clinica del Paciente "+(i+1)));
				miPaciente.setSexo(JOptionPane.showInputDialog("Ingrese el Sexo del Paciente "+(i+1)));
				miPaciente.setGrupoSanguineo(JOptionPane.showInputDialog("Ingrese el Grupo Sanguineo del Paciente "+(i+1)));
				
				int cantMedic =Integer.parseInt(JOptionPane.showInputDialog("Cuantos medicamentos a los que es alergico el paciente "+(i+1)+" desea registrar?"));
//				 String arregloMedicamentos[]= new String [cantMedic];
				ArrayList<String> arregloMedicamentos = new ArrayList<String> ();
				 
				 for (int j = 0; j < cantMedic; j++) {
					 arregloMedicamentos.add(JOptionPane.showInputDialog("Ingrese el medicamento "+(j+1)));
					 
				 }
				 
				 //cambie el set por un arrayList 
				 miPaciente.setListaMedicamentos(arregloMedicamentos);
					 
					 arregloPacientes.add(miPaciente);
				 }
				JOptionPane.showMessageDialog(null, "El registro de pacientes se ha realizado con exito");
			
		}else {
			JOptionPane.showMessageDialog(null, "Ya se han registrado los pacientes","ERROR",JOptionPane.ERROR_MESSAGE);
		}
	
	}
	
	/**
	 * si el arreglo es null entoces se hace la devida incercion de datos, la cual se hace desde los hijos, llamando 
	 * los atributos heredados, luego son guardados los datos en el obgeto.
	 */
	private void registrarEmpleado (int tipo) {
		
		switch (tipo) {
		case 1:
			if (arregloEmpleadosPlanilla.size()==0) {
				int cantEmpleadoPlanilla = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de empleados por planilla a registrar"));
//				arregloEmpleadosPlanilla = new EmpleadoPlanilla [cantEmpleadoPlanilla];
				for (int i = 0; i < cantEmpleadoPlanilla; i++) {
					JOptionPane.showInternalMessageDialog(null,"Registro de datos Empleado Planilla "+(i+1));
					asignarValoresGeneralesEmpleado("Empleados por planilla "+(i+1));
					EmpleadoPlanilla miEmpleadoPlanilla = new EmpleadoPlanilla();
					
					miEmpleadoPlanilla.setDni(dni);
					miEmpleadoPlanilla.setNombre(nombre);
					miEmpleadoPlanilla.setApellido(apellido);
					
					miEmpleadoPlanilla.setFechaNacimiento (fechaNacimiento);
					miEmpleadoPlanilla.setDireccion (direccion);
					miEmpleadoPlanilla.setCiudad (ciudad);
					
					miEmpleadoPlanilla.setCodEmpleado(codEmpleado);
					
					miEmpleadoPlanilla.setNumHorasExtras(numHorasExt);
					
					miEmpleadoPlanilla.setFechaIngreso(fechaIngreso);
					miEmpleadoPlanilla.setArea(area);
					miEmpleadoPlanilla.setCargo(cargo);
					

					miEmpleadoPlanilla.setSalarioMensual(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el salario mensual del empleado por planilla"+(i+1))));
					miEmpleadoPlanilla.setPorcentajeAdicional(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el salario mensual del empleado por planilla"+(i+1))));
					
					arregloEmpleadosPlanilla.add(miEmpleadoPlanilla);
				}
				JOptionPane.showMessageDialog(null, "El registro de empledos por Planilla se ha realizado con exito");
			}else {
				JOptionPane.showMessageDialog(null, "Ya se han registrado los empleados por Planilla","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			
			break;
			
		case 2:
			if (arregloEmpleadosEventual.size()==0) {
				int cantEmpleadoEventual = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de empleados eventuales a registrar"));
//				arregloEmpleadosEventual = new EmpleadoEventual [cantEmpleadoEventual];
				for (int i = 0; i < cantEmpleadoEventual; i++) {
					JOptionPane.showInternalMessageDialog(null,"Registro de datos Empleado Eventual "+(i+1));
					asignarValoresGeneralesEmpleado("Empleados Eventual "+(i+1));
					EmpleadoEventual miEmpleadoEventual = new EmpleadoEventual();
					
					miEmpleadoEventual.setDni(dni);
					miEmpleadoEventual.setNombre(nombre);
					miEmpleadoEventual.setApellido(apellido);
					
					miEmpleadoEventual.setFechaNacimiento (fechaNacimiento);
					miEmpleadoEventual.setDireccion (direccion);
					miEmpleadoEventual.setCiudad (ciudad);
					
					miEmpleadoEventual.setCodEmpleado(codEmpleado);
					
					miEmpleadoEventual.setNumHorasExtras(numHorasExt);
					
					miEmpleadoEventual.setFechaIngreso(fechaIngreso);
					miEmpleadoEventual.setArea(area);
					miEmpleadoEventual.setCargo(cargo);
					

					miEmpleadoEventual.setHonorariosHora(Double.parseDouble(JOptionPane.showInputDialog("Ingrese los honorarios hora del Empleado Eventual"+(i+1))));
					miEmpleadoEventual.setFechaTerminoContrato(JOptionPane.showInputDialog("Ingrese la fecha de terminacion del Empleado Eventual"+(i+1)));
					
					arregloEmpleadosEventual.add(miEmpleadoEventual);
				}
				JOptionPane.showMessageDialog(null, "El registro de Empledos Eventuales se ha realizado con exito");
			}else {
				JOptionPane.showMessageDialog(null, "Ya se han registrado los Empleados Eventual","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			
			break;

		case 3:
			if (arregloMedicos.size()==0) {
				int cantMedicos = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de Medicos a registrar"));
//				arregloMedicos = new Medico [cantMedicos];
				for (int i = 0; i < cantMedicos; i++) {
					JOptionPane.showInternalMessageDialog(null,"Registro de datos Medico "+(i+1));
					asignarValoresGeneralesEmpleado("Medico "+(i+1));
					Medico miMedico = new Medico();
					
					miMedico.setDni(dni);
					miMedico.setNombre(nombre);
					miMedico.setApellido(apellido);
					
					miMedico.setFechaNacimiento (fechaNacimiento);
					miMedico.setDireccion (direccion);
					miMedico.setCiudad (ciudad);
					
					miMedico.setCodEmpleado(codEmpleado);
					
					miMedico.setNumHorasExtras(numHorasExt);
					
					miMedico.setFechaIngreso(fechaIngreso);
					miMedico.setArea(area);
					miMedico.setCargo(cargo);
					

					miMedico.setEspecialidad (JOptionPane.showInputDialog("Ingrese la Especialidad del Medico"+(i+1)));
					miMedico.setNumConsultorio(Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de consultorio del Medico"+(i+1))));
					
					arregloMedicos.add(miMedico);
				}
				JOptionPane.showMessageDialog(null, "El registro de Medicos se ha realizado con exito");
			}else {
				JOptionPane.showMessageDialog(null, "Ya se han registrado los Medicos","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			
			break;
			
		default:
			break;
		}
	}
	
	/**
	 * se utilizan variables las cuales se les asignan valores ingresados
	 */
	private void asignarValoresGeneralesEmpleado (String tipo) {
		dni=JOptionPane.showInputDialog("Ingrese el DNI del tipo "+tipo);
		nombre=JOptionPane.showInputDialog("Ingrese el nombre del "+tipo);
		apellido=JOptionPane.showInputDialog("Ingrese el apellido del "+tipo);
		fechaNacimiento=JOptionPane.showInputDialog("Ingrese la fecha de Nacimiento del "+tipo);
		direccion=JOptionPane.showInputDialog("Ingrese la direccion del "+tipo);
		ciudad=JOptionPane.showInputDialog("Ingrese la ciudad del "+tipo);
		codEmpleado=JOptionPane.showInputDialog("Ingrese el cod empleado de "+tipo);
		numHorasExt=JOptionPane.showInputDialog("Ingrese el num de Horas Extras del  "+tipo);
		fechaIngreso=JOptionPane.showInputDialog("Ingrese la fecha Ingreso del "+tipo);
		area=JOptionPane.showInputDialog("Ingrese el area del "+tipo);
		cargo=JOptionPane.showInputDialog("Ingrese el cargo del "+tipo);
	}
	
}