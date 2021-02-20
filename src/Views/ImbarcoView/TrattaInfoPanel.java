package Views.ImbarcoView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controllers.ViewsController;
import Models.Tratta;

@SuppressWarnings("serial")
public class TrattaInfoPanel extends JPanel{

	private JLabel destinazioneLabel;
	private JLabel compagniaLabel;
	private JLabel dataLabel;
	private JLabel oraLabel;
	
	
	public TrattaInfoPanel (Tratta tratta, ViewsController viewsController) { 	
		setLayout(new BorderLayout());
		setBackground(new Color(0, 153, 255));
		
		destinazioneLabel = new JLabel("Volo per: " + tratta.getDestinazione()); 
		destinazioneLabel.setForeground(Color.WHITE);
		destinazioneLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		compagniaLabel = new JLabel("Compagnia: " + tratta.getCompagniaAerea().getNomeCompagnia());
		compagniaLabel.setForeground(Color.WHITE);
		compagniaLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		String date = tratta.getOraInizioImbarcoStimato().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String hour = tratta.getOraInizioImbarcoStimato().format(DateTimeFormatter.ofPattern("HH:mm"));
		dataLabel = new JLabel("Data: " + date);
		dataLabel.setForeground(Color.WHITE);
		dataLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		oraLabel = new JLabel("Ora: " + hour);
		oraLabel.setForeground(Color.WHITE);
		oraLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		
		JPanel trattaPanel = new JPanel (new FlowLayout());
		trattaPanel.setBackground(new Color(0, 153, 255));
		
		trattaPanel.add(destinazioneLabel);
		trattaPanel.add(Box.createHorizontalStrut(50));
		trattaPanel.add(compagniaLabel);
		trattaPanel.add(Box.createHorizontalStrut(50));
		trattaPanel.add(dataLabel);
		trattaPanel.add(Box.createHorizontalStrut(50));
		trattaPanel.add(oraLabel);
		trattaPanel.add(Box.createHorizontalStrut(50));
		
		JButton iniziaImbarcoButton = new JButton("Inizia Imbarco");
		iniziaImbarcoButton.setFocusPainted(false);
		iniziaImbarcoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				viewsController.updateInizioImbarco();
				viewsController.showCode(tratta);
				viewsController.loadPrenotatiImbarcoPerCoda("Tutte");
				iniziaImbarcoButton.setVisible(false);
			}
		});
		trattaPanel.add(iniziaImbarcoButton);
		add(trattaPanel, BorderLayout.CENTER);
	}
}
