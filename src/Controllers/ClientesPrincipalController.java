package Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ClientesPrincipalController {

    @FXML
    private Button AgregarButton;

    @FXML
    private TableColumn<?, ?> ApellidoColumn;

    @FXML
    private TextField ApellidoField;

    @FXML
    private Button BuscarButton;

    @FXML
    private TableView<?> ClientesTable;

    @FXML
    private Button EliminarButton;

    @FXML
    private Button LimpiarButton;

    @FXML
    private Button ModificarButton;

    @FXML
    private TableColumn<?, ?> NombreColumn;

    @FXML
    private TextField NombreField;

    @FXML
    private Button VolverButton;

    @FXML
    void AgregarPressed(ActionEvent event) {
    	try {
    		Parent root;
    		root = FXMLLoader.load((getClass().getResource("/interfaces/NuevoCliente.fxml")));
    		
    		Stage window = (Stage)VolverButton.getScene().getWindow();
    		window.setTitle("Propietarios");
    		window.setScene(new Scene(root));
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}

    }

    @FXML
    void BuscarPressed(ActionEvent event) {

    }

    @FXML
    void EliminarPressed(ActionEvent event) {

    }

    @FXML
    void LimpiarPressed(ActionEvent event) {

    }

    @FXML
    void ModificarPressed(ActionEvent event) {

    }

    @FXML
    void VolverPressed(ActionEvent event) {
    	
    	try {
    		Parent root;
    		root = FXMLLoader.load((getClass().getResource("/interfaces/PantallaPrincipal.fxml")));
    		
    		Stage window = (Stage)VolverButton.getScene().getWindow();
    		window.setTitle("Propietarios");
    		window.setScene(new Scene(root));
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}

    }

}
