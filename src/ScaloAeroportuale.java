import Models.Gate;

import java.time.LocalDateTime;

public class ScaloAeroportuale {

    public static void main(String[] args) {
        /*Tratta tratta = new Tratta();
        tratta.destinazione = "MILANO";
        tratta.ora_inizio_imbarco = LocalDateTime.of(
                2021,
                01,
                20,
                17,
                30
        );
        System.out.println(tratta.getTratta().getYear());*/

        Gate gate = new Gate();
        gate.nome_gate = "1";
        gate.store();
    }
}
