package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dto.ClienteDTO;
import dto.InmuebleDTO;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ClienteServices;
import services.InmuebleServices;

public class ClienteInmueblesController implements Initializable {

    @FXML
    private TableColumn<InmuebleDTO, String> BarrioColumn;

    @FXML
    private TableColumn<InmuebleDTO, String> CalleColumn;

    @FXML
    private Button ComprarButton;

    @FXML
    private TableColumn<InmuebleDTO, Integer> DormitoriosColumn;

    @FXML
    private TableColumn<InmuebleDTO, String> EstadoColumn;

    @FXML
    private TableView<InmuebleDTO> InmuebleTable;

    @FXML
    private TableColumn<InmuebleDTO, String> LocalidadColumn;

    @FXML
    private TableColumn<InmuebleDTO, Integer> NumeroColumn;

    @FXML
    private TableColumn<InmuebleDTO, Double> PrecioColumn;

    @FXML
    private TableColumn<InmuebleDTO,String> PropietarioColumn;

    @FXML
    private TableColumn<InmuebleDTO, String> ProvinciaColumn;

    @FXML
    private Button ReservarButton;

    @FXML
    private Button VerMasButton;

    @FXML
    private Button VolverButton;

    private InmuebleServices inmuebleService = InmuebleServices.getInstance();

    ObservableList<InmuebleDTO> listaDeInmuebles= FXCollections.observableArrayList();

	private int idCliente; 
	private ClienteServices clienteService = ClienteServices.getInstance();
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	Holder holder = Holder.getInstance();
    	idCliente = holder.getIdCliente();
    	
    	
    	//falta columna  propietario para eso pedir que me pasen "nombre Apellido" entonces agreguen 1 atributo "nombreApellido" y que el string que manden tenga un espacio entre el nombre y apellido
    	PropietarioColumn.setCellValueFactory(new PropertyValueFactory<>("nombreApellidoPropietario"));
    	BarrioColumn.setCellValueFactory(new PropertyValueFactory<>("barrio"));
    	DormitoriosColumn.setCellValueFactory(new PropertyValueFactory<>("dormitorios"));
    	LocalidadColumn.setCellValueFactory(new PropertyValueFactory<>("localidad"));
    	PrecioColumn.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
    	ProvinciaColumn.setCellValueFactory(new PropertyValueFactory<>("provincia"));
    	CalleColumn.setCellValueFactory(new PropertyValueFactory<>("calle"));
    	NumeroColumn.setCellValueFactory(new PropertyValueFactory<>("numero"));
    	EstadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));  
    	
    	
    	ClienteDTO cliente= clienteService.getClienteById(idCliente);
    	listaDeInmuebles.addAll(inmuebleService.listInmueblesFiltradosParaVenta(cliente));
    	InmuebleTable.setItems(listaDeInmuebles);
		
	}
    public void setIdCliente(int id) {
    	idCliente=id;
    }
    
    
    @FXML
    void ComprarPressed(ActionEvent event) {
    	if(InmuebleTable.getSelectionModel().getSelectedItem()==null) {
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Venta"); //titulo
    		alertaTipo.setContentText("Debe seleccionar un inmueble de la tabla antes de realizar una venta"); //informacion
    		alertaTipo.showAndWait();
    	}else {
    		Holder holder = Holder.getInstance();
        	holder.setIdCliente(idCliente);
        	holder.setIdVendedor(holder.getIdUsuario());
        	
        	holder.setIdInmueble(InmuebleTable.getSelectionModel().getSelectedItem().getId());
        	try {
        		Parent root;
        		root = FXMLLoader.load((getClass().getResource("/interfaces/VentaPrincipal.fxml")));
        		
        		Stage window = (Stage)ComprarButton.getScene().getWindow();
        		window.setTitle("Venta");
        		window.setScene(new Scene(root));
        	} catch (IOException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
        }
    	}
    

    @FXML
    void ReservarPressed(ActionEvent event) {
    	if(InmuebleTable.getSelectionModel().getSelectedItem()==null) {
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Reserva"); //titulo
    		alertaTipo.setContentText("Debe seleccionar un inmueble de la tabla antes de realizar una Reserva"); //informacion
    		alertaTipo.showAndWait();
    	}else {
    		Holder holder = Holder.getInstance();
        	holder.setIdCliente(idCliente);
        	
        	holder.setIdInmueble(InmuebleTable.getSelectionModel().getSelectedItem().getId());
        	try {
        		Parent root;
        		root = FXMLLoader.load((getClass().getResource("/interfaces/ReservaInmueblePrincipal.fxml")));
        		
        		Stage window = (Stage)ReservarButton.getScene().getWindow();
        		window.setTitle("Reserva");
        		window.setScene(new Scene(root));
        	} catch (IOException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
        }
    }

    @FXML
    void VerMasPressed(ActionEvent event) {
    	if(InmuebleTable.getSelectionModel().getSelectedItem()==null) {
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Inmueble"); //titulo
    		alertaTipo.setContentText("Debe seleccionar un Inmueble para ver sus detalles"); //informacion
    		alertaTipo.showAndWait();
    	}else {
    		
    		
    	
    		try {
    			int idInmueble= InmuebleTable.getSelectionModel().getSelectedItem().getId();
    			Holder holder = Holder.getInstance();
    			holder.setIdInmueble(idInmueble);
    			holder.setPantalla(1);
        		Parent root;
        		root = FXMLLoader.load((getClass().getResource("/interfaces/VerMasInmueble.fxml")));
        		
        		Stage window = (Stage)VolverButton.getScene().getWindow();
        		window.setTitle("Detalles Inmueble");
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
    		root = FXMLLoader.load((getClass().getResource("/interfaces/ClientesPrincipal.fxml")));
    		
    		Stage window = (Stage)VolverButton.getScene().getWindow();
    		window.setTitle("Clientes");
    		window.setScene(new Scene(root));
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

	

}
