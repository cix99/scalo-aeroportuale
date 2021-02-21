package Views.ImbarcoView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Models.Coda;
import Models.Prenotazione;
import Models.Tratta;
import Views.TopPanel;
import Controllers.ViewsController;

@SuppressWarnings("serial")
public class ImbarcoView extends JFrame{

	private JPanel mainPanel;
	private TopPanel topPanel;
	private JPanel gateSelectionPanel;
	private JLabel gateLabel;
	private JComboBox<String> gateComboBox;
	private JButton okButton;
	
	private JPanel centerPanel;
	private TrattaInfoPanel trattaInfoPanel;
	private CodeOptionsPanel codeOptionsPanel;
	private PrenotatiPanel prenotatiPanel;
	
	private JPanel chiudiImbarcoCodaPanel;
	private JButton chiudiImbarcoCodaButton;
	private JPanel chiudiImbarcoPanel;
	private JButton chiudiImbarcoButton;
	
	private ViewsController viewsController;

	public ImbarcoView (ViewsController controller) {	
		this.viewsController = controller;
		
		setTitle("Scalo Aeroportuale - Inizia Imbarco");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image logoImage = new ImageIcon (this.getClass().getResource("/aereo_logo.png")).getImage();
		setIconImage(logoImage);
		setBounds(175, 60, 1150, 650);
		setMinimumSize(new Dimension (1150,700));	
		
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
		mainPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(mainPanel);
		
		topPanel = new TopPanel(viewsController, false);
		
		gateSelectionPanel = new JPanel();
		gateSelectionPanel.setBackground(new Color(0, 204, 255));
		gateSelectionPanel.setLayout(new FlowLayout());
		gateSelectionPanel.setMinimumSize(new Dimension(300,100));
		gateLabel = new JLabel("Gate ");
		gateLabel.setForeground(Color.WHITE);
		gateLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		gateComboBox = new JComboBox<String>(viewsController.getGates());
		gateComboBox.setSize(new Dimension (20,10));	
		okButton = new JButton("Ok");
		okButton.setFocusPainted(false);
		okButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				viewsController.loadTrattaInfo(gateComboBox.getSelectedItem().toString());
			}
		});
		gateSelectionPanel.add(gateLabel);
		gateSelectionPanel.add(gateComboBox);
		gateSelectionPanel.add(okButton);
		topPanel.add(gateSelectionPanel, BorderLayout.SOUTH);
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
	
	public void emptyCenterPanel () {
		if (centerPanel != null) {
			centerPanel.removeAll();
			centerPanel.repaint();
		}
	}
	
	public void showTrattaInfo(Tratta tratta) {
		emptyCenterPanel();
		trattaInfoPanel = new TrattaInfoPanel(tratta, viewsController);
		centerPanel.add(trattaInfoPanel, BorderLayout.NORTH);
	}
	
	public void showCodeOptionsPanel (LinkedList<Coda> codeList) {
		if (codeOptionsPanel != null) {
			centerPanel.remove(codeOptionsPanel);
			topPanel.UpdateBackButton();
		}
		codeOptionsPanel = new CodeOptionsPanel(codeList, viewsController, topPanel);
		centerPanel.add(codeOptionsPanel, BorderLayout.WEST);
	}
	
	public void showPrenotatiPanel(LinkedList<Prenotazione> prenotati) {
		if (prenotatiPanel != null) {
			centerPanel.remove(prenotatiPanel);
			topPanel.UpdateBackButton();
		}
		prenotatiPanel = new PrenotatiPanel(prenotati, viewsController);	
		centerPanel.add(prenotatiPanel, BorderLayout.CENTER);
		
		if (chiudiImbarcoButton == null && chiudiImbarcoCodaButton == null) {
			addChiudiButtons();
		} else {
			if (codeOptionsPanel.getSelectedCoda().equals("Tutte")) {
				chiudiImbarcoButton.setEnabled(true);
				chiudiImbarcoCodaButton.setEnabled(false);
			}
			else {
				chiudiImbarcoButton.setEnabled(false);
				chiudiImbarcoCodaButton.setEnabled(true);
			}
		}

	}
	
	public void addChiudiButtons() {
		chiudiImbarcoCodaPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		chiudiImbarcoCodaPanel.setBackground(new Color(0, 0, 153));
		chiudiImbarcoCodaPanel.setBorder(new EmptyBorder(5,5,0,5));
		chiudiImbarcoCodaButton = new JButton("Chiudi Coda");
		chiudiImbarcoCodaButton.setFocusPainted(false);
		
		chiudiImbarcoCodaButton.setEnabled(false);
		chiudiImbarcoCodaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (chiudiImbarcoCodaButton.isEnabled()) {
					viewsController.updateFineImbarcoCoda(codeOptionsPanel.getSelectedCoda());
					chiudiImbarcoCodaButton.setEnabled(false);
				}		
			}
		});				

		chiudiImbarcoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		chiudiImbarcoPanel.setBackground(new Color(0, 0, 153));
		chiudiImbarcoPanel.setBorder(new EmptyBorder(0,5,5,5));
		chiudiImbarcoButton = new JButton("Chiudi Imbarco");
		chiudiImbarcoButton.setFocusPainted(false);
	
		chiudiImbarcoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (chiudiImbarcoButton.isEnabled()) {
					viewsController.updateFineImbarco();
					//controlla se è andato a buon fine
						//se si, mostra la nuova tratta per il gate
				}	
			}
		});
		
		chiudiImbarcoCodaPanel.add(chiudiImbarcoCodaButton);
		chiudiImbarcoPanel.add(chiudiImbarcoButton);
		
		centerPanel.add(chiudiImbarcoCodaPanel, BorderLayout.EAST);
		centerPanel.add(chiudiImbarcoPanel, BorderLayout.SOUTH);
	}
	
	public void emptyPrenotatiPanelTable() {
		prenotatiPanel.emptyTable();
		prenotatiPanel.repaint();
	}
}
