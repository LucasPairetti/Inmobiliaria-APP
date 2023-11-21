package services;

import java.util.List;

import Controllers.Validation;
import application.clases.Inmueble;
import application.clases.Localidad;
import application.clases.Propietario;
import application.clases.Provincia;
import application.clases.TipoInmueble;
import application.dao.InmuebleDAO;
import application.dao.PropietarioDAO;

import java.sql.Date;
import java.util.ArrayList;
public class InmuebleServices {
	private static InmuebleServices instance;
	private static InmuebleDAO inmuebledao;
	private static Validation validation;
	private static PropietarioDAO propietariodao;
	
	public static InmuebleServices getInstance() {
		if(instance==null) {
			instance= new InmuebleServices();
			inmuebledao = InmuebleDAO.getVentaDAO(); 
			propietariodao = PropietarioDAO.getPropietarioDAO();
			validation = Validation.getInstance();
		}
		return instance;
	}
	
	public int createInmueble(Propietario propietario, Date fechaCreacion, boolean estado, Provincia provincia,
			Localidad localidad, String calle, int numero, String pisodpto, String barrio, TipoInmueble tipoInmueble,
			double precioVenta, String orientacion, float frente, float fondo, int antiguedad, int dormitorios,
			int banios, boolean patio, boolean piscina, boolean aguaCorriente, boolean cloacas, boolean gasNatural,
			boolean aguaCaliente, boolean lavadero, boolean pavimento, int telefono, String observaciones) {
		
		Propietario p = propietariodao.getPropietarioById(propietario.getId());
		if(p == null) {
			return -2;
		}
		if(chequearDuplicado( provincia, localidad, calle, numero, pisodpto, tipoInmueble)) {
			return -3;
		}
		Inmueble inmueble=  new Inmueble(propietario, fechaCreacion, estado, provincia,
				localidad, calle, numero, pisodpto, barrio, tipoInmueble,
				precioVenta, orientacion, frente,  fondo,  antiguedad,  dormitorios,
				banios, patio, piscina, aguaCorriente, cloacas, gasNatural,
				aguaCaliente, lavadero,pavimento, telefono, observaciones);
		inmuebledao.createInmueble(inmueble);
		return 1;
	}
	
	public int deleteInmueble(int i) {
		Inmueble inmueble = getById(i);
		if(inmueble!=null) {
		inmuebledao.deleteInmueble(inmueble);
		return 1;
		}else {return -1;}
	}
	
	public int updateInmueble(Inmueble inmueble) {
		
		Inmueble og = inmuebledao.getInmuebleById(inmueble.getId());
		if(og!=null) {
		chequearModificaciones(og,inmueble); //si se modifico algo que no se debia se vuelve al original
		inmuebledao.updateInmueble(inmueble);
		return 1;}else {return -1;}
	}
	
	public List<Inmueble> listInmuebles() {
		return inmuebledao.getAllInmuebles();
	}
	
	public Inmueble getById(int id) {
		Inmueble i = inmuebledao.getInmuebleById(id);
		if(i!= null) {
			return i;
		}
		else {
			System.out.print("no existe inmueble con id: "+id);
			return null;
		}
	}
	
	public List<Inmueble> getInmueble(String p, String l, String b, List<String> tipos, int cantdorm,
			double min, double max){
		ArrayList<Object> criterios = new ArrayList<Object>();
		criterios.add(p);
		criterios.add(l);
		criterios.add(b);
		criterios.add(tipos);
		criterios.add(cantdorm);
		criterios.add(min);
		criterios.add(max);
		return inmuebledao.getInmueble(criterios);
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
		ArrayList<Object> criterios= new ArrayList<Object>;
		criterios.add(provincia);
		criterios.add(localidad);
		criterios.add(calle);
		criterios.add(numero);
		List<Inmueble> lista=inmuebledao.getInmueble(criterios);
		if(lista==null||
			lista.stream().filter(i -> TipoInmueble.D.equals(i.getTipoInmueble())).noneMatch(i->pisodpto.equals(i.getPisodpto())))
		{return false;}
		else { return true;}
		}
	
}

