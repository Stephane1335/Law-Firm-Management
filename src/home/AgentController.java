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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

public class AgentController implements Initializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(AgentController.class);

    ResultSet result =null;
    Connection cnx =connexionBD.connexionDB();

    @FXML
    private Button btnaddDos;
    @FXML
    private Label nomuser;
    @FXML
    private Button btnDos;
    @FXML
    private TableView<Agent> agentTableView;
    @FXML
    private TableColumn<Agent, Integer> id_Avocat;
    @FXML
    private TableColumn<Agent, String> nomAvocat;
    @FXML
    private TableColumn<Agent, String> prenomAvocat;
    @FXML
    private TableColumn<Agent, String> fonctionAvocat;
    @FXML
    private TableColumn<Agent, Date> date_em;
    @FXML
    private TableColumn<Agent, String> editCol;
    Agent agent = null ;
    ObservableList<Agent> agents = FXCollections.observableArrayList();


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
    public void handleClicks(ActionEvent actionEvent) throws  Exception{

    }
    public void Add(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ajoutagentaso.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    private void loadDate() {


        refreshTable();
        id_Avocat.setCellValueFactory(new PropertyValueFactory<>("id_Avocat"));
        nomAvocat.setCellValueFactory(new PropertyValueFactory<>("nomAvocat"));
        prenomAvocat.setCellValueFactory(new PropertyValueFactory<>("prenomAvocat"));
        fonctionAvocat.setCellValueFactory(new PropertyValueFactory<>("fonctionAvocat"));
        date_em.setCellValueFactory(new PropertyValueFactory<>("date_em"));

        Callback<TableColumn<Agent, String>, TableCell<Agent, String>> cellFoctory = (TableColumn<Agent, String> param) -> {
            final TableCell<Agent, String> cell = new TableCell<>() {
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
                                agent = agentTableView.getSelectionModel().getSelectedItem();
                                int i = +agent.getId_Avocat();
                                Preferences userPreferences = Preferences.userRoot();
                                userPreferences.put("id_Avocat", String.valueOf(i));
                                Parent root = FXMLLoader.load(getClass().getResource("detailagentaso.fxml"));
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
        agentTableView.setItems(agents);
    }
    @FXML
    private void refreshTable() {
        try {
            agents.clear();
            String i = null;
            String query = "SELECT * FROM `avocat`";
            PreparedStatement preparedStatement = cnx.prepareStatement(query);
            result = preparedStatement.executeQuery();

            while (result.next()){
                agents.add(new Agent(
                        result.getInt("id_Avocat"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("statut"),
                        result.getDate("date_Embauche")));
                agentTableView.setItems(agents);
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
