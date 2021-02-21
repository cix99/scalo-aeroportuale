package Views.CercaView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import Controllers.ViewsController;
import Models.Tratta;
import Views.Tables.TableModelTratta;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class CercaTrattePanel extends JPanel {

	private JScrollPane scrollPane;
	private JTable table;
	private TableModelTratta tableModel;
	
	private JPanel buttonsPanel;
	private JButton modificaButton;
	private JButton eliminaButton;
	
	private CercaView cercaView;
	
    public CercaTrattePanel(LinkedList<Tratta> tratte, ViewsController controller, CercaView cercaView) {
    	this.cercaView = cercaView;
    	
    	setLayout(new BorderLayout());
    	
    	tableModel = new TableModelTratta(controller);
		table = new JTable(tableModel);
		tableModel.setData(tratte);
		scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color (0, 0, 153));
		DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
		tableRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tableRenderer); //ID
		table.getColumnModel().getColumn(0).setMaxWidth(30);
		table.getColumnModel().getColumn(1).setCellRenderer(tableRenderer);	//Destinazione
		table.getColumnModel().getColumn(2).setCellRenderer(tableRenderer); //Compagnia Aerea
		table.getColumnModel().getColumn(3).setCellRenderer(tableRenderer); //Inizio imbarco stimato
		table.getColumnModel().getColumn(4).setCellRenderer(tableRenderer);	//Inizio imbarco effettivo
		table.getColumnModel().getColumn(5).setCellRenderer(tableRenderer); //Fine imbarco stimato
		table.getColumnModel().getColumn(6).setCellRenderer(tableRenderer);	//Fine imbarco effettivo
		table.getColumnModel().getColumn(7).setCellRenderer(tableRenderer);	//Gate
		table.getColumnModel().getColumn(7).setMaxWidth(35);
		table.getColumnModel().getColumn(8).setCellRenderer(tableRenderer); //Stato imbarco
		table.getColumnModel().getColumn(9).setCellRenderer(tableRenderer); //Ritardo
		table.getColumnModel().getColumn(9).setMinWidth(30);
		table.getColumnModel().getColumn(9).setMaxWidth(50);
		table.getColumnModel().getColumn(10).setCellRenderer(tableRenderer); //Max Prenotazioni
		table.getColumnModel().getColumn(10).setMinWidth(30);
		table.getColumnModel().getColumn(10).setMaxWidth(110);
		scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		//table.setFillsViewportHeight(false);
		
		add(scrollPane, BorderLayout.CENTER);
    }

	public JPanel getButtonsPanel() {
		buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.setBackground(new Color(0, 153, 255));
		modificaButton = new JButton("Modifica");
		modificaButton.setFocusPainted(false);
		modificaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showModificaTrattaDialog();
			}
			
		});
		eliminaButton = new JButton("Elimina");
		eliminaButton.setFocusPainted(false);
		eliminaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object[] options = {"Sì", "No"};
				if (table.getSelectedRow() != -1) {
					int choice = JOptionPane.showOptionDialog(CercaTrattePanel.this, "Vuoi davvero eliminare la tratta selezionata?", "Conferma cancellazione", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
							null,     //do not use a custom Icon
							options,  //the titles of buttons
							options[1]); //default button title
					if (choice == JOptionPane.YES_OPTION) {
						TableModelTratta model = (TableModelTratta) table.getModel();
						model.removeRow(table.getSelectedRow());
						//table.getSelectedRows(); per eliminare tutte le righe selezionate
					}
				}
				else {
					JOptionPane.showMessageDialog(CercaTrattePanel.this, "Seleziona un riga da cancellare", "Errore cancellazione", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buttonsPanel.add(modificaButton);
		buttonsPanel.add(eliminaButton);
		return buttonsPanel;
	}
	
	public void showModificaTrattaDialog() {
		JDialog codaDialog = new JDialog(cercaView, "Modifica", true);
		codaDialog.setMinimumSize(new Dimension(650,450));
		codaDialog.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 153));
		codaDialog.add(panel);
		TableModelTratta model = (TableModelTratta) table.getModel();
		if (table.getSelectedRow() != -1) {
			for (int i = 0; i < table.getColumnCount(); i++) {
				model.getValueAt(table.getSelectedRow(), i);
			}
		}
		else {
			JOptionPane.showMessageDialog(CercaTrattePanel.this, "Seleziona un riga da modificare", "Errore modifica", JOptionPane.ERROR_MESSAGE);
		}
		
		codaDialog.setLocationRelativeTo(null);
		codaDialog.setVisible(true);
		
	}

}
