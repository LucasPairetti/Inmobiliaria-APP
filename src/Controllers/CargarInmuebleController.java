package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.util.ResourceBundle;

import application.clases.Localidad;
import application.clases.Orientacion;
import application.clases.Propietario;
import application.clases.Provincia;
import application.clases.TipoDNI;
import application.clases.TipoInmueble;
import dto.InmuebleDTO;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.InmuebleServices;

public class CargarInmuebleController implements Initializable{

    @FXML
    private CheckBox AguaCheckBox;
    
    @FXML
    private CheckBox AguaCalienteCheckBox;

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
    private TextField EstadoField;

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
    private Button GuardarButton;

    @FXML
    private ImageView ImageView;

    @FXML
    private Button ImagenButton;

    @FXML
    private CheckBox LavaderoCheckBox;

    @FXML
    private ComboBox<String> LocalidadMenu;

    @FXML
    private TextField NumeroField;

    @FXML
    private TextArea ObservacionesField;

    @FXML
    private ChoiceBox<String> OrientacionMenu;

    @FXML
    private TextField OtraLocalidadField;

    @FXML
    private CheckBox PatioCheckBox;

    @FXML
    private CheckBox PavimentoCheckBox;

    @FXML
    private CheckBox PiscinaCheckBox;

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
    private ChoiceBox<String> TipoInmuebleMenu;

    @FXML
    private Button VolverButton;
    
    private int propietarioID;
    
    private Validacion validar;
    private InmuebleServices serviceInmueble= InmuebleServices.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date fecha = new Date();
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	ObservableList<String>localidades= (ObservableList<String>) Localidad.getLocalidad();
    	localidades.add("Otra localidad");
     	LocalidadMenu.setItems(localidades);
    	ProvinciaMenu.setItems((ObservableList<String>) Provincia.getProvincias());
    	TipoInmuebleMenu.setItems((ObservableList<String>) TipoInmueble.getTipoInmueble());
    	OrientacionMenu.setItems((ObservableList<String>) Orientacion.geOrientacion());
    	FechaField.setText(sdf.format(fecha));
	}
    

    @FXML
    void ImagenPressed(ActionEvent event) {
    	
    	//validacion de campos
    	
    	/*
    	 * metodos para validar campos
    	 * 
    	 */
    	
    	//logica de inmueble
    	/*
    	 * Metodos de logica y persistencia de inmueble
    	 * 
    	 */
    	
    	// actualizacion de pantalla
    	
    	/*
    	 * actualizo la pantalla post persistencia o logica
    	 * 
    	 */

    }

    @FXML
    void LocalidadSeleccionada(ActionEvent event) {
    	
    }

    @FXML
    void GuardarPressed(ActionEvent event) {
    	
    	//validaciones -> numeroCalle -> Calle ->otraLocalidad ->precio
    	
    	if(validar.esUnNumero(NumeroField.getText())!=1) {
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Numero de calle invalido"); //titulo
    		alertaTipo.setContentText("El campo 'Numero' para la calle debe ser de tipo numerico"); //informacion
    	}else if(validar.esUnNumero(PrecioField.getText())!=1){
    		
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Precio invalido"); //titulo
    		alertaTipo.setContentText("El campo 'Precio' debe ser de tipo numerico"); //informacion
    	}else if(validar.esUnNumero(SuperficieField.getText())!=1){
    		
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Superficie invalida"); //titulo
    		alertaTipo.setContentText("El campo 'superficie' debe ser de tipo numerico"); //informacion
    	}else if(validar.esUnNumero(AntiguedadField.getText())!=1){
    		
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Antiguedad invalida"); //titulo
    		alertaTipo.setContentText("El campo 'Antiguedad' debe ser de tipo numerico"); //informacion
    	}else if(validar.esUnNumero(DormitorioField.getText())!=1){
    		
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Dormitorios invalida"); //titulo
    		alertaTipo.setContentText("El campo 'Cantidad de Dormitorios' debe ser de tipo numerico"); //informacion
    	}else if(validar.esUnNumero(FrenteField.getText())!=1){
    		
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Frente invalido"); //titulo
    		alertaTipo.setContentText("El campo 'Frente' debe ser de tipo numerico"); //informacion
    	}else if(validar.esUnNumero(FondoField.getText())!=1){
    		
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Fondo invalido"); //titulo
    		alertaTipo.setContentText("El campo 'Fondo' debe ser de tipo numerico"); //informacion
    	}else if(validar.esUnNumero(BaniosField.getText())!=1){
    		
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("baños invalido"); //titulo
    		alertaTipo.setContentText("El campo 'Baños' debe ser de tipo numerico"); //informacion
    	}else if(validar.esString(BarrioTextField.getText())!=1){
    		
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Barrio invalido"); //titulo
    		alertaTipo.setContentText("El campo 'Barrio' es invalido"); //informacion
    	}
    	else {
    		
    		
    		try {
    			/*
    			 * public InmuebleDTO (int id,int idPropietario,Date fechaCreacion, boolean estado, String provincia, String localidad, String calle,
			int numero, String pisodpto, String barrio, String tipoInmueble,double precioVenta, String orientacion, float frente,float superficie,
			float fondo, int antiguedad, int dormitorios, int banios,boolean garaje,boolean pHorizontal, boolean patio, boolean piscina, boolean aguaCorriente,
			boolean cloacas, boolean gasNatural,boolean aguaCaliente, boolean lavadero, boolean pavimento, boolean telefono,
			String observaciones)
    			 * 
    			 * 
    			 */
    			
    			
    			InmuebleDTO nuevoInmueble= new InmuebleDTO(propietarioID, (java.sql.Date) fecha, false, null, null, null, 0, null, null, null, 0, null, 0, 0, 0, 0, 0, 0, false, false, false, false, false, false, false, false, false, false, false, null);
    					//crear inmueble -> 
    			
    			serviceInmueble.createInmueble(nuevoInmueble);
    			
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

    @FXML
    void ProvinciaSelccionada(ActionEvent event) {

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
