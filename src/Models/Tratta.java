package Models;

import java.time.LocalDateTime;

enum Stato {
    IN_ATTESA,
    IN_CORSO,
    CONCLUSO
}

public class Tratta extends Model {

    protected String table_name = "tratta";

    public String id;
    public String destinazione;
    public LocalDateTime ora_inizio_imbarco;
    public LocalDateTime ora_fine_imbarco_stimato;
    public LocalDateTime ora_fine_imbarco_effettivo;
    public String gate;
    public Stato stato_imbarco;
    public Boolean ritardo;


    public void store(){
        /*query = "INSERT INTO " + this.table_name + " ";
        this.connection.createStatement(query);*/
    }

    public LocalDateTime getTratta() {
        return ora_inizio_imbarco;
    }
}
