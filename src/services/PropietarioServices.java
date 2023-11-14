package services;

import java.util.List;

import Controllers.Validation;
import application.clases.Propietario;
import application.dao.PropietarioDAO;

public class PropietarioServices {
	PropietarioDAO propietariodao;
	public PropietarioServices() {
		super();
		propietariodao = PropietarioDAO.getPropietarioDAO();
	}
	
	public void createPropietario(Propietario propietario) {
		//validacion?
		propietariodao.createPropietario(propietario);
	}
	public void deletePropietario(Propietario propietario) {
		propietariodao.deletePropietario(propietario);
	}
	public void updatePropietario(Propietario propietario) {
		propietariodao.updatePropietario(propietario);
	}
	
	public Propietario getPropietarioById(int id) {
		Propietario i = propietariodao.getPropietarioById();
		if(i!= null) {
			return i;
		}
		else {
			System.out.print("no existe propietario con id: "+id);
			return null;
		}
	}
	
	public List<Propietario> listPropietarios(){
		return propietariodao.listPropietarios();
	}
	
}
