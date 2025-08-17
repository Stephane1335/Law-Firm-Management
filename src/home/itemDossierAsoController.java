package home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


import javafx.scene.Node;

public class itemDossierAsoController implements Initializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(itemDossierAsoController.class);

    @FXML
    private Button btnDetailDos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    public void handleDos(ActionEvent actionEvent) throws IOException{
        if (actionEvent.getSource() == btnDetailDos) {
            Parent root6 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("detaildossieraso.fxml")));
            Stage window = (Stage) btnDetailDos.getScene().getWindow();
            window.setScene(new Scene(root6, 1050, 576));
            window.show();
        }
    }
}
