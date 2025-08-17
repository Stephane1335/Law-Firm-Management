package home.Collabo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;import home.connexionBD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class DetailDossierCollaboController implements Initializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(DetailDossierCollaboController.class);

    ResultSet result = null;
    Connection cnx = connexionBD.connexionDB();
    @FXML
    protected Button btnevent;
    @FXML
    protected Button btnreturn;

    @FXML
    protected Label objet;
    @FXML
    protected Label nature;
    @FXML
    protected Label juridiction;
    @FXML
    protected Label commentaire;
    @FXML
    protected Label qualite;
    @FXML
    protected Label date;
    @FXML
    protected Label nomClient;
    @FXML
    protected Label nomAvocat;
    @FXML
    protected Label personne_Opposition;
    @FXML
    protected Label avocat_Opposotion;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Preferences userPreference = Preferences.userRoot();
            int i = 0;
            String info = userPreference.get("id", String.valueOf(i));
            String query = "SELECT * FROM `dossier` WHERE id_Dossier=?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setString(1,info);
            result = preparedStatement.executeQuery();
            result.next();
            objet.setText(String.valueOf(result.getString("objet")));
            nature.setText(String.valueOf(result.getString("nature")));
            juridiction.setText(String.valueOf(result.getString("juridiction")));
            commentaire.setText(String.valueOf(result.getString("commentaire")));
            qualite.setText(String.valueOf(result.getString("Qualite")));
            date.setText(String.valueOf(result.getString("date_dossier")));
            nomClient.setText(String.valueOf(result.getString("nomClient")));
            nomAvocat.setText(String.valueOf(result.getString("nomAvocat")));
            personne_Opposition.setText(String.valueOf(result.getString("personne_Opposition")));
            avocat_Opposotion.setText(String.valueOf(result.getString("avocat_Opposition")));
        } catch (SQLException throwables) {
            throwables.LOGGER.error("Unexpected exception", e);
        }
    }
    public void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnevent) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("evenementaso.fxml")));
            Stage window = (Stage) btnevent.getScene().getWindow();
            window.setScene(new Scene(root, 1050, 576));
        }
        if (actionEvent.getSource() == btnreturn) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dossieraso.fxml")));
            Stage window = (Stage) btnreturn.getScene().getWindow();
            window.setScene(new Scene(root, 1050, 576));
        }
    }
    public void GoToHome(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Homeaso.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void GoToDossier(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("dossieraso.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void GoToAgenda(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("agendaso.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void GoToTache(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("tacheaso.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void Logout(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../Login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
