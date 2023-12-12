package Controllers;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import dto.ClienteDTO;
import dto.InmuebleDTO;
import dto.ReservaDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ClienteServices;
import services.InmuebleServices;
import services.ReservaServices;
import services.VentaServices;

public class ReservaPrincipalController implements Initializable{

    @FXML
    private Button SalirButton;

    @FXML
    private AnchorPane Scenario;

    @FXML
    private Label apellidoClienteLabel;

    @FXML
    private Label calleInmuebleLabel;

    @FXML
    private Button confirmarButton;

    @FXML
    private Label emailClienteLabel;

    @FXML
    private Label localidadInmuebleLabel;

    @FXML
    private TextField montoField;

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
    private TextField tiempoVigenciaField;

    @FXML
    private Label tipoDocumentoLabel;

    @FXML
    private Label tipoInmuebleLabel;
    @FXML
    private TextField fechaField;
    
    private int idInmueble;
    private int idCliente;
    private Validacion validar;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    ClienteServices clienteServices = ClienteServices.getInstance();
    InmuebleServices inmuebleServices = InmuebleServices.getInstance();
    ReservaServices reservaServices = ReservaServices.getInstance();
    Holder holder = Holder.getInstance();
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	idInmueble = holder.getIdInmueble();
    	idCliente = holder.getIdCliente();
		// TODO Auto-generated method stub
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
    void ConfirmarPressedPressed(ActionEvent event) {
    	/*public ReservaDTO(int inmueble,int cliente,int vendedor,double importe, Date fecha,float tiempoVigencia)
		
	}*/
    	Date date = new Date();
    	if(validar.esUnNumero(montoField.getText())!=1){
    		
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Monto invalido"); //titulo
    		alertaTipo.setContentText("El campo 'Monto' debe ser de tipo numerico"); //informacion
    		alertaTipo.showAndWait();
    	} else if(validar.esUnNumero(tiempoVigenciaField.getText())!=1){
    		
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Tiempo de vigencia invalido"); //titulo
    		alertaTipo.setContentText("El campo 'Tiempo de vigencia' debe ser de tipo numerico"); //informacion
    		alertaTipo.showAndWait();
    	}else {
    		ReservaDTO reserva = new ReservaDTO(idInmueble, idCliente, holder.getIdVendedor(), Double.parseDouble(montoField.getText()),(java.sql.Date) date,Float.parseFloat(tiempoVigenciaField.getText()));
        	
        	
        	
        	
        	holder.setIdCliente(idCliente);
        	holder.setIdInmueble(idInmueble);
        	
        	if(reservaServices.createReserva(reserva)==1) {
        		try {
            		Parent root;
            		root = FXMLLoader.load((getClass().getResource("/interfaces/ReservaInmuebleConfirmada.fxml")));
            		
            		Stage window = (Stage)SalirButton.getScene().getWindow();
            		window.setTitle("Clientes");
            		window.setScene(new Scene(root));
            	} catch (IOException e) {
            		// TODO Auto-generated catch block
            		e.printStackTrace();
            	}
        	}else {
        		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
	    		alertaTipo.setTitle("Reserva incompleta"); //titulo
	    		alertaTipo.setContentText("La reserva no se pudo realizar debido al monto especificado"); //informacion
	    		alertaTipo.showAndWait();
        	}
        	
    	}
    	
    }

    @FXML
    void SalirPressed(ActionEvent event) {
    	try {
    		Parent root;
    		root = FXMLLoader.load((getClass().getResource("/interfaces/InmueblesParaCliente.fxml")));
    		
    		Stage window = (Stage)SalirButton.getScene().getWindow();
    		window.setTitle("Clientes");
    		window.setScene(new Scene(root));
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

	

}
