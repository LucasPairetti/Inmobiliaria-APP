package services;

import java.util.List;
import java.util.stream.Collectors;


import application.clases.TipoDNI;
import application.clases.TipoInmueble;
import application.clases.Vendedor;
import application.dao.VendedorDAO;


public class VendedorServices {
	private static VendedorServices instance;
	private static VendedorDAO vendedordao;
	
	public static VendedorServices getInstance() {
		if(instance==null) {
			instance = new VendedorServices();
			vendedordao = VendedorDAO.getVendedorDAO(); 
		}
		return instance;
	}
	public int createVendedor(VendedorDTO entrada) {
		if(chequearDuplicado( entrada.getTipodni(),entrada.getDni())) {return -1;}//si existe un duplicado retorna -1
		else {
			Vendedor vendedor = toVendedor(entrada);
			vendedordao.createVendedor(vendedor);
			return 1;}
		
	};
	public int deleteVendedor(int id) {
		Vendedor vendedor = vendedordao.getVendedorById(id);
		if(vendedor !=null) {
			vendedordao.deleteVendedor(vendedor);
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
		return new Cliente( entrada.getNombre(), entrada.getApellido(), entrada.getDni(),entrada.getTipoDNI(),entrada.getTelefono(),
				entrada.getMontoDisponible(),tipoInmueble, entrada.getLocalidadBuscada(), entrada.getBarrios(),entrada.getCaracteristicasDeseadas());
	}
	private boolean chequearDuplicado( TipoDNI tipo,String dni) {
		List<Cliente> lista=clientedao.getCliente(tipo,dni,null,null);
		if(lista==null){return false;}
		else { return true;}
		}
}
