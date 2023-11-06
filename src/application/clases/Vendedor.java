package application.clases;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Vendedor {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="idVendedor")
	String id;
	@Column(name="nombre", nullable=false)
	String nombre;
	@Column(name="apellido", nullable=false)
	String apellido;
	@Column(name="tipoDNI", nullable=false)
	TipoDNI tipodni;
	@Column(name="calle", nullable=false)
	String calle;
	// Esto es dni?
	@Column(name="dni", nullable=false)
	int numero;
	@Column(name="localidad", nullable=false)
	Localidad localidad;
	@Column(name="provincia", nullable=false)
	Provincia provincia;
	@Column(name="telefono", nullable=false)
	int telefono;
	@Column(name="email", nullable=false)
	String email;
	@Column(name="fechaNacimiento", nullable=false)
	Date fechaNacimiento;
	@Column(name="sueldo", nullable=false)
	double sueldo;
	@Column(name="clave", nullable=false)
	String clave;
	
	public Vendedor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Vendedor(String id, String nombre, String apellido, TipoDNI tipodni, String calle, int numero,
			Localidad localidad, Provincia provincia, int telefono, String email, Date fechaNacimiento, double sueldo,
			String clave) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipodni = tipodni;
		this.calle = calle;
		this.numero = numero;
		this.localidad = localidad;
		this.provincia = provincia;
		this.telefono = telefono;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.sueldo = sueldo;
		this.clave = clave;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public TipoDNI getTipodni() {
		return tipodni;
	}
	public void setTipodni(TipoDNI tipodni) {
		this.tipodni = tipodni;
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
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
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
	@Override
	public String toString() {
		return nombre + " " + apellido;
	}
	public boolean equals(Vendedor v) {
		if(v.getId().equals(id)) {return true;}
		else {return false;}
	}
}
