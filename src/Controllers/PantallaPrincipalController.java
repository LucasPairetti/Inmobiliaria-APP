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
    private Button VendedoresButton;


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
    		window.setTitle("Menu Principal");
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
    void VendedoresPressed(ActionEvent event) {
    	try {
    		Parent root;
    		root = FXMLLoader.load((getClass().getResource("/interfaces/VendedoresPrincipal.fxml")));
    		
    		Stage window = (Stage)VendedoresButton.getScene().getWindow();
    		window.setTitle("Vendedores");
    		window.setScene(new Scene(root));
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    @FXML
    void SalirPressed(ActionEvent event) {
    	try {
    		Parent root;
    		root = FXMLLoader.load((getClass().getResource("/interfaces/LoginPrincipal.fxml")));
    		
    		Stage window = (Stage)VendedoresButton.getScene().getWindow();
    		window.setTitle("Ingreso al sistema");
    		window.setScene(new Scene(root));
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

}
