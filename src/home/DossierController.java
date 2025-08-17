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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;


public class DossierController implements Initializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(DossierController.class);

    ResultSet result =null;
    Connection cnx =connexionBD.connexionDB();

    @FXML
    private Button btnaddDos;
    @FXML
    private Label nomuser;
    @FXML
    private Button btnDos;
    @FXML
    private TableView<Dossier> dossierTableView;
    @FXML
    private TableColumn<Dossier, String> id_Dossier;
    @FXML
    private TableColumn<Dossier, String> nomDossier;
    @FXML
    private TableColumn<Dossier, String> nomClient;
    @FXML
    private TableColumn<Dossier, String> nomAvocat;
    @FXML
    private TableColumn<Dossier, Date> date_dossier;
    @FXML
    private TableColumn<Dossier, String> editCol;
    Dossier dossier = null ;
    ObservableList<Dossier> dossiers = FXCollections.observableArrayList();


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

    public void handleDos(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnDos) {
            Parent root5 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("detaildossieraso.fxml")));
            Stage window = (Stage) btnDos.getScene().getWindow();
            window.setScene(new Scene(root5, 1050, 576));
        }
        if (actionEvent.getSource() == btnaddDos) {
            Parent root6 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ajoutaffaireaso.fxml")));
            Stage window = (Stage) btnaddDos.getScene().getWindow();
            window.setScene(new Scene(root6, 1050, 576));
        }
    }
    private void loadDate() {


        refreshTable();

        id_Dossier.setCellValueFactory(new PropertyValueFactory<>("id_Dossier"));
        nomAvocat.setCellValueFactory(new PropertyValueFactory<>("nomDossier"));
        nomDossier.setCellValueFactory(new PropertyValueFactory<>("nomAvocat"));
        nomClient.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        date_dossier.setCellValueFactory(new PropertyValueFactory<>("date_dossier"));

        Callback<TableColumn<Dossier, String>, TableCell<Dossier, String>> cellFoctory = (TableColumn<Dossier, String> param) -> {
            final TableCell<Dossier, String> cell = new TableCell<>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button detail = new Button("Voir Plus");

                        detail.setStyle(
                                " -fx-cursor: hand ;"

                                        + "-fx-text-fill:#000000;");

                        detail.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                dossier = dossierTableView.getSelectionModel().getSelectedItem();
                                int i = +dossier.getId_Dossier();
                                Preferences userPreferences = Preferences.userRoot();
                                userPreferences.put("id", String.valueOf(i));
                                Parent root = FXMLLoader.load(getClass().getResource("detaildossieraso.fxml"));
                                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
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
        dossierTableView.setItems(dossiers);
    }
    @FXML
    private void refreshTable() {
        try {
            dossiers.clear();
            String query = "SELECT * FROM `dossier`";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            result = preparedStatement.executeQuery();

            while (result.next()){
                dossiers.add(new Dossier(
                        result.getInt("id_Dossier"),
                        result.getString("nomDossier"),
                        result.getString("nomClient"),
                        result.getString("nomAvocat"),
                        result.getDate("date_dossier")));
                dossierTableView.setItems(dossiers);

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

