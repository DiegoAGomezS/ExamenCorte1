package uam.edu.org.examencortei.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ProyectoController {

    @FXML
    private TextField txtProyecto;

    @FXML
    private RadioButton rbWeb;

    @FXML
    private RadioButton rbMovil;

    @FXML
    private CheckBox chkJava;

    @FXML
    private CheckBox chkReact;

    @FXML
    private CheckBox chkPostgres;

    @FXML
    private ImageView imgProyecto;

    // Grupo que permite seleccionar un solo tipo de proyecto.
    private ToggleGroup grupoTipoProyecto;

    @FXML
    private void initialize() {

        // Creamos el grupo.
        grupoTipoProyecto = new ToggleGroup();

        // Agregamos ambos RadioButton al mismo grupo.
        rbWeb.setToggleGroup(grupoTipoProyecto);
        rbMovil.setToggleGroup(grupoTipoProyecto);
    }

    @FXML
    private void seleccionarImagen() {

        FileChooser fc = new FileChooser();

        fc.setTitle("Seleccionar imagen");

        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Imágenes",
                        "*.png",
                        "*.jpg",
                        "*.jpeg"
                )
        );

        File archivo = fc.showOpenDialog(
                (Stage) imgProyecto.getScene().getWindow()
        );

        if (archivo != null) {
            imgProyecto.setImage(
                    new Image(archivo.toURI().toString())
            );
        }
    }

    @FXML
    private void registrar() {

        // Obtenemos el RadioButton seleccionado.
        RadioButton tipo =
                (RadioButton) grupoTipoProyecto.getSelectedToggle();

        // Comprobamos que se haya seleccionado un tipo.
        if (tipo == null) {

            Alert alerta = new Alert(Alert.AlertType.WARNING);

            alerta.setTitle("Registro");
            alerta.setHeaderText("Tipo de proyecto no seleccionado");
            alerta.setContentText(
                    "Debe seleccionar Aplicación Web o Aplicación Móvil."
            );

            alerta.showAndWait();

            return;
        }

        String tecnologias = "";

        if (chkJava.isSelected()) {
            tecnologias += "Java ";
        }

        if (chkReact.isSelected()) {
            tecnologias += "React ";
        }

        if (chkPostgres.isSelected()) {
            tecnologias += "PostgreSQL ";
        }

        if (tecnologias.isEmpty()) {
            tecnologias = "Ninguna";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Proyecto registrado");
        alert.setHeaderText("Información del proyecto");

        alert.setContentText(
                "Proyecto: " + txtProyecto.getText()
                        + "\nTipo: " + tipo.getText()
                        + "\nTecnologías: " + tecnologias
        );

        alert.showAndWait();
    }
}