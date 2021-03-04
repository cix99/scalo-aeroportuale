package Views.CercaView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import Controllers.ViewsController;
import Models.Gate;
import Views.Tables.TableModelGate;

@SuppressWarnings("serial")
public class CercaGatesPanel extends JPanel {

	private JScrollPane scrollPane;
	private JTable table;
	private TableModelGate tableModelGate;
	private JPanel buttonsPanel;
	private JButton modificaButton;
	private JButton eliminaButton;
	
	private CercaView cercaView;
	private ViewsController controller;
	
    public CercaGatesPanel(LinkedList<Gate> gates, ViewsController viewsController, CercaView cercaView) {
    	this.cercaView = cercaView;
    	controller = viewsController;
    	
    	setLayout(new BorderLayout());
    	setBackground(new Color(0, 0, 153));
    	
    	tableModelGate = new TableModelGate(controller);
		table = new JTable(tableModelGate);
		tableModelGate.setData(gates);
		scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color (0, 0, 153));
		DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
		tableRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tableRenderer);
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
					TableModelGate model = (TableModelGate) table.getModel();
					for (int i = 0; i < table.getColumnCount(); i++) {
						model.getValueAt(table.getSelectedRow(), i);
					}
					showModificaGateDialog(model);
				}
				else {
					JOptionPane.showMessageDialog(CercaGatesPanel.this, "Seleziona un riga da modificare", "Errore modifica", JOptionPane.ERROR_MESSAGE);
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
					int choice = JOptionPane.showOptionDialog(CercaGatesPanel.this, "Vuoi davvero eliminare il gate selezionato?", "Conferma cancellazione", 
															  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]); 
					if (choice == JOptionPane.YES_OPTION) {
						TableModelGate model = (TableModelGate) table.getModel();
						model.removeRow(table.getSelectedRow());
					}
				}
				else {
					JOptionPane.showMessageDialog(CercaGatesPanel.this, "Seleziona un riga da cancellare", "Errore cancellazione", JOptionPane.ERROR_MESSAGE);
				}
			}
		});	
		
		buttonsPanel.add(modificaButton);
		buttonsPanel.add(eliminaButton);
		return buttonsPanel;
	}

    public void showModificaGateDialog(TableModelGate model) {
		JDialog gateDialog = new JDialog(cercaView, "Modifica", true);
		gateDialog.setSize(new Dimension(300,250));
		gateDialog.setResizable(false);
		gateDialog.setLayout(new BorderLayout());
		
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
				if (model.updateRow(gateTextField.getText(), table.getSelectedRow()))
						gateDialog.dispose();
			}
		});
		
		bottomPanel.add(aggiornaButton);
		
		gateDialog.add(menuPanel, BorderLayout.NORTH);
		gateDialog.add(mainPanel, BorderLayout.CENTER);
		gateDialog.add(bottomPanel, BorderLayout.SOUTH);
		
		gateDialog.setLocationRelativeTo(null);
		gateDialog.setVisible(true);	
	}	
    
}
