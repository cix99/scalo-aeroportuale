package Views.CercaView;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.event.ItemListener;

@SuppressWarnings("serial")
public class CercaView extends JPanel {

	public CercaView () {
		setPreferredSize (new Dimension(800,650));
		setBackground(new Color(0, 0, 0));
		setLayout(new BorderLayout());

		JPanel btnPanel = new JPanel();
		JPanel contentPanel = new JPanel();

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

		add(btnPanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
	}



}
