package Views.CercaView;

import Controllers.ViewsController;
import Models.CentoKilometri;
import Models.CompagniaAerea;
import Models.Gate;
import Models.Prenotazione;
import Models.Tratta;
import Views.TopPanel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
	
	private CercaTrattePanel cercaTrattePanel;
	private CercaPrenotazioniPanel cercaPrenotazioniPanel;
	private CercaCentoKilometriPanel cercaCentoKilometriPanel;
	private CercaCompagniePanel cercaCompagniePanel;
	private CercaGatesPanel cercaGatesPanel;
	
	private JPanel buttonsPanel;
	
	private ViewsController viewsController;
	
	public CercaView (ViewsController controller) {
		this.viewsController = controller;
		
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
	
	public void showListaTratte(LinkedList<Tratta> tratte) { 
		cercaTrattePanel = new CercaTrattePanel(tratte, viewsController, CercaView.this);
		centerPanel.add(cercaTrattePanel, BorderLayout.CENTER);
		buttonsPanel = cercaTrattePanel.getButtonsPanel();
		centerPanel.add(buttonsPanel, BorderLayout.SOUTH);
		//mainPanel.revalidate();
	}
	
	public void showListaPrenotazioni(LinkedList<Prenotazione> prenotazioni) { 
		cercaPrenotazioniPanel = new CercaPrenotazioniPanel(prenotazioni, viewsController);
		centerPanel.add(cercaPrenotazioniPanel, BorderLayout.CENTER);
		buttonsPanel = cercaPrenotazioniPanel.getButtonsPanel();
		centerPanel.add(buttonsPanel, BorderLayout.SOUTH);
		//mainPanel.revalidate();
	}
	
	public void showListaCentoKilometri(LinkedList<CentoKilometri> centoKilometri, ArrayList<String> clientiCKList) {
		cercaCentoKilometriPanel = new CercaCentoKilometriPanel(centoKilometri, clientiCKList, viewsController);
		centerPanel.add(cercaCentoKilometriPanel, BorderLayout.CENTER);
		buttonsPanel = cercaCentoKilometriPanel.getButtonsPanel();
		centerPanel.add(buttonsPanel, BorderLayout.SOUTH);
		//mainPanel.revalidate();
	}
	
	public void showListaCompagnie(LinkedList<CompagniaAerea> compagnie) { 
		cercaCompagniePanel = new CercaCompagniePanel(compagnie, viewsController);
		centerPanel.add(cercaCompagniePanel, BorderLayout.CENTER);
		buttonsPanel = cercaCompagniePanel.getButtonsPanel();
		centerPanel.add(buttonsPanel, BorderLayout.SOUTH);
		mainPanel.revalidate();
	}
	
	public void showListaGates(LinkedList<Gate> gates) { 
		cercaGatesPanel = new CercaGatesPanel(gates, viewsController, CercaView.this);
		centerPanel.add(cercaGatesPanel, BorderLayout.CENTER);
		buttonsPanel = cercaGatesPanel.getButtonsPanel();
		centerPanel.add(buttonsPanel, BorderLayout.SOUTH);
		mainPanel.revalidate();
	}
		
	public void emptyCenterPanel () {
		if (centerPanel != null) {
			centerPanel.removeAll();
			centerPanel.repaint();
		}
	}
}
