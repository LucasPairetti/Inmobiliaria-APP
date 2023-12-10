package dto;

import java.sql.Date;

import application.clases.Reserva;
import application.clases.Venta;



public class VentaDTO {
	
	int id;
	
	int inmueble;
	
	int cliente;
	
	int vendedor;

	double importeReserva;


	Date fecha;
	
	public VentaDTO(int id,int inmueble,int cliente,int vendedor,double importe, Date fecha) {
		this.id=id;
		this.inmueble=inmueble;
		this.cliente=cliente;
		this.vendedor=vendedor;
		this.importeReserva=importe;
		this.fecha=fecha;
		
	}
	public VentaDTO(int inmueble,int cliente,int vendedor,double importe, Date fecha) {
		this.inmueble=inmueble;
		this.cliente=cliente;
		this.vendedor=vendedor;
		this.importeReserva=importe;
		this.fecha=fecha;
		
	}
	public VentaDTO(Venta venta) {
		this.id=venta.getId();
		this.inmueble=venta.getPropiedad().getId();
		this.cliente=venta.getCliente().getId();
		this.vendedor=venta.getVendedor().getId();
		this.importeReserva=venta.getImporteVenta();
		this.fecha=venta.getFecha();
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
