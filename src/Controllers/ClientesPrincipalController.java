package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.clases.TipoDNI;
import dto.ClienteDTO;
import dto.InmuebleDTO;
import dto.PropietarioDTO;
import javafx.scene.control.ComboBox;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ClienteServices;

public class ClientesPrincipalController implements Initializable {

    @FXML
    private Button AgregarButton;

    @FXML
    private TableColumn<ClienteDTO, String> ApellidoColumn;

    @FXML
    private TextField ApellidoField;
    
    @FXML
    private TextField DocumentoField;

    @FXML
    private ComboBox<String> TipoDocMenu;
    
    @FXML
    private Button BuscarButton;

    @FXML
    private TableView<ClienteDTO> ClientesTable;

    @FXML
    private Button EliminarButton;

    @FXML
    private Button LimpiarButton;

    @FXML
    private Button ModificarButton;

    @FXML
    private TableColumn<ClienteDTO, String> NombreColumn;

    @FXML
    private TextField NombreField;
    
    @FXML
    private Button InmueblesDisponiblesButton;

    @FXML
    private Button VolverButton;
    
    private Validacion validar;
    private ObservableList<ClienteDTO> listaDeClientes= FXCollections.observableArrayList(); 
    private ClienteServices clientesService= ClienteServices.getInstance();
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	ObservableList<String> dnis= FXCollections.observableArrayList();
    			
    			dnis.addAll(TipoDNI.getTiposDNI());
    	TipoDocMenu.setItems(dnis);
    	
    	NombreColumn.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
    	ApellidoColumn.setCellValueFactory(new PropertyValueFactory<>("Apellido"));
    	listaDeClientes.addAll(clientesService.listClientes());
    	ClientesTable.setItems(listaDeClientes);
    	
	}
    
    
    
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
    	//getClientes(String tipoDNI,String dni,String nombre, String apellido)
    	
if(validar.esUnDNI(DocumentoField.getText())==1 ||DocumentoField.getText()=="" ) {
	Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
	alertaTipo.setTitle("DNI invalido"); //titulo
	alertaTipo.setContentText("El DNI indicado es invalido para la busqueda"); //informacion
}else if(validar.esString(NombreField.getText())==1 ||NombreField.getText()=="") {
	Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
	alertaTipo.setTitle("Nombre invalido"); //titulo
	alertaTipo.setContentText("debe completar el campo 'nombre' para la busqueda"); //informacion
}else if(validar.esString(ApellidoField.getText())==1 ||ApellidoField.getText()=="") {
	Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
	alertaTipo.setTitle("Apellido invalido"); //titulo
	alertaTipo.setContentText("debe completar el campo 'Apellido' para la busqueda"); //informacion
}
else {
    	listaDeClientes= (ObservableList<ClienteDTO>) clientesService.getClientes(NombreField.getText(), ApellidoField.getText(), TipoDocMenu.getValue(), DocumentoField.getText());
    	ClientesTable.setItems(listaDeClientes);
}
    	//ClientesTable.setItems(listaDeClientes);
    }

    @FXML
    void EliminarPressed(ActionEvent event) {
    	ClienteDTO Cliente= ClientesTable.getSelectionModel().getSelectedItem();
    	if(Cliente!=null) {
    		clientesService.deleteCliente(Cliente.getId());
    	
    		listaDeClientes.remove(Cliente);
    		ClientesTable.setItems(listaDeClientes);
    	}else {
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Propietario"); //titulo
    		alertaTipo.setContentText("Debe seleccionar un Propietario de la tabla antes de eliminarlo"); //informacion
    	}
    }
    

    @FXML
    void LimpiarPressed(ActionEvent event) {

    	//texto
    	NombreField.setText("");
    	ApellidoField.setText("");
    	DocumentoField.setText("");
    	
    	listaDeClientes.addAll(clientesService.listClientes());
    	ClientesTable.setItems(listaDeClientes);
    }

    @FXML
    void ModificarPressed(ActionEvent event) {
    	if(ClientesTable.getSelectionModel().getSelectedItem()==null) {
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Cliente"); //titulo
    		alertaTipo.setContentText("Debe seleccionar un cliente de la tabla antes de modificarlo"); //informacion
    		
    	}else {
    		
    		
    	/*
    	try {
    		
    		Parent root;
    	// root = FXMLLoader.load((getClass().getResource("/interfaces/NuevoPropietario.fxml")));
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/ModificarCliente.fxml"));
    		root = loader.load();
    		ModificarClienteController controladorModificarCliente = loader.getController();
    		controladorModificarCliente.setCliente(idCliente);
    		
    		Stage window = (Stage)ModificarButton.getScene().getWindow();
    		window.setTitle("Modificar Cliente");
    		window.setScene(new Scene(root));
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	*/
    		
			
    		try {
    			
    			
    			int idCliente= ClientesTable.getSelectionModel().getSelectedItem().getId();
    				
    			Parent root;
        	
    			Holder holder= Holder.getInstance();
        
        		holder.setIdCliente(idCliente);
        
        		
        		root = FXMLLoader.load((getClass().getResource("/interfaces/ModificarCliente.fxml")));
        		
        		Stage window = (Stage)VolverButton.getScene().getWindow();
        		window.setTitle("Propietarios");
        		window.setScene(new Scene(root));
        		
        	} catch (IOException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
    		
    		
    	}
    	

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
    
    @FXML
    void InmueblesDisponiblesPressed(ActionEvent event) {

    }

	

}
