package home;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;import java.util.Date;

public class Dossier {
    private static final Logger LOGGER = LoggerFactory.getLogger(Dossier.class);

    int id_Dossier;
    String nomAvocat;
    String nomDossier;
    String nomClient;
    Date date_dossier;

    public Dossier(int id_dossier, String nomAvocat, String nomDossier, String nomClient, Date date_dossier) {
        this.id_Dossier = id_dossier;
        this.nomAvocat = nomAvocat;
        this.nomDossier = nomDossier;
        this.nomClient = nomClient;
        this.date_dossier = date_dossier;
    }


    public int getId_Dossier() {
        return id_Dossier;
    }

    public void setId_Dossier(int id_Dossier) {
        this.id_Dossier = id_Dossier;
    }

    public String getNomAvocat() {
        return nomAvocat;
    }

    public void setNomAvocat(String nomAvocat) {
        this.nomAvocat = nomAvocat;
    }

    public String getNomDossier() {
        return nomDossier;
    }

    public void setNomDossier(String nomDossier) {
        this.nomDossier = nomDossier;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public Date getDate_dossier() {
        return date_dossier;
    }

    public void setDate_dossier(Date date_dossier) {
        this.date_dossier = date_dossier;
    }
}
