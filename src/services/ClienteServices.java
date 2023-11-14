package services;

import Controllers.Validation;
import application.clases.Cliente;
import application.dao.ClienteDAO;
import java.util.ArrayList;
import java.util.List;

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
	public void createCliente(Cliente cliente) {
		 clientedao.createCliente(cliente);
	};
	public void deleteCliente(Cliente cliente) {
		 clientedao.deleteCliente(cliente);
	};
	public void updateCliente(Cliente cliente) {
		clientedao.updateCliente(cliente);
	};
	public List<Cliente> getClientes(String nombre, String apellido) {
		ArrayList<String> criterios = new ArrayList<String>();
		criterios.add(nombre);
		criterios.add(apellido);
		return clientedao.getClientes(criterios);
	}
	public Cliente getClienteById(int id) {
		return clientedao.getClienteById(id);
	}
}
