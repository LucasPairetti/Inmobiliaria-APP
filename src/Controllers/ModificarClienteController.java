package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.clases.Localidad;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModificarClienteController implements Initializable{

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

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
    	//LocalidadMenu.setItems((ObservableList<String>) Localidad.getLocalidad());
    	//TipoInmuebleMenu.setItems((ObservableList<String>) TipoInmueble.getTipoInmueble());
	}
    
    
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
