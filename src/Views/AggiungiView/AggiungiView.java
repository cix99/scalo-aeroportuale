package Views.AggiungiView;

import Controllers.ViewsController;
import Views.HomeTopPanel;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class AggiungiView extends JFrame{
	
	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel sidePanel;
	private JPanel centerPanel;

	JPanel backButtonPanel;
	JButton backButton;

	public AggiungiView (ViewsController controller) {
		Image logoImage = new ImageIcon (this.getClass().getResource("/aereo_logo.png")).getImage();
		setIconImage(logoImage);
		setTitle("Aggiungi");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(50, 50, 1150, 650);
		setMinimumSize(new Dimension (1150,700));
		
		//Main Panel settings
		mainPanel = new JPanel(new BorderLayout(0,0));
		mainPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
		setContentPane(mainPanel);
		
		topPanel = new JPanel(new BorderLayout());
		backButtonPanel = new JPanel();
		backButtonPanel.setLayout(new FlowLayout());
		backButtonPanel.setBackground(new Color (0,0,153));
		backButtonPanel.setMinimumSize(new Dimension (50,10));
		backButton = new JButton("back");
		backButtonPanel.add(backButton);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.backToHomeView();
			}
		});
		topPanel.add(backButtonPanel, BorderLayout.EAST);
		topPanel.add(new HomeTopPanel(), BorderLayout.WEST);
		
		//sidePanel		
		sidePanel = new JPanel(new GridLayout(5, 1, 0, 10));
		JButton nuovaTrattaButton = new JButton("Nuova Tratta");
		JButton nuovaCompagniaButton = new JButton("Nuova Compagnia");
		JButton nuovaPrenotazioneButton = new JButton("Nuova Prenotazione");
		JButton nuovoCKButton = new JButton("Nuovo CK");
		JButton nuovoGateButton = new JButton("Nuovo Gate");
		sidePanel.add(nuovaTrattaButton);
		sidePanel.add(nuovaCompagniaButton);
		sidePanel.add(nuovaPrenotazioneButton);
		sidePanel.add(nuovoCKButton);
		sidePanel.add(nuovoGateButton);
		
		nuovaTrattaButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		nuovaCompagniaButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		nuovaPrenotazioneButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		nuovoCKButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		nuovoGateButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		CardLayout cardLayout = new CardLayout();
		centerPanel = new JPanel();
		centerPanel.setLayout(cardLayout);
		
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(sidePanel, BorderLayout.WEST);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		
		// Start Center Panel
		JPanel trattaPanel = new NuovaTrattaView();
		JPanel compagniaPanel = new NuovaCompagniaView();
		JPanel prenotazionePanel = new NuovaPrenotazioneView();
		JPanel ckPanel = new NuovoCKView();
		JPanel gatePanel = new NuovoGateView();
		
		centerPanel.add(trattaPanel, "trattaPanel");
		centerPanel.add(compagniaPanel, "compagniaPanel");
		centerPanel.add(prenotazionePanel, "prenotazionePanel");
		centerPanel.add(ckPanel, "ckPanel");
		centerPanel.add(gatePanel, "gatePanel");

		nuovaTrattaButton.addActionListener(e -> cardLayout.show(centerPanel, "trattaPanel"));
		nuovaCompagniaButton.addActionListener(e -> cardLayout.show(centerPanel, "compagniaPanel"));
		nuovaPrenotazioneButton.addActionListener(e -> cardLayout.show(centerPanel, "prenotazionePanel"));
		nuovoCKButton.addActionListener(e -> cardLayout.show(centerPanel, "ckPanel"));
		nuovoGateButton.addActionListener(e -> cardLayout.show(centerPanel, "gatePanel"));
		// End Center Panel
	}
	

}