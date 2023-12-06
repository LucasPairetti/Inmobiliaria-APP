package services;

import java.util.List;
import java.util.stream.Collectors;

import Controllers.Validacion;
import application.clases.Inmueble;
import application.clases.Localidad;
import application.clases.Orientacion;
import application.clases.Propietario;
import application.clases.Provincia;
import application.clases.TipoInmueble;
import application.dao.InmuebleDAO;
import application.dao.PropietarioDAO;
import dto.InmuebleDTO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumSet;
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
	
	public List<InmuebleDTO> getInmueble(String p, String l, String b, List<String> tipo, int cantdorm,
			float min, float max){
		Provincia provincia = Provincia.valueOf(p.replace(" ", "_"));
		TipoInmueble tipoInmueble = TipoInmueble.valueOf(tipo); //aca hay que arreglar
		return inmuebledao.getInmueble(provincia,l,b,tipoInmueble,cantdorm,min,max).stream()
	            .map(inmueble -> new InmuebleDTO(inmueble.getPropietario(), inmueble))
	            .collect(Collectors.toList());
	}
	public String getNombrePropietario(int id) {
		Inmueble i = inmuebledao.getInmuebleById(id);
		if(i!= null) {
			return i.getPropietario().getNombre();
		}
		else {
			return null;
		}
	}
	public double getMaxPrecio() {
		return inmuebledao.getAllInmuebles().stream().max(Comparator.comparingDouble(Inmueble::getPrecioVenta))
         .map(Inmueble::getPrecioVenta)
         .orElse(0.0);

	}
	public double getMinPrecio() {
		return inmuebledao.getAllInmuebles().stream().min(Comparator.comparingDouble(Inmueble::getPrecioVenta))
		         .map(Inmueble::getPrecioVenta)
		         .orElse(0.0);
	}
	public int getMaxDormitorios() {
		return inmuebledao.getAllInmuebles().stream().max(Comparator.comparingInt(Inmueble::getDormitorios))
		         .map(Inmueble::getDormitorios)
		         .orElse(0);
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
	private boolean chequearDuplicado( Provincia provincia, String localidad,String calle, int numero,
			String pisodpto, TipoInmueble tipoInmueble) {
		List<Inmueble> lista=inmuebledao.getInmueble(provincia,localidad,calle,numero,pisodpto,tipoInmueble);
		if(lista==null||
			lista.stream().filter(i -> TipoInmueble.D.equals(i.getTipoInmueble())).noneMatch(i->pisodpto.equals(i.getPisodpto())))
		{return false;}
		else { return true;}
		}
	private Inmueble toInmueble(Propietario propietario, InmuebleDTO entrada) {// cubrir Excepciones puede hacerse desde la UI
		
		Provincia provincia = Provincia.valueOf(entrada.getProvincia());
		TipoInmueble tipoInmueble = TipoInmueble.valueOf(entrada.getTipoInmueble());
		Orientacion orientacion = Orientacion.valueOf(entrada.getOrientacion());
		Inmueble inmueble = new Inmueble(propietario, entrada.getFechaCreacion(),  entrada.isEstado(), provincia, entrada.getLocalidad(),
				 entrada.getCalle(),  entrada.getNumero(),entrada.getPisodpto(),  entrada.getBarrio(), tipoInmueble,
				 entrada.getPrecioVenta(),   orientacion,entrada.getFrente(),  entrada.getFondo(),
				 entrada.getAntiguedad(),  entrada.getDormitorios(), entrada.getBanios(),  entrada.isPatio(),
				 entrada.isPiscina(), entrada.isAguaCorriente(), entrada.isCloacas(),entrada.isGasNatural(),
				 entrada.isAguaCaliente(),  entrada.isLavadero(),  entrada.isPavimento(),   entrada.getTelefono(),
				 entrada.getObservaciones());
		return inmueble;
		
	}
	
}