package dto;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import application.clases.Cliente;
import application.clases.TipoInmueble;

public class ClienteDTO {
	int id;
	
	String nombre;
	
	String dni;
	
	String tipoDNI;
	
	String apellido;
	
	int telefono;

	double montoDisponible;
	
	String tipoInmuebleBuscado;
	
	String localidadBuscada;
	
	String barrios;
	
	String caracteristicasDeseadas;
	

	
	public ClienteDTO( String nombre, String apellido,String dni,String tipoDNI, int telefono, double montoDisponible,
			String tipoInmuebleBuscado, String localidadBuscada, String barrios,
			String caracteristicasDeseadas) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni=dni;
		this.tipoDNI=tipoDNI.replace(" ", "_");;
		this.telefono = telefono;
		this.montoDisponible = montoDisponible;
		this.tipoInmuebleBuscado = tipoInmuebleBuscado.replace(" ", "_");
		this.localidadBuscada = localidadBuscada;
		this.barrios = barrios;
		this.caracteristicasDeseadas = caracteristicasDeseadas;
	}
	public ClienteDTO(Cliente cliente) {
		this.nombre = cliente.getNombre();
		this.apellido = cliente.getApellido();
		this.dni=cliente.getDni();
		this.tipoDNI=cliente.getTipodni().toString().replace("_", " ");
		this.telefono = cliente.getTelefono();
		this.montoDisponible = cliente.getMontoDisponible();
		this.tipoInmuebleBuscado = cliente.getTipoInmuebleBuscado().toString().replace("_", " ");
		this.localidadBuscada = cliente.getLocalidadBuscada();
		this.barrios = cliente.getBarrios();
		this.caracteristicasDeseadas = cliente.getCaracteristicasDeseadas();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public double getMontoDisponible() {
		return montoDisponible;
	}
	public void setMontoDisponible(double montoDisponible) {
		this.montoDisponible = montoDisponible;
	}
	public String getTipoInmuebleBuscado() {
		return tipoInmuebleBuscado;
	}
	public void setTipoInmuebleBuscado(String tipoInmuebleBuscado) {
		this.tipoInmuebleBuscado = tipoInmuebleBuscado;
	}
	public String getLocalidadBuscada() {
		return localidadBuscada;
	}
	public void setLocalidadBuscada(String localidadBuscada) {
		this.localidadBuscada = localidadBuscada;
	}
	public String getBarrios() {
		return barrios;
	}
	public void setBarrios(String barrios) {
		this.barrios = barrios;
	}
	public String getCaracteristicasDeseadas() {
		return caracteristicasDeseadas;
	}
	public void setCaracteristicasDeseadas(String caracteristicasDeseadas) {
		this.caracteristicasDeseadas = caracteristicasDeseadas;
	}
}
