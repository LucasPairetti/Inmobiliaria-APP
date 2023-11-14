package services;

import java.util.List;

import Controllers.Validation;
import application.clases.Inmueble;
import application.clases.Localidad;
import application.clases.Propietario;
import application.clases.Provincia;
import application.dao.InmuebleDAO;
import application.dao.PropietarioDAO;

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
	
	public void createInmuelbe(Inmueble inmueble) {
		Propietario p = propietariodao.getPropietarioById(inmueble.getPropietario().getId());
		if(p != null) {
		inmuebledao.createInmueble(inmueble);
		}
		else {
			System.out.print("error al crear, el propietario no existe");
		}
	}
	
	public void deleteInmueble(Inmueble inmueble) {
		inmuebledao.deleteInmueble(inmueble);
	}
	
	public void updateInmueble(Inmueble inmueble) {
		Inmueble og = inmuebledao.getInmuebleById(inmueble.getId());
		chequearModificaciones(og,inmueble); //si se modifico algo que no se debia se vuelve al original
		inmuebledao.updateInmueble(inmueble);
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
}
