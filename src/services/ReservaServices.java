package services;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Properties;

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
import dto.PropietarioDTO;
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
		double monto=0.0;
		if(reservasInmueble !=null) {
			List<Reserva> reservasValidas = reservasInmueble.stream().filter(Reserva::esReservaValida).collect(Collectors.toList());
			if(!reservasValidas.isEmpty()) {
				return reservasValidas.get(0).getCliente().getId(); // existe otra reserva vigente en este momento y te digo de que cliente es
			}else {
						monto=reservasValidas.get(0).getImporteReserva();
				}
			}
		if(monto>inmueble.getPrecioVenta()) {return -1;}//esta reservando por mayor valor del que debia
		
		Reserva reservaTerminada = toReserva(reserva,inmueble,cliente,vendedor);
		reservadao.createReserva(reservaTerminada);
		inmueble.setEstado(Estado.Reservado);
		inmuebledao.updateInmueble(inmueble);
		generarPDF(reservaTerminada);
		return 0;//retorna 0 para no concidir con el id 1 de un cliente que tenga una reserva
		
		
	}
	
	
	public List<ReservaDTO> getAllReservas(){
		return Optional.ofNullable( reservadao.getAllReservas())
	            .map(List::stream)
	            .orElseGet(Stream::empty)
	            .map(reserva -> new ReservaDTO(reserva))
	            .collect(Collectors.toList());
	}
	public List<ReservaDTO> getReservaByCliente(Cliente c){
		return Optional.ofNullable( reservadao.getReservasByCliente(c))
	            .map(List::stream)
	            .orElseGet(Stream::empty)
	            .map(reserva -> new ReservaDTO(reserva))
	            .collect(Collectors.toList());
	}
	public List<ReservaDTO> getReservaByInmueble(Inmueble i){
		return Optional.ofNullable( reservadao.getReservasByInmueble(i))
	            .map(List::stream)
	            .orElseGet(Stream::empty)
	            .map(reserva -> new ReservaDTO(reserva))
	            .collect(Collectors.toList());
	}
	public double getMontoReserva(Inmueble i, Cliente c) {
		List<Reserva> reservas =  reservadao.getReservasByInmueble(i);
		if(reservas != null) {
			List<Reserva> reservasValidas = reservas.stream().filter(Reserva::esReservaValida).collect(Collectors.toList());
			if(reservasValidas.isEmpty()) {
				return 0; // existe otra reserva vigente en este momento y te digo de que cliente es
			}else {
				if(c.getId()!=reservasValidas.get(0).getId()) {
					return -1;//la reserva es valida y esta a nombre de otro
				}else {
					return reservasValidas.get(0).getImporteReserva();//devuelvo en importe de la reserva
				}
			}
		}
		return 0;
	}
	private Reserva toReserva(ReservaDTO entrada,Inmueble inmueble,Cliente cliente, Vendedor vendedor ) {
		return new Reserva(inmueble,cliente,vendedor,entrada.getImporteReserva(),entrada.getTiempoVigencia(),entrada.getFecha());
	}
	
	public void generarPDF(Reserva reserva){
        Document documento = new Document();

        try {
            PdfWriter writer = PdfWriter.getInstance(documento, new FileOutputStream("ejemplo.pdf"));
            documento.open();

            PdfContentByte cb = writer.getDirectContent();
            Font fontInmob = FontFactory.getFont(Font.FontFamily.HELVETICA.toString(), 12, Font.BOLD);
            cb.beginText();
            cb.setFontAndSize(fontInmob.getBaseFont(), 32);
            cb.showTextAligned(Element.ALIGN_LEFT, "INMOBILIARIA", 36, 800, 0);
            cb.endText();

            Font font = FontFactory.getFont(Font.FontFamily.COURIER.toString(), 12, Font.NORMAL);
            Date fechaReserva = reserva.getFecha();
            cb.beginText();
            cb.setFontAndSize(font.getBaseFont(), 12);
            cb.showTextAligned(Element.ALIGN_RIGHT, fechaReserva.toString(), 559, 800, 0);
            cb.endText();

            // Agregar el texto principal (un párrafo)
            float alturaPagina = documento.getPageSize().getHeight();
            Paragraph espacio = new Paragraph(" ");
            Paragraph texto = new Paragraph("Se ha realizado una reserva para el inmueble " + 
            reserva.getInmueble().getTipoInmueble().toString() + "ubicado en calle " + 
            		reserva.getInmueble().getCalle()+reserva.getInmueble().getNumero() + ", " + 
            reserva.getInmueble().getLocalidad().toString() +
                    "A nombre de " + reserva.getCliente().toString() + " el día " + fechaReserva
                    + " en la ciudad de SANTA FE, " + "por el monto de $" +
                    reserva.getImporteReserva() + "(PESOS) " + "Valida por los siguientes: " 
                    + reserva.getTiempoVigencia() + " dias");
            texto.setSpacingBefore(alturaPagina/6);
            documento.add(espacio);
            documento.add(texto);
            documento.close();
            writer.close();

            System.out.println("PDF generado correctamente.");
            openpdf("ejemplo.pdf");
            if(reserva.getCliente().getEmail()!= null) {
            send("ejemplo.pdf",reserva.getCliente().getEmail());
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
	            message.setSubject("pdf test");
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
	
}
