package home;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;import java.util.Date;

public class Agent {
    private static final Logger LOGGER = LoggerFactory.getLogger(Agent.class);

    int id_Avocat;
    String nomAvocat;
    String prenomAvocat;
    String fonctionAvocat;
    Date date_em;

    public Agent(int id_Avocat, String nomAvocat, String prenomAvocat, String fonctionAvocat, Date date_em) {
        this.id_Avocat = id_Avocat;
        this.nomAvocat = nomAvocat;
        this.prenomAvocat = prenomAvocat;
        this.fonctionAvocat = fonctionAvocat;
        this.date_em = date_em;
    }

    public int getId_Avocat() {
        return id_Avocat;
    }

    public void setId_Avocat(int id_Avocat) {
        this.id_Avocat = id_Avocat;
    }

    public String getNomAvocat() {
        return nomAvocat;
    }

    public void setNomAvocat(String nomAvocat) {
        this.nomAvocat = nomAvocat;
    }

    public String getPrenomAvocat() {
        return prenomAvocat;
    }

    public void setPrenomAvocat(String prenomAvocat) {
        this.prenomAvocat = prenomAvocat;
    }

    public String getFonctionAvocat() {
        return fonctionAvocat;
    }

    public void setFonctionAvocat(String fonctionAvocat) {
        this.fonctionAvocat = fonctionAvocat;
    }

    public Date getDate_em() {
        return date_em;
    }

    public void setDate_em(Date date_em) {
        this.date_em = date_em;
    }
}
