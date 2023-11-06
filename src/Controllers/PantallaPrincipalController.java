package Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PantallaPrincipalController {

    @FXML
    private Button ClientesButton;

    @FXML
    private Button InmueblesButton;

    @FXML
    private Button PropietariosButton;

    @FXML
    private Button SalirButton;
    
    @FXML
    private AnchorPane Scenario;

    @FXML
    void ClientesPressed(ActionEvent event) {

try {
	Parent root;
	root = FXMLLoader.load((getClass().getResource("/interfaces/ClientesPrincipal.fxml")));
	
	Stage window = (Stage)ClientesButton.getScene().getWindow();
	window.setTitle("Clientes");
	window.setScene(new Scene(root));
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
    	
    	

    }

    @FXML
    void InmueblesPressed(ActionEvent event) {
    	try {
    		Parent root;
    		root = FXMLLoader.load((getClass().getResource("/interfaces/InmueblesBusqueda.fxml")));
    		
    		Stage window = (Stage)ClientesButton.getScene().getWindow();
    		window.setTitle("Buscar Inmueble");
    		window.setScene(new Scene(root));
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    @FXML
    void PropietariosPressed(ActionEvent event) {
    	try {
    		Parent root;
    		root = FXMLLoader.load((getClass().getResource("/interfaces/PropietariosPrincipal.fxml")));
    		
    		Stage window = (Stage)ClientesButton.getScene().getWindow();
    		window.setTitle("Propietarios");
    		window.setScene(new Scene(root));
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}

    }

    @FXML
    void SalirPressed(ActionEvent event) {
    	Stage stage = (Stage) Scenario.getScene().getWindow();
    	stage.close();
    }

}
