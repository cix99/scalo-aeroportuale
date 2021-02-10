package Views.CercaView;

import javax.swing.*;
import java.awt.*;

public class CercaTratteView extends JPanel {

    public CercaTratteView() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(0,0,0));

        String column[]={
                "ID","Destinazione","Compagnia Aerea", "Inizio Imbarco Stimato",
                "Inzio Imbarco Effettivo", "Fine Imbarco Stimato", "Fine Imbarco Effettivo"
        };

        String data[][]={
                {"936fbf46-225a-4d8d-91ff-bebec688a2dd","Roma","Ryanair", "08-02-2021 22:08", "08-02-2021 22:08", "08-02-2021 22:08", "08-02-2021 22:08"},
                {"936fbf46-225a-4d8d-91ff-bebec688a2dd","Roma","Ryanair", "08-02-2021 22:08", "08-02-2021 22:08", "08-02-2021 22:08", "08-02-2021 22:08"},
                {"936fbf46-225a-4d8d-91ff-bebec688a2dd","Roma","Ryanair", "08-02-2021 22:08", "08-02-2021 22:08", "08-02-2021 22:08", "08-02-2021 22:08"},
                {"936fbf46-225a-4d8d-91ff-bebec688a2dd","Roma","Ryanair", "08-02-2021 22:08", "08-02-2021 22:08", "08-02-2021 22:08", "08-02-2021 22:08"},
                {"936fbf46-225a-4d8d-91ff-bebec688a2dd","Roma","Ryanair", "08-02-2021 22:08", "08-02-2021 22:08", "08-02-2021 22:08", "08-02-2021 22:08"},
                {"936fbf46-225a-4d8d-91ff-bebec688a2dd","Roma","Ryanair", "08-02-2021 22:08", "08-02-2021 22:08", "08-02-2021 22:08", "08-02-2021 22:08"},
        };

        JTable tratteTable = new JTable(data,column);
        tratteTable.setFillsViewportHeight(true);
        JScrollPane sp = new JScrollPane(tratteTable);

        this.add(sp, BorderLayout.CENTER);
    }

}
