package dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import application.clases.Provincia;
import application.clases.TipoDNI;
import application.clases.Vendedor;

public class VendedorDTO {
	
	
	private int id;
	
	private String nombre;
	
	private String apellido;
	
	private String tipodni;
	
	private String dni;
	
	private String calle;
	
	private int numero;
	
	private String localidad;

	private String provincia;
	
	private int telefono;
	
	private String email;
	
	private Date fechaNacimiento;
	
	private double sueldo;
	
	private String clave;
	
	public VendedorDTO(String nombre, String apellido, String tipodni, String dni, String calle, int numero,
			String localidad, String provincia, int telefono, String email, Date fechaNacimiento, double sueldo,
			String clave) {
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
		this.fechaNacimiento = fechaNacimiento;
		this.sueldo = sueldo;
		this.clave = clave;
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
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public double getSueldo() {
		return sueldo;
	}
	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public VendedorDTO(Vendedor vendedor) {
		this.nombre = vendedor.getNombre();
		this.apellido = vendedor.getApellido();
		this.tipodni = vendedor.getTipodni().toString().replace("_"," ");
		this.dni = vendedor.getDni();
		this.calle = vendedor.getCalle();
		this.numero = vendedor.getNumero();
		this.localidad = vendedor.getLocalidad();
		this.provincia = vendedor.getProvincia().toString().replace("_"," ");
		this.telefono = vendedor.getTelefono();
		this.email = vendedor.getEmail();
		this.fechaNacimiento = vendedor.getFechaNacimiento();
		this.sueldo = vendedor.getSueldo();
		this.clave = vendedor.getClave();
	}

}
