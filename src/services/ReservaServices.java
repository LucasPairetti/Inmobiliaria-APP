package services;

import java.util.List;

import Controllers.Validacion;
import application.clases.Cliente;
import application.clases.Inmueble;
import application.clases.Reserva;
import application.clases.Vendedor;
import application.clases.Venta;
import application.dao.ClienteDAO;
import application.dao.InmuebleDAO;
import application.dao.ReservaDAO;
import application.dao.VendedorDAO;
import application.dao.VentaDAO;
import dto.ReservaDTO;

public class ReservaServices {
private static ReservaServices instance;
	
	private static ReservaDAO reservadao;
	private static InmuebleDAO inmuebledao;
	private static VendedorDAO vendedordao;
	private static ClienteDAO clientedao;
	private static Validacion validation;
	
	public static ReservaServices getInstance() {
		if(instance==null) {
			instance = new ReservaServices();
			reservadao = ReservaDAO.getReservaDAO();
			inmuebledao = InmuebleDAO.getInmuebleDAO();
			validation = Validacion.getInstance();
		}
		return instance;
	}int id;
	
	int inmueble;
	
	int cliente;
	
	int vendedor;
	
	public int createReserva(ReservaDTO reserva) {
		
		Inmueble inmueble= inmuebledao.getInmuebleById(reserva.getInmueble());
		if(inmueble==null) {return -2;}//no existe inmueble
		Cliente cliente = clientedao.getClienteById(reserva.getCliente());
		if(cliente==null) {return -3;}//no existe cliente
		Vendedor vendedor= vendedordao.getVendedorById(reserva.getVendedor());
		if(vendedor==null) {return -4;}//no existe el vendedor
		List<Reserva> reservasInmueble = reservadao.getReservasByInmueble(inmueble);
		if(reservasInmueble !=null) {
			
		}
		
		}
		else {
			//no existe el vendedor o el cliente o el inmueble
		}
	}
	public void updateVenta(Venta v) { 
		ventadao.updateVenta(v);
	}
	public void deleteVenta(Venta venta) {
		ventadao.deleteVenta(venta);
	}
	public Venta getVentaById(int id) {
		return ventadao.getVentaById(id);
	}
	
	public List<Venta> getAllVentas(){
		return ventadao.getAllVentas();
	}
	public List<Venta> getVentasByVendedor(Vendedor v){
		return ventadao.getVentasByVendedor(v);
	}
	
}
