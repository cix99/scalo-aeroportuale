package Views.CercaView;

import Controllers.ViewsController;
import Views.TopPanel;

import java.awt.*;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class CercaView extends JFrame {

	private TopPanel topPanel;
	private JPanel btnPanel;
	private JPanel contentPanel;

	public CercaView (ViewsController controller) {
		setTitle("Inizia Imbarco");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image logoImage = new ImageIcon (this.getClass().getResource("/aereo_logo.png")).getImage();
		setIconImage(logoImage);
		setBounds(175, 60, 1150, 650);
		setMinimumSize(new Dimension (1150,700));

		setBackground(new Color(0, 0, 0));
		setLayout(new BorderLayout());
		
		topPanel = new TopPanel(controller, false);
		btnPanel = new JPanel();
		contentPanel = new JPanel();
		
		contentPanel.setBorder(new EmptyBorder(0, 5, 5, 5));

		JPanel trattePanel = new CercaTratteView();
		JPanel prenotazioniPanel = new CercaPrenotazioniView();
		JPanel compagniePanel = new CercaCompagnieView();
		JPanel centoKilometriPanel = new CercaCentoKilometriView();
		JPanel gatesPanel = new CercaGatesView();
		JPanel codePanel = new CercaCodeView();

		JButton tratteButton = new JButton("Tratte");
		JButton prenotazioniButton = new JButton("Prenotazioni");
		JButton centoKilometriButton = new JButton("CentoKilometri");
		JButton gatesButton = new JButton("Gates");
		JButton codeButton = new JButton("Code");
		JButton compagnieButton = new JButton("Compagnie Aeree");

		CardLayout cardLayout = new CardLayout();
		contentPanel.setLayout(cardLayout);

		contentPanel.add(trattePanel, "trattePanel");
		contentPanel.add(prenotazioniPanel, "prenotazioniPanel");
		contentPanel.add(centoKilometriPanel, "centoKilometriPanel");
		contentPanel.add(gatesPanel, "gatesPanel");
		contentPanel.add(codePanel, "codePanel");
		contentPanel.add(compagniePanel, "compagniePanel");

		tratteButton.addActionListener(e -> cardLayout.show(contentPanel, "trattePanel"));
		prenotazioniButton.addActionListener(e -> cardLayout.show(contentPanel, "prenotazioniPanel"));
		centoKilometriButton.addActionListener(e -> cardLayout.show(contentPanel, "centoKilometriPanel"));
		gatesButton.addActionListener(e -> cardLayout.show(contentPanel, "gatesPanel"));
		codeButton.addActionListener(e -> cardLayout.show(contentPanel, "codePanel"));
		compagnieButton.addActionListener(e -> cardLayout.show(contentPanel, "compagniePanel"));

		btnPanel.add(tratteButton);
		btnPanel.add(prenotazioniButton);
		btnPanel.add(centoKilometriButton);
		btnPanel.add(gatesButton);
		btnPanel.add(codeButton);
		btnPanel.add(compagnieButton);

	
		add(topPanel, BorderLayout.NORTH);
		add(btnPanel, BorderLayout.CENTER);
		add(contentPanel, BorderLayout.SOUTH);

	}



}
