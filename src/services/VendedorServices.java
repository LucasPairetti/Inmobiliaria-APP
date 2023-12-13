package services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		}else {return -1;}//no existia el vendedor con esa id
	};
	public int updateVendedor(VendedorDTO entrada) {
		Vendedor og= vendedordao.getVendedorById(entrada.getId());
		if(og !=null) {
			actualizarVendedor(og,entrada);
			vendedordao.updateVendedor(og);
			return 1;
		}else {return -1;}//no existia el vendedor con esa id
		
	};
	public List<VendedorDTO> listVendedores() {
	    return Optional.ofNullable(vendedordao.getAllVendedor())
	            .map(List::stream)
	            .orElseGet(Stream::empty)
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
	public VendedorDTO validarVendedor(String email,String clave) {//si es null no esta validado, por ende no debe iniciar
		return Optional.ofNullable(vendedordao.getAllVendedor())
	            .map(List::stream)
	            .orElseGet(Stream::empty)
	            .filter(vendedor -> vendedor.getEmail().equals(email) && vendedor.getClave().equals(clave))
	            .map(vendedor -> new VendedorDTO(vendedor))
	            .findFirst()
	            .orElse(null);
	}
	public VendedorDTO getVendedorById(int id) {
	    Vendedor vendedor = vendedordao.getVendedorById(id);
	    return vendedor != null ? new VendedorDTO(vendedor) : null;
	}
	private Vendedor toVendedor( VendedorDTO entrada) {
		TipoDNI tipoDni= TipoDNI.valueOf(entrada.getTipodni());
		Provincia provincia= Provincia.valueOf(entrada.getProvincia());
		return new Vendedor( entrada.getNombre(), entrada.getApellido(),tipoDni, entrada.getDni(),entrada.getCalle(),entrada.getNumero(),
				entrada.getLocalidad(), provincia,entrada.getTelefono(),entrada.getEmail(),entrada.getFechaNacimiento(),entrada.getSueldo(),entrada.getClave());
		
	}
	private void actualizarVendedor(Vendedor vendedorOriginal, VendedorDTO vendedorNuevo) {
		
		Provincia provincia =Provincia.valueOf(vendedorNuevo.getProvincia());
		vendedorOriginal.setNombre(vendedorNuevo.getNombre());
		vendedorOriginal.setApellido(vendedorNuevo.getApellido());
		vendedorOriginal.setCalle(vendedorNuevo.getCalle());
		vendedorOriginal.setNumero(vendedorNuevo.getNumero());
		vendedorOriginal.setLocalidad(vendedorNuevo.getLocalidad());
		vendedorOriginal.setProvincia(provincia);
		vendedorOriginal.setEmail(vendedorNuevo.getEmail());
		vendedorOriginal.setFechaNacimiento(vendedorNuevo.getFechaNacimiento());
		vendedorOriginal.setSueldo(vendedorNuevo.getSueldo());
		vendedorOriginal.setClave(vendedorNuevo.getClave());
	}
	private boolean chequearDuplicado( TipoDNI tipo,String dni) {
		Vendedor vendedor=vendedordao.getVendedor(tipo,dni);
		if(vendedor==null){return false;}
		else { return true;}
		}
}
