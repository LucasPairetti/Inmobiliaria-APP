package Controllers;

public class Holder {
	 private static  Holder instance;
	 
	 private int idInmueble;
	 private int idCliente;
	 private int idVendedor;
	 private int idPropietario;
	 private int idReserva;
	 private int idVenta;
	 private int pantalla;
	 private int idUsuario; //vendedor que logeó
	 
	 
	public int getIdUsuario() {
		return idUsuario;
	}




	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}




	public int getPantalla() {
		return pantalla;
	}




	public void setPantalla(int pantalla) {
		this.pantalla = pantalla;
	}




	public static Holder getInstance() {
		if(instance ==null) {
			instance = new Holder();
		}
		return instance;
		
		
	}




	public int getIdInmueble() {
		return idInmueble;
	}




	public void setIdInmueble(int idInmueble) {
		this.idInmueble = idInmueble;
	}




	public int getIdCliente() {
		return idCliente;
	}




	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}




	public int getIdVendedor() {
		return idVendedor;
	}




	public void setIdVendedor(int idVendedor) {
		this.idVendedor = idVendedor;
	}




	public int getIdPropietario() {
		return idPropietario;
	}




	public void setIdPropietario(int idPropietario) {
		this.idPropietario = idPropietario;
	}




	public int getIdReserva() {
		return idReserva;
	}




	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}




	public int getIdVenta() {
		return idVenta;
	}




	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
}
