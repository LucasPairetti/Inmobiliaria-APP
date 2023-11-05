package Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class ConsultaInmueblesController {

    @FXML
    private TableColumn<?, ?> BarrioColumn;

    @FXML
    private ComboBox<?> BarrioMenu;

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
    private TableColumn<?, ?> DormitoriosColumn;

    @FXML
    private CheckBox GalponCheckBox;

    @FXML
    private TableView<?> InmuebleTable;

    @FXML
    private Button LimpiarButton;

    @FXML
    private CheckBox LocalCheckBox;

    @FXML
    private TableColumn<?, ?> LocalidadColumn;

    @FXML
    private ComboBox<?> LocalidadMenu;

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
    private TableColumn<?, ?> PrecioColumn;

    @FXML
    private TableColumn<?, ?> ProvinciaColumn;

    @FXML
    private ComboBox<?> ProvinciaMenu;

    @FXML
    private CheckBox QuintaCheckBox;

    @FXML
    private CheckBox TerrenoCheckBox;

    @FXML
    private TableColumn<?, ?> TipoColumn;

    @FXML
    private Button VolverButton;

    @FXML
    void DormitorioDrag(MouseEvent event) {

    }

    @FXML
    void EliminarPressed(ActionEvent event) {

    }

    @FXML
    void MaxDrag(MouseEvent event) {

    }

    @FXML
    void MinDrag(MouseEvent event) {

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
