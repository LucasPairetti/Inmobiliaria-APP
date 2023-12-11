package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ModificarVendedorController {

    @FXML
    private TextField ApellidoField;

    @FXML
    private TextField CalleField;

    @FXML
    private Button CancelarButton;

    @FXML
    private TextField ClaveField;

    @FXML
    private TextField EmailField;

    @FXML
    private DatePicker FechaNacimientoCalendar;

    @FXML
    private Button GuardarBUtton;

    @FXML
    private ComboBox<?> LocalidadMenu;

    @FXML
    private TextField NombreField;

    @FXML
    private TextField NumeroDomicilioField;

    @FXML
    private ComboBox<?> ProvinciaMenu;

    @FXML
    private TextField SueldoField;

    @FXML
    private TextField TelefonoField;

    @FXML
    private TextField numeroDocField;

    @FXML
    private ComboBox<?> tipoDocMenu;

    @FXML
    void CancelarPressed(ActionEvent event) {

    }

    @FXML
    void GuardarPressed(ActionEvent event) {

    }

}
