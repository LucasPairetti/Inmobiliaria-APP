package application.clases;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Propietario {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="idPropietario")
	int id;
	@Column(name="nombre", nullable=false)
	String nombre;
	@Column(name="apellido", nullable=false)
	String apellido;
	@Enumerated(EnumType.STRING)
	@Column(name="tipoDNI", nullable=false)
	TipoDNI tipodni;
	@Column(name="dni", nullable=false, unique=true)
	String dni;
	@Column(name="calle", nullable=false)
	String calle;
	@Column(name="numero", nullable=false)
	int numero;
	@Column(name="localidad", nullable=false)
	String localidad;
	@Enumerated(EnumType.STRING)
	@Column(name="provincia", nullable=false)
	Provincia provincia;
	@Column(name="telefono", nullable=false)
	int telefono;
	@Column(name="email", nullable=false)
	String email;
	
	public Propietario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Propietario(String nombre, String apellido, TipoDNI tipodni, String dni, String calle, int numero,
			String localidad, Provincia provincia, int telefono, String email) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipodni = tipodni;
		this.dni = dni;
		this.calle = calle;
		this.numero = numero;
		this.localidad = localidad;
		this.provincia = provincia;
		this.telefono = telefono;
		this.email = email;
	}
	
	public Integer getId() {
		return id;
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
	@Override
	public String toString() {
		return nombre + " " + apellido;
	}
	public boolean equals(Propietario p) {
		if(p.getId().equals(id)) {return true;}
		else {return false;}
	}
}
