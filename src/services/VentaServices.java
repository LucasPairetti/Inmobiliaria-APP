package services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Controllers.Validacion;
import application.clases.Cliente;
import application.clases.Estado;
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
import dto.VentaDTO;

public class VentaServices {
	private static VentaServices instance;
	
	private static VentaDAO ventadao;
	private static InmuebleDAO inmuebledao;
	private static VendedorDAO vendedordao;
	private static ClienteDAO clientedao;
	private static ReservaDAO reservadao;

	
	public static VentaServices getInstance() {
		if(instance==null) {
			instance = new VentaServices();
			ventadao = VentaDAO.getVentaDAO();
			inmuebledao= InmuebleDAO.getInmuebleDAO();
			reservadao=ReservaDAO.getReservaDAO();
		}
		return instance;
	}
	
	public int createVenta(VentaDTO venta) {
		Inmueble inmueble= inmuebledao.getInmuebleById(venta.getInmueble());
		if(inmueble==null) {return -2;}//no existe inmueble
		
		Cliente cliente = clientedao.getClienteById(venta.getCliente());
		if(cliente==null) {return -3;}//no existe cliente
		
		List<Reserva> reservas =reservadao.getReservasByInmueble(inmueble);
		double monto =0.0;
		if(reservas!= null) {
			List<Reserva> reservasValidas = reservas.stream().filter(Reserva::esReservaValida).collect(Collectors.toList());
			if(!reservasValidas.isEmpty()) {
				if(reservasValidas.get(0).getCliente().getId() != cliente.getId()) {
				return reservasValidas.get(0).getCliente().getId();}// existe una reserva vigente en este momento y te digo de que cliente es
				else{
					monto=reservasValidas.get(0).getImporteReserva();
				}
			}
			
		}
		if(inmueble.getPrecioVenta()<(monto+ venta.getImporteReserva())) {return -1;}//no es suficiente la suma de la reserva y el importe ingresado.
		Vendedor vendedor= vendedordao.getVendedorById(venta.getVendedor());
		if(vendedor==null) {return -4;}//no existe el vendedor
		
		ventadao.createVenta(toVenta(venta,inmueble,cliente,vendedor));
		inmueble.setEstado(Estado.Vendido);
		inmuebledao.updateInmueble(inmueble);
		return 0;//0 para evita chocar con el id 1
		//AGREGAR METODO IMPRIMIR
	}
	public VentaDTO getVentaById(int id) {
		Venta venta = ventadao.getVentaById(id);
		if(venta==null) {return null;}
		else {return new VentaDTO(venta);}
		
		
	}
	public List<VentaDTO> getAllVentas(){
		return Optional.ofNullable( ventadao.getAllVentas())
	            .map(List::stream)
	            .orElseGet(Stream::empty)
	            .map(venta -> new VentaDTO(venta))
	            .collect(Collectors.toList());
	}
	public List<VentaDTO> getVentasByVendedor(Vendedor v){
		return Optional.ofNullable( ventadao.getVentasByVendedor(v))
	            .map(List::stream)
	            .orElseGet(Stream::empty)
	            .map(venta -> new VentaDTO(venta))
	            .collect(Collectors.toList());
	}
	private Venta toVenta(VentaDTO entrada,Inmueble inmueble,Cliente cliente, Vendedor vendedor ) {
		return new Venta(inmueble,cliente,vendedor,entrada.getImporteReserva(),entrada.getFecha());
	}
}
