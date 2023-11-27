package services;

import java.util.List;
import java.util.ArrayList;
import Controllers.Validation;
import application.clases.Inmueble;
import application.clases.Propietario;
import application.clases.Provincia;
import application.clases.TipoDNI;
import application.clases.TipoInmueble;
import application.dao.PropietarioDAO;
import dto.PropietarioDTO;

public class PropietarioServices {
	private static PropietarioServices instance;
	
	private static PropietarioDAO propietariodao;
	
	public static PropietarioServices getInstance() {
		if(instance==null) {
			instance= new PropietarioServices();
			propietariodao = PropietarioDAO.getPropietarioDAO();
		}
		return instance;
	}
	
	public int createPropietario(PropietarioDTO entrada) {
		Propietario propietario = toPropietario(entrada);
		if(chequearDuplicado( propietario.getTipodni(),propietario.getDni())) {return -1;}//si existe un duplicado retorna -1
		else {
			propietariodao.createPropietario(propietario);
			return 1;}
		
	}
	public int deletePropietario(int i) {
		Propietario propietario = propietariodao.getPropietarioById(i);
		if(propietario!=null) {//si encuentra un propietario lo borra
		propietariodao.deletePropietario(propietario);
		return 1;
		}else {return -1;}
	}
	public int updatePropietario(Propietario entrada) {
		Propietario propietario = propietariodao.getPropietarioById(entrada.getId());
		if(propietario != null) {
		propietariodao.updatePropietario(propietario);
		return 1;}
		else {return-1;}
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
	private Propietario toPropietario(PropietarioDTO entrada) {
		Provincia provincia = Provincia.valueOf(entrada.getProvincia());
		TipoDNI tipodni= TipoDNI.valueOf(entrada.getTipodni());
		Propietario propietario = new Propietario(entrada.getNombre(),entrada.getApellido(),tipodni,entrada.getDni(),entrada.getCalle(),entrada.getNumero(),
				entrada.getLocalidad(),provincia,entrada.getTelefono(),entrada.getEmail());
		return propietario;
	}
	private boolean chequearDuplicado( TipoDNI tipo,String dni) {
		List<Propietario> lista=propietariodao.getCliente(tipo,dni,null,null);
		if(lista==null){return false;}
		else { return true;}
		}
}
