package application;
	
import java.sql.Date;

import application.clases.Inmueble;
import application.clases.Orientacion;
import application.clases.Propietario;
import application.clases.Provincia;
import application.clases.TipoDNI;
import application.clases.TipoInmueble;
import application.dao.InmuebleDAO;
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
		//launch(args);
		InmuebleDAO inmuebleDAO = InmuebleDAO.getInmuebleDAO();
		Propietario p = new Propietario("Lucas", "Pairetti", TipoDNI.DNI,"42870675", "asd", 1111, "Santa Fe", Provincia.Santa_Fe,1111111,"pairet@gmail.com");
		Inmueble inmueble = new Inmueble(p, Date.valueOf("2000-11-03"),true,Provincia.Santa_Fe,"Santa Fe","asd",2476,"","Fomento",
				TipoInmueble.C,20000000, Orientacion.Norte,256,1,1,50,3,3,true,true,true,true,true,true,true,true,true,true,true,"Fachera");
		inmuebleDAO.createInmueble(inmueble);
	}
}
