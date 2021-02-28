package Views.CercaView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import Controllers.ViewsController;
import Models.Prenotazione;
import Views.Tables.TableModelPrenotazione;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class CercaPrenotazioniPanel extends JPanel {

	private JScrollPane scrollPane;
	private JTable table;
	private TableModelPrenotazione tableModelPrenotazione;
	
	private JPanel buttonsPanel;
	private JButton modificaButton;
	private JButton eliminaButton;
	
	private CercaView cercaView;
//	private ViewsController viewsController;
	
    public CercaPrenotazioniPanel(LinkedList<Prenotazione> prenotazioni, ViewsController controller, CercaView cercaView) {
    	this.cercaView = cercaView;
//    	this.viewsController = controller;
    	
    	setLayout(new BorderLayout());
    	setBackground(new Color(0, 0, 153));
    	
    	tableModelPrenotazione = new TableModelPrenotazione(controller);
		table = new JTable(tableModelPrenotazione);
		tableModelPrenotazione.setData(prenotazioni);
		scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color (0, 0, 153));
		DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
		tableRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tableRenderer); //ID
		table.getColumnModel().getColumn(0).setPreferredWidth(240);
		table.getColumnModel().getColumn(1).setCellRenderer(tableRenderer); //Codice
		table.getColumnModel().getColumn(1).setMinWidth(40);
		table.getColumnModel().getColumn(1).setMaxWidth(60);
		table.getColumnModel().getColumn(2).setCellRenderer(tableRenderer); //Compagnia Aerea
		table.getColumnModel().getColumn(2).setMinWidth(50);
		table.getColumnModel().getColumn(3).setCellRenderer(tableRenderer); //ID Tratta
		table.getColumnModel().getColumn(3).setMinWidth(30);
		table.getColumnModel().getColumn(3).setMaxWidth(60);
		table.getColumnModel().getColumn(4).setCellRenderer(tableRenderer); //Nome
		table.getColumnModel().getColumn(4).setMinWidth(80);
		table.getColumnModel().getColumn(5).setCellRenderer(tableRenderer); //Cognome
		table.getColumnModel().getColumn(5).setMinWidth(100);
		table.getColumnModel().getColumn(6).setCellRenderer(tableRenderer); //Cento Kilometri
		table.getColumnModel().getColumn(6).setMinWidth(80);
		table.getColumnModel().getColumn(7).setCellRenderer(tableRenderer); //Coda
		table.getColumnModel().getColumn(7).setMinWidth(60);
		table.getColumnModel().getColumn(7).setMaxWidth(80);
		table.getColumnModel().getColumn(8).setCellRenderer(tableRenderer); //Imbarcato
		table.getColumnModel().getColumn(8).setMinWidth(40);
		table.getColumnModel().getColumn(8).setMaxWidth(65);
		scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		//table.setFillsViewportHeight(true);
		
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
					TableModelPrenotazione model = (TableModelPrenotazione) table.getModel();
					for (int i = 0; i < table.getColumnCount(); i++) {
						model.getValueAt(table.getSelectedRow(), i);
					}
					showModificaPrenotazioneDialog(model);
				}
				else {
					JOptionPane.showMessageDialog(CercaPrenotazioniPanel.this, "Seleziona un riga da modificare", "Errore modifica", JOptionPane.ERROR_MESSAGE);
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
					int choice = JOptionPane.showOptionDialog(CercaPrenotazioniPanel.this, "Vuoi davvero eliminare la prenotazione selezionata?", "Conferma cancellazione", 
															  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]); 
					if (choice == JOptionPane.YES_OPTION) {
						TableModelPrenotazione model = (TableModelPrenotazione) table.getModel();
						model.removeRow(table.getSelectedRow());
					}
				}
				else {
					JOptionPane.showMessageDialog(CercaPrenotazioniPanel.this, "Seleziona un riga da cancellare", "Errore cancellazione", JOptionPane.ERROR_MESSAGE);
				}
			}
		});				
		buttonsPanel.add(modificaButton);
		buttonsPanel.add(eliminaButton);
		return buttonsPanel;
	}
    
    public void showModificaPrenotazioneDialog(TableModelPrenotazione model) {
		JDialog prenotazioneDialog = new JDialog(cercaView, "Modifica", true);
		prenotazioneDialog.setSize(new Dimension(300,250));
		prenotazioneDialog.setResizable(false);
		prenotazioneDialog.setLayout(new BorderLayout());
		
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuPanel.setBackground(new Color(0, 204, 255));
		JLabel menuLabel = new JLabel("Modifica Gate");
		menuLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		menuLabel.setForeground(Color.WHITE);
		menuPanel.add(menuLabel);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(new Color (0, 0, 153));
		JPanel textPanel = new JPanel();
		textPanel.setBackground(new Color (0, 0, 153));
		JTextField gateTextField = new JTextField();
		gateTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		gateTextField.setColumns(3);
		gateTextField.setText(model.getValueAt(table.getSelectedRow(), 0).toString());
		textPanel.add(gateTextField);
		mainPanel.add(textPanel, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel(new FlowLayout());
		bottomPanel.setBackground(new Color (0, 153, 255));
		JButton aggiornaButton = new JButton("Aggiorna");
		aggiornaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				if (model.updateRow(gateTextField.getText(), table.getSelectedRow()))
//						prenotazioneDialog.dispose();
			}
		});
		
		bottomPanel.add(aggiornaButton);
		
		prenotazioneDialog.add(menuPanel, BorderLayout.NORTH);
		prenotazioneDialog.add(mainPanel, BorderLayout.CENTER);
		prenotazioneDialog.add(bottomPanel, BorderLayout.SOUTH);
		
		prenotazioneDialog.setLocationRelativeTo(null);
		prenotazioneDialog.setVisible(true);
		
	}	

}
