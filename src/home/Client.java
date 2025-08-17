package home;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;public class Client {
    private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);

    int idclt;
    String nomClient;
    String contact;
    String profession;
    String adresse;

    public Client(int idclt, String nomClient, String contact, String profession, String adresse) {
        this.idclt = idclt;
        this.nomClient = nomClient;
        this.contact = contact;
        this.profession = profession;
        this.adresse = adresse;
    }

    public int getIdclt() {
        return idclt;
    }

    public void setIdclt(int idclt) {
        this.idclt = idclt;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
