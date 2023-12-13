package services;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.sql.Date;

import Controllers.Validacion;
import application.clases.Cliente;
import application.clases.Estado;
import application.clases.Inmueble;
import application.clases.Reserva;
import application.clases.TipoInmueble;
import application.clases.Vendedor;
import application.clases.Venta;
import application.dao.ClienteDAO;
import application.dao.InmuebleDAO;
import application.dao.ReservaDAO;
import application.dao.VendedorDAO;
import application.dao.VentaDAO;
import dto.ReservaDTO;
import dto.VentaDTO;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

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
		
		Venta ventafinal = toVenta(venta,inmueble,cliente,vendedor);
		ventadao.createVenta(ventafinal);
		inmueble.setEstado(Estado.Vendido);
		inmuebledao.updateInmueble(inmueble);
		generarPDF(ventafinal);
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
	
	public void generarPDF(Venta venta){
        Document documento = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream("comprobanteVenta.pdf"));
            documento.open();

            PdfContentByte cb = writer.getDirectContent();
            Font fontInmob = FontFactory.getFont(Font.FontFamily.HELVETICA.toString(), 12, Font.BOLD);
            cb.beginText();
            cb.setFontAndSize(fontInmob.getBaseFont(), 32);
            cb.showTextAligned(Element.ALIGN_LEFT, "INMOBILIARIA", 36, 800, 0);
            cb.endText();

            Font font = FontFactory.getFont(Font.FontFamily.COURIER.toString(), 12, Font.NORMAL);
            Date ventaFecha = venta.getFecha();
            cb.beginText();
            cb.setFontAndSize(font.getBaseFont(), 12);
            cb.showTextAligned(Element.ALIGN_RIGHT, ventaFecha.toString(), 559, 800, 0);
            cb.endText();
            
            String tipoinmueble = tipoinmuebletostring(venta.getPropiedad().getTipoInmueble());
            
            // Agregar el texto principal (un párrafo)
            float alturaPagina = documento.getPageSize().getHeight();
            Paragraph espacio = new Paragraph(" ");
            Paragraph texto = new Paragraph("Se ha realizado la venta del inmueble " +
                    tipoinmueble + " ubicado en calle " +
                    venta.getPropiedad().getCalle()+" "+venta.getPropiedad().getNumero() + ", " +
                    venta.getPropiedad().getLocalidad().toString() +
                    " a partir del presente día " + venta.getFecha() +
                    ". El titulo de propiedad queda a nombre de " + venta.getCliente().toString()
                    + " en la ciudad de SANTA FE, " + "por el monto de $" +
                    venta.getImporteVenta());
            texto.setSpacingBefore(alturaPagina/6);
            documento.add(espacio);
            documento.add(texto);
            documento.close();
            writer.close();

            System.out.println("PDF generado correctamente.");
            openpdf("comprobanteVenta.pdf");
            if(venta.getCliente().getEmail()!= null) {
            send("comprobanteVenta.pdf",venta.getCliente().getEmail());
            }

        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
	
    private  void openpdf(String filepath){
        try {
            if (Desktop.isDesktopSupported()) {
                File pdfFile = new File(filepath);
                if (pdfFile.exists()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("The file does not exist.");
                }
            } else {
                System.out.println("Desktop not supported.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
    
    private void send(String pathpdf, String mailCliente){
        try {
            String mail = "sofiaknunez@gmail.com";
            String pass = "mxfpourbgmivpcim";
            // Set up mail properties
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your SMTP host
            properties.put("mail.smtp.ssl.trust","smtp.gmail.com");
            properties.put("mail.smtp.starttls.enable","true");
            properties.put("mail.smtp.port", "587"); // Replace with your SMTP port
            //properties.setProperty("mail.smtp.ssl.protocols.","TLSv1.2");
            properties.put("mail.smtp.user","mail");
            properties.put("mail.smtp.auth", "true");

            // Set up authentication
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mail,pass);
                }
            });

            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mail)); // Replace with your email
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailCliente)); // Replace with recipient's email
            message.setSubject("Comprobante de venta");
            // Create the attachment
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(new File(pathpdf)); // Path to your generated PDF

            Multipart multipart = new MimeMultipart();
            message.setText("Pdf here");
            multipart.addBodyPart(attachmentBodyPart);
            message.setContent(multipart);
            Transport transport = session.getTransport("smtp");
            transport.connect(mail,pass);
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();

        }
        catch(MessagingException | IOException e){
            e.printStackTrace();
        }
    }
    
    private String tipoinmuebletostring(TipoInmueble t) {
    	String s;
    	switch (t) {
        case C:
            s = "casa";
            break;
        case L:
        	s = "local";
            break;
        case D:
        	s = "departamento";
            break;
        case T:
        	s = "terreno";
            break;
        case Q:
        	s = "quinta";
            break;
        case G:
        	s = "garage";
            break;
        default:
        	s = " ";
            break;
    	}
            return s;
    };
	
}
