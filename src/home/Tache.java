package home;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;import java.sql.Date;

public class Tache {
    private static final Logger LOGGER = LoggerFactory.getLogger(Tache.class);

    int id_Tache;
    String nature;
    Date datefin;
    String nomAvocat;
    String description;


    public Tache(int id_Tache, String nature, Date datefin, String nomAvocat, String description) {
        this.id_Tache = id_Tache;
        this.nature = nature;
        this.datefin = datefin;
        this.nomAvocat = nomAvocat;
        this.description = description;
    }

    public int getId_Tache() {
        return id_Tache;
    }

    public void setId_Tache(int id_Tache) {
        this.id_Tache = id_Tache;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }



    public String getNomAvocat() {
        return nomAvocat;
    }

    public void setNomAvocat(String nomAvocat) {
        this.nomAvocat = nomAvocat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
