package application;
	
import java.sql.Date;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
try {
			
			//inicializar cosas antes de mostrar pantalla
			
			
Parent root = FXMLLoader.load((getClass().getResource("/interfaces/PantallaPrincipal.fxml")));
			
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
	*/
		launch(args);	
		
		
		
		
	}
}
