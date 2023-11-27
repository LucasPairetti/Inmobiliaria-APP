package dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import application.clases.Inmueble;
import application.clases.Localidad;
import application.clases.Propietario;
import application.clases.Provincia;
import application.clases.TipoDNI;
import application.clases.TipoInmueble;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class InmuebleDTO {
	
	private int id;
	
	int idPropietario;
	
	String nombrePropietario;
	
	String apellidoPropietario;

	String tipodniPropietario;
	
	String dniPropietario;
	
	Date fechaCreacion;
	
	boolean estado; //¿diponible - vendido?
	
	String provincia;

	String localidad;
	
	String calle;
	
	int numero;
	
	String pisodpto;
	
	String barrio;
	
	String tipoInmueble;
	
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
	
	public InmuebleDTO (int id,int idPropietario, String nombrePropietario, String apellidoPropietario, String tipodniPropietario,
			String dniPropietario, Date fechaCreacion, boolean estado, String provincia, String localidad, String calle,
			int numero, String pisodpto, String barrio, String tipoInmueble,double precioVenta, String orientacion, float frente,
			float fondo, int antiguedad, int dormitorios, int banios, boolean patio, boolean piscina, boolean aguaCorriente,
			boolean cloacas, boolean gasNatural,boolean aguaCaliente, boolean lavadero, boolean pavimento, int telefono,
			String observaciones) {
		super();
		this.id=id;
		this.idPropietario=idPropietario;
		this.nombrePropietario = nombrePropietario;
		this.apellidoPropietario= apellidoPropietario;
		this.tipodniPropietario= tipodniPropietario;
		this.dniPropietario= dniPropietario;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
		this.provincia = provincia.replace(" ", "_");
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
	public InmuebleDTO (Propietario propietario, Inmueble inmueble) {
		super();
		this.id=inmueble.getId();
		this.idPropietario=inmueble.getPropietario().getId();
		this.nombrePropietario = inmueble.getPropietario().getNombre();
		this.apellidoPropietario= inmueble.getPropietario().getApellido();
		this.tipodniPropietario= inmueble.getPropietario().getTipodni().toString();
		this.dniPropietario= inmueble.getPropietario().getDni();
		this.fechaCreacion = inmueble.getFechaCreacion();
		this.estado = inmueble.isEstado();
		this.provincia = inmueble.getProvincia().toString().replace("_", " ");
		this.localidad = inmueble.getLocalidad();
		this.calle = inmueble.getCalle();
		this.numero = inmueble.getNumero();
		this.pisodpto = inmueble.getPisodpto();
		this.barrio = inmueble.getBarrio();
		this.tipoInmueble = inmueble.getTipoInmueble().toString();
		this.precioVenta = inmueble.getPrecioVenta();
		this.orientacion = inmueble.getOrientacion();
		this.frente = inmueble.getFrente();
		this.fondo = inmueble.getFondo();
		this.antiguedad = inmueble.getAntiguedad();
		this.dormitorios = inmueble.getDormitorios();
		this.banios = inmueble.getBanios();
		this.patio = inmueble.isPatio();
		this.piscina = inmueble.isPiscina();
		this.aguaCorriente = inmueble.isAguaCorriente();
		this.cloacas = inmueble.isCloacas();
		this.gasNatural = inmueble.isGasNatural();
		this.aguaCaliente = inmueble.isAguaCaliente();
		this.lavadero = inmueble.isLavadero();
		this.pavimento = inmueble.isPavimento();
		this.telefono = inmueble.getTelefono();
		this.observaciones = inmueble.getObservaciones();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPropietario() {
		return idPropietario;
	}

	public void setIdPropietario(int idPropietario) {
		this.idPropietario = idPropietario;
	}

	public String getNombrePropietario() {
		return nombrePropietario;
	}

	public void setNombrePropietario(String nombrePropietario) {
		this.nombrePropietario = nombrePropietario;
	}

	public String getApellidoPropietario() {
		return apellidoPropietario;
	}

	public void setApellidoPropietario(String apellidoPropietario) {
		this.apellidoPropietario = apellidoPropietario;
	}

	public String getTipodniPropietario() {
		return tipodniPropietario;
	}

	public void setTipodniPropietario(String tipodniPropietario) {
		this.tipodniPropietario = tipodniPropietario;
	}

	public String getDniPropietario() {
		return dniPropietario;
	}

	public void setDniPropietario(String dniPropietario) {
		this.dniPropietario = dniPropietario;
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

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
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

	public String getTipoInmueble() {
		return tipoInmueble;
	}

	public void setTipoInmueble(String tipoInmueble) {
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
	
}
