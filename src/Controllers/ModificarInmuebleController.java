package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.clases.Localidad;
import application.clases.Orientacion;
import application.clases.Propietario;
import application.clases.Provincia;
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

public class ModificarInmuebleController implements Initializable {

    @FXML
    private CheckBox AguaCheckBox;
    
    @FXML
    private CheckBox AguaCalienteCheckBox;

    @FXML
    private TextField AntiguedadField;

    @FXML
    private TextField BaniosField;

    @FXML
    private TextField BarrioField;

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
    private TextField IdField;

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
    private TextField PisoDeptoField;

    @FXML
    private Button VolverButton;
    
  private int inmuebleID;
  private int IdPropietario;
    
    private Validacion validar;
    private InmuebleServices serviceInmueble= InmuebleServices.getInstance();
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	ObservableList<String>localidades= (ObservableList<String>) Localidad.getLocalidad();
    	localidades.add("Otra localidad");
     	LocalidadMenu.setItems(localidades);
     	
    	ProvinciaMenu.setItems((ObservableList<String>) Provincia.getProvincias());
    	TipoInmuebleMenu.setItems((ObservableList<String>) TipoInmueble.getTipoInmueble());
    	OrientacionMenu.setItems((ObservableList<String>) Orientacion.geOrientacion());
    	
	}
    
    public void setInmueblePropietario(int idInm, int idProp) {
    	inmuebleID= idInm;
    	IdPropietario=idProp;
    }

    @FXML
    void ImagenPressed(ActionEvent event) {

    }

    @FXML
    void LocalidadSeleccionada(ActionEvent event) {

    }

    @FXML
    void ModificarPressed(ActionEvent event) {
    	
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
    	}else if(validar.esString(BarrioField.getText())!=1){
    		
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Barrio invalido"); //titulo
    		alertaTipo.setContentText("El campo 'Barrio' es invalido"); //informacion
    	}
    	else {
    		
    		
    		try {
    			
    			String localidad= LocalidadMenu.getValue();
    			if(OtraLocalidadField.getText()!="") {
    				localidad=OtraLocalidadField.getText();
    			}
    			
    			InmuebleDTO nuevoInmueble= new InmuebleDTO(inmuebleID,IdPropietario, serviceInmueble.getById(inmuebleID).getFechaCreacion(), true, ProvinciaMenu.getValue(), localidad, CalleField.getText(), Integer.parseInt(NumeroField.getText()), PisoDeptoField.getText(), BarrioField.getText(),
    					TipoInmuebleMenu.getValue(), Double.parseDouble(PrecioField.getText()), OrientacionMenu.getValue(), Float.parseFloat(FrenteField.getText()), Float.parseFloat(SuperficieField.getText()),
    					Float.parseFloat(FondoField.getText()), Integer.parseInt(AntiguedadField.getText()), Integer.parseInt(DormitorioField.getText()), Integer.parseInt(BaniosField.getText()), GarajeCheckBox.isSelected(),
    					PropiedadHorizontalCheckBox.isSelected(), PatioCheckBox.isSelected(), PiscinaCheckBox.isSelected(), AguaCheckBox.isSelected(), CloacasCheckBox.isSelected(), GasCheckBox.isSelected(), AguaCalienteCheckBox.isSelected(),
    					LavaderoCheckBox.isSelected(), PavimentoCheckBox.isSelected(), TelefenoCheckBox.isSelected(), ObservacionesField.getText());
    					//modificar inmueble 
    			
    			serviceInmueble.updateInmueble(nuevoInmueble);
    			
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
    		root = FXMLLoader.load((getClass().getResource("/interfaces/PropietariosPrincipal.fxml")));
    		
    		Stage window = (Stage)VolverButton.getScene().getWindow();
    		window.setTitle("Propietarios");
    		window.setScene(new Scene(root));
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}

    }

	

}
