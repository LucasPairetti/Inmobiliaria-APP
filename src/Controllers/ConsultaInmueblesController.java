package Controllers;
import application.clases.*;
import dto.InmuebleDTO;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.InmuebleServices;

public class ConsultaInmueblesController implements Initializable {


    @FXML
    private TableColumn<InmuebleDTO, String> BarrioColumn; //entidad, tipo de atributo a mostrar (usamos solo integer, floats, doubles o strings)

    @FXML
    private TextField BarrioTextField; 

    @FXML
    private TableColumn<InmuebleDTO, String> EstadoColumn;
    @FXML
    private Button BuscarButton;

    @FXML
    private CheckBox CasaCheckBox;

    @FXML
    private CheckBox DepartamentoCheckBox;

    @FXML
    private Label DormitorioLabel;

    @FXML
    private Slider DormitorioSlider;

    @FXML
    private TableColumn<InmuebleDTO, Integer> DormitoriosColumn;

    @FXML
    private CheckBox GalponCheckBox;
    
    @FXML
    private Button VerMasButton;
    

    @FXML
    private TableView<InmuebleDTO> InmuebleTable;

    @FXML
    private Button LimpiarButton;

    @FXML
    private CheckBox LocalCheckBox;

    @FXML
    private TableColumn<InmuebleDTO, String> LocalidadColumn;

    @FXML
    private ComboBox<String> LocalidadMenu; //estos son las listas desplegables, las hacemos strings		

    @FXML
    private Label MaxPriceLabel;

    @FXML
    private Slider MaxPriceSlider;

    @FXML
    private Label MinPriceLabel;

    @FXML
    private Slider MinPriceSlider;

    @FXML
    private CheckBox OficinaCheckBox;

    @FXML
    private TableColumn<InmuebleDTO, Double> PrecioColumn;

    @FXML
    private TableColumn<InmuebleDTO, String> ProvinciaColumn;

    @FXML
    private ComboBox<String> ProvinciaMenu;

    @FXML
    private CheckBox QuintaCheckBox;

    @FXML
    private CheckBox TerrenoCheckBox;

    @FXML
    private TableColumn<InmuebleDTO, TipoInmueble> TipoColumn;

    @FXML
    private Button VolverButton;
    
    
    private InmuebleServices inmuebleService = InmuebleServices.getInstance();

    ObservableList<InmuebleDTO> listaDeInmuebles= FXCollections.observableArrayList(); 
    //para redondeos
    DecimalFormat df = new DecimalFormat("###.##");
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Este metodo inicializa los datos en pantalla, en caso de los inmuebles carga la lista de inmuebles
    	
    	//primero indicar en cada columna-> el atributo que le corresponde en la entidad, usamos las clases DTO porque es mas facil mostrar todo como string o numero, no como objeto
    	BarrioColumn.setCellValueFactory(new PropertyValueFactory<>("barrio"));
    	DormitoriosColumn.setCellValueFactory(new PropertyValueFactory<>("dormitorios"));
    	LocalidadColumn.setCellValueFactory(new PropertyValueFactory<>("localidad"));
    	PrecioColumn.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
    	ProvinciaColumn.setCellValueFactory(new PropertyValueFactory<>("provincia"));
    	TipoColumn.setCellValueFactory(new PropertyValueFactory<>("tipoInmueble"));
    	
