package Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModificarPropietarioController {

    @FXML
    private TextField ApellidoField;

    @FXML
    private Button CancelarButton;

    @FXML
    private Button GuardarBUtton;

    @FXML
    private ComboBox<?> InmuebleMenu;

    @FXML
    private ComboBox<?> InmuebleMenu1;

    @FXML
    private ComboBox<?> LocalidadMenu;

    @FXML
    private TextField NombreField;

    @FXML
    private TextField TelefonoField;

    @FXML
    private TextField TelefonoField1;

    @FXML
    private TextField TelefonoField11;

    @FXML
    private TextField barrioField;

    @FXML
    private TextField barrioField1;

    @FXML
    private TextField barrioField11;

    @FXML
    void CancelarPressed(ActionEvent event) {
    	try {
    		Parent root;
    		root = FXMLLoader.load((getClass().getResource("/interfaces/PropietariosPrincipal.fxml")));
    		
    		Stage window = (Stage)CancelarButton.getScene().getWindow();
    		window.setTitle("Propietarios");
    		window.setScene(new Scene(root));
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    @FXML
    void GuardarPressed(ActionEvent event) {

    }

}
