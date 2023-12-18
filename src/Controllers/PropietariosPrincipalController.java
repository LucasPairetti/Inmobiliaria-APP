package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.clases.TipoDNI;
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
    private TableColumn<InmuebleDTO, String> EstadoColumn;

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
    private Button AgregarInmuebleButton;

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
    
    private Validacion validar =Validacion.getInstance();
    
    @FXML
    private Button TestButton;
    
    ObservableList<InmuebleDTO> listaDeInmuebles= FXCollections.observableArrayList(); 
    ObservableList<PropietarioDTO> listaDePropietario= FXCollections.observableArrayList(); 
    
    private InmuebleServices inmuebleService = InmuebleServices.getInstance();
    private PropietarioServices propietarioService = PropietarioServices.getInstance();
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	
    	ObservableList<String> dnis= FXCollections.observableArrayList();
    			dnis.addAll(TipoDNI.getTiposDNI());
    	TipoDocMenu.setItems(dnis);
    	
    	NombreColumn.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
    	ApellidoColumn.setCellValueFactory(new PropertyValueFactory<>("Apellido"));
    	
     	BarrioColumn.setCellValueFactory(new PropertyValueFactory<>("barrio"));
    	DormitoriosColumn.setCellValueFactory(new PropertyValueFactory<>("dormitorios"));
    	LocalidadColumn.setCellValueFactory(new PropertyValueFactory<>("localidad"));
    	PrecioColumn.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
    	ProvinciaColumn.setCellValueFactory(new PropertyValueFactory<>("provincia"));
    	TipoColumn.setCellValueFactory(new PropertyValueFactory<>("tipoInmueble"));
    	EstadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado")); 
    	
    	listaDePropietario.addAll(propietarioService.listPropietarios());
    	propietariosTable.setItems(listaDePropietario);
    	
    	
	}

    @FXML
    void AgergarInmueblePressed(ActionEvent event) {
    	if(propietariosTable.getSelectionModel().getSelectedItem()!=null) {
    		int idPropietario= propietariosTable.getSelectionModel().getSelectedItem().getId();
    	

    		try {
    			
    			Holder holder = Holder.getInstance();
    			holder.setIdPropietario(idPropietario);
        		Parent root;
        		root = FXMLLoader.load((getClass().getResource("/interfaces/CargarInmueble.fxml")));
        		
        		Stage window = (Stage)ModificarInmuebleButton.getScene().getWindow();
        		window.setTitle("Propietarios");
        		window.setScene(new Scene(root));
        	} catch (IOException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
    	}else {
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Propietario"); //titulo
    		alertaTipo.setContentText("Debe seleccionar un propietario de la tabla antes de agregar un inmueble"); //informacion
    		alertaTipo.showAndWait();
    	}
    	
    }

    @FXML
    void AgregarPropietarioPressed(ActionEvent event) {
    	try {
    		Parent root;
    		root = FXMLLoader.load((getClass().getResource("/interfaces/NuevoPropietario.fxml")));
    		
    		Stage window = (Stage)VolverButton.getScene().getWindow();
    		window.setTitle("Agregar Propietario");
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
    		alertaTipo.showAndWait();
    		
    		return;
    	}else if(ApellidoField.getText()!="" || validar.esString(ApellidoField.getText())!=1) {
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Campo apellido incompleto"); //titulo
    		alertaTipo.setContentText("Debe completarse el campo apellido para buscar un propietario"); //informacion
    		alertaTipo.showAndWait();
    		
    		
    	}else if(NumeroDocField.getText()!="" || validar.esUnNumero(NumeroDocField.getText())!=1) {
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Campo DNI incompleto"); //titulo
    		alertaTipo.setContentText("Debe completarse el campo apellido para buscar un propietario con un formato numerico"); //informacion
    		alertaTipo.showAndWait();
    		
    		
    	}else {
    		
    		listaDePropietario= (ObservableList<PropietarioDTO>) propietarioService.getPropietario(NombreField.getText(), ApellidoField.getText(), TipoDocMenu.getValue(), NumeroDocField.getText());
    		propietariosTable.setItems(listaDePropietario);
    		
    	}
    	
    	

    }
    
 
    
    @FXML
    void PropietarioSeleccionado(MouseEvent event) {
    	if(propietariosTable.getSelectionModel().getSelectedItem()!=null) {
    	int idPropietario= propietariosTable.getSelectionModel().getSelectedItem().getId();
    	listaDeInmuebles.clear();
    	listaDeInmuebles.addAll(inmuebleService.getInmueblesByPropietario(idPropietario));
    	InmuebleTable.setItems(listaDeInmuebles);
    	}

    }

    @FXML
    void EliminarInmueblePressed(ActionEvent event) {
    	InmuebleDTO Inmueble= InmuebleTable.getSelectionModel().getSelectedItem();
    	if(Inmueble!=null) {
    		inmuebleService.deleteInmueble(Inmueble.getId());
    	
    	listaDeInmuebles.remove(Inmueble);
    	InmuebleTable.setItems(listaDeInmuebles);
    	}else {
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Inmueble"); //titulo
    		alertaTipo.setContentText("Debe seleccionar un inmueble de la tabla antes de eliminarlo"); //informacion
    		alertaTipo.showAndWait();
    	}
    }

    @FXML
    void EliminarPropietarioPressed(ActionEvent event) {
    	PropietarioDTO prop= propietariosTable.getSelectionModel().getSelectedItem();
    	if(prop!=null) {
    	propietarioService.deletePropietario(prop.getId());
    	
    	listaDePropietario.remove(prop);
    	propietariosTable.setItems(listaDePropietario);
    	}else {
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Propietario"); //titulo
    		alertaTipo.setContentText("Debe seleccionar un propietario de la tabla antes de eliminarlo"); //informacion
    		alertaTipo.showAndWait();
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
    	if(InmuebleTable.getSelectionModel().getSelectedItem()!=null) {
    		int idPropietario= InmuebleTable.getSelectionModel().getSelectedItem().getIdPropietario();
    		int idInmueble = InmuebleTable.getSelectionModel().getSelectedItem().getId();
    	
    		try {
    			
    			Holder holder = Holder.getInstance();
    			holder.setIdInmueble(idInmueble);
    			holder.setIdPropietario(idPropietario);
        		Parent root;
        		root = FXMLLoader.load((getClass().getResource("/interfaces/ModificarInmueble.fxml")));
        		
        		Stage window = (Stage)ModificarInmuebleButton.getScene().getWindow();
        		window.setTitle("Propietarios");
        		window.setScene(new Scene(root));
        	} catch (IOException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
    	}else {
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Inmueble"); //titulo
    		alertaTipo.setContentText("Debe seleccionar un inmueble de la tabla antes de modificarlo"); //informacion
    		alertaTipo.showAndWait();
    	}
    }

    @FXML
    void ModificarPropietarioPressed(ActionEvent event) {
    	if(propietariosTable.getSelectionModel().getSelectedItem()!=null) {
    		
    		
    		
    	
    		try {
    			int idPropietario= propietariosTable.getSelectionModel().getSelectedItem().getId();
    			Holder holder = Holder.getInstance();
    			
    			holder.setIdPropietario(idPropietario);
        		Parent root;
        		root = FXMLLoader.load((getClass().getResource("/interfaces/ModificarPropietario.fxml")));
        		
        		Stage window = (Stage)ModificarInmuebleButton.getScene().getWindow();
        		window.setTitle("Propietarios");
        		window.setScene(new Scene(root));
        	} catch (IOException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
    	}else {
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Propietario"); //titulo
    		alertaTipo.setContentText("Debe seleccionar un Propietario de la tabla antes de modificarlo"); //informacion
    		alertaTipo.showAndWait();
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
