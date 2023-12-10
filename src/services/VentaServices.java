package services;

import java.util.List;
import java.util.stream.Collectors;

import Controllers.Validacion;
import application.clases.Cliente;
import application.clases.Estado;
import application.clases.Inmueble;
import application.clases.Reserva;
import application.clases.Vendedor;
import application.clases.Venta;
import application.dao.ClienteDAO;
import application.dao.InmuebleDAO;
import application.dao.VendedorDAO;
import application.dao.VentaDAO;
import dto.ReservaDTO;
import dto.VentaDTO;

public class VentaServices {
	private static VentaServices instance;
	
	private static VentaDAO ventadao;
	private static InmuebleDAO inmuebledao;
	private static VendedorDAO vendedordao;
	private static ClienteDAO clientedao;

	
	public static VentaServices getInstance() {
		if(instance==null) {
			instance = new VentaServices();
			ventadao = VentaDAO.getVentaDAO();
			inmuebledao= InmuebleDAO.getInmuebleDAO();
		}
		return instance;
	}
	
	public int createVenta(VentaDTO venta) {
		Inmueble inmueble= inmuebledao.getInmuebleById(venta.getInmueble());
		if(inmueble==null) {return -2;}//no existe inmueble
		
		Cliente cliente = clientedao.getClienteById(venta.getCliente());
		if(cliente==null) {return -3;}//no existe cliente
		
		Vendedor vendedor= vendedordao.getVendedorById(venta.getVendedor());
		if(vendedor==null) {return -4;}//no existe el vendedor
		
		ventadao.createVenta(toVenta(venta,inmueble,cliente,vendedor));
		inmueble.setEstado(Estado.Vendido);
		inmuebledao.updateInmueble(inmueble);
		return 1;
		//AGREGAR METODO IMPRIMIR
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
	private Venta toVenta(VentaDTO entrada,Inmueble inmueble,Cliente cliente, Vendedor vendedor ) {
		return new Venta(inmueble,cliente,vendedor,entrada.getImporteReserva(),entrada.getFecha());
	}
}
