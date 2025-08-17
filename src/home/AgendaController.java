package home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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

public class AgendaController implements Initializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(AgendaController.class);

    ResultSet result =null;
    Connection cnx =connexionBD.connexionDB();

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
    @FXML
    private TableColumn<Agenda, String> editCol;
    Agenda agenda = null ;
    ObservableList<Agenda> agendas = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDate();
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
    public void handleClicks(ActionEvent actionEvent) throws  Exception{

    }
    public void Add(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ajoutagendaaso.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

                        Button detail = new Button("Fini!");

                        detail.setStyle(
                                " -fx-cursor: hand ;"

                                        + "-fx-text-fill:#000000;");

                        detail.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                agenda = agendaTableView.getSelectionModel().getSelectedItem();
                                String query = "DELETE FROM `agenda` WHERE `id_rdv`  ="+agenda.getId_rdv();
                                PreparedStatement preparedStatement = cnx.prepareStatement(query);
                                preparedStatement.execute();
                                refreshTable();
                            } catch (SQLException throwables) {
                                throwables.LOGGER.error("Unexpected exception", e);
                            }
                        });


                        HBox managebtn = new HBox(detail);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(detail, new Insets(1, 1, 0, 1));
                        setGraphic(managebtn);
                        setText(null);
                    }
                }
            };
            return cell;
        };
        editCol.setCellFactory(cellFoctory);
        agendaTableView.setItems(agendas);
    }
    @FXML
    private void refreshTable() {
        try {
            agendas.clear();
            String i = null;
            String query = "SELECT * FROM `agenda`";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            result = preparedStatement.executeQuery();

            while (result.next()){
                agendas.add(new Agenda(
                        result.getInt("id_rdv"),
                        result.getDate("date_Rdv"),
                        i = result.getString("heure_Rdv"),
                        result.getString("lieu_Rdv"),
                        result.getString("objet_Rdv"),
                        result.getString("client_Rdv"),
                        result.getString("dossier_Rdv")));
                agendaTableView.setItems(agendas);
                Preferences userPreferences = Preferences.userRoot();
                userPreferences.put("id", i);
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
