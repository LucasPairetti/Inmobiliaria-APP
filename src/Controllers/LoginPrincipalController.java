package Controllers;

import java.io.IOException;

import dto.VendedorDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.VendedorServices;

public class LoginPrincipalController {

    @FXML
    private Button IngresarButton;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button SalirButton;

    @FXML
    private AnchorPane Scenario;

    @FXML
    private TextField emailField;
    VendedorServices vendedorServices = VendedorServices.getInstance();
    Holder holder = Holder.getInstance();
    @FXML
    void IngresarPressed(ActionEvent event) {
    	
    	
    	VendedorDTO usuario = vendedorServices.validarVendedor(emailField.getText(), PasswordField.getText());
    	
    	if(usuario!=null) {
    		
    		try {
    			holder.setIdUsuario(usuario.getId());
        		Parent root;
        		root = FXMLLoader.load((getClass().getResource("/interfaces/PantallaPrincipal.fxml")));
        		
        		Stage window = (Stage)IngresarButton.getScene().getWindow();
        		window.setTitle("Menu Principal");
        		window.setScene(new Scene(root));
        	} catch (IOException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
    	}else {
    		Alert alertaTipo = new Alert(Alert.AlertType.ERROR); //esto es un mensaje de alerta
    		alertaTipo.setTitle("Usuario invalido"); //titulo
    		alertaTipo.setContentText("El email o contraseña de este vendedor es incorrecta"); //informacion
    		alertaTipo.showAndWait();
    	}
    	
    	
    }

    @FXML
    void SalirPressed(ActionEvent event) {
    	Stage stage = (Stage) Scenario.getScene().getWindow();
    	stage.close();
    }

}
