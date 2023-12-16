package Controllers;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ResourceBundle;

import dto.ClienteDTO;
import dto.InmuebleDTO;
import dto.VendedorDTO;
import dto.VentaDTO;
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
import services.VendedorServices;
import services.VentaServices;

public class VentaPrincipalController implements Initializable{

    @FXML
    private Button SalirButton;

    @FXML
    private AnchorPane Scenario;

    @FXML
    private Label apellidoClienteLabel;

    @FXML
    private Label apellidoVendedorLabel;

    @FXML
    private Label calleInmuebleLabel;

    @FXML
    private Button confirmarButton;

    @FXML
    private Label emailClienteLabel;

    @FXML
    private Label emailVendedorLabel;

    @FXML
    private Label localidadInmuebleLabel;

    @FXML
    private TextField montoField;

    @FXML
    private Label nombreClienteLabel;

    @FXML
    private Label nombreVendedorLabel;

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
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private int idInmueble;
    private int idCliente;
    private int idVendedor;
    private Validacion validar =Validacion.getInstance();
    Holder holder = Holder.getInstance();
    ClienteServices clienteServices = ClienteServices.getInstance();
    InmuebleServices inmuebleServices = InmuebleServices.getInstance();
    VendedorServices vendedorServices = VendedorServices.getInstance();
    VentaServices ventaServices = VentaServices.getInstance();
    Date fecha = new Date(System.currentTimeMillis());
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	Holder holder = Holder.getInstance();
    	idInmueble = holder.getIdInmueble();
    	idCliente = holder.getIdCliente();
    	idVendedor = holder.getIdUsuario();
     	ClienteDTO cliente = clienteServices.getClienteById(idCliente);
    		InmuebleDTO inmueble = inmuebleServices.getById(idInmueble);
    		VendedorDTO vendedor = vendedorServices.getVendedorById(idVendedor);
    		
    		fechaField.setText(sdf.format(fecha));
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
    		//vendedor
    		apellidoVendedorLabel.setText(vendedor.getApellido());
    		emailVendedorLabel.setText(vendedor.getEmail());
    		nombreVendedorLabel.setText(vendedor.getNombre());
	}

    @FXML
    void ConfirmarPressedPressed(ActionEvent event) {
if(validar.esUnNumero(montoField.getText())!=1){
    		
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Monto invalido"); //titulo
    		alertaTipo.setContentText("El campo 'Monto' debe ser de tipo numerico"); //informacion
    		alertaTipo.showAndWait();
    	}
else {
	
	//public VentaDTO(int inmueble,int cliente,int vendedor,double importe, Date fecha)
	VentaDTO venta = new VentaDTO(idInmueble, idCliente, idVendedor,Double.parseDouble(montoField.getText()),fecha);
	
	ventaServices.createVenta(venta);
	holder.setIdCliente(idCliente);
	holder.setIdInmueble(idInmueble);
	holder.setIdVendedor(idVendedor);
	if(ventaServices.createVenta(venta)==1) {
		try {
			Parent root;
			root = FXMLLoader.load((getClass().getResource("/interfaces/VentaConfirmada.fxml")));
			
			Stage window = (Stage)SalirButton.getScene().getWindow();
			window.setTitle("Clientes");
			window.setScene(new Scene(root));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}else {
		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
		alertaTipo.setTitle("Venta incompleta"); //titulo
		alertaTipo.setContentText("La Venta no se pudo realizar debido al monto especificado"); //informacion
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
