package services;

import java.util.List;

import Controllers.Validacion;
import application.clases.Vendedor;
import application.clases.Venta;
import application.dao.ClienteDAO;
import application.dao.InmuebleDAO;
import application.dao.VendedorDAO;
import application.dao.VentaDAO;

public class VentaServices {
	private static VentaServices instance;
	
	private static VentaDAO ventadao;
	private static InmuebleDAO inmuebledao;
	private static VendedorDAO vendedordao;
	private static ClienteDAO clientedao;
	private static Validacion validation;
	
	public static VentaServices getInstance() {
		if(instance==null) {
			instance = new VentaServices();
			ventadao = VentaDAO.getVentaDAO();
			inmuebledao = InmuebleDAO.getInmuebleDAO();
			validation = Validacion.getInstance();
		}
		return instance;
	}
	
	public void createVenta(Venta venta) {
		//check todos existen
		if(inmuebledao.getInmuebleById(venta.getPropiedad().getId())!=null 
				/* && vendedordao.getVendedorById(venta.getVendedor().getId())!= null &&
				clientedao.getClienteById(venta.getCliente().getId()!= null)*/) {
			ventadao.createVenta(venta);
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
