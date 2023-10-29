package application.clases;

public class Cliente {
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cliente(String id, String nombre, String apellido, int telefono, double montoDisponible,
			TipoInmueble tipoInmuebleBuscado, Localidad localidadBuscada, String barrios,
			String caracteristicasDeseadas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.montoDisponible = montoDisponible;
		this.tipoInmuebleBuscado = tipoInmuebleBuscado;
		this.localidadBuscada = localidadBuscada;
		this.barrios = barrios;
		this.caracteristicasDeseadas = caracteristicasDeseadas;
	}
	String id;
	String nombre;
	String apellido;
	int telefono;
	double montoDisponible;
	TipoInmueble tipoInmuebleBuscado;
	Localidad localidadBuscada;
	String barrios;
	String caracteristicasDeseadas;
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
	public Localidad getLocalidadBuscada() {
		return localidadBuscada;
	}
	public void setLocalidadBuscada(Localidad localidadBuscada) {
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