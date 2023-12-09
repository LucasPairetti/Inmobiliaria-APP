package application.clases;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="Vendedor")
public class Vendedor {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="idVendedor")
	private int id;
	@Column(name="nombre", nullable=false)
	private String nombre;
	@Column(name="apellido", nullable=false)
	private String apellido;
	@Enumerated(EnumType.STRING)
	@Column(name="tipoDNI", nullable=false)
	private TipoDNI tipodni;
	@Column(name="DNI", nullable=false, unique=true)
	private String dni;
	@Column(name="calle", nullable=false)
	String calle;
	@Column(name="numeroCalle", nullable=false)
	private int numero;
	@Column(name="localidad", nullable=false)
	private String localidad;
	@Enumerated(EnumType.STRING)
	@Column(name="provincia", nullable=false)
	private Provincia provincia;
	@Column(name="telefono", nullable=false)
	private int telefono;
	@Column(name="email", nullable=false)
	String email;
	@Column(name="fechaNacimiento", nullable=false)
	private Date fechaNacimiento;
	@Column(name="sueldo", nullable=false)
	private double sueldo;
	@Column(name="clave", nullable=false)
	private String clave;
	
	public Vendedor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Vendedor(String nombre, String apellido, TipoDNI tipodni, String dni, String calle, int numero,
			String localidad, Provincia provincia, int telefono, String email, Date fechaNacimiento, double sueldo,
			String clave) {
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
		this.fechaNacimiento = fechaNacimiento;
		this.sueldo = sueldo;
		this.clave = clave;
	}
	

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
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
	public String getId() {
		// TODO Auto-generated method stub
		return this.dni;
	}
}
