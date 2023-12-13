package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.clases.TipoDNI;
import dto.ClienteDTO;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.VendedorServices;

public class VendedoresPrincipalController implements Initializable{

    @FXML
    private Button AgregarButton;

    @FXML
    private TableColumn<VendedorDTO, String> ApellidoColumn;

    @FXML
    private TextField ApellidoField;

    @FXML
    private Button BuscarButton;

    @FXML
    private TextField DocumentoField;

    @FXML
    private Button EliminarButton;

    @FXML
    private Button LimpiarButton;

    @FXML
    private Button ModificarButton;

    @FXML
    private TableColumn<VendedorDTO,String> NombreColumn;

    @FXML
    private TextField NombreField;

    @FXML
    private ComboBox<String> TipoDocMenu;

    @FXML
    private TableView<VendedorDTO> VendedoresTable;

    @FXML
    private Button VolverButton;
    
    private Validacion validar =Validacion.getInstance();
    private VendedorServices vendedorServices= VendedorServices.getInstance();
    private ObservableList<VendedorDTO> listaVendedores =FXCollections.observableArrayList();
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	
    	NombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	ApellidoColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));
    	
    	ObservableList<String> TipoDocumentos=  FXCollections.observableArrayList();
    	TipoDocumentos.addAll(TipoDNI.getTiposDNI());
    	TipoDocMenu.setItems(TipoDocumentos);
    	
    	listaVendedores.addAll(vendedorServices.listVendedores());
    	VendedoresTable.setItems(listaVendedores);
    	
	}

    @FXML
    void AgregarPressed(ActionEvent event) {
    	try {
    		Parent root;
    		root = FXMLLoader.load((getClass().getResource("/interfaces/NuevoVendedor.fxml")));
    		
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
    	
    	if(validar.esUnDNI(DocumentoField.getText())==1 ||DocumentoField.getText()=="" ) {
	Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
	alertaTipo.setTitle("DNI invalido"); //titulo
	alertaTipo.setContentText("El DNI indicado es invalido para la busqueda"); //informacion
	alertaTipo.showAndWait();
    	}else if(validar.esString(NombreField.getText())==1 ||NombreField.getText()=="") {
	Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
	alertaTipo.setTitle("Nombre invalido"); //titulo
	alertaTipo.setContentText("debe completar el campo 'nombre' para la busqueda"); //informacion
	alertaTipo.showAndWait();
    	}else if(validar.esString(ApellidoField.getText())==1 ||ApellidoField.getText()=="") {
	Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
	alertaTipo.setTitle("Apellido invalido"); //titulo
	alertaTipo.setContentText("debe completar el campo 'Apellido' para la busqueda"); //informacion
	alertaTipo.showAndWait();
    	}
    	else {
    		
//agregar get vendedores
    		//VendedoresTable.setItems(listaVendedores);
}
    	//ClientesTable.setItems(listaDeClientes);
    }
    

    @FXML
    void EliminarPressed(ActionEvent event) {
    	VendedorDTO vendedor= VendedoresTable.getSelectionModel().getSelectedItem();
    	if(vendedor!=null) {
    		vendedorServices.deleteVendedor(vendedor.getId());
    	
    		listaVendedores.remove(vendedor);
    		VendedoresTable.setItems(listaVendedores);
    	}else {
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Vendedores"); //titulo
    		alertaTipo.setContentText("Debe seleccionar un vendedor de la tabla antes de eliminarlo"); //informacion
    		alertaTipo.showAndWait();
    	}
    }
    	
    

    @FXML
    void LimpiarPressed(ActionEvent event) {
    	NombreField.setText("");
    	ApellidoField.setText("");
    	DocumentoField.setText("");
    	
    	listaVendedores.addAll(vendedorServices.listVendedores());
    	VendedoresTable.setItems(listaVendedores);
    }

    @FXML
    void ModificarPressed(ActionEvent event) {
    	if(VendedoresTable.getSelectionModel().getSelectedItem()==null) {
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Vendedor"); //titulo
    		alertaTipo.setContentText("Debe seleccionar un vendedor de la tabla antes de modificarlo"); //informacion
    		alertaTipo.showAndWait();
    		
    	}else {
    		
    		try {
    			
    			
    			int idVendedor= VendedoresTable.getSelectionModel().getSelectedItem().getId();
    				
    			Parent root;
        	
    			Holder holder= Holder.getInstance();
        
        		holder.setIdVendedor(idVendedor);
        
        		
        		root = FXMLLoader.load((getClass().getResource("/interfaces/ModificarVendedor.fxml")));
        		
        		Stage window = (Stage)VolverButton.getScene().getWindow();
        		window.setTitle("Vendedores");
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

	

}
