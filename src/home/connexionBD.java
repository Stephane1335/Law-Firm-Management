package home;



import home.Db;import org.slf4j.Logger;
import org.slf4j.LoggerFactory;import java.sql.* ;

public class connexionBD {
    private static final Logger LOGGER = LoggerFactory.getLogger(connexionBD.class);

    public Connection cn = null;
    public static Connection connexionDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn=Db.getConnection() /* connection centralized in home.Db (config.properties) */;
            System.out.println("cnx reussie");
            return cn;

        }catch(Exception e) {
            e.LOGGER.error("Unexpected exception", e);
            System.out.println("cnx ECHOUEE");
            return null;

        }

    }

}
