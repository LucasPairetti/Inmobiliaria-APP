package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.clases.Localidad;
import application.clases.TipoDNI;
import application.clases.TipoInmueble;
import dto.ClienteDTO;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ClienteServices;

public class ModificarClienteController implements Initializable{

    @FXML
    private TextField ApellidoField;

    @FXML
    private Button CancelarButton;

    @FXML
    private Button GuardarBUtton;

    @FXML
    private ComboBox<String> InmuebleMenu;

    @FXML
    private ComboBox<String> LocalidadMenu;

    @FXML
    private TextField MontoUSDField;


    @FXML
    private TextField NombreField;

    @FXML
    private TextField TelefonoField;

    @FXML
    private TextField barrioField;

    @FXML
    private TextArea caracteristicasTextArea;
    @FXML
    private TextField numeroDocField;

    @FXML
    private ComboBox<String> tipoDocMenu;
    @FXML
    private TextField EmailField;
    
    private int idCliente;
    private Validacion validar =Validacion.getInstance();
    private ClienteServices clienteServices= ClienteServices.getInstance();

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	Holder holder = Holder.getInstance();
    	idCliente= holder.getIdCliente();
    	
    	System.out.println(idCliente);
    	ObservableList<String> dnis= FXCollections.observableArrayList();
		
		dnis.addAll(TipoDNI.getTiposDNI());
		tipoDocMenu.setItems(dnis);	
		
    	ObservableList<String> Inmuebles=FXCollections.observableArrayList();
    	Inmuebles.addAll(TipoInmueble.getTipoInmueble());
    	InmuebleMenu.setItems(Inmuebles);
    	
