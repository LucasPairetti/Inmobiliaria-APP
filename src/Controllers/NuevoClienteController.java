package Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NuevoClienteController {

    @FXML
    private TextField ApellidoField;

    @FXML
    private Button CancelarButton;

    @FXML
    private Button GuardarBUtton;

    @FXML
    private ComboBox<?> InmuebleMenu;

    @FXML
    private ComboBox<?> LocalidadMenu;

    @FXML
    private TextField MontoUSD;

    @FXML
    private TextField NombreField;

    @FXML
    private TextField TelefonoField;

    @FXML
    private TextField barrioField;

    @FXML
    private TextArea caracteristicasTextArea;

    @FXML
    void CancelarPressed(ActionEvent event) {
    	try {
    		Parent root;
    		root = FXMLLoader.load((getClass().getResource("/interfaces/ClientesPrincipal.fxml")));
    		
    		Stage window = (Stage)CancelarButton.getScene().getWindow();
    		window.setTitle("Clientes");
    		window.setScene(new Scene(root));
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}

    }

    @FXML
    void GuardarPressed(ActionEvent event) {

    }

}