package services;

import Controllers.Validation;
import application.clases.Cliente;
import application.clases.Propietario;
import application.clases.TipoDNI;
import application.clases.TipoInmueble;
import application.dao.ClienteDAO;
import dto.ClienteDTO;
import dto.PropietarioDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteServices {
	private static ClienteServices instance;
	private static ClienteDAO clientedao;
	
	public static ClienteServices getInstance() {
		if(instance==null) {
			instance = new ClienteServices();
			clientedao = ClienteDAO.getClienteDAO(); 
		}
		return instance;
	}
	public int createCliente(ClienteDTO entrada) {
		TipoDNI tipoDNI= TipoDNI.valueOf(entrada.getTipoDni());
		if(chequearDuplicado( tipoDNI,entrada.getDni())) {return -1;}//si existe un duplicado retorna -1
		else {
			Cliente cliente = toCliente(entrada);
			 clientedao.createCliente(cliente);
			return 1;}
		
	};
	public int deleteCliente(int id) {
		Cliente cliente = clientedao.getClienteById(id);
		if(cliente !=null) {
			clientedao.deleteCliente(cliente);
			return 1;
		}else {return -1;}
	};
	public int updateCliente(ClienteDTO entrada) {
		Cliente og = clientedao.getClienteById(entrada.getId());
		if(og !=null) {
			Cliente cliente = toCliente(entrada);
			cliente.setDni(og.getDni());
			cliente.setTipodni(og.getTipodni());
			clientedao.updateCliente(cliente);
			return 1;
		}else {return -1;}
		
	};
	public List<ClienteDTO> listClientes(){
		return clientedao.getAllClientes().stream()
	            .map(cliente -> new ClienteDTO(cliente))
	            .collect(Collectors.toList());
	}
	public List<ClienteDTO> getClientes(String tipoDNI,String dni,String nombre, String apellido) {
		TipoDNI tipo= TipoDNI.valueOf(tipoDNI.replace(" ", "_"));

		return clientedao.getCliente(tipo,dni,nombre,apellido).stream()
	            .map(cliente -> new ClienteDTO(cliente))
	            .collect(Collectors.toList());
	}
	public ClienteDTO getClienteById(int id) {
		return new ClienteDTO(clientedao.getClienteById(id));
	}
	private Cliente toCliente( ClienteDTO entrada) {
		TipoInmueble tipoInmueble= TipoInmueble.valueOf(entrada.getTipoInmuebleBuscado());
		TipoDNI tipoDNI= TipoDNI.valueOf(entrada.getTipoDni());
		return new Cliente( entrada.getNombre(), entrada.getApellido(), entrada.getDni(),tipoDNI,entrada.getTelefono(),
				entrada.getMontoDisponible(),tipoInmueble, entrada.getLocalidadBuscada(), entrada.getBarrios(),entrada.getCaracteristicasDeseadas());
	}
	private boolean chequearDuplicado( TipoDNI tipo,String dni) {
		List<Cliente> lista=clientedao.getCliente(tipo,dni,null,null);
		if(lista==null){return false;}
		else { return true;}
		}
}