    	ObservableList<String> localidades=  FXCollections.observableArrayList();
    	localidades.addAll(Localidad.getLocalidad());
    	LocalidadMenu.setItems(localidades);
    	ClienteDTO cliente= clienteServices.getClienteById(idCliente);
    	tipoDocMenu.setValue(cliente.getTipoDNI());
    	NombreField.setText(cliente.getNombre());
    	InmuebleMenu.setValue(cliente.getTipoInmuebleBuscado());
    	LocalidadMenu.setValue(cliente.getLocalidadBuscada());
    	ApellidoField.setText(cliente.getApellido());
    	EmailField.setText(cliente.getEmail());
    	MontoUSDField.setText(String.valueOf(cliente.getMontoDisponible())); 
    	TelefonoField.setText(String.valueOf(cliente.getTelefono())); 
    	numeroDocField.setText(cliente.getDni());
    	barrioField.setText(cliente.getBarrios());
	}
    
    public void setCliente(int id) {
    	idCliente=id;
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
    	
    	/*public ClienteDTO( String nombre, String apellido,String dni,String tipoDNI, int telefono,String email, double montoDisponible,
		String tipoInmuebleBuscado, String localidadBuscada, String barrios,
		String caracteristicasDeseadas)
    	*/
    	
    	if (NombreField.getText().equals("") || validar.esString(NombreField.getText()) != 1) {
    	    Alert alertaTipo = new Alert(Alert.AlertType.ERROR);
    	    alertaTipo.setTitle("Nombre inválido o vacío");
    	    alertaTipo.setContentText("El campo 'Nombre' es inválido o está vacío");
    	    alertaTipo.showAndWait();
    	} else if (ApellidoField.getText().equals("") || validar.esString(ApellidoField.getText()) != 1) {
    	    Alert alertaTipo = new Alert(Alert.AlertType.ERROR);
    	    alertaTipo.setTitle("Apellido inválido o vacío");
    	    alertaTipo.setContentText("El campo 'Apellido' es inválido o está vacío");
    	    alertaTipo.showAndWait();
    	} else if (numeroDocField.getText().equals("") || validar.esString(numeroDocField.getText()) != 1) {
    	    Alert alertaTipo = new Alert(Alert.AlertType.ERROR);
    	    alertaTipo.setTitle("Número de documento inválido o vacío");
    	    alertaTipo.setContentText("El campo 'Número de documento' es inválido o está vacío");
    	    alertaTipo.showAndWait();
    	} else if (TelefonoField.getText().equals("") || validar.esUnNumero(TelefonoField.getText()) != 1) {
    	    Alert alertaTipo = new Alert(Alert.AlertType.ERROR);
    	    alertaTipo.setTitle("Número de teléfono inválido o vacío");
    	    alertaTipo.setContentText("El campo 'Teléfono' debe ser de tipo numérico y no puede estar vacío");
    	    alertaTipo.showAndWait();
    	} else if (EmailField.getText().equals("") || validar.esString(EmailField.getText()) != 1) {
    	    Alert alertaTipo = new Alert(Alert.AlertType.ERROR);
    	    alertaTipo.setTitle("Email inválido o vacío");
    	    alertaTipo.setContentText("El campo 'Email' es inválido o está vacío");
    	    alertaTipo.showAndWait();
    	} else if (MontoUSDField.getText().equals("") || validar.esUnNumero(MontoUSDField.getText()) != 1) {
    	    Alert alertaTipo = new Alert(Alert.AlertType.ERROR);
    	    alertaTipo.setTitle("Monto en USD inválido o vacío");
    	    alertaTipo.setContentText("El campo 'Monto en USD' debe ser de tipo numérico y no puede estar vacío");
    	    alertaTipo.showAndWait();
    	} else if (barrioField.getText().equals("") || validar.esString(barrioField.getText()) != 1) {
    	    Alert alertaTipo = new Alert(Alert.AlertType.ERROR);
    	    alertaTipo.setTitle("Barrio inválido o vacío");
    	    alertaTipo.setContentText("El campo 'Barrio' es inválido o está vacío");
    	    alertaTipo.showAndWait();
    	} else if (caracteristicasTextArea.getText().equals("") || validar.esString(caracteristicasTextArea.getText()) != 1) {
    	    Alert alertaTipo = new Alert(Alert.AlertType.ERROR);
    	    alertaTipo.setTitle("Características inválidas o vacías");
    	    alertaTipo.setContentText("El campo 'Características' es inválido o está vacío");
    	    alertaTipo.showAndWait();
    	}else if(InmuebleMenu.getValue()==null) {
     		 Alert alertaTipo = new Alert(Alert.AlertType.ERROR);
        	    alertaTipo.setTitle("tipo de inmueble vacío");
        	    alertaTipo.setContentText("El campo 'tipo de inmueble' no puede estar vacío");
        	    alertaTipo.showAndWait();
       		
       	}else if(LocalidadMenu.getValue()==null) {
        		 Alert alertaTipo = new Alert(Alert.AlertType.ERROR);
         	    alertaTipo.setTitle("localidad vacía");
         	    alertaTipo.setContentText("El campo 'localidad' no puede estar vacío");
         	    alertaTipo.showAndWait();
        		
        	}else if(tipoDocMenu.getValue()==null) {
       		 Alert alertaTipo = new Alert(Alert.AlertType.ERROR);
          	    alertaTipo.setTitle("tipo de documento vacío");
          	    alertaTipo.setContentText("El campo 'Tipo de documento' no puede estar vacío");
          	    alertaTipo.showAndWait();
         		
         	}else {
    		
    	
    	
    	try {
			
    		ClienteDTO nuevoCliente = new ClienteDTO(idCliente,NombreField.getText(), ApellidoField.getText(), numeroDocField.getText(), tipoDocMenu.getValue(), Integer.parseInt(TelefonoField.getText()), 
        			EmailField.getText(), Double.parseDouble(MontoUSDField.getText()), InmuebleMenu.getValue(), LocalidadMenu.getValue(), barrioField.getText(), caracteristicasTextArea.getText());
    		
    		if(clienteServices.updateCliente(nuevoCliente)==1) {
    			Parent root;
        		root = FXMLLoader.load((getClass().getResource("/interfaces/ClientesPrincipalPrincipal.fxml")));
        		
        		Stage window = (Stage)CancelarButton.getScene().getWindow();
        		window.setTitle("Clientes");
        		window.setScene(new Scene(root));
    		}else {
    			Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
	    		alertaTipo.setTitle("Cliente inexistente"); //titulo
	    		alertaTipo.setContentText("Este cliente no existe "); //informacion
	    		alertaTipo.showAndWait();
    		}
			
			
    		
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	}
    }

}
