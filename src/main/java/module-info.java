module uam.edu.org.examencortei {

    requires javafx.controls;
    requires javafx.fxml;

    // Permite que javafx.fxml acceda a los controllers.
    opens uam.edu.org.examencortei.Controllers
            to javafx.fxml;

    // Permite que javafx.fxml acceda a los elementos FXML
    // del paquete principal si fuera necesario.
    opens uam.edu.org.examencortei
            to javafx.fxml;

    exports uam.edu.org.examencortei;
}