package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import application.clases.TipoDNI;
import dto.VendedorDTO;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.VendedorServices;

public class ModificarVendedorController implements Initializable {

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
    private Validacion validar;
    private VendedorServices vendedorServices= VendedorServices.getInstance();
    private int idVendedor;
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	
    	Holder holder = Holder.getInstance();
    	idVendedor = holder.getIdVendedor();
    	VendedorDTO vendedor= vendedorServices.getVendedorById(idVendedor);
    	ObservableList<String> TipoDocumentos=  FXCollections.observableArrayList();
    	TipoDocumentos.addAll(TipoDNI.getTiposDNI());
    	tipoDocMenu.setItems(TipoDocumentos);
    	
    	NombreField.setText(vendedor.getNombre());
    	ApellidoField.setText(vendedor.getApellido());
    	ClaveField.setText(vendedor.getClave());
    	EmailField.setText(vendedor.getEmail());
    	CalleField.setText(vendedor.getCalle());
    	NumeroDomicilioField.setText(String.valueOf(vendedor.getNumero())); 
    	SueldoField.setText(String.valueOf(vendedor.getSueldo())); 
    	TelefonoField.setText(String.valueOf(vendedor.getTelefono())); 
    	numeroDocField.setText(vendedor.getDni());
    	FechaNacimientoCalendar.setValue(vendedor.getFechaNacimiento().toLocalDate());
    	
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
    	}else if (CalleField.getText().equals("") || validar.esString(CalleField.getText()) != 1) {
    	    Alert alertaTipo = new Alert(Alert.AlertType.ERROR);
    	    alertaTipo.setTitle("Calle inválida o vacío");
    	    alertaTipo.setContentText("El campo 'calle' es inválido o está vacío");
    	    alertaTipo.showAndWait();
    	} else if (SueldoField.getText().equals("") || validar.esUnNumero(SueldoField.getText()) != 1) {
    	    Alert alertaTipo = new Alert(Alert.AlertType.ERROR);
    	    alertaTipo.setTitle("Monto en USD inválido o vacío");
    	    alertaTipo.setContentText("El campo 'Monto en USD' debe ser de tipo numérico y no puede estar vacío");
    	    alertaTipo.showAndWait();
    	} else if (NumeroDomicilioField.getText().equals("") || validar.esUnNumero(NumeroDomicilioField.getText()) != 1) {
    	    Alert alertaTipo = new Alert(Alert.AlertType.ERROR);
    	    alertaTipo.setTitle("Numero domicilio inválido o vacío");
    	    alertaTipo.setContentText("El campo 'Numero de domicilio' debe ser de tipo numérico y no puede estar vacío");
    	    alertaTipo.showAndWait();
    	}else if(FechaNacimientoCalendar.getValue()==null) {
    		 Alert alertaTipo = new Alert(Alert.AlertType.ERROR);
     	    alertaTipo.setTitle("Fecha de nacimiento vacía");
     	    alertaTipo.setContentText("El campo 'fecha de nacimiento' no puede estar vacío");
     	    alertaTipo.showAndWait();
    		
    	}
    	 else if (ClaveField.getText().equals("") || validar.esString(ClaveField.getText()) != 1) {
     	    Alert alertaTipo = new Alert(Alert.AlertType.ERROR);
     	    alertaTipo.setTitle("Clave inválida o vacío");
     	    alertaTipo.setContentText("El campo 'Clave' es inválido o está vacío");
     	    alertaTipo.showAndWait();
     	}else {
     		/*
     		 * public VendedorDTO(int id,String nombre, String apellido, String tipodni, String dni, String calle, int numero,
			String localidad, String provincia, int telefono, String email, Date fechaNacimiento, double sueldo,
			String clave) 
     		 * 
     		 */
     		
     		  Date date = java.sql.Date.valueOf(FechaNacimientoCalendar.getValue());
     		VendedorDTO nuevoVendedor= new VendedorDTO(idVendedor,NombreField.getText(), ApellidoField.getText(), tipoDocMenu.getValue(), numeroDocField.getText(), CalleField.getText(),
     				Integer.parseInt(NumeroDomicilioField.getText()), LocalidadMenu.getValue(), ProvinciaMenu.getValue(), Integer.parseInt(TelefonoField.getText()),
     				EmailField.getText(), date, Double.parseDouble(SueldoField.getText()), ClaveField.getText());
     		if(vendedorServices.updateVendedor(nuevoVendedor)==1) {
     			try {
            		Parent root;
            		root = FXMLLoader.load((getClass().getResource("/interfaces/VendedoresPrincipal.fxml")));
            		
            		Stage window = (Stage)GuardarBUtton.getScene().getWindow();
            		window.setTitle("Vendedores");
            		window.setScene(new Scene(root));
            	} catch (IOException e) {
            		// TODO Auto-generated catch block
            		e.printStackTrace();
            	}
     		}else {
     			Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
	    		alertaTipo.setTitle("Vendedor inexistente"); //titulo
	    		alertaTipo.setContentText("Este Vendedor no existe "); //informacion
	    		alertaTipo.showAndWait();
     		}
     		
     	
     		
     		
     		
     	}
    }

	

}
