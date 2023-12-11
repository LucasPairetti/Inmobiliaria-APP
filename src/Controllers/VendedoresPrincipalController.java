package Controllers;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    
    private VendedorServices vendedorServices= VendedorServices.getInstance();
    private ObservableList<VendedorDTO> listaVendedores =FXCollections.observableArrayList();
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	ObservableList<String> TipoDocumentos=  FXCollections.observableArrayList();
    	TipoDocumentos.addAll(TipoDNI.getTiposDNI());
    	TipoDocMenu.setItems(TipoDocumentos);
    	//listaVendedores.addAll(vendedorServices.listVendedores());
    	//VendedoresTable.setItems(listaVendedores);
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

    }

    @FXML
    void EliminarPressed(ActionEvent event) {

    }

    @FXML
    void LimpiarPressed(ActionEvent event) {

    }

    @FXML
    void ModificarPressed(ActionEvent event) {

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
