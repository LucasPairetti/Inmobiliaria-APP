package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NuevoVendedorController implements Initializable {

    @FXML
    private TextField ApellidoField;

    @FXML
    private TextField CalleField;

    @FXML
    private Button CancelarButton;

    @FXML
    private TextField ClaveField;

    @FXML
    private TextField EmailField;

    @FXML
    private DatePicker FechaNacimientoCalendar;

    @FXML
    private Button GuardarBUtton;

    @FXML
    private ComboBox<String> LocalidadMenu;

    @FXML
    private TextField NombreField;

    @FXML
    private TextField NumeroDomicilioField;

    @FXML
    private ComboBox<String> ProvinciaMenu;

    @FXML
    private TextField SueldoField;

    @FXML
    private TextField TelefonoField;

    @FXML
    private TextField numeroDocField;

    @FXML
    private ComboBox<String> tipoDocMenu;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
    @FXML
    void CancelarPressed(ActionEvent event) {
    	try {
    		Parent root;
    		root = FXMLLoader.load((getClass().getResource("/interfaces/VendedoresPrincipal.fxml")));
    		
    		Stage window = (Stage)CancelarButton.getScene().getWindow();
    		window.setTitle("Vendedores");
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
