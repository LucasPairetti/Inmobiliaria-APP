package application.clases;

import java.sql.Date;

public class Reserva {
	public Reserva(String id, String idPropiedad, String idCliente, String idVendedor, double importeReserva,
			float tiempoVigencia, Date fecha) {
		super();
		this.id = id;
		this.idPropiedad = idPropiedad;
		this.idCliente = idCliente;
		this.idVendedor = idVendedor;
		this.importeReserva = importeReserva;
		this.tiempoVigencia = tiempoVigencia;
		this.fecha = fecha;
	}
	public Reserva() {
		super();
		// TODO Auto-generated constructor stub
	}
	String id;
	String idPropiedad;
	String idCliente;
	String idVendedor;
	double importeReserva;
	float tiempoVigencia;
	Date fecha;
	public String getIdPropiedad() {
		return idPropiedad;
	}
	public void setIdPropiedad(String idPropiedad) {
		this.idPropiedad = idPropiedad;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getIdVendedor() {
		return idVendedor;
	}
	public void setIdVendedor(String idVendedor) {
		this.idVendedor = idVendedor;
	}
	public double getImporteReserva() {
		return importeReserva;
	}
	public void setImporteReserva(double importeReserva) {
		this.importeReserva = importeReserva;
	}
	public float getTiempoVigencia() {
		return tiempoVigencia;
	}
	public void setTiempoVigencia(float tiempoVigencia) {
		this.tiempoVigencia = tiempoVigencia;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean equals(Reserva v) {
		if(v.getId().equals(id)) {return true;}
		else {return false;}
	}
}
