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
    @FXML
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

        // Validar nombre del proyecto
        if (txtProyecto.getText().trim().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Registro");
            alerta.setHeaderText("Falta información");
            alerta.setContentText("Debe ingresar el nombre del proyecto.");
            alerta.showAndWait();
            return;
        }

        // Validar tipo de proyecto
        RadioButton tipo =
                (RadioButton) grupoTipoProyecto.getSelectedToggle();

        if (tipo == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Registro");
            alerta.setHeaderText("Falta información");
            alerta.setContentText(
                    "Debe seleccionar el tipo de proyecto."
            );
            alerta.showAndWait();
            return;
        }

        // Validar tecnologías
        if (!chkJava.isSelected()
                && !chkReact.isSelected()
                && !chkPostgres.isSelected()) {

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Registro");
            alerta.setHeaderText("Falta información");
            alerta.setContentText(
                    "Debe seleccionar al menos una tecnología."
            );
            alerta.showAndWait();
            return;
        }

        // Validar imagen
        if (imgProyecto.getImage() == null) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Registro");
            alerta.setHeaderText("Falta información");
            alerta.setContentText(
                    "Debe seleccionar una imagen del proyecto."
            );
            alerta.showAndWait();
            return;
        }

        // Obtener tecnologías seleccionadas
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

        // Mostrar información del proyecto
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