package services;

import java.util.List;

import Controllers.Validation;
import application.clases.Inmueble;
import application.clases.Localidad;
import application.clases.Provincia;
import application.dao.InmuebleDAO;

public class InmuebleServices {
	InmuebleDAO inmuebledao;
	Validation validation;
	
	public InmuebleServices() {
		super();
		inmuebledao = InmuebleDAO.getVentaDAO(); 
		validation = Validation.getInstance();
	}
	
	public void createInmuelbe(Inmueble inmueble) {
		inmuebledao.createInmueble(inmueble);
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
	
	public List<Inmueble> resultadoBusqueda(String p, String l, String b, List<String> tipos, int cantdorm,
			double min, double max){
		return null;
	}
	
	public Inmueble setInmueble() {
		return null;
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
