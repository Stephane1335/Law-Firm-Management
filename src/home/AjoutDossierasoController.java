package home;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class AjoutDossierasoController implements Initializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjoutDossierasoController.class);

    Connection connection = connexionBD.connexionDB();
    Stage stage = new Stage();
    @FXML
    protected Label nomuser;
    @FXML
    protected TextField nomDossier;
    @FXML
    protected TextField objet;
    @FXML
    protected TextField nature;
    @FXML
    protected TextField juridiction;
    @FXML
    protected TextField commentaire;
    @FXML
    protected TextField qualite;
    @FXML
    protected TextField date_dossier;
    @FXML
    protected TextField nomClient;
    @FXML
    protected TextField nomAvocat;
    @FXML
    protected TextField personne_Opposition;
    @FXML
    protected TextField avocat_Opposition;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Preferences userPreferences = Preferences.userRoot();
            String i = null;
            String info = userPreferences.get("User", i);
            String query = "SELECT * FROM `avocat`WHERE id_Avocat=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, info);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            nomuser.setText(String.valueOf(result.getString("nomUtilisateur")));
        } catch (SQLException throwables) {
            throwables.LOGGER.error("Unexpected exception", e);
        }
    }
    public void Back(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("dossieraso.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
    public void GoToClient(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("cltaso.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void GoToAgent(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("agentaso.fxml"));
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
    public void ajoutdossier(ActionEvent event) throws SQLException, IOException {
        String nomDossierText = nomDossier.getText();
        String objetText= objet.getText();
        String natureText=nature.getText();
        String juridictionText= juridiction.getText();
        String commentaireText=commentaire.getText();
        String qualiteText= qualite.getText();
        String dateText=date_dossier.getText();
        String nomClientText= nomClient.getText();
        String nomAvocatText=nomAvocat.getText();
        String personne_oppositionText= personne_Opposition.getText();
        String avocat_oppositionText=avocat_Opposition.getText();
        String query = "INSERT INTO `dossier`(`nomDossier`, `objet`, `nature`, `juridiction`,`commentaire`,`date_dossier`,`Qualite`,`nomAvocat`,`nomClient`,`personne_Opposition`,`avocat_Opposition`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nomDossierText);
        preparedStatement.setString(2,objetText);
        preparedStatement.setString(3, natureText);
        preparedStatement.setString(4, juridictionText);
        preparedStatement.setString(5, commentaireText);
        preparedStatement.setString(6, dateText);
        preparedStatement.setString(7, qualiteText);
        preparedStatement.setString(8, nomAvocatText);
        preparedStatement.setString(9, nomClientText);
        preparedStatement.setString(10, personne_oppositionText);
        preparedStatement.setString(11, avocat_oppositionText);
        preparedStatement.execute();
        Parent root = FXMLLoader.load(getClass().getResource("dossieraso.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void Logout(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}