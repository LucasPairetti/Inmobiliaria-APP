package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.clases.Localidad;
import application.clases.Provincia;
import application.clases.TipoDNI;
import application.dao.PropietarioDAO;
import dto.PropietarioDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.PropietarioServices;

public class ModificarPropietarioController implements Initializable {

    @FXML
    private TextField ApellidoField;

    @FXML
    private TextField CalleField;

    @FXML
    private Button CancelarButton;

    @FXML
    private TextField DNIField;

    @FXML
    private Button GuardarBUtton;

    @FXML
    private ComboBox<String> LocalidadMenu;

    @FXML
    private TextField NombreField;

    @FXML
    private ComboBox<String> ProvinciaMenu;

    @FXML
    private TextField TelefonoField;

    @FXML
    private ComboBox<String> TipoDocMenu;

    @FXML
    private TextField emailField;

    @FXML
    private TextField numeroField;

    private int propietarioID;
    private Validacion validar;
    private PropietarioServices propietarioService= PropietarioServices.getInstance();

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
    	ObservableList<String> localidades=  FXCollections.observableArrayList();
    	localidades.addAll(Localidad.getLocalidad());
    	ObservableList<String> provincias=  FXCollections.observableArrayList();
    	provincias.addAll(Provincia.getProvincias());
    	ObservableList<String> TipoDocumentos=  FXCollections.observableArrayList();
    	TipoDocumentos.addAll(TipoDNI.getTiposDNI());
    	LocalidadMenu.setItems( localidades );
     	ProvinciaMenu.setItems( provincias );
     	TipoDocMenu.setItems(TipoDocumentos);
    	
    	
	}

    public void setPropietario(int id) {
    	propietarioID =id;
    }
    
    @FXML
    void CancelarPressed(ActionEvent event) {
    	try {
    		Parent root;
    		root = FXMLLoader.load((getClass().getResource("/interfaces/PropietariosPrincipal.fxml")));
    		
    		Stage window = (Stage)CancelarButton.getScene().getWindow();
    		window.setTitle("Propietarios");
    		window.setScene(new Scene(root));
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    @FXML
    void GuardarPressed(ActionEvent event) {
    	if (validar.esUnNumero(numeroField.getText()) != 1) {
    	    Alert alertaTipo = new Alert(Alert.AlertType.ERROR);
    	    alertaTipo.setTitle("Número de calle inválido");
    	    alertaTipo.setContentText("El campo 'Número' para la calle debe ser de tipo numérico");
    	} else if (NombreField.getText().equals("") || validar.esString(NombreField.getText()) != 1) {
    	    Alert alertaTipo = new Alert(Alert.AlertType.ERROR);
    	    alertaTipo.setTitle("Nombre inválido o vacío");
    	    alertaTipo.setContentText("El campo 'Nombre' es inválido o está vacío");
    	} else if (ApellidoField.getText().equals("") || validar.esString(ApellidoField.getText()) != 1) {
    	    Alert alertaTipo = new Alert(Alert.AlertType.ERROR);
    	    alertaTipo.setTitle("Apellido inválido o vacío");
    	    alertaTipo.setContentText("El campo 'Apellido' es inválido o está vacío");
    	} else if (DNIField.getText().equals("") || validar.esString(DNIField.getText()) != 1) {
    	    Alert alertaTipo = new Alert(Alert.AlertType.ERROR);
    	    alertaTipo.setTitle("DNI inválido o vacío");
    	    alertaTipo.setContentText("El campo 'DNI' es inválido o está vacío");
    	} else if (CalleField.getText().equals("") || validar.esString(CalleField.getText()) != 1) {
    	    Alert alertaTipo = new Alert(Alert.AlertType.ERROR);
    	    alertaTipo.setTitle("Calle inválida o vacía");
    	    alertaTipo.setContentText("El campo 'Calle' es inválido o está vacío");
    	} else if (validar.esUnNumero(TelefonoField.getText()) != 1) {
    	    Alert alertaTipo = new Alert(Alert.AlertType.ERROR);
    	    alertaTipo.setTitle("Número de teléfono inválido");
    	    alertaTipo.setContentText("El campo 'Teléfono' debe ser de tipo numérico");
    	} else if (emailField.getText().equals("") || validar.esString(emailField.getText()) != 1) {
    	    Alert alertaTipo = new Alert(Alert.AlertType.ERROR);
    	    alertaTipo.setTitle("Email inválido o vacío");
    	    alertaTipo.setContentText("El campo 'Email' es inválido o está vacío");
    	}else {
    		/*
        	 * public PropietarioDTO(String nombre, String apellido, String tipodni, String dni, String calle, int numero,
    			String localidad, String provincia, int telefono, String email)
        	 */
    		
    		try {
    			
    			PropietarioDTO nuevoPropietario= new PropietarioDTO(propietarioID,NombreField.getText(), ApellidoField.getText(), TipoDocMenu.getValue(), DNIField.getText(), CalleField.getText(), Integer.parseInt(numeroField.getText()),
            			LocalidadMenu.getValue(), ProvinciaMenu.getValue(), Integer.parseInt(TelefonoField.getText()), emailField.getText());
    			propietarioService.updatePropietario(nuevoPropietario);
    			
    			
        		Parent root;
        		root = FXMLLoader.load((getClass().getResource("/interfaces/PropietariosPrincipal.fxml")));
        		
        		Stage window = (Stage)CancelarButton.getScene().getWindow();
        		window.setTitle("Propietarios");
        		window.setScene(new Scene(root));
        	} catch (IOException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
    	}
    	
    	
    	
    }

	
}
