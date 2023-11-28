package services;

import java.util.List;
import java.util.stream.Collectors;
import application.clases.Propietario;
import application.clases.Provincia;
import application.clases.TipoDNI;
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
	
	public PropietarioDTO getPropietarioById(int id) {
		Propietario i = propietariodao.getPropietarioById(id);
		if(i!= null) {
			return new PropietarioDTO(i);
		}
		else {
			return null;
		}
	}
	
	public List<PropietarioDTO> listPropietarios(){
		
		return propietariodao.getAllPropietario().stream()
	            .map(propietario -> new PropietarioDTO(propietario))
	            .collect(Collectors.toList());
	}
	
	public List<PropietarioDTO> getPropietario(String n, String a, String dni, String tipodni){
		
		TipoDNI tipo= TipoDNI.valueOf(tipodni.replace(" ", "_"));
		
		return propietariodao.getCliente(tipo,dni,n,a).stream()
	            .map(propietario -> new PropietarioDTO(propietario))
	            .collect(Collectors.toList());
		
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
