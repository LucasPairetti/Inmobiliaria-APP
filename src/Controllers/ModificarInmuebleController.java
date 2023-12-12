package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import application.clases.Estado;
import application.clases.Localidad;
import application.clases.Orientacion;
import application.clases.Propietario;
import application.clases.Provincia;
import application.clases.TipoInmueble;
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
    private ComboBox<String> estadoMenu;
    
    @FXML
    private TextField PisoDeptoField;

    @FXML
    private Button VolverButton;
    
  private int inmuebleID;
  private int IdPropietario;
    
    private Validacion validar;
    private InmuebleServices serviceInmueble= InmuebleServices.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	
    	Holder holder = Holder.getInstance();
    	IdPropietario= holder.getIdCliente();
    	inmuebleID= holder.getIdInmueble();
		// TODO Auto-generated method stub
    	ObservableList<String>localidades= FXCollections.observableArrayList(); 
    	localidades.addAll(Localidad.getLocalidad());
    	localidades.add("Otra localidad");
     	LocalidadMenu.setItems(localidades);
     	
     	ObservableList<String>estados=FXCollections.observableArrayList(); 
     	estados.addAll(Estado.getEstado());
     	
     	ObservableList<String>provincia= FXCollections.observableArrayList(); 
     	provincia.addAll(Provincia.getProvincias());
    	ProvinciaMenu.setItems(provincia );
    	ObservableList<String>inmuebles=FXCollections.observableArrayList(); 
    	inmuebles.addAll(TipoInmueble.getTipoInmueble());
    	TipoInmuebleMenu.setItems(inmuebles);
    	ObservableList<String>orientaciones=FXCollections.observableArrayList(); 
    	orientaciones.addAll(Orientacion.geOrientacion());
    	OrientacionMenu.setItems(orientaciones);
    	
    	InmuebleDTO inmueble = serviceInmueble.getById(inmuebleID);
		AntiguedadField.setText(String.valueOf(inmueble.getAntiguedad()));
		BaniosField.setText(String.valueOf(inmueble.getBanios()));
		BarrioField.setText(inmueble.getBarrio());
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
    		alertaTipo.showAndWait();
    	}else if(validar.esUnNumero(PrecioField.getText())!=1){
    		
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Precio invalido"); //titulo
    		alertaTipo.setContentText("El campo 'Precio' debe ser de tipo numerico"); //informacion
    		alertaTipo.showAndWait();
    	}else if(validar.esUnNumero(SuperficieField.getText())!=1){
    		
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Superficie invalida"); //titulo
    		alertaTipo.setContentText("El campo 'superficie' debe ser de tipo numerico"); //informacion
    		alertaTipo.showAndWait();
    	}else if(validar.esUnNumero(AntiguedadField.getText())!=1){
    		
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Antiguedad invalida"); //titulo
    		alertaTipo.setContentText("El campo 'Antiguedad' debe ser de tipo numerico"); //informacion
    		alertaTipo.showAndWait();
    	}else if(validar.esUnNumero(DormitorioField.getText())!=1){
    		
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Dormitorios invalida"); //titulo
    		alertaTipo.setContentText("El campo 'Cantidad de Dormitorios' debe ser de tipo numerico"); //informacion
    		alertaTipo.showAndWait();
    	}else if(validar.esUnNumero(FrenteField.getText())!=1){
    		
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Frente invalido"); //titulo
    		alertaTipo.setContentText("El campo 'Frente' debe ser de tipo numerico"); //informacion
    		alertaTipo.showAndWait();
    	}else if(validar.esUnNumero(FondoField.getText())!=1){
    		
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Fondo invalido"); //titulo
    		alertaTipo.setContentText("El campo 'Fondo' debe ser de tipo numerico"); //informacion
    		alertaTipo.showAndWait();
    	}else if(validar.esUnNumero(BaniosField.getText())!=1){
    		
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("baños invalido"); //titulo
    		alertaTipo.setContentText("El campo 'Baños' debe ser de tipo numerico"); //informacion
    		alertaTipo.showAndWait();
    	}else if(validar.esString(BarrioField.getText())!=1){
    		
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Barrio invalido"); //titulo
    		alertaTipo.setContentText("El campo 'Barrio' es invalido"); //informacion
    		alertaTipo.showAndWait();
    	}
    	else {
    		
    		
    		try {
    			
    			String localidad= LocalidadMenu.getValue();
    			if(OtraLocalidadField.getText()!="") {
    				localidad=OtraLocalidadField.getText();
    			}
    			
    			/*
    			 * public InmuebleDTO (int id,int idPropietario,Date fechaCreacion, String estado, String provincia, String localidad, String calle,
			int numero, String pisodpto, String barrio, String tipoInmueble,double precioVenta, String orientacion, float frente,float superficie,
			float fondo, int antiguedad, int dormitorios, int banios,boolean garaje,boolean pHorizontal, boolean patio, boolean piscina, boolean aguaCorriente,
			boolean cloacas, boolean gasNatural,boolean aguaCaliente, boolean lavadero, boolean pavimento, boolean telefono,
			String observaciones) 
    			 */
    			InmuebleDTO nuevoInmueble= new InmuebleDTO(inmuebleID,IdPropietario, serviceInmueble.getById(inmuebleID).getFechaCreacion(), estadoMenu.getValue(), ProvinciaMenu.getValue(), localidad, CalleField.getText(), Integer.parseInt(NumeroField.getText()), PisoDeptoField.getText(), BarrioField.getText(),
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
