package services;

import java.sql.Date;
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
import application.dao.ReservaDAO;
import application.dao.VendedorDAO;
import application.dao.VentaDAO;
import dto.ReservaDTO;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class ReservaServices {
private static ReservaServices instance;
	
	private static ReservaDAO reservadao;
	private static InmuebleDAO inmuebledao;
	private static VendedorDAO vendedordao;
	private static ClienteDAO clientedao;
	
	public static ReservaServices getInstance() {
		if(instance==null) {
			instance = new ReservaServices();
			reservadao = ReservaDAO.getReservaDAO();
			inmuebledao = InmuebleDAO.getInmuebleDAO();
		}
		return instance;
	}
	
	public int createReserva(ReservaDTO reserva) {
		
		Inmueble inmueble= inmuebledao.getInmuebleById(reserva.getInmueble());
		if(inmueble==null) {return -2;}//no existe inmueble
		
		Cliente cliente = clientedao.getClienteById(reserva.getCliente());
		if(cliente==null) {return -3;}//no existe cliente
		
		Vendedor vendedor= vendedordao.getVendedorById(reserva.getVendedor());
		if(vendedor==null) {return -4;}//no existe el vendedor
		
		List<Reserva> reservasInmueble = reservadao.getReservasByInmueble(inmueble);
		if(reservasInmueble !=null) {
			List<Reserva> reservasValidas = reservasInmueble.stream().filter(Reserva::esReservaValida).collect(Collectors.toList());
			if(reservasValidas != null) {
				return reservasValidas.get(0).getCliente().getId(); // existe otra reserva vigente en este momento y te digo de que cliente es
			}
		}
		reservadao.createReserva(toReserva(reserva,inmueble,cliente,vendedor));
		inmueble.setEstado(Estado.Reservado);
		inmuebledao.updateInmueble(inmueble);
		return 0;
		//AGREGAR METODO MAIL.
		
	}
	
	
	public List<Reserva> getAllReservas(){
		return reservadao.getAllReservas();
	}
	public List<Reserva> getReservaByCliente(Cliente c){
		return reservadao.getReservasByCliente(c);
	}
	public List<Reserva> getReservaByInmueble(Inmueble i){
		return reservadao.getReservasByInmueble(i);
	}
	private Reserva toReserva(ReservaDTO entrada,Inmueble inmueble,Cliente cliente, Vendedor vendedor ) {
		return new Reserva(inmueble,cliente,vendedor,entrada.getImporteReserva(),entrada.getTiempoVigencia(),entrada.getFecha());
	}
		
	
	
}
