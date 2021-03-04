package Views.CercaView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.ListIterator;

import Controllers.ViewsController;
import Models.CentoKilometri;
import Models.Coda;
import Models.Prenotazione;
import Views.Tables.TableModelPrenotazione;

@SuppressWarnings("serial")
public class CercaPrenotazioniPanel extends JPanel {

	private JScrollPane scrollPane;
	private JTable table;
	private TableModelPrenotazione tableModelPrenotazione;
	private JPanel buttonsPanel;
	private JButton modificaButton;
	private JButton eliminaButton;
	
	private CercaView cercaView;
	private ViewsController controller;
	
    public CercaPrenotazioniPanel(LinkedList<Prenotazione> prenotazioni, ViewsController viewsController, CercaView cercaView) {
    	this.cercaView = cercaView;
    	controller = viewsController;
    	
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
		prenotazioneDialog.setSize(new Dimension(800,600));
		prenotazioneDialog.setResizable(false);
		prenotazioneDialog.setLayout(new BorderLayout());
		
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuPanel.setBackground(new Color(0, 204, 255));
		JLabel menuLabel = new JLabel("Modifica Prenotazione");
		menuLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		menuLabel.setForeground(Color.WHITE);
		menuPanel.add(menuLabel);
		
		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(new Color (0, 0, 153));
		
		JPanel idPanel = new JPanel(new BorderLayout());
		idPanel.setBackground(new Color (0, 0, 153));
		JPanel idTrattaPanel =  new JPanel(new BorderLayout());
		idTrattaPanel.setBackground(new Color (0, 0, 153));
		JPanel compagniaPanel = new JPanel(new BorderLayout());
		compagniaPanel.setBackground(new Color (0, 0, 153));
		JPanel codicePanel = new JPanel(new BorderLayout());
		codicePanel.setBackground(new Color (0, 0, 153));
		JPanel nomePanel = new JPanel(new BorderLayout());
		nomePanel.setBackground(new Color (0, 0, 153));
		JPanel cognomePanel = new JPanel(new BorderLayout());
		cognomePanel.setBackground(new Color (0, 0, 153));
		JPanel centoKilometriPanel = new JPanel(new BorderLayout());
		centoKilometriPanel.setBackground(new Color (0, 0, 153));
		JPanel codaPanel = new JPanel(new BorderLayout());
		codaPanel.setBackground(new Color (0, 0, 153));
		
		JLabel idLabel = new JLabel("ID Prenotazione");
		idLabel.setForeground(Color.WHITE);
		idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		JLabel idTrattaLabel = new JLabel("ID Tratta");
		idTrattaLabel.setForeground(Color.WHITE);
		idTrattaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		JLabel compagniaLabel = new JLabel("Compagnia");
		compagniaLabel.setForeground(Color.WHITE);
		compagniaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		JLabel codiceLabel = new JLabel("Codice Prenotazione");
		codiceLabel.setForeground(Color.WHITE);
		codiceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		JLabel nomeLabel = new JLabel("Nome");
		nomeLabel.setForeground(Color.WHITE);
		nomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		JLabel cognomeLabel = new JLabel("Cognome");
		cognomeLabel.setForeground(Color.WHITE);
		cognomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		JLabel centoKilometriLabel = new JLabel("Cento Kilometri");
		centoKilometriLabel.setForeground(Color.WHITE);
		centoKilometriLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		JLabel codaLabel = new JLabel("Coda");
		codaLabel.setForeground(Color.WHITE);
		codaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		
		idPanel.add(idLabel, BorderLayout.WEST);
		idTrattaPanel.add(idTrattaLabel, BorderLayout.WEST);
		compagniaPanel.add(compagniaLabel, BorderLayout.WEST);
		codicePanel.add(codiceLabel, BorderLayout.WEST);
		nomePanel.add(nomeLabel, BorderLayout.WEST);
		cognomePanel.add(cognomeLabel, BorderLayout.WEST);
		centoKilometriPanel.add(centoKilometriLabel, BorderLayout.WEST);
		codaPanel.add(codaLabel, BorderLayout.WEST);
		
		JTextField idTextField = new JTextField();
		idTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		idTextField.setEditable(false);
		idTextField.setForeground(new Color(184,207,229));
		idTextField.setColumns(10);
		idTextField.setText(model.getValueAt(table.getSelectedRow(), 0).toString());
		JTextField idTrattaTextField = new JTextField();
		idTrattaTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		idTrattaTextField.setEnabled(false);
		idTrattaTextField.setColumns(3);
		idTrattaTextField.setText(model.getValueAt(table.getSelectedRow(), 3).toString());
		JTextField compagniaTextField = new JTextField();
		compagniaTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		compagniaTextField.setEnabled(false);
		compagniaTextField.setColumns(10);
		compagniaTextField.setText(model.getValueAt(table.getSelectedRow(), 2).toString());
		JTextField codiceTextField = new JTextField();
		codiceTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		codiceTextField.setEnabled(false);
		codiceTextField.setColumns(8);
		codiceTextField.setText(model.getValueAt(table.getSelectedRow(), 1).toString());
		JTextField nomeTextField = new JTextField();
		nomeTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		nomeTextField.setEnabled(false);
		nomeTextField.setColumns(10);
		nomeTextField.setText(model.getValueAt(table.getSelectedRow(), 4).toString());
		JTextField cognomeTextField = new JTextField();
		cognomeTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		cognomeTextField.setEnabled(false);
		cognomeTextField.setColumns(10);
		cognomeTextField.setText(model.getValueAt(table.getSelectedRow(), 5).toString());
		LinkedList<CentoKilometri> centoKilometriList = controller.getCentoKilometri(model.getValueAt(table.getSelectedRow(), 2).toString());
		String [] centoKilometriArray = new String[centoKilometriList.size() + 1];
		ListIterator<CentoKilometri> centoKilometriCursor = centoKilometriList.listIterator();
		centoKilometriArray[0] = "-";
		int j = 1;
		while (centoKilometriCursor.hasNext()) {
			CentoKilometri current = centoKilometriCursor.next();
			centoKilometriArray[j] = current.getCodiceCompagnia();
			j++;
		}
		JComboBox<String> centoKilometriComboBox = new JComboBox<String>(centoKilometriArray);
		centoKilometriComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		centoKilometriComboBox.setEnabled(false);
		if (model.getValueAt(table.getSelectedRow(), 6).toString().equals("-"))
			centoKilometriComboBox.setSelectedItem(model.getValueAt(table.getSelectedRow(), 6).toString());
		else
			centoKilometriComboBox.setSelectedItem(model.getValueAt(table.getSelectedRow(), 6).toString().substring(0, model.getValueAt(table.getSelectedRow(), 6).toString().indexOf(" ")));
		LinkedList<Coda> codeList = controller.getCodaFromIdTratta((int) model.getValueAt(table.getSelectedRow(), 3));
		String [] codeArray = new String[codeList.size()];
		ListIterator<Coda> cursor = codeList.listIterator();
		int i = 0;
		while (cursor.hasNext()) {
			Coda current = cursor.next();
			codeArray[i] = current.getNomeCoda();
			i++;
		}
		JComboBox<String> codaComboBox = new JComboBox<String>(codeArray);
		codaComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		codaComboBox.setSelectedItem(model.getValueAt(table.getSelectedRow(), 7).toString());
		
		idPanel.add(idTextField, BorderLayout.SOUTH);
		idTrattaPanel.add(idTrattaTextField, BorderLayout.SOUTH);
		compagniaPanel.add(compagniaTextField, BorderLayout.SOUTH);
		codicePanel.add(codiceTextField, BorderLayout.SOUTH);
		nomePanel.add(nomeTextField, BorderLayout.SOUTH);
		cognomePanel.add(cognomeTextField, BorderLayout.SOUTH);
		centoKilometriPanel.add(centoKilometriComboBox, BorderLayout.SOUTH);
		codaPanel.add(codaComboBox, BorderLayout.SOUTH);
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;  
		gc.gridy = 0;
		gc.insets = new Insets(0,0,0,0);
		gc.anchor = GridBagConstraints.WEST;
		mainPanel.add(idPanel, gc);
		gc.gridx = 1;     
		gc.gridy = 0;
		gc.insets = new Insets(0,100,0,0);
		gc.anchor = GridBagConstraints.WEST;
		mainPanel.add(idTrattaPanel, gc);
		gc.gridx = 0;     
		gc.gridy = 1;
		gc.insets = new Insets(20,0,0,0);
		gc.anchor = GridBagConstraints.WEST;
		mainPanel.add(compagniaPanel, gc);
		gc.gridx = 1;     
		gc.gridy = 1;
		gc.insets = new Insets(20,100,0,0);
		gc.anchor = GridBagConstraints.WEST;
		mainPanel.add(codicePanel, gc);
		gc.gridx = 0;     
		gc.gridy = 2;
		gc.insets = new Insets(20,0,0,0);
		gc.anchor = GridBagConstraints.WEST;
		mainPanel.add(nomePanel, gc);
		gc.gridx = 1;     
		gc.gridy = 2;
		gc.insets = new Insets(20,100,0,0);
		gc.anchor = GridBagConstraints.WEST;
		mainPanel.add(cognomePanel, gc);
		gc.gridx = 0;     
		gc.gridy = 3;
		gc.insets = new Insets(20,0,0,0);
		gc.anchor = GridBagConstraints.WEST;
		mainPanel.add(centoKilometriPanel, gc);
		gc.gridx = 1;     
		gc.gridy = 3;
		gc.insets = new Insets(20,100,0,0);
		gc.anchor = GridBagConstraints.WEST;
		mainPanel.add(codaPanel, gc);
		
		JPanel bottomPanel = new JPanel(new FlowLayout());
		bottomPanel.setBackground(new Color (0, 153, 255));
		JButton aggiornaButton = new JButton("Aggiorna");
		aggiornaButton.setFocusPainted(false);
		aggiornaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (model.updateRow(codaComboBox.getSelectedItem().toString(), table.getSelectedRow()))
						prenotazioneDialog.dispose();
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
