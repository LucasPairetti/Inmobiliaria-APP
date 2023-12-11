package dto;

import java.sql.Date;

import application.clases.Reserva;

public class ReservaDTO {
int id;
	
	int inmueble;
	
	int cliente;
	
	int vendedor;

	double importeReserva;
	float tiempoVigencia;

	Date fecha;
	
	public ReservaDTO(int id,int inmueble,int cliente,int vendedor,double importe, Date fecha,float tiempoVigencia) {
		this.id=id;
		this.inmueble=inmueble;
		this.cliente=cliente;
		this.vendedor=vendedor;
		this.importeReserva=importe;
		this.fecha=fecha;
		this.tiempoVigencia=tiempoVigencia;
		
	}
	public ReservaDTO(int inmueble,int cliente,int vendedor,double importe, Date fecha,float tiempoVigencia) {
		this.inmueble=inmueble;
		this.cliente=cliente;
		this.vendedor=vendedor;
		this.importeReserva=importe;
		this.fecha=fecha;
		this.tiempoVigencia=tiempoVigencia;
		
	}
	public ReservaDTO(Reserva reserva) {
		this.id=reserva.getId();
		this.inmueble=reserva.getInmueble().getId();
		this.cliente=reserva.getCliente().getId();
		this.vendedor=reserva.getVendedor().getId();
		this.importeReserva=reserva.getImporteReserva();
		this.fecha=reserva.getFecha();
		this.tiempoVigencia=reserva.getTiempoVigencia();
	}
	public float getTiempoVigencia() {
		return tiempoVigencia;
	}
	public void setTiempoVigencia(float tiempoVigencia) {
		this.tiempoVigencia = tiempoVigencia;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInmueble() {
		return inmueble;
	}
	public void setInmueble(int inmueble) {
		this.inmueble = inmueble;
	}
	public int getCliente() {
		return cliente;
	}
	public void setCliente(int cliente) {
		this.cliente = cliente;
	}
	public int getVendedor() {
		return vendedor;
	}
	public void setVendedor(int vendedor) {
		this.vendedor = vendedor;
	}
	public double getImporteReserva() {
		return importeReserva;
	}
	public void setImporteReserva(double importeReserva) {
		this.importeReserva = importeReserva;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
