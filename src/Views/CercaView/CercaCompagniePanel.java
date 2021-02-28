package Views.CercaView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import Controllers.ViewsController;
import Models.CompagniaAerea;
import Views.Tables.TableModelCompagnia;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class CercaCompagniePanel extends JPanel {

	private JScrollPane scrollPane;
	private JTable table;
	private TableModelCompagnia tableModelCompagnia;
	
	private JPanel buttonsPanel;
	private JButton modificaButton;
	private JButton eliminaButton;
	
	private CercaView cercaView;
//	private ViewsController viewsController;
	
    public CercaCompagniePanel(LinkedList<CompagniaAerea> compagnie, ViewsController controller, CercaView cercaView) {
    	this.cercaView = cercaView;
//    	this.viewsController = controller;
    	setLayout(new BorderLayout());
    	setBackground(new Color(0, 0, 153));
    	
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
					TableModelCompagnia model = (TableModelCompagnia) table.getModel();
					for (int i = 0; i < table.getColumnCount(); i++) {
						model.getValueAt(table.getSelectedRow(), i);
					}
					showModificaCompagniaDialog(model);
				}
				else {
					JOptionPane.showMessageDialog(CercaCompagniePanel.this, "Seleziona un riga da modificare", "Errore modifica", JOptionPane.ERROR_MESSAGE);
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
					int choice = JOptionPane.showOptionDialog(CercaCompagniePanel.this, "Vuoi davvero eliminare la compagnia selezionata?", "Conferma cancellazione", 
															  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]); 
					if (choice == JOptionPane.YES_OPTION) {
						TableModelCompagnia model = (TableModelCompagnia) table.getModel();
						model.removeRow(table.getSelectedRow());
					}
				}
				else {
					JOptionPane.showMessageDialog(CercaCompagniePanel.this, "Seleziona un riga da cancellare", "Errore cancellazione", JOptionPane.ERROR_MESSAGE);
				}
			}
		});	
		
		buttonsPanel.add(modificaButton);
		buttonsPanel.add(eliminaButton);
		return buttonsPanel;
	}
    
    public void showModificaCompagniaDialog(TableModelCompagnia model) {
		JDialog compagniaDialog = new JDialog(cercaView, "Modifica", true);
		compagniaDialog.setSize(new Dimension(450,300));
		compagniaDialog.setResizable(false);
		compagniaDialog.setLayout(new BorderLayout());
		
		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuPanel.setBackground(new Color(0, 204, 255));
		JLabel menuLabel = new JLabel("Modifica Compagnia");
		menuLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		menuLabel.setForeground(Color.WHITE);
		menuPanel.add(menuLabel);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(new Color (0, 0, 153));
		JPanel textPanel = new JPanel();
		textPanel.setBackground(new Color (0, 0, 153));
		JTextField compagniaTextField = new JTextField();
		compagniaTextField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		compagniaTextField.setColumns(15);
		compagniaTextField.setText(model.getValueAt(table.getSelectedRow(), 0).toString());
		textPanel.add(compagniaTextField);
		mainPanel.add(textPanel, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel(new FlowLayout());
		bottomPanel.setBackground(new Color (0, 153, 255));
		JButton aggiornaButton = new JButton("Aggiorna");
		aggiornaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (model.updateRow(compagniaTextField.getText(), table.getSelectedRow()))
						compagniaDialog.dispose();
			}
		});
		
		bottomPanel.add(aggiornaButton);
		
		compagniaDialog.add(menuPanel, BorderLayout.NORTH);
		compagniaDialog.add(mainPanel, BorderLayout.CENTER);
		compagniaDialog.add(bottomPanel, BorderLayout.SOUTH);
		
		compagniaDialog.setLocationRelativeTo(null);
		compagniaDialog.setVisible(true);
		
	}	
    
}