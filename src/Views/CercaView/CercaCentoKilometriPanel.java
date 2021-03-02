package Views.CercaView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import Controllers.ViewsController;
import Models.CentoKilometri;
import Views.Tables.TableModelCentoKilometri;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class CercaCentoKilometriPanel extends JPanel {

	private JScrollPane scrollPane;
	private JTable table;
	private TableModelCentoKilometri tableModelCentoKilometri;
	
	private JPanel buttonsPanel;
	private JButton modificaButton;
	private JButton eliminaButton;
	
	private CercaView cercaView;
	private ViewsController viewsController;
	
    public CercaCentoKilometriPanel(LinkedList<CentoKilometri> centoKilometri, ViewsController controller, CercaView cercaView) {
    	this.cercaView = cercaView;
    	this.viewsController = controller;
    	
    	setLayout(new BorderLayout());
    	setBackground(new Color(0, 0, 153));
    	
    	tableModelCentoKilometri = new TableModelCentoKilometri(controller);
		table = new JTable(tableModelCentoKilometri);
		tableModelCentoKilometri.setData(centoKilometri);
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
					TableModelCentoKilometri model = (TableModelCentoKilometri) table.getModel();
					for (int i = 0; i < table.getColumnCount(); i++) {
						model.getValueAt(table.getSelectedRow(), i);
					}
					showModificaCentoKilometriDialog(model);
				}
				else {
					JOptionPane.showMessageDialog(CercaCentoKilometriPanel.this, "Seleziona un riga da modificare", "Errore modifica", JOptionPane.ERROR_MESSAGE);
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
					int choice = JOptionPane.showOptionDialog(CercaCentoKilometriPanel.this, "Vuoi davvero eliminare il cento kilometri selezionato?", "Conferma cancellazione", 
															  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]); 
					if (choice == JOptionPane.YES_OPTION) {
						TableModelCentoKilometri model = (TableModelCentoKilometri) table.getModel();
						model.removeRow(table.getSelectedRow());
					}
				}
				else {
					JOptionPane.showMessageDialog(CercaCentoKilometriPanel.this, "Seleziona un riga da cancellare", "Errore cancellazione", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buttonsPanel.add(modificaButton);
		buttonsPanel.add(eliminaButton);
		return buttonsPanel;
	}
    
    public void showModificaCentoKilometriDialog(TableModelCentoKilometri model) {
		JDialog centoKilometriDialog = new JDialog(cercaView, "Modifica", true);
		centoKilometriDialog.setSize(new Dimension(600,500));
		centoKilometriDialog.setResizable(false);
		centoKilometriDialog.setLayout(new BorderLayout());
		
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuPanel.setBackground(new Color(0, 204, 255));
		JLabel menuLabel = new JLabel("Modifica Cento Kilometri");
		menuLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		menuLabel.setForeground(Color.WHITE);
		menuPanel.add(menuLabel);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(new Color (0, 0, 153));
		
		JLabel compagniaLabel = new JLabel("Nome Compagnia");
		compagniaLabel.setForeground(Color.WHITE);
		compagniaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		compagniaLabel.setMinimumSize(new Dimension(100, 30));
		JComboBox<String> compagniaComboBox = new JComboBox<String>(viewsController.getCompagnieAeree());
		compagniaComboBox.setEditable(false);
		compagniaComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		compagniaComboBox.setSelectedItem(model.getValueAt(table.getSelectedRow(), 1).toString());
		
		JLabel codiceLabel = new JLabel("Codice");
		codiceLabel.setForeground(Color.WHITE);
		codiceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		codiceLabel.setMinimumSize(new Dimension(100, 30));
		JTextField codiceTextField = new JTextField();
		codiceTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		codiceTextField.setColumns(5);
		codiceTextField.setText(model.getValueAt(table.getSelectedRow(), 2).toString());
		
		codiceTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int pos = codiceTextField.getCaretPosition();
				codiceTextField.setText(codiceTextField.getText().toUpperCase());
				codiceTextField.setCaretPosition(pos);
			}
		});
		
		JLabel nomeLabel = new JLabel("Codice");
		nomeLabel.setForeground(Color.WHITE);
		nomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		nomeLabel.setMinimumSize(new Dimension(100, 30));
		JTextField nomeTextField = new JTextField();
		nomeTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		nomeTextField.setColumns(10);
		nomeTextField.setText(model.getValueAt(table.getSelectedRow(), 3).toString());
		
		JLabel cognomeLabel = new JLabel("Codice");
		cognomeLabel.setForeground(Color.WHITE);
		cognomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		cognomeLabel.setMinimumSize(new Dimension(100, 30));
		JTextField cognomeTextField = new JTextField();
		cognomeTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		cognomeTextField.setColumns(10);
		cognomeTextField.setText(model.getValueAt(table.getSelectedRow(), 4).toString());
		
		JLabel puntiLabel = new JLabel("Punti");
		puntiLabel.setForeground(Color.WHITE);
		puntiLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		puntiLabel.setMinimumSize(new Dimension(100, 30));
		JTextField puntiTextField = new JTextField();
		puntiTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		puntiTextField.setColumns(5);
		puntiTextField.setText(model.getValueAt(table.getSelectedRow(), 5).toString());
		
		puntiTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		
		JPanel centerPanel = new JPanel (new GridBagLayout());
		centerPanel.setBackground(new Color (0, 0, 153));
		JPanel compagniaPanel = new JPanel(new BorderLayout());
		compagniaPanel.setBackground(new Color(0, 0, 153));
		compagniaPanel.add(compagniaLabel, BorderLayout.WEST);
		compagniaPanel.add(compagniaComboBox, BorderLayout.SOUTH);
		JPanel codicePanel = new JPanel(new BorderLayout());
		codicePanel.setBackground(new Color(0, 0, 153));
		codicePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		codicePanel.add(codiceLabel, BorderLayout.WEST);
		codicePanel.add(codiceTextField, BorderLayout.SOUTH);
		JPanel nomePanel = new JPanel(new BorderLayout());
		nomePanel.setBackground(new Color(0, 0, 153));
		nomePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		nomePanel.add(nomeLabel, BorderLayout.WEST);
		nomePanel.add(nomeTextField, BorderLayout.SOUTH);
		JPanel cognomePanel = new JPanel(new BorderLayout());
		cognomePanel.setBackground(new Color(0, 0, 153));
		cognomePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		cognomePanel.add(cognomeLabel, BorderLayout.WEST);
		cognomePanel.add(cognomeTextField, BorderLayout.SOUTH);
		JPanel puntiPanel = new JPanel(new BorderLayout());
		puntiPanel.setBackground(new Color(0, 0, 153));
		puntiPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		puntiPanel.add(puntiLabel, BorderLayout.WEST);
		puntiPanel.add(puntiTextField, BorderLayout.SOUTH);
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;  
		gc.gridy = 0;
		//gc.gridwidth = 1;
		//gc.insets = new Insets(10,20,0,0);
		gc.anchor = GridBagConstraints.WEST;
		centerPanel.add(compagniaPanel, gc);
		gc.gridx = 1;  
		gc.gridy = 0;
		gc.insets = new Insets(0,20,0,0);
		centerPanel.add(codicePanel, gc);
		gc.gridx = 0;  
		gc.gridy = 1;
		gc.insets = new Insets(20,0,0,0);
		centerPanel.add(nomePanel, gc);
		gc.gridx = 1;  
		gc.gridy = 1;
		gc.insets = new Insets(20,15,0,0);
		centerPanel.add(cognomePanel, gc);
		gc.gridx = 0;  
		gc.gridy = 2;
		gc.insets = new Insets(20,0,0,0);
		centerPanel.add(puntiPanel, gc);
		
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		
		JPanel bottomPanel = new JPanel(new FlowLayout());
		bottomPanel.setBackground(new Color (0, 153, 255));
		JButton aggiornaButton = new JButton("Aggiorna");
		aggiornaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (model.updateRow(codiceTextField.getText(), compagniaComboBox.getSelectedItem().toString(), nomeTextField.getText(), cognomeTextField.getText(), puntiTextField.getText(), table.getSelectedRow()))
						centoKilometriDialog.dispose();
			}
		});
		
		bottomPanel.add(aggiornaButton);
		
		centoKilometriDialog.add(menuPanel, BorderLayout.NORTH);
		centoKilometriDialog.add(mainPanel, BorderLayout.CENTER);
		centoKilometriDialog.add(bottomPanel, BorderLayout.SOUTH);
		
		centoKilometriDialog.setLocationRelativeTo(null);
		centoKilometriDialog.setVisible(true);
		
	}	
    
}