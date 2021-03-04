package Views.CercaView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import Controllers.ViewsController;
import Models.Stato;
import Models.Tratta;
import Views.Tables.TableModelTratta;

@SuppressWarnings("serial")
public class CercaTrattePanel extends JPanel {

	private JScrollPane scrollPane;
	private JTable table;
	private TableModelTratta tableModel;
	
	private JPanel buttonsPanel;
	private JButton modificaButton;
	private JButton eliminaButton;
	
	private CercaView cercaView;
	private ViewsController controller;
	
    public CercaTrattePanel(LinkedList<Tratta> tratte, ViewsController viewsController, CercaView cercaView) {
    	this.cercaView = cercaView;
    	controller = viewsController;
    	
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
				if (table.getSelectedRow() != -1) {
					TableModelTratta model = (TableModelTratta) table.getModel();
					for (int i = 0; i < table.getColumnCount(); i++) {
						model.getValueAt(table.getSelectedRow(), i);
					}
					if (model.getValueAt(table.getSelectedRow(), 8) == Stato.IN_ATTESA)
						showModificaTrattaDialog(model);
					else
						JOptionPane.showMessageDialog(CercaTrattePanel.this, "Non puoi modificare una tratta in corso o già conclusa!", "Errore modifica", JOptionPane.ERROR_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(CercaTrattePanel.this, "Seleziona un riga da modificare", "Errore modifica", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		eliminaButton = new JButton("Elimina");
		eliminaButton.setFocusPainted(false);
		eliminaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object[] options = {"Sì", "No"};
				if (table.getSelectedRow() != -1) {
					int choice = JOptionPane.showOptionDialog(CercaTrattePanel.this, "Vuoi davvero eliminare la tratta selezionata?", "Conferma cancellazione", 
															  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]); 
					if (choice == JOptionPane.YES_OPTION) {
						TableModelTratta model = (TableModelTratta) table.getModel();
						model.removeRow(table.getSelectedRow());
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
	
	public void showModificaTrattaDialog(TableModelTratta model) {
		JDialog trattaDialog = new JDialog(cercaView, "Modifica", true);
		trattaDialog.setSize(new Dimension(800,600));
		trattaDialog.setResizable(false);
		trattaDialog.setLayout(new BorderLayout());
		
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuPanel.setBackground(new Color(0, 153, 255));
		JLabel menuLabel = new JLabel("   Modifica Tratta");
		menuLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		menuLabel.setForeground(Color.WHITE);
		menuPanel.add(menuLabel);
		
		TrattaDialogPanel trattaDialogPanel = new TrattaDialogPanel(controller, cercaView, trattaDialog, model);
		trattaDialogPanel.setIdTratta((int) model.getValueAt(table.getSelectedRow(), 0));
		trattaDialogPanel.setDestinazione(model.getValueAt(table.getSelectedRow(), 1).toString());
		trattaDialogPanel.setCompagniaAerea(model.getValueAt(table.getSelectedRow(), 2).toString());
		trattaDialogPanel.setInizioImbarcoStimato(model.getValueAt(table.getSelectedRow(), 3).toString());
		trattaDialogPanel.setFineImbarcoStimato(model.getValueAt(table.getSelectedRow(), 5).toString());
		trattaDialogPanel.setGate(model.getValueAt(table.getSelectedRow(), 7).toString());
		trattaDialogPanel.setMaxPrenotazioni((int) model.getValueAt(table.getSelectedRow(), 10));
		trattaDialogPanel.setCode(controller.getCodaFromIdTratta((int) model.getValueAt(table.getSelectedRow(), 0)));
		
		trattaDialog.add(menuPanel, BorderLayout.NORTH);
		trattaDialog.add(trattaDialogPanel, BorderLayout.CENTER);

		trattaDialog.setLocationRelativeTo(null);
		trattaDialog.setVisible(true);
	}	

}
