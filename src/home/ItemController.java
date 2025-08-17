package home;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemController implements Initializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    public PreparedStatement ps;
    public ResultSet result;

    public BorderPane bordepane1;


    Connection cnx=connexionBD.connexionDB();
    @FXML
    public Label id;
    @FXML
    public Label nomdos;
    @FXML
    public Label datedos;
    @FXML
    public Label nomav;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String query = "SELECT * FROM `dossier`";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = cnx.prepareStatement(query);
            result = preparedStatement.executeQuery();
            result.next();
            id.setText(String.valueOf(result.getInt("id_avocat")));
            nomdos.setText(String.valueOf(result.getString("nomDossier")));
            datedos.setText(String.valueOf(result.getDate("date_dossier")));
            nomav.setText(String.valueOf(result.getString("nomAvocat")));
        } catch (SQLException throwables) {
            throwables.LOGGER.error("Unexpected exception", e);
        }


    }
}
