package home;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;


public class loginController implements Initializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(loginController.class);

    public PreparedStatement ps;
    public ResultSet result;

    public BorderPane bordepane1;


    Connection cnx=connexionBD.connexionDB();

    @FXML
    public TextField champNom;
    public BorderPane borderpane1;
    @FXML
    private PasswordField champPsswd;


    @FXML
    public void handleConnectUserButton(ActionEvent e) throws Exception{
        String userName= champNom.getText();
        String passwd=champPsswd.getText();
        String sql="SELECT * FROM avocat where nomUtilisateur=? AND mdp=? ";


            try {
                ps = cnx.prepareStatement(sql);
                ps.setString(1,userName);
                ps.setString(2,passwd);
                result = ps.executeQuery();
                if(result.next()) {


                    if(result.getString("statut").equals("Associé")){
                        bordepane1.getScene().getWindow().hide();
                        Stage home=new Stage();
                        String i = result.getString("id_Avocat");
                        Preferences userPreference = Preferences.userRoot();
                        userPreference.put("User",i);
                        try {
                            FXMLLoader fxmlLoader2 = new FXMLLoader(Main.class.getResource("Homeaso.fxml"));
                            Parent root2 = fxmlLoader2.load(getClass().getResource("Homeaso.fxml"));
                            Scene scene2 = new Scene(root2);
                            home.setScene(scene2);
                            home.show();
                        }catch (Exception b){
                            b.LOGGER.error("Unexpected exception", e);
                        }
                        }else if (result.getString("statut").equals("Collaborateur")){
                        bordepane1.getScene().getWindow().hide();
                        Stage home=new Stage();
                        String i = result.getString("nomUtilisateur");
                        Preferences userPreference = Preferences.userRoot();
                        userPreference.put("User",i);
                        try {
                            FXMLLoader fxmlLoader2 = new FXMLLoader(Main.class.getResource("Collabo/Homeaso.fxml"));
                            Parent root2 = fxmlLoader2.load(getClass().getResource("Collabo/Homeaso.fxml"));
                            Scene scene2 = new Scene(root2);
                            home.setScene(scene2);
                            home.show();
                        }catch (Exception b){
                            b.LOGGER.error("Unexpected exception", e);
                        }
                    }

                }

            }catch (SQLException i) {
                System.out.println("pas dans la BD");
                i.LOGGER.error("Unexpected exception", e);
            }
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}

