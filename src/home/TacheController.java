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

public class TacheController implements Initializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(TacheController.class);

    ResultSet result =null;
    Connection cnx =connexionBD.connexionDB();
    @FXML
    private Button btnaddDos;
    @FXML
    private Label nomuser;
    @FXML
    private Button btnDos;
    @FXML
    private TableView<Tache> tacheTableView;
    @FXML
    private TableColumn<Tache, Integer> id_Tache;
    @FXML
    private TableColumn<Tache, String> nature;
    @FXML
    private TableColumn<Tache, Date> datefin;
    @FXML
    private TableColumn<Tache, String> nomAvocat;
    @FXML
    private TableColumn<Tache, String> description;
    @FXML
    private TableColumn<Tache, String> editCol;
    Tache tache = null;
    ObservableList<Tache> taches = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
    public void handleClicks(ActionEvent event) throws Exception{

    }
    public void Add(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ajouttacheaso.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private void loadDate() {
        refreshTable();
        id_Tache.setCellValueFactory(new PropertyValueFactory<>("id_Tache"));
        nature.setCellValueFactory(new PropertyValueFactory<>("nature"));
        datefin.setCellValueFactory(new PropertyValueFactory<>("datefin"));
        nomAvocat.setCellValueFactory(new PropertyValueFactory<>("nomAvocat"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        Callback<TableColumn<Tache, String>, TableCell<Tache, String>> cellFoctory = (TableColumn<Tache, String> param) -> {
            final TableCell<Tache, String> cell = new TableCell<>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button detail = new Button("Fini !");
                        detail.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-fx-text-fill:#000000;");
                        detail.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                tache = tacheTableView.getSelectionModel().getSelectedItem();
                                String query = "DELETE FROM `tache` WHERE `id_Tache`  ="+tache.getId_Tache();
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
        tacheTableView.setItems(taches);
    }
    @FXML
    private void refreshTable() {
        try {
            taches.clear();
            String i = null;
            String query = "SELECT * FROM `tache`";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            result = preparedStatement.executeQuery();
            while (result.next()){
                taches.add(new Tache(
                        result.getInt("id_Tache"),
                        result.getString("natureTache"),
                        result.getDate("dateFin"),
                        i = result.getString("nomAvocat"),
                        result.getString("description")));
                tacheTableView.setItems(taches);
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
