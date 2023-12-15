package application;
import dto.*;
import application.clases.Reserva;
import application.clases.Venta;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;

import application.clases.Cliente;
import application.clases.Estado;
import application.clases.Inmueble;
import application.clases.Orientacion;
import application.clases.Propietario;
import application.clases.Provincia;
import application.clases.TipoDNI;
import application.clases.TipoInmueble;
import application.clases.Vendedor;
import application.dao.HibernateUtil;
import application.dao.InmuebleDAO;
import application.dao.VendedorDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import services.InmuebleServices;
import services.ReservaServices;
import services.VendedorServices;
import services.VentaServices;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
try {
			
			//inicializar cosas antes de mostrar pantalla
			
			
Parent root = FXMLLoader.load((getClass().getResource("/interfaces/LoginPrincipal.fxml")));
			
			Scene scene = new Scene(root);
			primaryStage.setTitle("Ingreso al sistema");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		// Antes de ejecutar este main borrar database y volver a crear una con el nombre inmboliariaapp. 
		// Luego correr este main.
		// Una vez corrido, comentarlo, porque ya se creo la BD.
		/*
		InmuebleDAO inmuebleDAO = InmuebleDAO.getInmuebleDAO();
		Propietario p = new Propietario("Lucas", "Pairetti", TipoDNI.DNI,"42870675", "asd", 1111, "Santa Fe", Provincia.Santa_Fe,1111111,"pairet@gmail.com");
		Inmueble inmueble = new Inmueble(p, Date.valueOf("2000-11-03"),Estado.Disponible,Provincia.Santa_Fe,"Santa Fe","asd",2476,"","Fomento",
				TipoInmueble.C,20000000, Orientacion.Norte,256,1,1,50,3,3,true,true,true,true,true,true,true,true,true,true,true,"Fachera");
		inmuebleDAO.createInmueble(inmueble);
		
		
		Cliente cliente = new Cliente("Tomas", "Auday","44444444",TipoDNI.DNI, 111111111,"tomitoauday@gmail.com",40000000,
				TipoInmueble.C, "Santa Fe", "Fomento/7 Jefes/Guadalupe",
				"Varios banios, varios dormitorios, patio");
		
		System.out.println(cliente.getEmail());
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		session.beginTransaction();
		session.persist(cliente);
		session.getTransaction().commit();
		session.close();
		System.out.println(cliente);
		/*
		
		InmuebleDAO i = InmuebleDAO.getInmuebleDAO();
		
		System.out.println(i.getInmueble(null,null, null, null, null, null));
	
		
		Cliente cliente = new Cliente("Tomas", "Auday","44444444",TipoDNI.DNI, 111111111,"sofia.30.10.99@gmail.com",40000000,
				TipoInmueble.C, "Santa Fe", "Fomento/7 Jefes/Guadalupe",
				"Varios banios, varios dormitorios, patio");
		Propietario p = new Propietario("Lucas", "Pairetti", TipoDNI.DNI,"42870675", "asd", 1111, "Santa Fe", Provincia.Santa_Fe,1111111,"pairet@gmail.com");
		Inmueble inmueble = new Inmueble(p, Date.valueOf("2000-11-03"),Estado.Disponible,Provincia.Santa_Fe,"Santa Fe","asd",2476,"","Fomento",
				TipoInmueble.C,20000000, Orientacion.Norte,256,1,1,50,3,3,true,true,true,true,true,true,true,true,true,true,true,"Fachera");
		Vendedor vendedor = new Vendedor();
		Date date = Date.valueOf("2023-10-10");
		/*
		Reserva reserva = new Reserva(inmueble,cliente, vendedor,4000.0,60,date);
		
		ReservaServices reservaS = ReservaServices.getInstance();
		reservaS.generarPDF(reserva);
		Venta venta = new Venta(inmueble,cliente,vendedor,10000.0,date);
		
		VentaServices ventaservices = VentaServices.getInstance();
		ventaservices.generarPDF(venta);
		*/
		/*
		Cliente cliente = new Cliente("Tomas", "Auday","44444444",TipoDNI.DNI, 111111111,"sofia.30.10.99@gmail.com",40000000,
				TipoInmueble.C, "Santa Fe", "Fomento/7 Jefes/Guadalupe",
				"Varios banios, varios dormitorios, patio");
		
		ClienteDTO clientedto = new ClienteDTO(cliente);
		clientedto.setId(3);
		InmuebleServices inmservices = InmuebleServices.getInstance();
		//System.out.print(inmservices.getInmueblesDisponibles());
		//System.out.print(inmservices.inmueblesDisponiblesParaCliente(clientedto,TipoInmueble.C));
		System.out.print(inmservices.reservasValidasCliente(clientedto));
		/*/
		//launch(args);
		
		Date fecha = new Date(99, 6, 15);
		VendedorDTO vendedor = new VendedorDTO("Julio", "Chort", TipoDNI.DNI.toString(), "41906985", "Necochea", 4101,
				"Santa Fe", "Santa Fe", 155282518, "juliochort@gmail.com",fecha , 300000.0,
				"1234");
		VendedorServices vendedors = VendedorServices.getInstance();
		vendedors.createVendedor(vendedor);
		
		
	}
}
