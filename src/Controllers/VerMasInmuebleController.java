package Controllers;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import application.clases.Localidad;
import application.clases.Orientacion;
import application.clases.Provincia;
import application.clases.TipoInmueble;
import dto.InmuebleDTO;
import dto.PropietarioDTO;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.InmuebleServices;
import services.PropietarioServices;

public class VerMasInmuebleController implements Initializable{

    @FXML
    private CheckBox AguaCalienteCheckBox;

    @FXML
    private CheckBox AguaCheckBox;

    @FXML
    private TextField AntiguedadField;

    @FXML
    private TextField BaniosField;

    @FXML
    private TextField BarrioTextField;

    @FXML
    private TextField CalleField;

    @FXML
    private CheckBox CloacasCheckBox;

    @FXML
    private TextField DormitorioField;

    @FXML
    private TextField FechaField;

    @FXML
    private TextField FondoField;

    @FXML
    private TextField FrenteField;

    @FXML
    private CheckBox GarajeCheckBox;

    @FXML
    private CheckBox GasCheckBox;

    @FXML
    private ImageView ImageView;

    @FXML
    private CheckBox LavaderoCheckBox;

    @FXML
    private ComboBox<String> LocalidadMenu;

    @FXML
    private TextField NumeroField;

    @FXML
    private TextArea ObservacionesField;

    @FXML
    private ComboBox<String> OrientacionMenu;

    @FXML
    private TextField OtraLocalidadField;

    @FXML
    private CheckBox PatioCheckBox;

    @FXML
    private CheckBox PavimentoCheckBox;

    @FXML
    private CheckBox PiscinaCheckBox;

    @FXML
    private TextField PisoDeptoField;

    @FXML
    private TextField PrecioField;

    @FXML
    private CheckBox PropiedadHorizontalCheckBox;

    @FXML
    private TextField PropietarioField;

    @FXML
    private ComboBox<String> ProvinciaMenu;

    @FXML
    private TextField SuperficieField;

    @FXML
    private CheckBox TelefenoCheckBox;

    @FXML
    private ComboBox<String> TipoInmuebleMenu;

    @FXML
    private Button VolverButton;
    
    private int idInmueble;
    
    private int pantalla; //0 para busquedaInmueble o 1 para ClienteInmuebles
    
    private InmuebleServices inmuebleService = InmuebleServices.getInstance();
    private PropietarioServices propietarioService = PropietarioServices.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	Holder holder = Holder.getInstance();
    	idInmueble=holder.getIdInmueble();
		pantalla= holder.getPantalla();


		InmuebleDTO inmueble = inmuebleService.getById(idInmueble);
		AntiguedadField.setText(String.valueOf(inmueble.getAntiguedad()));
		BaniosField.setText(String.valueOf(inmueble.getBanios()));
		BarrioTextField.setText(inmueble.getBarrio());
		CalleField.setText(inmueble.getCalle());
		DormitorioField.setText(String.valueOf(inmueble.getDormitorios()));
		FechaField.setText(sdf.format(inmueble.getFechaCreacion()));
		FondoField.setText(String.valueOf(inmueble.getFondo()));
		FrenteField.setText(String.valueOf(inmueble.getFrente()));
		NumeroField.setText(String.valueOf(inmueble.getNumero()));
		ObservacionesField.setText(inmueble.getObservaciones());
		OtraLocalidadField.setText(inmueble.getLocalidad());
		PisoDeptoField.setText(inmueble.getPisodpto());
		PrecioField.setText(String.valueOf(inmueble.getPrecioVenta()));
		PropietarioField.setText(inmueble.getNombreApellidoPropietario());
		SuperficieField.setText(String.valueOf(inmueble.getSuperficie()));

		
		AguaCalienteCheckBox.setSelected(inmueble.isAguaCaliente());
		AguaCheckBox.setSelected(inmueble.isAguaCorriente());
		CloacasCheckBox.setSelected(inmueble.isCloacas());
		GarajeCheckBox.setSelected(inmueble.isGaraje());
		GasCheckBox.setSelected(inmueble.isGasNatural());
		LavaderoCheckBox.setSelected(inmueble.isLavadero());
		PatioCheckBox.setSelected(inmueble.isPatio());
		PavimentoCheckBox.setSelected(inmueble.isPavimento());
		PiscinaCheckBox.setSelected(inmueble.isPiscina());
		PropiedadHorizontalCheckBox.setSelected(inmueble.ispHorizontal());
		TelefenoCheckBox.setSelected(inmueble.isTelefono());

		
		
     	LocalidadMenu.setPromptText(inmueble.getLocalidad());
     	
     	
    
    	ProvinciaMenu.setPromptText(inmueble.getProvincia());
    	
    	TipoInmuebleMenu.setPromptText(inmueble.getTipoInmueble());
    
    	OrientacionMenu.setPromptText(inmueble.getOrientacion());
		
	}

    @FXML
    void LocalidadSeleccionada(ActionEvent event) {

    }
    
    public void setIdInmuebleypantalla(int id, int pantalla) {
    	this.idInmueble= id;
    	this.pantalla=pantalla;
    }

    @FXML
    void ProvinciaSelccionada(ActionEvent event) {

    }

    @FXML
    void VolverPressed(ActionEvent event) {
    	try {
    		if(pantalla==0) {
    			Parent root;
        		root = FXMLLoader.load((getClass().getResource("/interfaces/InmueblesBusqueda.fxml")));
        		
        		Stage window = (Stage)VolverButton.getScene().getWindow();
        		window.setTitle("Buscar Inmueble");
        		window.setScene(new Scene(root));
    		}else if (pantalla==1) {
    			Parent root;
        		root = FXMLLoader.load((getClass().getResource("/interfaces/InmueblesParaCliente.fxml")));
        		
        		Stage window = (Stage)VolverButton.getScene().getWindow();
        		window.setTitle("Buscar Inmueble");
        		window.setScene(new Scene(root));
    		}
    		
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

	

}
