package Views.CercaView;

import Controllers.ViewsController;
import Models.CentoKilometri;
import Models.CompagniaAerea;
import Models.Gate;
import Models.Prenotazione;
import Models.Tratta;
import Views.TopPanel;
import Views.Tables.TableModelCentoKilometri;
import Views.Tables.TableModelCompagnia;
import Views.Tables.TableModelGate;
import Views.Tables.TableModelPrenotazione;
import Views.Tables.TableModelTratta;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class CercaView extends JFrame {

	private JPanel mainPanel;
	private TopPanel topPanel;
	private JPanel selectionPanel;
	private JLabel cercaLabel;
	private String[] items = {"Tratte", "Prenotazioni", "Cento Kilometri", "Compagnie Aeree", "Gates"};
	private JComboBox<String> cercaComboBox;
	private JButton okButton;
	private JPanel centerPanel;
	
	private JScrollPane scrollPane;
	private JTable table;
	private TableModelTratta tableModel;
	private TableModelPrenotazione tableModelPrenotazione;
	private TableModelCentoKilometri tableModelCentoKilometri;
	private TableModelCompagnia tableModelCompagnia;
	private TableModelGate tableModelGate;

	

	public CercaView (ViewsController controller) {
		setTitle("Scalo Aeroportuale - Cerca");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image logoImage = new ImageIcon (this.getClass().getResource("/aereo_logo.png")).getImage();
		setIconImage(logoImage);
		setBounds(175, 60, 1150, 650);
		setMinimumSize(new Dimension (1150,700));
	
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
		mainPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(mainPanel);
		
		topPanel = new TopPanel(controller, false);
		
		selectionPanel = new JPanel();
		selectionPanel.setBackground(new Color(0, 204, 255));
		selectionPanel.setLayout(new FlowLayout());
		selectionPanel.setMinimumSize(new Dimension(300,100));
		
		cercaLabel = new JLabel("Cerca in ");
		cercaLabel.setForeground(Color.WHITE);
		cercaLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		selectionPanel.add(cercaLabel);
		cercaComboBox = new JComboBox<String>(items);
		cercaComboBox.setSize(new Dimension (20,10));
		selectionPanel.add(cercaComboBox);
		okButton = new JButton("Ok");
		okButton.setFocusPainted(false);
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.loadCercaCenterPanel(cercaComboBox.getSelectedItem().toString());
			}
		});
		selectionPanel.add(okButton);
		topPanel.add(selectionPanel, BorderLayout.SOUTH);
		mainPanel.add(topPanel, BorderLayout.NORTH);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setBackground(new Color (0,0,153));
		centerPanel.setMinimumSize(new Dimension (500,100));
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
		        topPanel.UpdateBackButton();
		    }
		});
		
	}
	
	public void showListaTratte(LinkedList<Tratta> tratte, ViewsController controller) { 

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
		//table.setFillsViewportHeight(true);
		
		centerPanel.add(scrollPane, BorderLayout.CENTER);
		showButtonPanel(cercaComboBox.getSelectedItem().toString(), controller);
		mainPanel.revalidate();
	}
	
	public void showListaPrenotazioni(LinkedList<Prenotazione> prenotazioni, ViewsController controller) { 

		tableModelPrenotazione = new TableModelPrenotazione(controller);
		table = new JTable(tableModelPrenotazione);
		tableModelPrenotazione.setData(prenotazioni);
		scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color (0, 0, 153));
		DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
		tableRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tableRenderer); //ID
		//table.getColumnModel().getColumn(0).setMinWidth(240);
		table.getColumnModel().getColumn(0).setPreferredWidth(240);
		table.getColumnModel().getColumn(1).setCellRenderer(tableRenderer); //ID tratta
		table.getColumnModel().getColumn(1).setMinWidth(35);
		table.getColumnModel().getColumn(1).setMaxWidth(60);
		table.getColumnModel().getColumn(2).setCellRenderer(tableRenderer); //Nome
		table.getColumnModel().getColumn(3).setCellRenderer(tableRenderer); //Cognome
		table.getColumnModel().getColumn(4).setCellRenderer(tableRenderer); //Compagnia Aerea
		table.getColumnModel().getColumn(5).setCellRenderer(tableRenderer); //Codice Prenotazione
		table.getColumnModel().getColumn(5).setMinWidth(80);
		table.getColumnModel().getColumn(6).setCellRenderer(tableRenderer); //Cento Kilometri
		table.getColumnModel().getColumn(6).setMinWidth(100);
		table.getColumnModel().getColumn(7).setCellRenderer(tableRenderer); //Coda
		table.getColumnModel().getColumn(8).setCellRenderer(tableRenderer); //Imbarcato
		table.getColumnModel().getColumn(8).setMinWidth(40);
		table.getColumnModel().getColumn(8).setMaxWidth(65);
		scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		//table.setFillsViewportHeight(true);
		
		centerPanel.add(scrollPane, BorderLayout.CENTER);
		showButtonPanel(cercaComboBox.getSelectedItem().toString(), controller);
		mainPanel.revalidate();
	
	}
	
	public void showListaCompagnie(LinkedList<CompagniaAerea> compagnie, ViewsController controller) { 

		tableModelCompagnia = new TableModelCompagnia(controller);
		table = new JTable(tableModelCompagnia);
		tableModelCompagnia.setData(compagnie);
		scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color (0, 0, 153));
		DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
		tableRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tableRenderer);
		scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		//table.setFillsViewportHeight(true);
		
		centerPanel.add(scrollPane, BorderLayout.CENTER);
		showButtonPanel(cercaComboBox.getSelectedItem().toString(), controller);
		mainPanel.revalidate();
	
	}
	
	public void showListaGates(LinkedList<Gate> gates, ViewsController controller) { 

		tableModelGate = new TableModelGate(controller);
		table = new JTable(tableModelGate);
		tableModelGate.setData(gates);
		scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color (0, 0, 153));
		DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
		tableRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tableRenderer);
		scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		//table.setFillsViewportHeight(true);
		
		centerPanel.add(scrollPane, BorderLayout.CENTER);
		showButtonPanel(cercaComboBox.getSelectedItem().toString(), controller);
		mainPanel.revalidate();
	
	}

	public void showListaCentoKilometri(LinkedList<CentoKilometri> centoKilometri, ArrayList<String> clientiCKList, ViewsController controller) {
		tableModelCentoKilometri = new TableModelCentoKilometri(controller);
		table = new JTable(tableModelCentoKilometri);
		tableModelCentoKilometri.setData(centoKilometri, clientiCKList);
		scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color (0, 0, 153));
		DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
		tableRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tableRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(tableRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(tableRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(tableRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(tableRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(tableRenderer);
		scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		//table.setFillsViewportHeight(true);
		
		centerPanel.add(scrollPane, BorderLayout.CENTER);
		showButtonPanel(cercaComboBox.getSelectedItem().toString(), controller);
		mainPanel.revalidate();
	}

	public void showButtonPanel (String tableChoice, ViewsController controller) {
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonsPanel.setBackground(new Color(0, 153, 255));
		JButton modificaButton = new JButton("Modifica riga");
		modificaButton.setFocusPainted(false);
		modificaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRow() != -1) {
					switch (tableChoice) {
						case "Tratte": {
							
//							TableModelTratta model = (TableModelTratta) table.getModel();
//							
//							
//							showModificaTrattaDialog (controller, model);
//							
//							
//							
//							model.removeRow(table.getSelectedRow());
						}
							
						case "Prenotazioni": {
							
						}
						case "Cento Kilometri": {
							
						}
						case "Compagnie Aeree": {
							
						}
						case "Gates": {
							
						}
					}
				}
			}
		});
		
		JButton eliminaButton = new JButton("Elimina riga");
		eliminaButton.setFocusPainted(false);
		eliminaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object[] options = {"Sì", "No"};
				if (table.getSelectedRow() != -1) {
					switch (tableChoice) {
						case "Tratte": {
							int choice = JOptionPane.showOptionDialog(CercaView.this, "Vuoi davvero eliminare la tratta selezionata?", "Conferma cancellazione", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
									null,     //do not use a custom Icon
									options,  //the titles of buttons
									options[1]); //default button title
							if (choice == JOptionPane.YES_OPTION) {
								TableModelTratta model = (TableModelTratta) table.getModel();
								model.removeRow(table.getSelectedRow());
								//table.getSelectedRows(); per eliminare tutte le righe selezionate
							}
							break;
						}
						case "Prenotazioni": {
							int choice = JOptionPane.showOptionDialog(CercaView.this, "Vuoi davvero eliminare la prenotazione selezionata?", "Conferma cancellazione", 
																	  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]); 
							if (choice == JOptionPane.YES_OPTION) {
								TableModelPrenotazione model = (TableModelPrenotazione) table.getModel();
								model.removeRow(table.getSelectedRow());
							}
							break;
						}
						case "Cento Kilometri": {
							int choice = JOptionPane.showOptionDialog(CercaView.this, "Vuoi davvero eliminare il cento kilometri selezionato?", "Conferma cancellazione", 
																	  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]); 
							if (choice == JOptionPane.YES_OPTION) {
								TableModelCentoKilometri model = (TableModelCentoKilometri) table.getModel();
								model.removeRow(table.getSelectedRow());
							}
							break;
						}
						case "Compagnie Aeree": {
							int choice = JOptionPane.showOptionDialog(CercaView.this, "Vuoi davvero eliminare la compagnia aerea selezionata?", "Conferma cancellazione", 
																	  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]); 
							if (choice == JOptionPane.YES_OPTION) {
								TableModelCompagnia model = (TableModelCompagnia) table.getModel();
								model.removeRow(table.getSelectedRow());
							}
							break;
						}
						case "Gates": {
							int choice = JOptionPane.showOptionDialog(CercaView.this, "Vuoi davvero eliminare il gate selezionato?", "Conferma cancellazione", 
																	  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]); 
							if (choice == JOptionPane.YES_OPTION) {
								TableModelGate model = (TableModelGate) table.getModel();
								model.removeRow(table.getSelectedRow());
							}
							break;
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(CercaView.this, "Seleziona un riga da cancellare", "Errore cancellazione", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buttonsPanel.add(modificaButton);
		buttonsPanel.add(eliminaButton);
		centerPanel.add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	public void showModificaTrattaDialog (ViewsController controller, AbstractTableModel model) {
		JDialog codaDialog = new JDialog(CercaView.this, "Modifica", true);
    	codaDialog.setMinimumSize(new Dimension(650,450));
    	codaDialog.setLayout(new BorderLayout());
    	for (int i = 0; i < table.getColumnCount(); i++) {
    		model.getValueAt(table.getSelectedRow(), i);
    	}
    	

	}
	
	public void emptyCenterPanel () {
		if (centerPanel != null) {
			centerPanel.removeAll();
			centerPanel.repaint();
		}
	}
}
