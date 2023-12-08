package application.clases;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

public class Inmueble {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="idInmueble")
	int id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idPropietario", nullable = false, referencedColumnName="idPropietario", 
				foreignKey=@ForeignKey(name="FK_Inmueble_Propietario", value=ConstraintMode.CONSTRAINT))
	Propietario propietario;
	@Column(name="fechaCreacion", nullable=false)
	Date fechaCreacion;
	@Column(name="estado", nullable=false)
	boolean estado; //¿diponible - vendido?
	@Enumerated(EnumType.STRING)
	@Column(name="provincia", nullable=false)
	Provincia provincia;
	@Column(name="localidad", nullable=false)
	String localidad;
	@Column(name="calle", nullable=false)
	String calle;
	@Column(name="numeroCalle", nullable=false)
	int numero;
	@Column(name="pisoDpto", nullable=true)
	String pisodpto;
	@Column(name="barrio", nullable=false)
	String barrio;
	@Enumerated(EnumType.STRING)
	@Column(name="tipoInmueble", nullable=false)
	TipoInmueble tipoInmueble;
	@Column(name="precioVenta", nullable=false)
	double precioVenta;
	@Enumerated(EnumType.STRING)
	@Column(name="orientacion", nullable=true)
	Orientacion orientacion;
	@Column(name="frente",  nullable=false)
	float frente;
	@Column(name="fondo",  nullable=false)
	float fondo;
	@Column(name="antiguedad",  nullable=false)
	int antiguedad;
	@Column(name="dormitorios",  nullable=false)
	int dormitorios;
	@Column(name="banios",  nullable=false)
	int banios;
	@Column(name="superfice",  nullable=false)
	float superficie;
	@Column(name="garaje",  nullable=false)
	boolean garaje;
	@Column(name="pHorizontal",  nullable=false)
	boolean pHorizontal;
	@Column(name="patio",  nullable=false)
	boolean patio;
	@Column(name="piscina",  nullable=false)
	boolean piscina;
	@Column(name="aguaCorriente",  nullable=false)
	boolean aguaCorriente;
	@Column(name="cloacas",  nullable=false)
	boolean cloacas;
	@Column(name="gasNatural",  nullable=false)
	boolean gasNatural;
	@Column(name="aguaCaliente",  nullable=false)
	boolean aguaCaliente;
	@Column(name="lavadero",  nullable=false)
	boolean lavadero;
	@Column(name="pavimento",  nullable=false)
	boolean pavimento;
	@Column(name="telefono",  nullable=false)
	boolean telefono;
	@Column(name="observaciones",  nullable=true)
	String observaciones;
	
	//foto?
	public Integer getId() {
		return id;
	}
	
	public Inmueble() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Inmueble(Propietario propietario, Date fechaCreacion, boolean estado, Provincia provincia,
			String localidad, String calle, int numero, String pisodpto, String barrio, TipoInmueble tipoInmueble,
			double precioVenta, Orientacion orientacion,float superficie, float frente, float fondo, int antiguedad, int dormitorios,
			int banios,boolean garaje,boolean pHorizontal, boolean patio, boolean piscina, boolean aguaCorriente, boolean cloacas, boolean gasNatural,
			boolean aguaCaliente, boolean lavadero, boolean pavimento, boolean telefono, String observaciones) {
		super();
		
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
		this.superficie= superficie;
		this.garaje=garaje;
		this.pHorizontal=pHorizontal;
		this.observaciones = observaciones;
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
	public float getSuperficie() {
		return superficie;
	}

	public void setSuperficie(float superficie) {
		this.superficie = superficie;
	}

	public boolean isGaraje() {
		return garaje;
	}

	public void setGaraje(boolean garaje) {
		this.garaje = garaje;
	}

	public boolean ispHorizontal() {
		return pHorizontal;
	}

	public void setpHorizontal(boolean pHorizontal) {
		this.pHorizontal = pHorizontal;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTelefono(boolean telefono) {
		this.telefono = telefono;
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
	public Orientacion getOrientacion() {
		return orientacion;
	}
	public void setOrientacion(Orientacion orientacion) {
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
	
	public boolean isTelefono() {
		return telefono;
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
