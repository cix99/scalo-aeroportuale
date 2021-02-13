package Views.AggiungiView;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controllers.ViewsController;

@SuppressWarnings("serial")
public class AggiungiSidePanel extends JPanel {

	private JPanel prenotazionePanel;
	private JLabel prenotazioneLabel;
	private JPanel trattaPanel;
	private JLabel trattaLabel;
	private JPanel centroKilometriPanel;
	private JLabel centoKilometriLabel;
	private JPanel compagniaAereaPanel;
	private JLabel compagniaAereaLabel;
	private JPanel gatePanel;
	private JLabel gateLabel;
	
	public AggiungiSidePanel (ViewsController controller, JPanel centerPanel, CardLayout cardLayout) {
		
		setPreferredSize(new Dimension (200,650));
		setBackground(new Color(0, 153, 255));
		setBorder(new EmptyBorder(10, 0, 10, 0));
		setLayout(new GridLayout(5, 1, 0, 10));
		
		prenotazionePanel = new JPanel();
		prenotazionePanel.setBackground(new Color(0, 204, 255));
		prenotazionePanel.setBounds(0, 105, 200, 85);
		prenotazionePanel.setLayout(new GridBagLayout());
		add(prenotazionePanel);
		
		prenotazioneLabel = new JLabel("Prenotazione");
		prenotazioneLabel.setForeground(Color.WHITE);
		prenotazioneLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.CENTER;
		prenotazionePanel.add(prenotazioneLabel, gc);
		
		prenotazionePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(centerPanel, "prenotazionePanel");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				prenotazioneLabel.setForeground(new Color (220, 220, 220));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				prenotazioneLabel.setForeground(Color.WHITE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				prenotazioneLabel.setForeground(new Color (150, 150, 150));
				prenotazionePanel.setBackground(new Color(0, 180, 255));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				prenotazioneLabel.setForeground(Color.WHITE);
				prenotazionePanel.setBackground(new Color(0, 204, 255));
			}
		});
		
		trattaPanel = new JPanel();
		trattaPanel.setBackground(new Color(0, 204, 255));
		trattaPanel.setBounds(0, 10, 200, 85);
		trattaPanel.setLayout(new GridBagLayout());
		add(trattaPanel);
		trattaLabel = new JLabel("Tratta");
		trattaLabel.setForeground(Color.WHITE);
		trattaLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));

		trattaPanel.add(trattaLabel, gc);
		
		trattaPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(centerPanel, "trattaPanel");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				trattaLabel.setForeground(new Color (220, 220, 220));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				trattaLabel.setForeground(Color.WHITE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				trattaLabel.setForeground(new Color (150, 150, 150));
				trattaPanel.setBackground(new Color(0, 180, 255));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				trattaLabel.setForeground(Color.WHITE);
				trattaPanel.setBackground(new Color(0, 204, 255));
			}
		});
		
		/* Aggiungi option */
		centroKilometriPanel = new JPanel();
		centroKilometriPanel.setBackground(new Color(0, 204, 255));
		centroKilometriPanel.setBounds(0, 105, 200, 85);
		centroKilometriPanel.setLayout(new GridBagLayout());
		add(centroKilometriPanel);
		
		centoKilometriLabel = new JLabel("CentoKilometri");
		centoKilometriLabel.setForeground(Color.WHITE);
		centoKilometriLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		centroKilometriPanel.add(centoKilometriLabel, gc);
		
		centroKilometriPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(centerPanel, "ckPanel");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				centoKilometriLabel.setForeground(new Color (220, 220, 220));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				centoKilometriLabel.setForeground(Color.WHITE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				centoKilometriLabel.setForeground(new Color (150, 150, 150));
				centroKilometriPanel.setBackground(new Color(0, 180, 255));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				centoKilometriLabel.setForeground(Color.WHITE);
				centroKilometriPanel.setBackground(new Color(0, 204, 255));
			}
		});
		
		/* Cerca option */
		compagniaAereaPanel = new JPanel();
		compagniaAereaPanel.setBackground(new Color(0, 204, 255));
		compagniaAereaPanel.setBounds(0, 200, 200, 85);
		compagniaAereaPanel.setLayout(new GridBagLayout());
		add(compagniaAereaPanel);
		
		compagniaAereaLabel = new JLabel("Compagnia Aerea");
		compagniaAereaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		compagniaAereaLabel.setForeground(Color.WHITE);
		compagniaAereaLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		compagniaAereaPanel.add(compagniaAereaLabel, gc);
		
		compagniaAereaPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(centerPanel, "compagniaPanel");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				compagniaAereaLabel.setForeground(new Color (220, 220, 220));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				compagniaAereaLabel.setForeground(Color.WHITE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				compagniaAereaLabel.setForeground(new Color (150, 150, 150));
				compagniaAereaPanel.setBackground(new Color(0, 180, 255));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				compagniaAereaLabel.setForeground(Color.WHITE);
				compagniaAereaPanel.setBackground(new Color(0, 204, 255));
			}
		});
		
		/* Elimina option */
		gatePanel = new JPanel();
		
		gatePanel.setBackground(new Color(0, 204, 255));
		gatePanel.setBounds(0, 295, 200, 85);
		gatePanel.setLayout(new GridBagLayout());
		add(gatePanel);
		
		gateLabel = new JLabel("Gate");
		gateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gateLabel.setForeground(Color.WHITE);
		gateLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		gatePanel.add(gateLabel, gc);
		
		gatePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(centerPanel, "gatePanel");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				gateLabel.setForeground(new Color (220, 220, 220));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				gateLabel.setForeground(Color.WHITE);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				gateLabel.setForeground(new Color (150, 150, 150));
				gatePanel.setBackground(new Color(0, 180, 255));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				gateLabel.setForeground(Color.WHITE);
				gatePanel.setBackground(new Color(0, 204, 255));
			}
		});

	}
}