    	//cuando arreglen estado, lo cambio
    	EstadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));  
    	
    	
    	//inicializar todos los combobox
    
    	ObservableList<String> localidades=  FXCollections.observableArrayList();
    	localidades.addAll(Localidad.getLocalidad());
    	ObservableList<String> provincias=  FXCollections.observableArrayList();
    	provincias.addAll(Provincia.getProvincias());
    	
     	LocalidadMenu.setItems( localidades );
     	ProvinciaMenu.setItems( provincias );
    	
    
    	
    	//inicializar los minimos y maximos de los sliders
    		
    	MinPriceSlider.setMin(inmuebleService.getMinPrecio());
    	MaxPriceSlider.setMax(inmuebleService.getMaxPrecio());
    	DormitorioSlider.setMin(0);
    	DormitorioSlider.setMax(inmuebleService.getMaxDormitorios());
    	
    	MinPriceLabel.setText(String.valueOf(df.format(MinPriceSlider.getValue())));
    	MaxPriceLabel.setText(String.valueOf(df.format(MaxPriceSlider.getValue())));
    	DormitorioLabel.setText(String.valueOf(df.format(DormitorioSlider.getValue())));
    	
    	
    	// actualizar lista de inmuebles
    	listaDeInmuebles.addAll(inmuebleService.listInmuebles());
    	InmuebleTable.setItems(listaDeInmuebles);
		
	}
    
    
    @FXML
    void DormitorioDrag(MouseEvent  event) {
    	//esto hace que cuando soltas el drag, te dé el valor que selecciona
    	DormitorioLabel.setText(String.valueOf((int)(DormitorioSlider.getValue())));
    }

    @FXML
    void LimpiarPressed(ActionEvent event) {
    	//limpiar checkboxes
    	LocalCheckBox.setSelected(false);
    	CasaCheckBox.setSelected(false);
    	DepartamentoCheckBox.setSelected(false);
    	TerrenoCheckBox.setSelected(false);
    	QuintaCheckBox.setSelected(false);
    	GalponCheckBox.setSelected(false);
    	OficinaCheckBox.setSelected(false);
    	//texto
    	BarrioTextField.setText("");
    	//sliders
    	MinPriceSlider.setValue(inmuebleService.getMinPrecio());
    	MaxPriceSlider.setValue(inmuebleService.getMaxPrecio());
    	DormitorioSlider.setValue(0);
    	
    	//actualiza lista a todos los inmuebles sin filtro
    	listaDeInmuebles.clear();
    	listaDeInmuebles.addAll(inmuebleService.listInmuebles());
    	InmuebleTable.setItems(listaDeInmuebles);
    }

    @FXML
    void MaxDrag(MouseEvent  event) {
    	//esto hace que cuando soltas el drag, te dé el valor que selecciona
    	
    	MaxPriceLabel.setText(String.valueOf(df.format(MaxPriceSlider.getValue())));
    	

    }

    @FXML
    void MinDrag(MouseEvent  event) {
    	//esto hace que cuando soltas el drag, te dé el valor que selecciona
    	MinPriceLabel.setText(String.valueOf(df.format(MinPriceSlider.getValue())));
    	
    }

    @FXML
    void BuscarPressed(ActionEvent event) {
    	List<String>tipo= new ArrayList<String>();
    	
    	//esto permite saber el tipo de inmueble que habra, el .isSelected() te indica si el checkbox está activada y devuelve true o false
    	
    	if (LocalCheckBox.isSelected()) {
    	    tipo.add("L");
    	}
    	if (CasaCheckBox.isSelected()) {
    	    tipo.add("C");
    	}
    	if (DepartamentoCheckBox.isSelected()) {
    	    tipo.add("D");
    	}
    	if (TerrenoCheckBox.isSelected()) {
    	    tipo.add("T");
    	}
    	if (QuintaCheckBox.isSelected()) {
    	    tipo.add("Q");
    	}
    	if (GalponCheckBox.isSelected()) {
    	    tipo.add("G");
    	}
    	if (OficinaCheckBox.isSelected()) {
    	    tipo.add("O");
    	}
    	
    	if(tipo.isEmpty()){
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Faltan tipos de inmueble"); //titulo
    		alertaTipo.setContentText("debe seleccionarse al menos un tipo de inmueble"); //informacion
    		
    		return;
    		
    	}
    	
    	
    	//se solicitan todos los datos
    	
    	//si no hay provincia o localidad o barrio seleccionado, que busque todo. sino tengo que hacer mas alertas y queda feo
    	listaDeInmuebles.clear();
    	listaDeInmuebles.addAll(inmuebleService.getInmueble(ProvinciaMenu.getSelectionModel().getSelectedItem(), LocalidadMenu.getSelectionModel().getSelectedItem(), BarrioTextField.getText(), tipo, (int)DormitorioSlider.getValue(),(float)MinPriceSlider.getValue() ,(float)MaxPriceSlider.getValue()));
    	
    	//se muestran los datos
    	InmuebleTable.setItems(listaDeInmuebles);
    	
    	
    }

    @FXML
    void VolverPressed(ActionEvent event) {
    	//esto es para volver a la anterior pagina
    	
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
    void VerMasPressed(ActionEvent event) {

    }

	

}
