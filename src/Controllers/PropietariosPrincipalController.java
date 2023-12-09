package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.clases.Localidad;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PropietariosPrincipalController implements Initializable {

    @FXML
    private Button AgregarPropietarioButton;

    @FXML
    private TableColumn<?, ?> ApellidoColumn;

    @FXML
    private TextField ApellidoField;

    @FXML
    private TableColumn<?, ?> BarrioColumn;

    @FXML
    private Button BuscarButton;

    @FXML
    private TableView<?> ClientesTable;

    @FXML
    private TableColumn<?, ?> DormitoriosColumn;

    @FXML
    private Button EliminarInmuebleButton;

    @FXML
    private Button EliminarPropietarioButton;

    @FXML
    private TableView<?> InmuebleTable;

    @FXML
    private Button LimpiarButton;

    @FXML
    private TableColumn<?, ?> LocalidadColumn;

    @FXML
    private Button ModificarButton11;

    @FXML
    private Button ModificarInmuebleButton;

    @FXML
    private Button ModificarPropietarioButton;

    @FXML
    private TableColumn<?, ?> NombreColumn;

    @FXML
    private TextField NombreField;

    @FXML
    private TextField NumeroDocField;

    @FXML
    private TableColumn<?, ?> PrecioColumn;

    @FXML
    private TableColumn<?, ?> ProvinciaColumn;

    @FXML
    private TableColumn<?, ?> TipoColumn;

    @FXML
    private ComboBox<?> TipoDocMenu;

    @FXML
    private Button VolverButton;
    
    
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
    	
    	//inicializar todos los combobox
    	
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

    }

    @FXML
    void EliminarInmueblePressed(ActionEvent event) {

    }

    @FXML
    void EliminarPropietarioPressed(ActionEvent event) {

    }

    @FXML
    void LimpiarPressed(ActionEvent event) {

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
