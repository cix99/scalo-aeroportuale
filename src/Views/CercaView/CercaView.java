package Views.CercaView;

import Controllers.ViewsController;
import Models.CompagniaAerea;
import Models.Gate;
import Models.Prenotazione;
import Models.Tratta;
import Views.TopPanel;
import Views.Tables.TableModelCompagnia;
import Views.Tables.TableModelGate;
import Views.Tables.TableModelPrenotazione;
import Views.Tables.TableModelTratta;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class CercaView extends JFrame {

	private JPanel mainPanel;
	private TopPanel topPanel;
	private JPanel selectionPanel;
	private JLabel cercaLabel;
	private String[] items = {"Tratte", "Prenotazioni", "Cento Kilometri", "Compagnie", "Code", "Gate"};
	private JComboBox<String> cercaComboBox;
	private JButton okButton;
	private JPanel centerPanel;
	
	private JScrollPane scrollPane;
	private JTable table;
	private TableModelTratta tableModel;
	private TableModelPrenotazione tableModelPrenotazione;
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
		setTratteData(tratte);
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
		table.getColumnModel().getColumn(6).setCellRenderer(tableRenderer);
		table.getColumnModel().getColumn(7).setCellRenderer(tableRenderer);
		table.getColumnModel().getColumn(8).setCellRenderer(tableRenderer);
		scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		//table.setFillsViewportHeight(true);
		
		centerPanel.add(scrollPane, BorderLayout.CENTER);
		
//		JPanel chiudiImbarcoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//		chiudiImbarcoPanel.setBackground(new Color(0, 0, 153));
//		chiudiImbarcoPanel.setBorder(new EmptyBorder(0,5,5,5));
//		JButton chiudiImbarcoButton = new JButton("Chiudi Imbarco");
//		chiudiImbarcoButton.setFocusPainted(false);
//		chiudiImbarcoPanel.add(chiudiImbarcoButton);
//		centerPanel.add(chiudiImbarcoPanel, BorderLayout.SOUTH);
		
		mainPanel.revalidate();
	
	}
	
	public void setTratteData (LinkedList<Tratta> tratte) {
		tableModel.setData(tratte);
		
	}
	
	public void showListaPrenotazioni(LinkedList<Prenotazione> prenotazioni, ViewsController controller) { 

		tableModelPrenotazione = new TableModelPrenotazione(controller);
		table = new JTable(tableModelPrenotazione);
		setPrenotazioniData(prenotazioni);
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
		table.getColumnModel().getColumn(6).setCellRenderer(tableRenderer);
		table.getColumnModel().getColumn(7).setCellRenderer(tableRenderer);
		table.getColumnModel().getColumn(8).setCellRenderer(tableRenderer);
		scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		//table.setFillsViewportHeight(true);
		
		centerPanel.add(scrollPane, BorderLayout.CENTER);
		
		mainPanel.revalidate();
	
	}
	
	public void setPrenotazioniData (LinkedList<Prenotazione> prenotazioni) {
		tableModelPrenotazione.setData(prenotazioni);
		
	}
	
	public void showListaCompagnie(LinkedList<CompagniaAerea> compagnie, ViewsController controller) { 

		tableModelCompagnia = new TableModelCompagnia(controller);
		table = new JTable(tableModelCompagnia);
		setCompagnieData(compagnie);
		scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color (0, 0, 153));
		DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
		tableRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tableRenderer);
		scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		//table.setFillsViewportHeight(true);
		
		centerPanel.add(scrollPane, BorderLayout.CENTER);
		
		mainPanel.revalidate();
	
	}
	
	public void setCompagnieData (LinkedList<CompagniaAerea> compagnie) {
		tableModelCompagnia.setData(compagnie);
		
	}
	
	public void showListaGates(LinkedList<Gate> gates, ViewsController controller) { 

		tableModelGate = new TableModelGate(controller);
		table = new JTable(tableModelGate);
		setGateData(gates);
		scrollPane = new JScrollPane(table);
		scrollPane.setBackground(new Color (0, 0, 153));
		DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
		tableRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(tableRenderer);
		scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		//table.setFillsViewportHeight(true);
		
		centerPanel.add(scrollPane, BorderLayout.CENTER);
		
		mainPanel.revalidate();
	
	}
	
	public void setGateData (LinkedList<Gate> gates) {
		tableModelGate.setData(gates);
		
	}
		
	public void emptyCenterPanel () {
		if (centerPanel != null) {
			centerPanel.removeAll();
			centerPanel.repaint();
		}
	}
}
