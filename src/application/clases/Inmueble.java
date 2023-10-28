package application.clases;

import java.sql.Date;

public class Inmueble {
	public Inmueble() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Inmueble(String id, Propietario propietario, Date fechaCreacion, boolean estado, Provincia provincia,
			Localidad localidad, String calle, int numero, String pisodpto, String barrio, TipoInmueble tipoInmueble,
			double precioVenta, String orientacion, float frente, float fondo, int antiguedad, int dormitorios,
			int banios, boolean patio, boolean piscina, boolean aguaCorriente, boolean cloacas, boolean gasNatural,
			boolean aguaCaliente, boolean lavadero, boolean pavimento, int telefono, String observaciones) {
		super();
		this.id = id;
		this.propietario = propietario;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
		this.provincia = provincia;
		this.localidad = localidad;
		this.calle = calle;
		this.numero = numero;
		this.pisodpto = pisodpto;
		this.barrio = barrio;
		this.tipoInmueble = tipoInmueble;
		this.precioVenta = precioVenta;
		this.orientacion = orientacion;
		this.frente = frente;
		this.fondo = fondo;
		this.antiguedad = antiguedad;
		this.dormitorios = dormitorios;
		this.banios = banios;
		this.patio = patio;
		this.piscina = piscina;
		this.aguaCorriente = aguaCorriente;
		this.cloacas = cloacas;
		this.gasNatural = gasNatural;
		this.aguaCaliente = aguaCaliente;
		this.lavadero = lavadero;
		this.pavimento = pavimento;
		this.telefono = telefono;
		this.observaciones = observaciones;
	}
	String id;
	Propietario propietario;
	Date fechaCreacion;
	boolean estado; //¿diponible - vendido?
	Provincia provincia;
	Localidad localidad;
	String calle;
	int numero;
	String pisodpto;
	String barrio;
	TipoInmueble tipoInmueble;
	double precioVenta;
	String orientacion;
	float frente;
	float fondo;
	int antiguedad;
	int dormitorios;
	int banios;
	boolean patio;
	boolean piscina;
	boolean aguaCorriente;
	boolean cloacas;
	boolean gasNatural;
	boolean aguaCaliente;
	boolean lavadero;
	boolean pavimento;
	
	int telefono;
	String observaciones;
	//foto?
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Propietario getPropietario() {
		return propietario;
	}
	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
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
	public String getPisodpto() {
		return pisodpto;
	}
	public void setPisodpto(String pisodpto) {
		this.pisodpto = pisodpto;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public TipoInmueble getTipoInmueble() {
		return tipoInmueble;
	}
	public void setTipoInmueble(TipoInmueble tipoInmueble) {
		this.tipoInmueble = tipoInmueble;
	}
	public double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	public String getOrientacion() {
		return orientacion;
	}
	public void setOrientacion(String orientacion) {
		this.orientacion = orientacion;
	}
	public float getFrente() {
		return frente;
	}
	public void setFrente(float frente) {
		this.frente = frente;
	}
	public float getFondo() {
		return fondo;
	}
	public void setFondo(float fondo) {
		this.fondo = fondo;
	}
	public int getAntiguedad() {
		return antiguedad;
	}
	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}
	public int getDormitorios() {
		return dormitorios;
	}
	public void setDormitorios(int dormitorios) {
		this.dormitorios = dormitorios;
	}
	public int getBanios() {
		return banios;
	}
	public void setBanios(int banios) {
		this.banios = banios;
	}
	public boolean isPatio() {
		return patio;
	}
	public void setPatio(boolean patio) {
		this.patio = patio;
	}
	public boolean isPiscina() {
		return piscina;
	}
	public void setPiscina(boolean piscina) {
		this.piscina = piscina;
	}
	public boolean isAguaCorriente() {
		return aguaCorriente;
	}
	public void setAguaCorriente(boolean aguaCorriente) {
		this.aguaCorriente = aguaCorriente;
	}
	public boolean isCloacas() {
		return cloacas;
	}
	public void setCloacas(boolean cloacas) {
		this.cloacas = cloacas;
	}
	public boolean isGasNatural() {
		return gasNatural;
	}
	public void setGasNatural(boolean gasNatural) {
		this.gasNatural = gasNatural;
	}
	public boolean isAguaCaliente() {
		return aguaCaliente;
	}
	public void setAguaCaliente(boolean aguaCaliente) {
		this.aguaCaliente = aguaCaliente;
	}
	public boolean isLavadero() {
		return lavadero;
	}
	public void setLavadero(boolean lavadero) {
		this.lavadero = lavadero;
	}
	public boolean isPavimento() {
		return pavimento;
	}
	public void setPavimento(boolean pavimento) {
		this.pavimento = pavimento;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	@Override
	public String toString() {
		return calle + "al " + numero + pisodpto + ", " + localidad.toString();
	}
	public boolean equals(Inmueble v) {
		if(v.getId().equals(id)) {return true;}
		else {return false;}
	}
}
