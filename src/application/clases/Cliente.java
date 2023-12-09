package application.clases;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="Cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="idCliente")
	int id;
	@Enumerated(EnumType.STRING)
	@Column(name="tipoDNI", nullable=false)
	TipoDNI tipodni;
	@Column(name="dni", nullable=false, unique=true)
	String dni;
	@Column(name="nombreCliente", nullable = false)
	String nombre;
	@Column(name="apellidoCliente", nullable = false)
	String apellido;
	@Column(name="telefonoCliente", nullable = false)
	int telefono;
	@Column(name="email", nullable=false)
	String email;
	@Column(name="montoDisponible")
	double montoDisponible;
	@Column(name="tipoInmuebleBuscado")
	@Enumerated(EnumType.STRING)
	TipoInmueble tipoInmuebleBuscado;
	@Column(name="localidadBuscada")
	String localidadBuscada;
	@Column(name="Barrio")
	String barrios;
	@Column(name="caracteristicasDeseadas")
	String caracteristicasDeseadas;
	
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Cliente(String nombre, String apellido,String dni,TipoDNI tipoDni, int telefono,String email, double montoDisponible,
			TipoInmueble tipoInmuebleBuscado, String localidadBuscada, String barrios,
			String caracteristicasDeseadas) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni=dni;
		this.tipodni=tipoDni;
		this.email=email;
		this.telefono = telefono;
		this.montoDisponible = montoDisponible;
		this.tipoInmuebleBuscado = tipoInmuebleBuscado;
		this.localidadBuscada = localidadBuscada;
		this.barrios = barrios;
		this.caracteristicasDeseadas = caracteristicasDeseadas;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
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
	public TipoInmueble getTipoInmuebleBuscado() {
		return tipoInmuebleBuscado;
	}
	public void setTipoInmuebleBuscado(TipoInmueble tipoInmuebleBuscado) {
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
	@Override
	public String toString() {
		return "Cliente: " + nombre + " " + apellido;
	}
	public boolean equals(Cliente c) {
		if(c.getId().equals(id)) {return true;}
		else {return false;}
	}
}
