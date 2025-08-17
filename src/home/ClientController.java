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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

public class ClientController implements Initializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);


    ResultSet result =null;
    Connection cnx =connexionBD.connexionDB();

    @FXML
    private Button btnaddDos;
    @FXML
    private Label nomuser;
    @FXML
    private Button btnDos;
    @FXML
    private TableView<Client> clientTableView;
    @FXML
    private TableColumn<Client, Integer> idclt;
    @FXML
    private TableColumn<Client, String> nomClient;
    @FXML
    private TableColumn<Client, String> contact;
    @FXML
    private TableColumn<Client, String> profession;
    @FXML
    private TableColumn<Client, String> adresse;
    @FXML
    private TableColumn<Client, String> editCol;
    Client client = null ;
    ObservableList<Client> clients = FXCollections.observableArrayList();

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
    public void handleClicks(ActionEvent actionEvent) {

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
    public void Add(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ajoutcltaso.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private void loadDate() {


        refreshTable();

        idclt.setCellValueFactory(new PropertyValueFactory<>("idclt"));
        nomClient.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
        contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        profession.setCellValueFactory(new PropertyValueFactory<>("profession"));
        adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        Callback<TableColumn<Client, String>, TableCell<Client, String>> cellFoctory = (TableColumn<Client, String> param) -> {
            final TableCell<Client, String> cell = new TableCell<>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button detail = new Button("Supprimer");

                        detail.setStyle(
                                          "-fx-cursor: hand ;"
                                        + "-fx-text-fill:#000000;");

                        detail.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                client = clientTableView.getSelectionModel().getSelectedItem();
                                String query = "DELETE FROM `client` WHERE `id_Clt`="+client.getIdclt();
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
        clientTableView.setItems(clients);
    }
    @FXML
    private void refreshTable() {
        try {
            clients.clear();
            String i = null;
            String query = "SELECT * FROM `client`";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            result = preparedStatement.executeQuery();

            while (result.next()){
                clients.add(new Client(
                        result.getInt("id_Clt"),
                        i = result.getString("nomClient"),
                        result.getString("contact"),
                        result.getString("profession"),
                        result.getString("adresse")));
                clientTableView.setItems(clients);
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
