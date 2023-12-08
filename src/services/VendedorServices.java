package services;

import java.util.List;
import java.util.stream.Collectors;

import application.clases.Provincia;
import application.clases.TipoDNI;
import application.clases.Vendedor;
import application.dao.VendedorDAO;
import dto.VendedorDTO;


public class VendedorServices {
	private static VendedorServices instance;
	private static VendedorDAO vendedordao;
	
	public static VendedorServices getInstance() {
		if(instance==null) {
			instance = new VendedorServices();
			vendedordao = VendedorDAO.getVendedorDAO(); 
		}
		return instance;
	}
	public int createVendedor(VendedorDTO entrada) {
		TipoDNI tipoDNI= TipoDNI.valueOf(entrada.getTipodni());
		if(chequearDuplicado( tipoDNI,entrada.getDni())) {return -1;}//si existe un duplicado retorna -1
		else {
			Vendedor vendedor = toVendedor(entrada);
			vendedordao.createVendedor(vendedor);
			return 1;}
		
	};
	public int deleteVendedor(int id) {
		Vendedor vendedor = vendedordao.getVendedorById(id);
		if(vendedor !=null) {
			vendedordao.deleteVendedor(vendedor);
			return 1;
		}else {return -1;}
	};
	public int updateVendedor(VendedorDTO entrada) {
		Vendedor og= vendedordao.getVendedorById(entrada.getId());
		if(og !=null) {
			Vendedor vendedor = toVendedor(entrada);
			vendedor.setDni(og.getDni());
			vendedor.setTipodni(og.getTipodni());
			vendedordao.updateVendedor(vendedor);
			return 1;
		}else {return -1;}
		
	};
	public List<VendedorDTO> listVendedores(){
		return vendedordao.getAllVendedor().stream()
	            .map(vendedor -> new VendedorDTO(vendedor))
	            .collect(Collectors.toList());
	}
	public VendedorDTO validarVendedor(String dni,String nombre, String apellido,String clave) {//si es null no esta validado, por ende no debe iniciar
		Vendedor vendedor = vendedordao.validarVendedor(dni,nombre,apellido,clave);
		if(vendedor==null) {return null;}
		else {
		return new VendedorDTO(vendedor);
		}
	}
	public VendedorDTO getVendedorById(int id) {
		return new VendedorDTO(vendedordao.getVendedorById(id));
	}
	private Vendedor toVendedor( VendedorDTO entrada) {
		TipoDNI tipoDni= TipoDNI.valueOf(entrada.getTipodni());
		Provincia provincia= Provincia.valueOf(entrada.getProvincia());
		return new Vendedor( entrada.getNombre(), entrada.getApellido(),tipoDni, entrada.getDni(),entrada.getCalle(),entrada.getNumero(),
				entrada.getLocalidad(), provincia,entrada.getTelefono(),entrada.getEmail(),entrada.getFechaNacimiento(),entrada.getSueldo(),entrada.getClave());
		
	}
	private boolean chequearDuplicado( TipoDNI tipo,String dni) {
		Vendedor vendedor=vendedordao.getVendedor(tipo,dni);
		if(vendedor==null){return false;}
		else { return true;}
		}
}
