package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dto.InmuebleDTO;
import javafx.scene.input.MouseEvent;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.InmuebleServices;
import services.PropietarioServices;

public class PropietariosPrincipalController implements Initializable {

    @FXML
    private Button AgregarPropietarioButton;

    @FXML
    private TableColumn<PropietarioDTO, String> ApellidoColumn;

    @FXML
    private TextField ApellidoField;

    @FXML
    private TableColumn<InmuebleDTO, String> BarrioColumn;

    @FXML
    private Button BuscarButton;

    @FXML
    private TableView<PropietarioDTO> propietariosTable;

    @FXML
    private TableColumn<InmuebleDTO, Integer> DormitoriosColumn;

    @FXML
    private Button EliminarInmuebleButton;

    @FXML
    private Button EliminarPropietarioButton;

    @FXML
    private TableView<InmuebleDTO> InmuebleTable;

    @FXML
    private Button LimpiarButton;

    @FXML
    private TableColumn<InmuebleDTO, String> LocalidadColumn;

    @FXML
    private Button ModificarButton11;

    @FXML
    private Button ModificarInmuebleButton;

    @FXML
    private Button ModificarPropietarioButton;

    @FXML
    private TableColumn<PropietarioDTO, String> NombreColumn;

    @FXML
    private TextField NombreField;

    @FXML
    private TextField NumeroDocField;

    @FXML
    private TableColumn<InmuebleDTO, Double> PrecioColumn;

    @FXML
    private TableColumn<InmuebleDTO, String> ProvinciaColumn;

    @FXML
    private TableColumn<InmuebleDTO, String> TipoColumn;

    @FXML
    private ComboBox<String> TipoDocMenu;

    @FXML
    private Button VolverButton;
    
    private Validacion validar;
    
    ObservableList<InmuebleDTO> listaDeInmuebles= FXCollections.observableArrayList(); 
    ObservableList<PropietarioDTO> listaDePropietario= FXCollections.observableArrayList(); 
    
    private InmuebleServices inmuebleService = InmuebleServices.getInstance();
    private PropietarioServices propietarioService = PropietarioServices.getInstance();
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	
    	NombreColumn.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
    	ApellidoColumn.setCellValueFactory(new PropertyValueFactory<>("Apellido"));
    	
     	BarrioColumn.setCellValueFactory(new PropertyValueFactory<>("barrio"));
    	DormitoriosColumn.setCellValueFactory(new PropertyValueFactory<>("dormitorios"));
    	LocalidadColumn.setCellValueFactory(new PropertyValueFactory<>("localidad"));
    	PrecioColumn.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
    	ProvinciaColumn.setCellValueFactory(new PropertyValueFactory<>("provincia"));
    	TipoColumn.setCellValueFactory(new PropertyValueFactory<>("tipoInmueble"));
		
    	
    	listaDePropietario.addAll(propietarioService.listPropietarios());
    	propietariosTable.setItems(listaDePropietario);
    	
    	
	}

    @FXML
    void AgergarInmueblePressed(ActionEvent event) {

    }

    @FXML
    void AgregarPropietarioPressed(ActionEvent event) {
    	try {
    		Parent root;
    		root = FXMLLoader.load((getClass().getResource("/interfaces/NuevoPropietario.fxml")));
    		
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
    	
    	if(NombreField.getText()!="" || validar.esString(NombreField.getText())!=1) {
    		
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Campo nombre incompleto"); //titulo
    		alertaTipo.setContentText("Debe completarse el campo nombre para buscar un propietario"); //informacion
    		
    		return;
    	}else if(ApellidoField.getText()!="" || validar.esString(ApellidoField.getText())!=1) {
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Campo apellido incompleto"); //titulo
    		alertaTipo.setContentText("Debe completarse el campo apellido para buscar un propietario"); //informacion
    		
    		
    	}else if(NumeroDocField.getText()!="" || validar.esUnNumero(NumeroDocField.getText())!=1) {
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Campo DNI incompleto"); //titulo
    		alertaTipo.setContentText("Debe completarse el campo apellido para buscar un propietario con un formato numerico"); //informacion
    		
    		
    	}else {
    		
    		listaDePropietario= (ObservableList<PropietarioDTO>) propietarioService.getPropietario(NombreField.getText(), ApellidoField.getText(), TipoDocMenu.getValue(), NumeroDocField.getText());
    		propietariosTable.setItems(listaDePropietario);
    		
    	}
    	
    	

    }
    
    
    @FXML
    void PropietarioSeleccionado(MouseEvent event) {
    	int idPropietario= propietariosTable.getSelectionModel().getSelectedItem().getId();
    	listaDeInmuebles= (ObservableList<InmuebleDTO>) inmuebleService.getInmueblesByPropietario(idPropietario);
    	InmuebleTable.setItems(listaDeInmuebles);
    	

    }

    @FXML
    void EliminarInmueblePressed(ActionEvent event) {
    	InmuebleDTO Inmueble= InmuebleTable.getSelectionModel().getSelectedItem();
    	if(Inmueble!=null) {
    	propietarioService.deletePropietario(Inmueble.getId());
    	
    	listaDeInmuebles.remove(Inmueble);
    	InmuebleTable.setItems(listaDeInmuebles);
    	}
    }

    @FXML
    void EliminarPropietarioPressed(ActionEvent event) {
    	PropietarioDTO prop= propietariosTable.getSelectionModel().getSelectedItem();
    	if(prop!=null) {
    	propietarioService.deletePropietario(prop.getId());
    	
    	listaDePropietario.remove(prop);
    	propietariosTable.setItems(listaDePropietario);
    	}
    	
    }

    @FXML
    void LimpiarPressed(ActionEvent event) {

    	ApellidoField.setText("");
    	NombreField.setText("");
    	NumeroDocField.setText("");
    	listaDePropietario.addAll(propietarioService.listPropietarios());
    	propietariosTable.setItems(listaDePropietario);
    }

    @FXML
    void ModificarInmueblePressed(ActionEvent event) {

    }

    @FXML
    void ModificarPropietarioPressed(ActionEvent event) {

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
