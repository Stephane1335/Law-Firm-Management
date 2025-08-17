package home;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class DetailAgentController implements Initializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(DetailAgentController.class);

    ResultSet result = null;
    Connection connection = connexionBD.connexionDB();
    Stage stage = new Stage();
    @FXML
    private Label nomag;
    @FXML
    private Label prenomag;
    @FXML
    private Label fonctionag;
    @FXML
    private Label cniag;
    @FXML
    private Label sexeag;
    @FXML
    private Label telag;
    @FXML
    private Label emailag;
    @FXML
    private Label detailag;
    @FXML
    private Label paysag;
    @FXML
    private Label villeag;
    @FXML
    private Label quartierag;
    @FXML
    private Label adresseag;
    @FXML
    private Label salaireag;
    @FXML
    private Label date_emag;
    @FXML
    private Label userag;
    @FXML
    private Label mdpag;
    @FXML
    private Label nomuser;
    @FXML
    private Label nomuser1;
    @FXML
    private Label statut1;
    @FXML
    private Button btnevent;
    @FXML
    private   Button btndelete;
    @FXML
    private Button btnprint;
    @FXML
    private Button btnreturn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Preferences userPreferences = Preferences.userRoot();
            String i = null;
            String info = userPreferences.get("User", i);
            String query = "SELECT * FROM `avocat`WHERE id_Avocat=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, info);
            result = preparedStatement.executeQuery();
            result.next();
            nomuser1.setText(String.valueOf(result.getString("nomUtilisateur")));
        } catch (SQLException throwables) {
            throwables.LOGGER.error("Unexpected exception", e);
        }
        try {
            Preferences userPreference = Preferences.userRoot();
            int i = 0;
            String info = userPreference.get("id_Avocat", String.valueOf(i));
            String query = "SELECT * FROM `avocat` WHERE id_Avocat=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,info);
            result = preparedStatement.executeQuery();
            result.next();
            statut1.setText(String.valueOf(result.getString("statut")));
            nomuser.setText(String.valueOf(result.getString("nomUtilisateur")));
            nomag.setText(String.valueOf(result.getString("nom")));
            prenomag.setText(String.valueOf(result.getString("prenom")));
            fonctionag.setText(String.valueOf(result.getString("statut")));
            cniag.setText(String.valueOf(result.getString("CNI")));
            sexeag.setText(String.valueOf(result.getString("sexe")));
            telag.setText(String.valueOf(result.getString("telephone")));
            emailag.setText(String.valueOf(result.getString("email")));
            detailag.setText(String.valueOf(result.getString("detail")));
            paysag.setText(String.valueOf(result.getString("pays")));
            villeag.setText(String.valueOf(result.getString("ville")));
            quartierag.setText(String.valueOf(result.getString("quartier")));
            adresseag.setText(String.valueOf(result.getString("adresse")));
            salaireag.setText(String.valueOf(result.getInt("salaire")));
            date_emag.setText(String.valueOf(result.getDate("date_Embauche")));
            userag.setText(String.valueOf(result.getString("nomUtilisateur")));
            mdpag.setText(String.valueOf(result.getString("mdp")));
        } catch (SQLException throwables) {
            throwables.LOGGER.error("Unexpected exception", e);
        }
    }

    public void Back(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("agentaso.fxml"));
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
    public void delete(ActionEvent event) throws Exception{
        int i = 0;
        Preferences userPreference = Preferences.userRoot();
        String info = userPreference.get("id_Avocat", String.valueOf(i));
        String query = "DELETE FROM `avocat` WHERE `id_Avocat`  ="+info;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
        Parent root = FXMLLoader.load(getClass().getResource("agentaso.fxml"));
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

