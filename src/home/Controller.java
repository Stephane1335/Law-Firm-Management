package home;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

public class Controller implements Initializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);


    ResultSet result =null;
    Connection cnx =connexionBD.connexionDB();


    @FXML
    private VBox pnItems = null;
    @FXML
    private Label nomuser;
    @FXML
    private Label nbaf;
    @FXML
    private Label nbclt;
    @FXML
    private Label nbtch;

    @FXML
    private TableView<accueil> accueilTableView;
    @FXML
    private TableColumn<accueil, String> id_Dossier  ;
    @FXML
    private TableColumn<accueil, String> nomDossier;
    @FXML
    private TableColumn<accueil, String> nomClient;
    @FXML
    private TableColumn<accueil, String> nomAvocat;

    accueil accueil = null ;

    ObservableList<accueil> accueilsList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Preferences userPreferences = Preferences.userRoot();
            String i = null;
            String info = userPreferences.get("User", i);
            String query = "SELECT * FROM `avocat`WHERE id_Avocat=?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setString(1, info);
            result = preparedStatement.executeQuery();
            result.next();
            nomuser.setText(String.valueOf(result.getString("nomUtilisateur")));
        } catch (SQLException throwables) {
            throwables.LOGGER.error("Unexpected exception", e);
        }
        try {
            Preferences userPreferences = Preferences.userRoot();
            String i = null;
            String info = userPreferences.get("User", i);
            String query = "SELECT COUNT(*) AS total FROM `dossier`";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            result = preparedStatement.executeQuery();
            result.next();
            nbaf.setText(String.valueOf(result.getInt("total")));
        } catch (SQLException throwables) {
            throwables.LOGGER.error("Unexpected exception", e);
        }
        try {
            Preferences userPreferences = Preferences.userRoot();
            String i = null;
            String info = userPreferences.get("User", i);
            String query = "SELECT COUNT(*) AS total FROM `client`";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            result = preparedStatement.executeQuery();
            result.next();
            nbclt.setText(String.valueOf(result.getInt("total")));
        } catch (SQLException throwables) {
            throwables.LOGGER.error("Unexpected exception", e);
        }
        try {
            Preferences userPreferences = Preferences.userRoot();
            String i = null;
            String info = userPreferences.get("User", i);
            String query = "SELECT COUNT(*) AS total FROM `tache`";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            result = preparedStatement.executeQuery();
            result.next();
            nbtch.setText(String.valueOf(result.getInt("total")));
        } catch (SQLException throwables) {
            throwables.LOGGER.error("Unexpected exception", e);
        }
        try {
            Preferences userPreferences = Preferences.userRoot();
            String i = null;
            String info = userPreferences.get("User", i);
            String query = "SELECT * FROM `dossier`";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            result = preparedStatement.executeQuery();
            result.next();
        } catch (SQLException throwables) {
            throwables.LOGGER.error("Unexpected exception", e);
        }
        loadDate();
        }


        @FXML
    public void GoToHome(javafx.event.ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Homeaso.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void GoToDossier(javafx.event.ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("dossieraso.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void GoToClient(javafx.event.ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("cltaso.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void GoToAgent(javafx.event.ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("agentaso.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void GoToAgenda(javafx.event.ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("agendaso.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void GoToTache(javafx.event.ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("tacheaso.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void loadFxml(javafx.event.ActionEvent event) throws IOException {

    }

    public void handleClicks(ActionEvent event) {
    }
    private void loadDate() {


        refreshTable();

        id_Dossier.setCellValueFactory(new PropertyValueFactory<>("id_Dossier"));
        nomDossier.setCellValueFactory(new PropertyValueFactory<>("nomDossier"));
        nomAvocat.setCellValueFactory(new PropertyValueFactory<>("nomAvocat"));
        nomClient.setCellValueFactory(new PropertyValueFactory<>("nomClient"));

        Callback<TableColumn<accueil, String>, TableCell<accueil, String>> cellFoctory = (TableColumn<accueil, String> param) -> {
            final TableCell<accueil, String> cell = new TableCell<>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                    }
                }
            };
            return cell;
        };

        accueilTableView.setItems(accueilsList);
    }
    @FXML
    private void refreshTable() {
        try {
            accueilsList.clear();

            String query = "SELECT * FROM `dossier`";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            result = preparedStatement.executeQuery();

            while (result.next()){
                accueilsList.add(new accueil(
                        result.getInt("id_Dossier"),
                        result.getString("nomDossier"),
                        result.getString("nomClient"),
                        result.getString("nomAvocat")));
                accueilTableView.setItems(accueilsList);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Logout(javafx.event.ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}

