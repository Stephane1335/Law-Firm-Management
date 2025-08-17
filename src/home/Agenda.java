package home;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;import java.util.Date;

public class Agenda {
    private static final Logger LOGGER = LoggerFactory.getLogger(Agenda.class);

    int id_rdv;
    Date daterdv;
    String heurerdv;
    String lieurdv;
    String objetrdv;
    String clientrdv;
    String dossierrdv;

    public Agenda(int id_rdv, Date daterdv, String heurerdv, String lieurdv, String objetrdv, String clientrdv, String dossierrdv) {
        this.id_rdv = id_rdv;
        this.daterdv = daterdv;
        this.heurerdv = heurerdv;
        this.lieurdv = lieurdv;
        this.objetrdv = objetrdv;
        this.clientrdv = clientrdv;
        this.dossierrdv = dossierrdv;
    }

    public int getId_rdv() {
        return id_rdv;
    }

    public void setId_rdv(int id_rdv) {
        this.id_rdv = id_rdv;
    }

    public Date getDaterdv() {
        return daterdv;
    }

    public void setDaterdv(Date daterdv) {
        this.daterdv = daterdv;
    }

    public String getHeurerdv() {
        return heurerdv;
    }

    public void setHeurerdv(String heurerdv) {
        this.heurerdv = heurerdv;
    }

    public String getLieurdv() {
        return lieurdv;
    }

    public void setLieurdv(String lieurdv) {
        this.lieurdv = lieurdv;
    }

    public String getObjetrdv() {
        return objetrdv;
    }

    public void setObjetrdv(String objetrdv) {
        this.objetrdv = objetrdv;
    }

    public String getClientrdv() {
        return clientrdv;
    }

    public void setClientrdv(String clientrdv) {
        this.clientrdv = clientrdv;
    }

    public String getDossierrdv() {
        return dossierrdv;
    }

    public void setDossierrdv(String dossierrdv) {
        this.dossierrdv = dossierrdv;
    }
}
