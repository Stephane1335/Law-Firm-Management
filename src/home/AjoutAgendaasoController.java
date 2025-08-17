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

public class AjoutAgendaasoController implements Initializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjoutAgendaasoController.class);

    Connection connection = connexionBD.connexionDB();
    Stage stage = new Stage();
    @FXML
    private Label nomuser;
    @FXML
    private TextField daterdv;
    @FXML
    private TextField heurerdv;
    @FXML
    private TextField lieurdv;
    @FXML
    private TextField objetrdv;
    @FXML
    private TextField clientrdv;
    @FXML
    private TextField dossierrdv;
    @FXML
    private TextField nomAvocat;

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
        Parent root = FXMLLoader.load(getClass().getResource("agendaso.fxml"));
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
    public void handleClicks(ActionEvent event) throws Exception{

    }
    public void Ajoutrdv(ActionEvent event) throws SQLException, IOException {
        String daterdvText = daterdv.getText();
        String heurerdvText= heurerdv.getText();
        String lieurdvText=lieurdv.getText();
        String objetrdvText= objetrdv.getText();
        String clientrdvText=clientrdv.getText();
        String dossierrdvText= dossierrdv.getText();
        String nomAvocatText=nomAvocat.getText();
        String query = "INSERT INTO `agenda`(`date_Rdv`, `heure_Rdv`, `lieu_Rdv`, `objet_Rdv`, `client_Rdv`, `dossier_Rdv`,`nomAvocat`) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, daterdvText);
        preparedStatement.setString(2,heurerdvText);
        preparedStatement.setString(3, lieurdvText);
        preparedStatement.setString(4, objetrdvText);
        preparedStatement.setString(5, clientrdvText);
        preparedStatement.setString(6, dossierrdvText);
        preparedStatement.setString(7, nomAvocatText);
        preparedStatement.execute();
        Parent root = FXMLLoader.load(getClass().getResource("agendaso.fxml"));
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

