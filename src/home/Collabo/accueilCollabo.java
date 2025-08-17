package home.Collabo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;public class accueilCollabo {
    private static final Logger LOGGER = LoggerFactory.getLogger(accueilCollabo.class);

    int id_Dossier;
    String nomAvocat;
    String nomDossier;
    String nomClient;

    public accueilCollabo(int id_Dossier, String nomDossier, String nomClient, String nomAvocat) {
        this.id_Dossier = id_Dossier;
        this.nomDossier = nomDossier;
        this.nomClient = nomClient;
        this.nomAvocat = nomAvocat;
    }

    public int getId_Dossier() {
        return id_Dossier;
    }

    public void setId_Dossier(int id_Dossier) {
        this.id_Dossier = id_Dossier;
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

    public String getNomAvocat() {
        return nomAvocat;
    }

    public void setNomAvocat(String nomAvocat) {
        this.nomAvocat = nomAvocat;
    }


}
