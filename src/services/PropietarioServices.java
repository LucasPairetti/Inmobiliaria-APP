package services;

import java.util.List;
import java.util.ArrayList;
import Controllers.Validation;
import application.clases.Propietario;
import application.clases.TipoDNI;
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
		Propietario i = propietariodao.getPropietarioById(id);
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
	
	public List<Propietario> getPropietario(String n, String a, String dni, TipoDNI tipodni){
		ArrayList<Object> criterios = new ArrayList<Object>();
		criterios.add(n);
		criterios.add(a);
		criterios.add(dni);
		criterios.add(tipodni);
		propietariodao.getPropietario(criterios);
	}
}
