package Controllers;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import dto.ClienteDTO;
import dto.InmuebleDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ClienteServices;
import services.InmuebleServices;

public class ReservaConfirmadaController implements Initializable {

    @FXML
    private AnchorPane Scenario;

    @FXML
    private Button VolverButton;

    @FXML
    private Label apellidoClienteLabel;

    @FXML
    private Label calleInmuebleLabel;

    @FXML
    private Label emailClienteLabel;

    @FXML
    private Label localidadInmuebleLabel;

    @FXML
    private Label nombreClienteLabel;

    @FXML
    private Label nroDocumentoClienteLabel;

    @FXML
    private Label numeroInmuebleLabel;

    @FXML
    private Label pisoDeptoLabel;

    @FXML
    private Label propietarioLabel;

    @FXML
    private Label provinciaInmuebleLabel;

    @FXML
    private Label telefonoClienteLabel;

    @FXML
    private Label tipoDocumentoLabel;

    @FXML
    private Label tipoInmuebleLabel;
    

    @FXML
    private TextField fechaField;
    
    private int idInmueble;
    private int idCliente;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    ClienteServices clienteServices = ClienteServices.getInstance();
    InmuebleServices inmuebleServices = InmuebleServices.getInstance();
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	
    	Holder holder = Holder.getInstance();
    	idInmueble = holder.getIdInmueble();
    	idCliente = holder.getIdCliente();
    	
		ClienteDTO cliente = clienteServices.getClienteById(idCliente);
		InmuebleDTO inmueble = inmuebleServices.getById(idInmueble);
		Date date = new Date();
		fechaField.setText(sdf.format(date));
		//cliente
		nombreClienteLabel.setText(cliente.getNombre());
		apellidoClienteLabel.setText(cliente.getApellido());
		emailClienteLabel.setText(cliente.getEmail());
		nroDocumentoClienteLabel.setText(cliente.getDni());
		tipoDocumentoLabel.setText(cliente.getTipoDNI());
		telefonoClienteLabel.setText(String.valueOf(cliente.getTelefono()));
		//inmueble
		localidadInmuebleLabel.setText(inmueble.getLocalidad());
		calleInmuebleLabel.setText(inmueble.getCalle());
		numeroInmuebleLabel.setText(String.valueOf(inmueble.getNumero()));
		pisoDeptoLabel.setText(String.valueOf(inmueble.getPisodpto()));
		propietarioLabel.setText(inmueble.getNombreApellidoPropietario());
		provinciaInmuebleLabel.setText(inmueble.getProvincia());
		tipoInmuebleLabel.setText(inmueble.getTipoInmueble());
		
	}

    @FXML
    void VolverPressed(ActionEvent event) {
    	try {
    		Parent root;
    		root = FXMLLoader.load((getClass().getResource("/interfaces/PantallaPrincipal.fxml")));
    		
    		Stage window = (Stage)VolverButton.getScene().getWindow();
    		window.setTitle("Clientes");
    		window.setScene(new Scene(root));
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

	

}
