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

public class AjoutAgentController implements Initializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(AjoutAgentController.class);

    Connection connection = connexionBD.connexionDB();
    Stage stage = new Stage();
    @FXML
    protected TextField nomag;
    @FXML
    protected TextField prenomag;
    @FXML
    protected TextField fonctionag;
    @FXML
    protected TextField cniag;
    @FXML
    protected TextField sexeag;
    @FXML
    protected TextField telag;
    @FXML
    protected TextField emailag;
    @FXML
    protected TextField detailag;
    @FXML
    protected TextField paysag;
    @FXML
    protected TextField villeag;
    @FXML
    protected TextField quartierag;
    @FXML
    protected TextField adresseag;
    @FXML
    protected TextField salaireag;
    @FXML
    protected TextField date_emag;
    @FXML
    protected TextField userag;
    @FXML
    protected TextField mdpag;
    @FXML
    protected Label nomuser;

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
    public void handleClicks(ActionEvent event) throws Exception{

    }
    public void ajoutagent(ActionEvent event) throws SQLException, IOException {
        String nomagText = nomag.getText();
        String prenomagText= prenomag.getText();
        String fonctionagText=fonctionag.getText();
        String cniagText= cniag.getText();
        String sexeagText=sexeag.getText();
        String telagText= telag.getText();
        String emailagText=emailag.getText();
        String detailagText= detailag.getText();
        String paysagText=paysag.getText();
        String villeagText= villeag.getText();
        String quartieragText=quartierag.getText();
        String adresseagText=adresseag.getText();
        String salaireagText=salaireag.getText();
        String date_emagText=date_emag.getText();
        String useragText=userag.getText();
        String mdpagText=mdpag.getText();
        String query = "INSERT INTO `avocat`(`nomUtilisateur`, `nom`, `prenom`, `sexe`, `telephone`, `email`, `pays`, `ville`, `quartier`, `adresse`, `date_Embauche`, `CNI`, `salaire`, `statut`, `mdp`,`detail`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, useragText);
        preparedStatement.setString(2,nomagText);
        preparedStatement.setString(3, prenomagText);
        preparedStatement.setString(4, sexeagText);
        preparedStatement.setString(5, telagText);
        preparedStatement.setString(6, emailagText);
        preparedStatement.setString(7, paysagText);
        preparedStatement.setString(8, villeagText);
        preparedStatement.setString(9, quartieragText);
        preparedStatement.setString(10, adresseagText);
        preparedStatement.setString(11, date_emagText);
        preparedStatement.setString(12, cniagText);
        preparedStatement.setString(13, salaireagText);
        preparedStatement.setString(14, fonctionagText);
        preparedStatement.setString(15, mdpagText);
        preparedStatement.setString(16, detailagText);
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

