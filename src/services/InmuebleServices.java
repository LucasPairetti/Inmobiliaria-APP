package services;

import java.util.List;
import java.util.stream.Collectors;

import Controllers.Validation;
import application.clases.Inmueble;
import application.clases.Localidad;
import application.clases.Propietario;
import application.clases.Provincia;
import application.clases.TipoInmueble;
import application.dao.InmuebleDAO;
import application.dao.PropietarioDAO;
import dto.InmuebleDTO;

import java.sql.Date;
import java.util.ArrayList;
public class InmuebleServices {
	
	private static InmuebleServices instance;
	private static InmuebleDAO inmuebledao;
	private static PropietarioDAO propietariodao;
	
	public static InmuebleServices getInstance() {
		if(instance==null) {
			instance= new InmuebleServices();
			inmuebledao = InmuebleDAO.getInmuebleDAO(); 
			propietariodao = PropietarioDAO.getPropietarioDAO();
		}
		return instance;
	}
	
	public int createInmueble(InmuebleDTO entrada) {
		
		Propietario p = propietariodao.getPropietarioById(entrada.getIdPropietario());
		if(p == null) {
			return -2;
		}
		Inmueble inmueble= toInmueble(p, entrada);
		if(chequearDuplicado(inmueble.getProvincia(), inmueble.getLocalidad(), inmueble.getCalle(), inmueble.getNumero(),
				inmueble.getPisodpto(), inmueble.getTipoInmueble())) {
			return -3;
		}
		
		inmuebledao.createInmueble(inmueble);
		return 1;
	}
	
	public int deleteInmueble(int i) {
		Inmueble inmueble = inmuebledao.getInmuebleById(i);
		if(inmueble!=null) {
		inmuebledao.deleteInmueble(inmueble);
		return 1;
		}else {return -1;}
	}
	
	public int updateInmueble(InmuebleDTO entrada) {
		
		Inmueble og = inmuebledao.getInmuebleById(entrada.getId());
		if(og != null) {
		Inmueble inmueble = toInmueble(og.getPropietario(),entrada);
		chequearModificaciones(og,inmueble); //si se modifico algo que no se debia se vuelve al original
		inmuebledao.updateInmueble(inmueble);
		return 1;}
		else {return -1;}
	}
	
	public List<InmuebleDTO> listInmuebles() {
		return inmuebledao.getAllInmuebles().stream()
	            .map(inmueble -> new InmuebleDTO(inmueble.getPropietario(), inmueble))
	            .collect(Collectors.toList());
	} 
	
	public InmuebleDTO getById(int id) {
		Inmueble i = inmuebledao.getInmuebleById(id);
		if(i!= null) {
			return new InmuebleDTO(i.getPropietario(), i);
		}
		else {
			return null;
		}
	}
	
	public List<InmuebleDTO> getInmueble(String p, String l, String b, String t, int cantdorm,
			float min, float max){
		Provincia provincia = Provincia.valueOf(p.replace(" ", "_"));
		Localidad localidad = Localidad.valueOf(l);
		TipoInmueble tipoInmueble = TipoInmueble.valueOf(t);
		return inmuebledao.getInmueble(provincia,localidad,b,tipoInmueble,cantdorm,min,max).stream()
	            .map(inmueble -> new InmuebleDTO(inmueble.getPropietario(), inmueble))
	            .collect(Collectors.toList());
	}

	
	private void chequearModificaciones(Inmueble og, Inmueble i) {
		i.setPropietario(og.getPropietario());
		i.setFechaCreacion(og.getFechaCreacion());
		i.setProvincia(og.getProvincia());
		i.setLocalidad(og.getLocalidad());
		i.setCalle(og.getCalle());
		i.setNumero(og.getNumero());
		i.setPisodpto(og.getPisodpto());
		i.setBarrio(og.getBarrio());
	}
	private boolean chequearDuplicado( Provincia provincia, Localidad localidad,String calle, int numero,
			String pisodpto, TipoInmueble tipoInmueble) {
		List<Inmueble> lista=inmuebledao.getInmueble(provincia,localidad,calle,numero,pisodpto,tipoInmueble);
		if(lista==null||
			lista.stream().filter(i -> TipoInmueble.D.equals(i.getTipoInmueble())).noneMatch(i->pisodpto.equals(i.getPisodpto())))
		{return false;}
		else { return true;}
		}
	private Inmueble toInmueble(Propietario propietario, InmuebleDTO entrada) {// cubrir Excepciones puede hacerse desde la UI
		
		Provincia provincia = Provincia.valueOf(entrada.getProvincia().replace(" ", "_"));
		Localidad localidad = Localidad.valueOf(entrada.getLocalidad());
		TipoInmueble tipoInmueble = TipoInmueble.valueOf(entrada.getTipoInmueble());
		Inmueble inmueble = new Inmueble(propietario, entrada.getFechaCreacion(),  entrada.isEstado(), provincia, localidad,
				 entrada.getCalle(),  entrada.getNumero(),entrada.getPisodpto(),  entrada.getBarrio(), tipoInmueble,
				 entrada.getPrecioVenta(),   entrada.getOrientacion(),entrada.getFrente(),  entrada.getFondo(),
				 entrada.getAntiguedad(),  entrada.getDormitorios(), entrada.getBanios(),  entrada.isPatio(),
				 entrada.isPiscina(), entrada.isAguaCorriente(), entrada.isCloacas(),entrada.isGasNatural(),
				 entrada.isAguaCaliente(),  entrada.isLavadero(),  entrada.isPavimento(),   entrada.getTelefono(),
				 entrada.getObservaciones());
		return inmueble;
		
	}
	
}