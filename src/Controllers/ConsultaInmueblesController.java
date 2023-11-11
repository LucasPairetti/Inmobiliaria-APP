package Controllers;
import application.clases.*;


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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ConsultaInmueblesController implements Initializable {

    @FXML
    private TableColumn<Inmueble, String> BarrioColumn;

    @FXML
    private ComboBox<String> BarrioMenu;

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
    private TableColumn<Inmueble, Integer> DormitoriosColumn;

    @FXML
    private CheckBox GalponCheckBox;

    @FXML
    private TableView<Inmueble> InmuebleTable;

    @FXML
    private Button LimpiarButton;

    @FXML
    private CheckBox LocalCheckBox;

    @FXML
    private TableColumn<Inmueble, Localidad> LocalidadColumn;

    @FXML
    private ComboBox<String> LocalidadMenu;

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
    private TableColumn<Inmueble, Double> PrecioColumn;

    @FXML
    private TableColumn<Inmueble, Provincia> ProvinciaColumn;

    @FXML
    private ComboBox<String> ProvinciaMenu;

    @FXML
    private CheckBox QuintaCheckBox;

    @FXML
    private CheckBox TerrenoCheckBox;

    @FXML
    private TableColumn<Inmueble, TipoInmueble> TipoColumn;

    @FXML
    private Button VolverButton;

    ObservableList<Inmueble> listaDeInmuebles= FXCollections.observableArrayList(); 
    //para redondeos
    DecimalFormat df = new DecimalFormat("###.##");
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Este metodo inicializa los datos en pantalla, en caso de los inmuebles carga la lista de inmuebles
    	
    	
    	//inicializar todos los combobox
    	
    	//inicializar los minimos y maximos de los sliders
    		//necesito obtener todos los inmuebles y buscar el min y maximo de dormitorios y precios
    	
    	
    	//necesito metodos para sacar esto en la capa de logica
    	//MinPriceSlider.setMin(minPrecio);
    	//MinPriceSlider.setMax(maxPrecio);
    	//dormitorioSlider.setMin(minDormitorio);
    	//dormitorioSlider.setMax(maxDormitorio);
    	
    	MinPriceLabel.setText(String.valueOf(df.format(MinPriceSlider.getValue())));
    	MaxPriceLabel.setText(String.valueOf(df.format(MaxPriceSlider.getValue())));
    	DormitorioLabel.setText(String.valueOf(df.format(DormitorioSlider.getValue())));
    	
    	
    	// actualizar lista de inmuebles
    	//listaDeInmuebles.addall(gestor.getInmuebles());
    	InmuebleTable.setItems(listaDeInmuebles);
		
	}
    
    
    @FXML
    void DormitorioDrag(MouseEvent  event) {
    	DormitorioLabel.setText(String.valueOf((int)(DormitorioSlider.getValue())));
    }

    @FXML
    void EliminarPressed(ActionEvent event) {

    }

    @FXML
    void MaxDrag(MouseEvent  event) {
    	
    	
    	MaxPriceLabel.setText(String.valueOf(df.format(MaxPriceSlider.getValue())));
    	

    }

    @FXML
    void MinDrag(MouseEvent  event) {

    	MinPriceLabel.setText(String.valueOf(df.format(MinPriceSlider.getValue())));
    	
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
