package services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import application.clases.Propietario;
import application.clases.Provincia;
import application.clases.TipoDNI;
import application.clases.TipoInmueble;
import application.clases.Vendedor;
import application.dao.InmuebleDAO;
import application.dao.PropietarioDAO;
import dto.InmuebleDTO;
import dto.PropietarioDTO;
import dto.VendedorDTO;

public class PropietarioServices {
	private static PropietarioServices instance;
	
	private static PropietarioDAO propietariodao;
	private static InmuebleDAO inmuebledao;
	
	public static PropietarioServices getInstance() {
		if(instance==null) {
			instance= new PropietarioServices();
			propietariodao = PropietarioDAO.getPropietarioDAO();
			inmuebledao = InmuebleDAO.getInmuebleDAO();
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
	public int updatePropietario(PropietarioDTO entrada) {
		Propietario og = propietariodao.getPropietarioById(entrada.getId());
		if(og != null) {//si existe el propierario reestablece los datos que no se modifican y guarda los nuevos.
			actualizarPropietario(og,entrada);
			propietariodao.updatePropietario(og);
			return 1;}
		else {return -1;}
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
	
	public List<PropietarioDTO> listPropietarios() {
	    return Optional.ofNullable(propietariodao.getAllPropietario())
	            .map(List::stream)
	            .orElseGet(Stream::empty)
	            .map(propietario -> new PropietarioDTO(propietario))
	            .collect(Collectors.toList());
	}
	public List<PropietarioDTO> getPropietario(String n, String a, String dni, String tipodni) {
	    TipoDNI tipo = TipoDNI.valueOf(tipodni.replace(" ", "_"));

	    return Optional.ofNullable(propietariodao.getPropietario(tipo, dni, n, a))
	            .map(List::stream)
	            .orElseGet(Stream::empty)
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
private void actualizarPropietario(Propietario original, PropietarioDTO nuevo) {
		
		Provincia provincia =Provincia.valueOf(nuevo.getProvincia());
		original.setNombre(nuevo.getNombre());
		original.setApellido(nuevo.getApellido());
		original.setCalle(nuevo.getCalle());
		original.setNumero(nuevo.getNumero());
		original.setLocalidad(nuevo.getLocalidad());
		original.setProvincia(provincia);
		original.setEmail(nuevo.getEmail());
		original.setTelefono(nuevo.getTelefono());
		
	}
	private boolean chequearDuplicado( TipoDNI tipo,String dni) {
		List<Propietario> lista=propietariodao.getPropietario(tipo,dni,null,null);
		if(lista.isEmpty()) return false;
		else return true;
		}
}
