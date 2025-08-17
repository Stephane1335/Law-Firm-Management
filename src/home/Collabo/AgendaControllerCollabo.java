package home.Collabo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;import home.Agenda;
import home.Controller;
import home.connexionBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

public class AgendaControllerCollabo implements Initializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(AgendaControllerCollabo.class);

    ResultSet result =null;
    Connection cnx = connexionBD.connexionDB();

    @FXML
    private Button btnaddDos;
    @FXML
    private Label nomuser;
    @FXML
    private Button btnDos;
    @FXML
    private TableView<Agenda> agendaTableView;
    @FXML
    private TableColumn<Agenda, Date> daterdv;
    @FXML
    private TableColumn<Agenda, Integer> id_rdv;
    @FXML
    private TableColumn<Agenda, String> heurerdv;
    @FXML
    private TableColumn<Agenda, String> lieurdv;
    @FXML
    private TableColumn<Agenda, String> objetrdv;
    @FXML
    private TableColumn<Agenda, String> clientrdv;
    @FXML
    private TableColumn<Agenda, String> dossierrdv;
    Agenda agenda = null ;
    ObservableList<Agenda> agendas = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDate();
        try {
            Preferences userPreferences = Preferences.userRoot();
            String i = null;
            String info = userPreferences.get("User", i);
            String query = "SELECT * FROM `avocat`WHERE nomUtilisateur=?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setString(1, info);
            result = preparedStatement.executeQuery();
            result.next();
            nomuser.setText(String.valueOf(result.getString("nomUtilisateur")));
        } catch (SQLException throwables) {
            throwables.LOGGER.error("Unexpected exception", e);
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
    public void handleClicks(ActionEvent actionEvent) throws  Exception{

    }

    private void loadDate() {


        refreshTable();
        id_rdv.setCellValueFactory(new PropertyValueFactory<>("id_rdv"));
        daterdv.setCellValueFactory(new PropertyValueFactory<>("daterdv"));
        heurerdv.setCellValueFactory(new PropertyValueFactory<>("heurerdv"));
        lieurdv.setCellValueFactory(new PropertyValueFactory<>("lieurdv"));
        objetrdv.setCellValueFactory(new PropertyValueFactory<>("objetrdv"));
        clientrdv.setCellValueFactory(new PropertyValueFactory<>("clientrdv"));
        dossierrdv.setCellValueFactory(new PropertyValueFactory<>("dossierrdv"));
        Callback<TableColumn<Agenda, String>, TableCell<Agenda, String>> cellFoctory = (TableColumn<Agenda, String> param) -> {
            final TableCell<Agenda, String> cell = new TableCell<>() {
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
        agendaTableView.setItems(agendas);
    }
    @FXML
    private void refreshTable() {
        try {
            agendas.clear();
            Preferences userPreferences = Preferences.userRoot();
            String i = null;
            String info = userPreferences.get("User", i);
            String query = "SELECT * FROM `Agenda` WHERE nomAvocat=?";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            preparedStatement.setString(1, info);
            result = preparedStatement.executeQuery();

            while (result.next()){
                agendas.add(new Agenda(
                        result.getInt("id_rdv"),
                        result.getDate("date_Rdv"),
                        result.getString("heure_Rdv"),
                        result.getString("lieu_Rdv"),
                        result.getString("objet_Rdv"),
                        result.getString("client_Rdv"),
                        result.getString("dossier_Rdv")));
                agendaTableView.setItems(agendas);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Logout(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
