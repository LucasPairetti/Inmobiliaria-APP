package application.clases;

public class Propietario {
	public Propietario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Propietario(String id, String nombre, String apellido, TipoDNI tipodni, String dni, String calle, int numero,
			Localidad localidad, Provincia provincia, int telefono, String email) {
		super();
		this.id = id;
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
	String id;
	String nombre;
	String apellido;
	TipoDNI tipodni;
	String dni;
	String calle;
	int numero;
	Localidad localidad;
	Provincia provincia;
	int telefono;
	String email;
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
	@Override
	public String toString() {
		return nombre + " " + apellido;
	}
	public boolean equals(Propietario p) {
		if(p.getId().equals(id)) {return true;}
		else {return false;}
	}
}
