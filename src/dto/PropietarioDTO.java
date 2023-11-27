package dto;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import application.clases.Localidad;
import application.clases.Propietario;
import application.clases.Provincia;
import application.clases.TipoDNI;

public class PropietarioDTO {
	int id;
	
	String nombre;

	String apellido;
	
	String tipodni;

	String dni;

	String calle;

	int numero;
	
	String localidad;
	
	String provincia;
	
	int telefono;
	
	String email;
	
	public PropietarioDTO(String nombre, String apellido, String tipodni, String dni, String calle, int numero,
			String localidad, String provincia, int telefono, String email) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipodni = tipodni.replace(" ", "_");
		this.dni = dni;
		this.calle = calle;
		this.numero = numero;
		this.localidad = localidad;
		this.provincia = provincia.replace(" ", "_");
		this.telefono = telefono;
		this.email = email;
	}
	public PropietarioDTO(Propietario propietario) {
		super();
		this.nombre = propietario.getNombre();
		this.apellido = propietario.getApellido();
		this.tipodni = propietario.getTipodni().toString().replace("_"," ");
		this.dni = propietario.getDni();
		this.calle = propietario.getCalle();
		this.numero = propietario.getNumero();
		this.localidad = propietario.getLocalidad();
		this.provincia = propietario.getProvincia().toString().replace("_", " ");
		this.telefono = propietario.getTelefono();
		this.email = propietario.getEmail();
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
	public String getTipodni() {
		return tipodni;
	}
	public void setTipodni(String tipodni) {
		this.tipodni = tipodni;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
